public abstract class Staff extends Person{
    public Staff(String n,String a,String p){
        super(n,a,p);
    }
    //borrower use cases
    public Book searchByName(String name){
        
        return lib.searchByName(name);
    }
    public void searchByAuthor(String author){
        
        lib.searchByAuthor(author);
    }
    public void searchBySubject(String subject){
        
        lib.searchBySubject(subject);
    }
    
    public void BorrowForBorrower(Borrower br,Book bk,myDate dt){
        
        if(bk.hold){
            System.out.println("\nThis book is currently held by another borrower. Added to your waiting list.");
            br.waitingList.add(bk);return;
        }
        checkOut(br,bk,dt);
        
    }
    
    public void displayBorrowerInfo(Borrower br){br.printInfo();}
    public void displayBorrowerInfoAndCurrentBooks(Borrower br){br.printInfoAndCurrentBooks();}
    public void displayBorrowerWaitingList(Borrower br){br.printWaitingList();}
    
    public void addBorrowerBalance(Borrower br,int amount){br.balance+=amount;}
    public int getBorrowerBalance(Borrower br){return br.balance;}
    
    
    ///Clerk use cases
    
    
    public void checkIn(Borrower br, Book bk, myDate dt){
        
        if(!bk.hold){System.out.println("\nThis book is currently not issued to anyone.");return;}
        
        for(int i=0;i<br.loans.size();i++) {
            if(br.loans.get(i).bk==bk){
                
                if(dt.isGreater(br.loans.get(i).due)){
                    //fine
                    int fine=50*(dt.difference(br.loans.get(i).due));
                    br.balance-=fine;
                    System.out.println("\nThe book '"+bk.name+"' has been returned by the borrower "
                            +br.name+" (ID : "+br.bid+") "+dt.difference(br.loans.get(i).due)+" DAYS LATE with fine amount "+fine+".");
                    
                }
                
                br.loans.remove(br.loans.get(i));
                bk.hold=false;
                System.out.println("\nThe book '"+bk.name+"' has been returned by the borrower "
                        +br.name+" (ID : "+br.bid+") within due date.");
                return;
                
            }
        }
        System.out.println("\nThis book is not issued to the specified borrower.");
        
    }
    
    public void checkOut(Borrower br, Book bk,myDate dt){
        
        if(br.balance<0){System.out.println("\nBook can not be issued unless borrower pays due fines.");return;}
        bk.hold=true;
        Loan l=new Loan(bk,br,dt);
        br.loans.add(l);
        System.out.print("\nThe book '"+bk.name+"' has been issued to the borrower "+br.name+" (ID : "+br.bid+"). "
                +"Return it by ");
        dt.add(7).printDate();
        System.out.println(".");
    }
    
    public Borrower getBorrower(int id){
        
        Borrower b=null;
        for(int i=0;i<lib.persons.size();i++){
            
            Person p=lib.persons.get(i);
            if(p instanceof Borrower){
                
                b=(Borrower)p;
                if(b.bid==id)break;
                else b=null;
            }
        }
        return b;
    }
    
    public void addBorrower(Borrower b){
        lib.addBorrower(b);
    }
    
    
    public void updateBorrower(Borrower b,String t,String ad){
        lib.updateBorrower(b, t, ad);
    }
    
    public void renewLoan(Borrower br, Book bk, myDate dt){
    
        if(bk.hold){
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
        if(b==null){
            System.out.println("\nThis book is not held by this borrower.");
            return;
        }
        if(dt.isGreater(l.due)){
            System.out.println("\nYou can not renew loan after deadline. Return the book with due fine.");
            return;
        }
        
        if(br.balance<0){System.out.println("\nLoan can not be renewed unless borrower pays due fines.");return;}
        
        l.due=dt.add(7);
        System.out.print("\nLoan renewed, The book '"+bk.name+"' has been issued to the borrower "+br.name+" (ID : "+br.bid+") again. "
                +"Return it by ");
        l.due.printDate();
        System.out.println(".");
    }
    
    public void clearBookHistory(Book bk){
    
        if(bk.hold){System.out.println("\nBook is currently issued. Operation Failed.");return;}
        bk.loans.clear();
        System.out.println("\nBook history cleared.");
    }
    
}
