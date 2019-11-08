import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyCallableConsumer implements  Runnable {
    private BlockingQueue<Future<String>> queue;
    private  FileReader fileReader;
    private  BufferedReader bufferedReader;
    private  String filePath;
    private File file;

    public MyCallableConsumer(BlockingQueue<Future<String>> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            Future<String> future = queue.take();
            filePath = future.get();
            file = new File(filePath);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.delete();
        }

    }
}
