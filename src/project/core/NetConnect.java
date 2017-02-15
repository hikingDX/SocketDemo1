package project.core;

/**
 * Created by Administrator on 2017/2/8.
 */
import project.utils.STD;
import project.beans.tagConnectInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class NetConnect {
    private final static String TAG = NetConnect.class.getSimpleName();


    private SocketChannel mSocketChannel[];
    private int mIndexForSocketAddr[];
    private int mSocketChannelNum;
    private int mConnectCompleteNum;    //连接完成个数

    private String mAddr[];
    private int mAddrNum;

    private NetThread mNetThreads[];

    public NetConnect() {
        mSocketChannel = new SocketChannel[10];
        mIndexForSocketAddr = new int[10];
        mSocketChannelNum = 0;

        mAddr = new String[10];
        mAddrNum = 0;

        mNetThreads = new NetThread[10];
    }

    synchronized public void clear() {
        for (int i = 0; i < mAddrNum; i++) {
            mNetThreads[i].quit();
            mNetThreads[i] = null;

            mAddr[i] = null;
        }
        mAddrNum = 0;

        for (int i = 0; i < mSocketChannelNum; i++) {
            try {
                mSocketChannel[i].close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mSocketChannel[i] = null;
            mIndexForSocketAddr[i] = 0;
        }
        mSocketChannelNum = 0;
        mConnectCompleteNum = 0;
    }

    synchronized public void setAddr(String addr[], int num) {
        mConnectCompleteNum = 0;
        mAddrNum = Math.min(mAddr.length, num);
        for (int i = 0; i < mAddrNum; i++) {
            mAddr[i] = addr[i];

            mNetThreads[i] = new NetThread();
            mNetThreads[i].setAddr(mAddr[i], i);
            mNetThreads[i].start();
        }
    }

    synchronized private void addConnectCompleteNum() {
        mConnectCompleteNum++;
    }

    public boolean getSocketChannel(tagConnectInfo info, int millis) {
        if (mSocketChannelNum == 0 && millis > 0) {
            long lasttimes = System.currentTimeMillis();
            while (System.currentTimeMillis() - lasttimes < millis) {
                if (mConnectCompleteNum == mAddrNum)    //全部完成
                {
                    break;
                }
                if (mSocketChannelNum > 0) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (this) {
            if (mSocketChannelNum > 0) {
                SocketChannel t = mSocketChannel[0];
                for (int i = 1; i < mSocketChannelNum; i++) {
                    mSocketChannel[i - 1] = mSocketChannel[i];
                }
                mSocketChannelNum--;
                mSocketChannel[mSocketChannelNum] = null;
                info.socket = t;
                info.index = mIndexForSocketAddr[0];
                return true;
            }
        }
        return false;
    }

    synchronized private int addSocketChannel(SocketChannel socket, int index) {
        if (mSocketChannelNum < mSocketChannel.length) {
            mSocketChannel[mSocketChannelNum] = socket;
            mIndexForSocketAddr[mSocketChannelNum] = index;
            mSocketChannelNum++;
            System.out.println("addSocketChannel : " + mSocketChannelNum);
            return mSocketChannelNum;
        }
        return -1;
    }

    private class NetThread extends Thread {
        public boolean mRun;
        SocketChannel mSocketChannel;
        String mAddr;
        int mIndex;

        public NetThread() {
            mRun = false;
        }

        private void setAddr(String addr, int index) {
            mAddr = addr;
            mIndex = index;
            mRun = true;
        }

        public void quit() {
            mRun = false;
            if (mSocketChannel != null) {
                try {
                    mSocketChannel.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            System.out.println( "NetThread start:" + mAddr);

            mSocketChannel = null;
            try {
                mSocketChannel = SocketChannel.open();
                mSocketChannel.configureBlocking(false);

                System.out.println( "start addr:  " + mAddr);
                mAddr = STD.GetValue(mAddr, 1, '|');    //updateAddressWithName需要先解析|
                String ip = STD.GetValue(mAddr, 1, ':');
                int port = STD.GetValueInt(mAddr, 2, ':');
                System.out.println("hiking++"+ip);
                InetAddress inetAddr = InetAddress.getByName(ip);
                SocketAddress address = new InetSocketAddress(inetAddr.getHostAddress(), port);

                System.out.println( "begin connect... " + mAddr + ", ip = " + ip + ", port = " + port);
                boolean ret = mSocketChannel.connect(address);
                System.out.println("connect end.  ret:" + ret + " ----mAddr ：" + mAddr);

                long lasttimes = System.currentTimeMillis();

                while (mRun) {
                    if (mSocketChannel.finishConnect()) {
                        System.out.println("connect success  socket:" + mAddr + ", ip = " + ip + ", port = " + port);
                        System.out.println("mSocketChannel = " + mSocketChannel);
                        addSocketChannel(mSocketChannel, mIndex);
                        mSocketChannel = null;
                        break;
                    }
                    if (System.currentTimeMillis() - lasttimes >= 60 * 1000)    //连接超时
                    {
                        System.out.println( "timeout: " + mAddr);
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("connect Exception:" + mAddr);
                if (mSocketChannel != null) {
                    try {
                        mSocketChannel.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
            System.out.println("NetThread end:" + mAddr);
            addConnectCompleteNum();
        }
    }
}

