# OOAD-Project
Final Project

import java.util.ArrayList;
public class LMS {

    ArrayList<Book> collection;
    ArrayList<Borrower> borrowers;
    
    public LMS(){
        collection=new ArrayList<>();
        borrowers=new ArrayList<>();
    }
    
    public boolean searchByName(String name){
    
        return true;
    }
    
    public boolean searchByAuthor(String name){
    
        return true;
    }
    
    public boolean searchBySubject(String name){
    
        return true;
    }
}

public class Loan extends LMS{

    Borrower br;
    Book bk;
    
}

public class Accounting extends LMS {
    
}

public class Book extends LMS{
    
    String name;
    String author;
    String subject;
    Loan loanObj;
    boolean hold;
    ArrayList<Loan> loans;

    public Book() {
        name=null;
        author=null;
        subject=null;
        loanObj=null;
    }

    public Book(String name, String author, String subject, Loan loanObj) {
        this.name = name;
        this.author = author;
        this.subject = subject;
        this.loanObj = loanObj;
    }
    
    public void loan(boolean b){
        hold = b;
    }
    
}

public class Person extends LMS{

    
    
}

public class Borrower extends Person{

    ArrayList<Loan> loans;
    ArrayList<Book> books;
    int finepaid;
    
    public void checkinfo(){
    
    }
    
    public void listbooks(){
    
    }   
}

public class Librarian extends Person{

    public void additem(){
    
    }
    
    public void deleteitem(){
    
    }
    
    public void updateitem(){
    
    }
    
}

public class Clerk extends Person{

    public void checkin(){
    
    }
    
    public void checkout(){
    
    }
    
    public void addborrower(){
    
    }
    
    public void updateborrower(){
    
    }
    
}
