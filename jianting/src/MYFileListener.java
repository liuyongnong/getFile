import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.*;

public class MYFileListener implements FileAlterationListener {
        CountDownLatch latch = new CountDownLatch(100);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        BlockingQueue<Future<String>> queue =
                new ArrayBlockingQueue<Future<String>>(100);

        @Override
        public void onStart(FileAlterationObserver fileAlterationObserver) {
            ///System.out.println("monitor start scan files..");
        }


        @Override
        public void onDirectoryCreate(File file) {
            //System.out.println(file.getName()+" director created.");
        }


        @Override
        public void onDirectoryChange(File file) {
            // System.out.println(file.getName()+" director changed.");
        }


        @Override
        public void onDirectoryDelete(File file) {
            //System.out.println(file.getName()+" director deleted.");
        }


        @Override
        public void onFileCreate(File file) {
            Future<String> future = pool.submit(new MyCallableProducer(latch, file.getAbsolutePath()));
            queue.add(future);
            pool.execute(new MyCallableConsumer(queue));

        }
        @Override
        public void onFileChange(File file) {
            System.out.println(file.getName() + " changed.");
        }


        @Override
        public void onFileDelete(File file) {
            System.out.println(file.getName() + " deleted.");
        }


        @Override
        public void onStop(FileAlterationObserver fileAlterationObserver) {
            // System.out.println("monitor stop scanning..");
        }
    }
