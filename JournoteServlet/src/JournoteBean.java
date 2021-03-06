public class JournoteBean {

    private String date;
    private String title;
    private String content;
    private int isjournal; //isjournal = 1, isjournal;isjournal= 0, is note;
    private int hasnoti; //hasnoti=1, has notification;
    private int label; //use 1 to 5 to add laebel
    private String tag; //identify the journote with time instead of id
    //private int authorid;
    private String username;

    public JournoteBean(String date, String title, String content, int isjournal, int hasnoti, int label, String tag, String username){
        //this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.isjournal = isjournal;
        this.hasnoti = hasnoti;
        this.label = label;
        this.tag = tag;
        this.username = username;
    }
    public JournoteBean(){
    }

    //public int getId(){return id;}
    //public void setId(int id){this.id = id;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getIsjournal(){return isjournal;}
    public void setIsjournal(int isjournal){this.isjournal = isjournal;}

    public int getHasnoti(){return hasnoti;}
    public void setHasnoti(int hasnoti){this.hasnoti = hasnoti;}

    public int getLabel(){return label;}
    public void setLabel(int label){this.label = label;}

    public String getTag(){return tag;}
    public void setTag(String tag) { this.tag = tag; }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
}
