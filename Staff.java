import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public abstract class Staff extends Person{
    
    public Staff(String n,String a,String p,String pass){
        super(n,a,p,pass);
    }
    
    public Staff(String n,String a,String p,String pass,int id,LMS l){
        super(n,a,p,pass,id,l);
    }
    
    
    //borrower use cases
    
 
    public Book getByID(int id){
    
        return lib.getByID(id);
    }
    
    public void searchByID(int id){
    
        lib.searchByID(id);
    }
    
    public void searchByName(String name){
    
        lib.searchByName(name);
    }
    public void searchByAuthor(String author){
        
        lib.searchByAuthor(author);
    }
    public void searchBySubject(String subject){
        
        lib.searchBySubject(subject);
    }
    
    public void BorrowForBorrower(Borrower br,Book bk,myDate dt){
        
        if(bk.isHeld()){
            System.out.println("\nThis book is currently held by another borrower. Added to your waiting list.");
            br.waitingList.add(bk);return;
        }
        checkOut(br,bk,dt);
        
    }
    
    public void printBorrowerInfo(Borrower br){br.printInfo();}
    public void printBorrowerInfoAndCurrentBooks(Borrower br){br.printInfoAndCurrentBooks();}
    public void printBorrowerWaitingList(Borrower br){br.printWaitingList();}
    
    public void payForBorrower(Borrower br,int amount,myDate dt){br.pay(amount, dt);}
    public int getBorrowerFine(Borrower br){return br.getFine();}
    public void printBorrowerFineHistory(Borrower br){br.printFineHistory();}
    
    
    ///Clerk use cases
    
    
    public void checkIn(Borrower br, Book bk, myDate dt){
        
        if(!bk.isHeld()){System.out.println("\nThis book is currently not issued to anyone.");return;}
        
        for(int i=0;i<br.loans.size();i++) {
            if(br.loans.get(i).bk==bk){
                
                if(dt.isGreater(br.loans.get(i).due)){
                    //fine

                    int fine=50*(br.loans.get(i).due.difference(dt));
                    br.fineLoans.add(br.loans.get(i));
                    br.fineDates.add(dt);
                    br.fines.add(fine);
                    
                    
                    System.out.println("\nThe book '"+bk.name+"' has been returned by the borrower "+br.name+" (ID : "+br.pid+") "+dt.difference(br.loans.get(i).due)+" DAYS LATE. Due fine is "+fine+".");  
                }
                
                try {
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("delete from ooad.loan where PersonID = "+br.loans.get(i).br.pid+" and BookID = "+br.loans.get(i).bk.bkid);
                }
                catch (SQLException ex) {
                    Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                br.loans.remove(br.loans.get(i));
                bk.loan=null;
                System.out.println("\nThe book '"+bk.name+"' has been returned by the borrower "
                        +br.name+" (ID : "+br.pid+") within due date.");
                return;   
            }
        }
        System.out.println("\nThis book is not issued to the specified borrower.");
        
    }
    
    public void checkOut(Borrower br, Book bk,myDate dt){
        if(br.getFine()>0){System.out.println("\nBook can not be issued unless borrower pays due fines.");return;}
        bk.loan=null;
        Loan l=new Loan(bk,br,dt);
        br.loans.add(l);
        bk.loan=l;
        System.out.print("\nThe book '"+bk.name+"' has been issued to the borrower "+br.name+" (ID : "+br.pid+"). "
                +"Return it by ");
        dt.add(7).printDate();
        System.out.println(".");
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO ooad.loan(PersonID,BookID,DueDate,IssueDate)"+"VALUES('"+l.br.pid+"','"+l.bk.bkid+"','"+l.due.getDateObject()+"','"+l.issue.getDateObject()+"')");
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Borrower getBorrower(int id){
        Borrower b=null;
        for(int i=0;i<lib.persons.size();i++){
            
            Person p=lib.persons.get(i);
            if(p instanceof Borrower){
                b=(Borrower)p;
                if(b.pid==id)break;
                else b=null;
            }
        }
        return b;
    }
    
    public void addBorrower(Borrower b){
        lib.addBorrower(b);
    }
    
    public void deleteBorrower(Borrower b){
        lib.deleteBorrower(b);
    }
    
    public void updateBorrower(Borrower b,String name,String address,String phone,String password){
        lib.updateBorrower(b, name, address, phone, password);
    }
    
    public void renewLoan(Borrower br, Book bk, myDate dt){
    
        if(bk.isHeld()){
            System.out.println("\nThis book is not held by this borrower.");
            return;
        }
        Book b=null;
        Loan l=null;
        for(int i=0;i<br.loans.size();i++){
            
            l=br.loans.get(i);
            b=l.bk;
            if(b==bk)break;
            else b=null;
            
        }
        if(b==null || l==null){
            System.out.println("\nThis book is not held by this borrower.");
            return;
        }
        if(dt.isGreater(l.due)){
            System.out.println("\nYou can not renew loan after deadline. Return the book with due fine.");
            return;
        }
        
        if(br.getFine()>0){System.out.println("\nLoan can not be renewed unless borrower pays due fines.");return;}
        
        l.due=dt.add(7);
        System.out.print("\nLoan renewed, The book '"+bk.name+"' has been issued to the borrower "+br.name+" (ID : "+br.pid+") again. "
                +"Return it by ");
        l.due.printDate();
        System.out.println(".");
    }   
}
