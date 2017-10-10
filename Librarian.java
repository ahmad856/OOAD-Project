public class Librarian extends Staff{
    
    protected int lid;
    private static int noOfLibrarians=0;
    
    public Librarian(String n,String a,String p){
    
        super(n,a,p);
        lid=noOfLibrarians;
        noOfLibrarians++;
    }
    
    //clerk use cases
    
    public void addClerk(Clerk cl){
    
        lib.addClerk(cl);
    }

    
    public void addBook(Book b){
        lib.addBook(b);
    }
    
    public void deleteBook(Book b){
        lib.deleteBook(b);
    }
    
    public void updateBook(Book b,String n,String a,String s){
        lib.updateBook(b, n, a, s);
    }
    
    public int getCount(){return noOfLibrarians;}
    
}
