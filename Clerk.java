public class Clerk extends Staff{
    
    protected static int noOfClerks=0;
    
    public Clerk(String n,String a,String p,String pass){
    
        super(n,a,p,pass);
        noOfClerks++;
    }
    
        
    public Clerk(String n,String a,String p,String pass,int id,LMS l){
    
        super(n,a,p,pass,id,l);
        noOfClerks++;
    }
    
    public static int getCountOfClerks(){return noOfClerks;}
    
}
