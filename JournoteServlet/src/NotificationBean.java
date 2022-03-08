import java.util.Date;

public class NotificationBean {

    private String journoteTag;
    private String date_str;
    //private boolean isFinished;

    public NotificationBean(String journoteTag, String date_str){
        this.journoteTag = journoteTag;
        this.date_str = date_str;
        //this.isFinished = isFinished;
    }
    public NotificationBean(){

    }
    public String getJournoteTag(){return journoteTag;}
    public void setJournoteTag(String journoteTag){this.journoteTag = journoteTag;}

    public String getDateStr(){return date_str;}
    public void setDateStr(String date_str){this.date_str = date_str;}

    //public boolean getIsFinished(){return isFinished;}
    //public void setIsFinished(boolean isFinished){this.isFinished = isFinished;}
}
