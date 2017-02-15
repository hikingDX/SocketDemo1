package TCPDemo;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/7.
 */
public class TCPClient {
    private static final int PORT = 7788;
    public void connect() throws Exception{
        //创建一个Socket并连接到给出地址和端口号的计算机
        Socket client = new Socket(InetAddress.getLocalHost(),PORT);
        InputStream is = client.getInputStream();//得到接收数据的流
        byte[] buf = new byte[1024];
        int len = is.read(buf);
        System.out.println(new String(buf,0,len));
        client.close();
    }
}
