public abstract class Person {
    
    protected String name;
    protected String address;
    protected String phone;
    protected LMS lib;
    protected int pid;
    protected String password;
    protected static int noOfPersons=0;

    public Person(String n,String a,String p,String pass){
        name = n;
        address = a;
        phone = p;
        lib=null;
        pid=noOfPersons;
        noOfPersons++;
        password=pass;
    }
    
    public Person(String n,String a,String p,String pass, int id,LMS l){
        name = n;
        address = a;
        phone = p;
        lib=null;
        pid=id;
        noOfPersons++;
        password=pass;
        lib=l;
        l.persons.add(this);
    }
}
