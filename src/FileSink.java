import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by codedog29 on 7/13/17.
 */
public class FileSink extends Sink {

    private String FILENAME;
    BufferedWriter bw;
    FileWriter fw;

    public FileSink(HashMap<String, String> config) {
        super(config);
//        FILENAME = config.get("file_location") ;
        FILENAME = "/Users/codedog29/Code/office/logger/fs.txt" ;
        init();
    }

    @Override
    public void init() {

        try {

            File file = new File(FILENAME);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeMessage(Log log) {

        while (true) {
            try {
                bw.write("\n"+log.content);
                bw.flush();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception writing message in FileSink: " + e.getMessage());
                init();
            }
        }

    }
}
