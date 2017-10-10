public class Librarian extends Staff{
    
    private static int noOfLibrarians=0;
    
    public Librarian(String n,String a,String p,String pass,LMS lib,int id){
    
        super(n,a,p,pass,id,lib);
        noOfLibrarians++;
    }
    
    public Librarian(String n,String a,String p,String pass,LMS lib){
    
        super(n,a,p,pass);
        noOfLibrarians++;
        this.lib=lib;
        lib.persons.add(this);
    }
   
    
    //clerk use cases
     
    
    public Clerk getClerk(int id){
        
        Clerk c=null;
        for(int i=0;i<lib.persons.size();i++){
            
            Person p=lib.persons.get(i);
            if(p instanceof Clerk){
                
                c=(Clerk)p;
                if(c.pid==id)break;
                else c=null;
            }
        }
        return c;
    }
    public Librarian getLibrarian(int id){
        
        Librarian l=null;
        for(int i=0;i<lib.persons.size();i++){
            
            Person p=lib.persons.get(i);
            if(p instanceof Librarian){
                
                l=(Librarian)p;
                if(l.pid==id)break;
                else l=null;
            }
        }
        return l;
    }
    
    public void addClerk(Clerk cl){
    
        lib.addClerk(cl);
    }
    
    public void deleteClerk(Clerk cl){
    
        lib.deleteClerk(cl);
    }
    
    public void addLibrarian(Librarian ln){
        lib.addLibrarian(ln);
    }
    public void deleteLibrarian(Librarian ln){
	lib.deleteLibrarian(ln);
    }

    public void addBook(Book b){
        lib.addBook(b);
    }
    
    public void deleteBook(Book b){
        lib.deleteBook(b);
    }
    
    public void updateBook(Book b,String name,String author,String subject){
        lib.updateBook(b, name, author, subject);
    }
    
    public int getCount(){return noOfLibrarians;}
    
}
