import project.core.NetSendThread;
import project.core.global_net_class;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2016/12/23.
 */
public class Hello {
    NetSendThread mSendThread;
    public static void main(String[] args) throws InterruptedException {
        global_net_class global_net_class = new global_net_class();
        global_net_class.addSendData(0,0,100);
        sleep(10000);
        global_net_class.addSendData(0,0,122);
    }
}
