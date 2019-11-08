import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MyCallableProducer implements Callable<String> {
    private String filePath;
    private CountDownLatch latch;

    public MyCallableProducer(CountDownLatch latch, String filePath) {
        this.filePath = filePath;
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        return filePath;
    }
}
