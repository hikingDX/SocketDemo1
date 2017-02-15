package TCPDemo;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/7.
 */
public class TCPServer {
    private static final int PORT = 7788;//定义一个端口
    //定义一个listen()方法
    public void listen() throws Exception{
        //1.创建ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(PORT);
        //2.调用ServerSocket的accept()方法接收数据
        Socket client = serverSocket.accept();
        //获取客户端的输出流
        OutputStream os = client.getOutputStream();
        System.out.println("开始与客户端交互数据");
        os.write(("庖丁计划Day01").getBytes());
        Thread.sleep(5000);
        System.out.println("结束与客户端交互数据");
        os.close();
        client.close();
    }

}
