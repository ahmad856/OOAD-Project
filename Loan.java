public class Loan {
    protected Borrower br;
    protected Book bk;
    protected myDate issue;
    protected myDate due;
 
    
    public Loan(Book bk, Borrower br, myDate i) {
        this.bk = bk;
        this.br = br;
        this.issue = i;
        this.due = i.add(7);
    }


}
