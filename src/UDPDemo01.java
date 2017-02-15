import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Administrator on 2017/2/7.
 */
public class UDPDemo01 {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[1024];//创建一个长度为1024的字节数组，用于接收数据
        //定义一个DatagramSocket对象，监听的端口号为8954
        DatagramSocket ds = new DatagramSocket(8954);
        //定义一个DatagramPacket对象，用于接收数据
        DatagramPacket dp = new DatagramPacket(buf,1024);
        System.out.println("等待接收的数据:");
        ds.receive(dp); //等待接收数据,如果没有数据则会阻塞
        //调用DatagramPacket的方法获得接收的信息,包括内容,长度,IP地址和端口号
        String str = new String(dp.getData(),0,dp.getLength())+"from"+dp.getAddress().getHostAddress()+":"+dp.getPort();
        System.out.println(str);//打印接收的信息
        ds.close();//释放资源
    }
}
