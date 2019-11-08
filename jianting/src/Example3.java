import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.*;

public class Example3 {
    public static void main(String[] args) throws Exception{
        File directory = new File("");
        // 轮询间隔 5 秒
        long interval = TimeUnit.MILLISECONDS.toMillis(10);
        // 创建一个文件观察器用于处理文件的格式
        FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),FileFilterUtils.suffixFileFilter(".txt")));
        //设置文件变化监听器
        observer.addListener(new MYFileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
        monitor.start();
    }
}

