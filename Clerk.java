public class Clerk extends Staff{
    
    protected int cid;
    protected static int noOfClerks=0;
    
    public Clerk(String n,String a,String p){
    
        super(n,a,p);
        cid=noOfClerks;
        noOfClerks++;
    }
    
    public static int getCountOfClerks(){return noOfClerks;}
    
}
