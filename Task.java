package 多线程聊天;

public class Task implements Runnable{
    private Object lock = null;
    private String name;
    Content[] chats;
    public Task(){}
    public Task(Object lock,String name,Content[] chats){
        this.lock = lock;
        this.name = name;
        this.chats = chats;
    }
    @Override
    public void run(){
        int id = 0;
        //EndOfRun:
        while(id < chats.length){
            synchronized(lock){
                if(name == "A"){
                    ///跳过其他人说的话
                    while(id<chats.length && chats[id].getId()!=1){
                        //if(chats[id].getContent() == "拜拜")break EndOfRun;
                        id++;
                    }
                    ///输出所有自己说的内容
                    while(id<chats.length && chats[id].getId()==1){
                        System.out.println(name+"说:"+chats[id].getContent());
                        //if(chats[id].getContent() == "拜拜")break EndOfRun;
                        id++;
                    }
                }
                else if(name == "B"){
                    while(id<chats.length && chats[id].getId()!=2){
                        //if(chats[id].getContent() == "拜拜")break EndOfRun;
                        id++;
                    }
                    while(id<chats.length && chats[id].getId()==2){
                        System.out.println(name+"说:"+chats[id].getContent());
                        //if(chats[id].getContent() == "拜拜")break EndOfRun;
                        id++;
                    }
                }
                ///释放锁并激活另外一个线程
                lock.notify();
                ///自己线程进入阻塞态
                try{lock.wait();}
                catch (InterruptedException e){e.printStackTrace();}
            }
        }
        
        return;
    }
    public static void main(String[] args){
        Object lock = new Object();
        Content[] chats = new Content[6];
        chats[0] = new Content(0,"");
        chats[1] = new Content(1,"你好啊");
        chats[2] = new Content(2,"你好");
        chats[3] = new Content(2,"啥事");
        chats[4] = new Content(1,"没事");
        chats[5] = new Content(1,"拜拜");
        new Thread(new Task(lock,"A",chats)).start();
        new Thread(new Task(lock,"B",chats)).start();
    }
}
