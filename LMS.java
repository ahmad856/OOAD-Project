import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LMS {    
    ArrayList<Book> collection;
    ArrayList<Person> persons;

    public Person login(int id,String password){
        for(Person p:persons){
            if(p.pid==id && p.password.equals(password))
                return p;   
        }
        return null;
    }
    
    public void printAll(){
        System.out.println("");
        System.out.println("Books");
        for (Book collection1 : collection){
            System.out.println(collection1.name+" by "+collection1.author+". Subject : "+collection1.subject);
        }
        
        System.out.println("");
        System.out.println("Persons");
        for (Person p : persons) {
            if(p instanceof Borrower)
                System.out.println(p.name+" Type : Borrower. ID : "+p.pid);
            else if(p instanceof Clerk)
                System.out.println(p.name+" Type : Clerk. ID : "+p.pid);
            else if(p instanceof Librarian)
                System.out.println(p.name+" Type : Librarian. ID : "+p.pid);   
        }
    }
    
    public LMS(){
        collection=new ArrayList<>();
        persons=new ArrayList<>();
    }

    protected Book getByID(int id){
        Book found=null;
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).bkid==id)
                found=collection.get(i);
        }
        return found;
    }
    
    protected void searchByID(int id){
        boolean found=false;
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).bkid==id){
                collection.get(i).printInfo();found=true;}
        }
        if(!found)
            System.out.println("No books found.");
    }
    
    protected void searchByName(String name){
        boolean found=false;
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).name.equals(name)){
                collection.get(i).printInfo();found=true;}
        }
        if(!found)
            System.out.println("No books found.");
    }
    
    protected void searchByAuthor(String auth){
        boolean found=false;
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).author.equals(auth)){
                collection.get(i).printInfo();found=true;}
        }   
        if(!found)
            System.out.println("No books found.");
    }
    
    protected void searchBySubject(String sub){
        boolean found=false;
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).subject.equals(sub)){
                collection.get(i).printInfo();found=true;
            }
        }
        if(!found)
            System.out.println("No books found.");
    }
    
    protected void addBorrower(Borrower br){
        br.lib=this;
	persons.add(br);
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO ooad.person(PersonID,Password,PersonName,Address,Phone,PersonType)"+
                    "VALUES('"+br.pid+"','"+br.password+"','"+br.name+"','"+br.address+"','"+br.phone+"','Borrower')");
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteBorrower(Borrower br){
	persons.remove(br);
        br.lib=null;
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from ooad.person where PersonID = "+br.pid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void addClerk(Clerk ck){
        ck.lib=this;
	persons.add(ck);
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO ooad.person(PersonID,Password,PersonName,Address,Phone,PersonType)"+"VALUES('"+ck.pid+"','"+ck.password+"','"+ck.name+"','"+ck.address+"','"+ck.phone+"','Clerk')");
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteClerk(Clerk ck){
	persons.remove(ck);
        ck.lib=null;
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from ooad.person where PersonID = "+ck.pid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void addLibrarian(Librarian ln){
        ln.lib=this;
	persons.add(ln);
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO ooad.person(PersonID,Password,PersonName,Address,Phone,PersonType)"+
                    "VALUES('"+ln.pid+"','"+ln.password+"','"+ln.name+"','"+ln.address+"','"+ln.phone+"','Librarian')");
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteLibrarian(Librarian ln){
	persons.remove(ln);
        ln.lib=null;
        //Update DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from ooad.person where PersonID = "+ln.pid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateBorrower(Borrower b,String name,String address,String phone,String password){
        for(int i=0;i<persons.size();i++){
            if(persons.get(i)==b){
                persons.get(i).name = name;
                persons.get(i).password = password;
                persons.get(i).address = address;
                persons.get(i).phone = phone;
            }
        }
        //Update DB
        try {
            String n="'"+name+"'";
            String a="'"+address+"'";
            String p="'"+phone+"'";
            String pass="'"+password+"'";
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update ooad.book set PersonName = "+n+" Address = "+a+" Phone = "+p+" Password = "+pass+" where PersonID = "+b.pid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addBook(Book bk){
        bk.lib=this;
	collection.add(bk);
        //ADD TO DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO ooad.book(BookID,BookName,Author,Subject)"+
                    "VALUES('"+bk.bkid+"','"+bk.name+"','"+bk.author+"','"+bk.subject+"')");
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void deleteBook(Book bk){
        collection.remove(bk);
        bk.lib=null;
        //REMOVE FROM DB
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from ooad.book where BookID = "+bk.bkid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void updateBook(Book b,String n,String a,String s){
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).equals(b)){
                collection.get(i).author = a;
                collection.get(i).name = n;
                collection.get(i).subject = s;
            }
        }
        //Update DB
        try {
            String name="'"+n+"'";
            String author="'"+a+"'";
            String subject="'"+s+"'";
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ooad","ahmad","69824");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update ooad.book set BookName = "+name+" Author = "+author+" Subject = "+subject+" where BookID = "+b.bkid);
        }
        catch (SQLException ex) {
            Logger.getLogger(OOADAssignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printBorrowers(){
        for(int i=0;i<persons.size();i++){
            if(persons.get(i) instanceof Borrower){
                System.out.println("Name : "+persons.get(i).name+"   ID : "+persons.get(i).pid);
            }
        }
    }
}
