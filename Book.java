public class Book {
    
    protected String name;
    protected String author;
    protected String subject;
    protected ArrayList<Loan> loans;
    protected LMS lib;
    protected boolean hold;
    
    public Book(String name, String author, String subject) {
        this.name = name;
        this.author = author;
        this.subject = subject;
        this.loans=new ArrayList<>();
        this.hold = false;
    }
    
    public boolean isHeld(){return hold;}
    
    public void printHistory(){
    
        System.out.println("");
        System.out.println("Loan history of book '"+name+"' by "+author+" :");
        System.out.println("");
        for(int i=0;i<loans.size();i++){
        
            System.out.print("Issued by "+loans.get(i).br.name+" on ");
            loans.get(i).issue.printDate();
            System.out.println(".");
        }
    }
    
    public void printInfo(){
    
        System.out.println("");
        System.out.println("Name : "+name);
        System.out.println("Author : "+author);
        System.out.println("Subject : "+subject);
        System.out.println("Currently held? : "+hold);
    }
}
