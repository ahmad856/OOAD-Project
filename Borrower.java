import java.util.ArrayList;
import java.util.Random;
public class Borrower extends Person{
    
    protected ArrayList<Loan> loans;
    protected ArrayList<Book> waitingList;       //hold the books which are loaned by someone else
    protected int fine;
    protected static int noOfBorrowers=0;
    protected ArrayList<myDate> fineDates;
    protected ArrayList<Integer> fines;
    protected ArrayList<Loan> fineLoans;

    
    public void pay(int amount,myDate dt){
        
        if(amount<1){System.out.println("Payment failed.");return;}

        fine-=amount;
        fineDates.add(dt);
        fines.add(-1*amount);
        fineLoans.add(null);
        System.out.println("Payment done.");
    }
    
    public int getFine(){return fine;}
    
    protected void printFineHistory(){
        
        System.out.println("");
        System.out.println("Account history of "+name+" (ID : "+pid+") is as follows : ");
        System.out.println("");
        
        for(int i=0;i<fines.size();i++){
            
            int acc=0;
            System.out.print((i+1)+". Date : ");
            fineDates.get(i).printDate();
            if(fines.get(i)<0){
                acc-=fines.get(i);
                System.out.println(" Payment : "+(-1*fines.get(i))+" Total Fine : "+acc);
            }
                
            else {
                acc+=fines.get(i);
                System.out.print(" Fine : Issued book '"+fineLoans.get(i).bk.name
                        +"' (Book ID : "+fineLoans.get(i).bk.bkid+") on ");
                fineLoans.get(i).issue.printDate();
                System.out.println(" and returned "+fineLoans.get(i).issue.difference(fineDates.get(i))
                        +" days late. Fine : "+fines.get(i)+" Total Fine : "+acc);
            }
        }
        System.out.println("Due fine is : "+fine+".");

    }
    
    
    public Borrower(String n,String a,String p,String pass){
    
        super(n,a,p,pass);
        noOfBorrowers++;
        
        loans=new ArrayList<>();
        waitingList=new ArrayList<>();
        fineDates=new ArrayList<>();
        fines=new ArrayList<>();
        fineLoans=new ArrayList<>();
    }
    
    public Borrower(String n,String a,String p,String pass,int id,LMS l){
    
        super(n,a,p,pass,id,l);
        noOfBorrowers++;
        
        loans=new ArrayList<>();
        waitingList=new ArrayList<>();
        fineDates=new ArrayList<>();
        fines=new ArrayList<>();
        fineLoans=new ArrayList<>();
    }
    
    
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
    
    public void Borrow(Book bk,myDate dt){
        
        if(bk.isHeld()){
            System.out.println("\nThis book is currently held by another borrower. Added to your waiting list.");
            waitingList.add(bk);return;
        }
        /*
        Random gen=new Random();
        int cid=gen.nextInt(Clerk.noOfClerks);
        Clerk c=null;
        for(int i=0;i<lib.persons.size();i++){
            
            Person p=lib.persons.get(i);
            if(p instanceof Clerk){
            
                c=(Clerk)p;
                if(c.cid==cid)break;
                else c=null;
            }
        }
        if(c!=null)c.checkOut(this,bk,dt);
        else System.out.println("No Clerk Available.");
        */
        
    }
    
    public void printWaitingList(){
    
        if(waitingList.isEmpty()){
        
            System.out.println("\nWaiting list is empty.");
            return;
        }
        for (Book bk : waitingList) {
            bk.printInfo();
        }
    }
    
    public void printInfo(){
        System.out.println("");
        System.out.println("ID : "+pid);
        System.out.println("Password : "+password);
        System.out.println("Name : "+name);
        System.out.println("Address : "+address);
        System.out.println("Phone : "+phone);
        
    }
    
    public void listCurrentBooks(){
        System.out.println("");
        System.out.println("This borrower has borrowed the following books currently : ");
        for(int i=0;i<loans.size();i++){
            System.out.println((i+1)+". "+loans.get(i).bk.name+" by "+loans.get(i).bk.author);
        }
    }
    
    public void  printInfoAndCurrentBooks(){
        printInfo();
        listCurrentBooks();
    }
//1) The borrower can place hold requests
//2) The system shall maintain requests in FIFO order
//3) Any user can view the hold-request queue for a given book
    
}
