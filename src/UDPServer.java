import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 2017/2/7.
 */
public class UDPServer{
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(3000);
        String str = "helloworld!";
        DatagramPacket dp = new DatagramPacket(str.getBytes(),str.length(), InetAddress.getByName("localhost"),8954);
        ds.send(dp);
        ds.close();
    }

}
