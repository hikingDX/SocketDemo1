package TCPUpadteDemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/2/7.
 */
public class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        String ip = socket.getInetAddress().getHostAddress();//获取客户端ip
        int count = 1;
        try {
            InputStream in = socket.getInputStream();
            File parentFile = new File("d:\\upload\\");
            if (!parentFile.exists()){
                parentFile.mkdir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
