import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by codedog29 on 7/13/17.
 */
public class Logger {

    Queue<Log> queue ;
    Map<String,Sink> classMap ;
    long count = 0 ;

    public Logger() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //init code here
        queue = new LinkedList<>();
        classMap = new HashMap<>() ;
        classMap.put("INFO", (Sink) Class.forName("FileSink").getConstructor(HashMap.class).newInstance(new HashMap<>())) ;
    }

    public void start() throws InterruptedException {

        while (true) {
            if(queue.isEmpty()) loadLogs() ;
            Log log = getLog() ;
            classMap.get(log.level).writeMessage(log);
        }

    }

    private Log getLog() {
        return queue.remove() ;
    }

    private void loadLogs() throws InterruptedException {
        Thread.sleep(1000);
        queue.add(new Log(count+++"","INFO","namespace")) ;
        queue.add(new Log(count+++"","INFO","namespace")) ;
        queue.add(new Log(count+++"","INFO","namespace")) ;
        queue.add(new Log(count+++"","INFO","namespace")) ;
        queue.add(new Log(count+++"","INFO","namespace")) ;
    }

}
