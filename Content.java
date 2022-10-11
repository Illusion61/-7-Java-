package 多线程聊天;

public class Content {
    private int id;
    private String content;
    public Content(){}
    public Content(int id,String content){
        this.id = id;
        this.content = content;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setContent(String content){
        this.content = content;
    }
    public int getId(){return this.id;}
    public String getContent(){return this.content;}
}
