package TCPDemo;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/7.
 */
//多线程
public class Server {
    private static final int PORT = 7788;
    public void listen() throws Exception{
        //创建ServerSocket对象,监听指定的端口
        ServerSocket serverSocket = new ServerSocket(PORT);
        //使用while循环不停的接收客户端发送的请求
        while(true){
            //调用ServerSocket的accept()方法与客户端建立连接
            final Socket client = serverSocket.accept();
            //开启一个新线程
            new Thread(){
                @Override
                public void run() {
                    OutputStream os;//定义一个输出流对象
                    try{
                        os = client.getOutputStream();
                        System.out.println("开始和客户端进行交互数据");
                        os.write(("庖丁计划").getBytes());
                        Thread.sleep(5000);
                        System.out.println("结束和客户端进行交互数据");
                        os.close();
                        client.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
