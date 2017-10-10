import java.util.LinkedList;
import java.util.Queue;
public class Book{
    
    protected int bkid;
    protected String name;
    protected String author;
    protected String subject;
    protected Loan loan;
    protected Queue<Borrower> waitingList;
    LMS lib;
    protected static int noOfBooks=0;

    public Book(int bkid, String name, String author, String subject,LMS l) {
        this.bkid = bkid;
        this.name = name;
        this.author = author;
        this.subject = subject;
        noOfBooks++;
        loan=null;
        waitingList=new LinkedList<>();
        lib=l;
        l.collection.add(this);
    }
    
    public Book(String name, String author, String subject) {
        this.bkid=noOfBooks;
        noOfBooks++;
        this.name = name;
        this.author = author;
        this.subject = subject;
        loan=null;
        waitingList=new LinkedList<>();
    }
    
    public boolean isHeld(){
        return loan != null;
    }
    
    public void printInfo(){
        System.out.println("");
        System.out.println("Name : "+name);
        System.out.println("Author : "+author);
        System.out.println("Subject : "+subject);
        System.out.println("Currently held? : "+isHeld());
    }   
}
