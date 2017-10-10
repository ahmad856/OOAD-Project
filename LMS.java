public class LMS {
    
    ArrayList<Book> collection;
    ArrayList<Person> persons;
    Accounting ac;
    
    public LMS(){
        collection=new ArrayList<>();
        persons=new ArrayList<>();
        ac=new Accounting(this);
    }

    
    protected Book searchByName(String name){
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).name.equals(name))
                return collection.get(i);
        }
        return null;
    }
    
    protected void searchByAuthor(String auth){
        
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).author.equals(auth))
                collection.get(i).printInfo();
        }
    }
    
    protected void searchBySubject(String sub){
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).subject.equals(sub))
                collection.get(i).printInfo();
        }
    }
    
    protected void addBorrower(Borrower br){
        br.lib=this;
	persons.add(br);
    }
    protected void deleteBorrower(Borrower br){
	persons.remove(br);
        br.lib=null;
    }
    
    protected void addClerk(Clerk ck){
        ck.lib=this;
	persons.add(ck);
    }
    protected void removeClerk(Clerk ck){
	persons.remove(ck);
        ck.lib=null;
    }
    
    protected void addLibrarian(Librarian ln){
        ln.lib=this;
	persons.add(ln);
    }
    protected void deleteLibrarian(Librarian ln){
	persons.remove(ln);
        ln.lib=null;
    }
    
    protected void updateBorrower(Borrower b,String p,String ad){
        for(int i=0;i<persons.size();i++){
            if(persons.get(i).equals(b)){
                persons.get(i).address = ad;
                persons.get(i).phone = p;
            }
        }
    }
    
    protected void addBook(Book bk){
        bk.lib=this;
	collection.add(bk);
    }
    
    protected void deleteBook(Book bk){
        collection.remove(bk);
        bk.lib=null;
    }
    
    protected void updateBook(Book b,String n,String a,String s){
        for(int i=0;i<collection.size();i++){
            if(collection.get(i).equals(b)){
                collection.get(i).author = a;
                collection.get(i).name = n;
                collection.get(i).subject = s;
            }
        }
    }
    
    protected void renew(Book b){
        //extend deadline
    }
}
