import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.util.Calendar;

public class myDate {
    
    private int date=0;
    private int month=0;
    private int year=0;
    
    public myDate(int d,int m,int y){
        
        date=d;
        month=m;
        year=y;
        
    }
    
    public myDate add(int days){
        
            
            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String inputString1=date+" "+month+" "+year;
            
            try{
                Calendar c = Calendar.getInstance();
                c.setTime(myFormat.parse(inputString1));
                c.add(Calendar.DATE, days);  // number of days to add
                inputString1 = myFormat.format(c.getTime());  // dt is now the new date
            }
            catch(ParseException e) {
            e.printStackTrace();
            }
            return new myDate(Integer.parseInt(inputString1.substring(0,2)),Integer.parseInt(inputString1.substring(3,5))
            ,Integer.parseInt(inputString1.substring(6,10)));
    }
    
    
    public int difference(myDate dt){
        
        if(dt.isEqual(this))return 0;
        int result=0;
        
        if(dt.isGreater(this)){
            
            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String inputString1=date+" "+month+" "+year;
            String inputString2=dt.date+" "+dt.month+" "+dt.year;
            
            try{
                Date date1 = myFormat.parse(inputString1);
                Date date2 = myFormat.parse(inputString2);
                long diff = date2.getTime() - date1.getTime();
                result=(int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            }
            catch(ParseException e) {
            e.printStackTrace();
            }
            
        }
        else{
            
            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String inputString2=date+" "+month+" "+year;
            String inputString1=dt.date+" "+dt.month+" "+dt.year;
            
            try{
                Date date1 = myFormat.parse(inputString1);
                Date date2 = myFormat.parse(inputString2);
                long diff = date2.getTime() - date1.getTime();
                result=(int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            }
            catch(ParseException e) {
            e.printStackTrace();
            }
            
        }
        return result;
    }
    
    public boolean isSmaller(myDate dt){
        
        if(dt.year>year)return true;
        if(dt.month>month && dt.year==year)return true;
        if(dt.date>date && dt.month==month && dt.year==year)return true;
        return false;
        
    }
    public boolean isGreater(myDate dt){
        
        if(dt.year<year)return true;
        if(dt.month<month && dt.year==year)return true;
        if(dt.date<date && dt.month==month && dt.year==year)return true;
        return false;
        
    }
    public boolean isEqual(myDate dt){
        
        if(dt.date==date && dt.month==month && dt.year==year)return true;
        return false;
        
    }
    
    public void printDate(){
        
        System.out.print(date+"-"+month+"-"+year);
    }
    
}
