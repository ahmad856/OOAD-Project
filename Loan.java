public class Loan {
    protected Borrower br;
    protected Book bk;
    protected myDate due;
    protected myDate issue;

    public Loan(Book bk, Borrower br, myDate i) {
        this.bk = bk;
        this.br = br;
        this.issue=i;
        this.due = i.add(7);
    }
    
    public Loan(Book bk, Borrower br, myDate d,myDate i) {
        this.bk = bk;
        this.br = br;
        this.due=d;
        this.issue = i;
    }
}
