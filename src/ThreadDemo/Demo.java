package ThreadDemo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Administrator on 2017/2/8.
 */
public class Demo {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }
        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();
    }
}
