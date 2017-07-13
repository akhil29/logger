import java.util.HashMap;

/**
 * Created by codedog29 on 7/13/17.
 */
public abstract class Sink extends Thread{

    HashMap<String ,String> config = null;

    public Sink(HashMap<String ,String> config) {
        this.config = config ;
    }

    public abstract void init() ;
    public abstract void writeMessage(Log log) ;

}
