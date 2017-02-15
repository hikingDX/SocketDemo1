import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/2/7.
 */
public class Example01 {
    public static void main(String[] args) throws Exception {
        InetAddress localAddress = InetAddress.getLocalHost();
        InetAddress remoteAddress = InetAddress.getByName("www.baidu.com");
        System.out.println("本地IP地址:"+localAddress.getHostAddress());
        System.out.println("百度IP地址:"+remoteAddress.getHostAddress());
        System.out.println("3秒是否可达:"+remoteAddress.isReachable(3000));
    }
}
