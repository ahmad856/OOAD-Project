public class Borrower extends Person{
    
    protected ArrayList<Loan> loans;
    protected ArrayList<Book> waitingList;       //hold the books which are loaned by someone else
    protected int finepaid;
    protected int bid;
    protected int balance;
    protected static int noOfBorrowers=0;
    
    public Borrower(String n,String a,String p){
    
        super(n,a,p);
        bid=noOfBorrowers;
        noOfBorrowers++;
        balance=0;
    }
    
    public Book searchByName(String name){
    
        return lib.searchByName(name);
    }
    public void searchByAuthor(String author){
    
        lib.searchByAuthor(author);
    }
    public void searchBySubject(String subject){
    
        lib.searchBySubject(subject);
    }
    
    public void Borrow(Book bk,myDate dt){
        
        if(bk.hold){
            System.out.println("\nThis book is currently held by another borrower. Added to your waiting list.");
            waitingList.add(bk);return;
        }
        
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
        System.out.println("Name : "+name);
        System.out.println("Address : "+address);
        System.out.println("Phone : "+phone);
        
    }
}
