package TCPUpadteDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/7.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10001);
        while(true){
            //调用accept()方法接收客户端请求,得到socket对象
            Socket s = serverSocket.accept();
            //每当和客户端建立Scocket连接后,单独开启一个线程处理和客户端的交互
            new Thread(new ServerThread(s));
        }
    }
}
