package project.core;

import project.beans.MC_FrameHead;
import project.beans.tagConnectInfo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2016/12/28.
 */
public class NetSendThread extends Thread {
    private static final long HEART_TIMER = 30 * 1000;    //心跳包间隔时间
    private static final long SESSION_TIMEOUT = 15 * 60 * 1000;    //15分钟，会话超时时间
    private static final long CONNECT_TIMEOUT = 10 * 1000;        //连接超时时间
    private static final long NETDATA_TIMEOUT = 20 * 1000;    //网络数据超时时间
    private static final long SERVICE_LOOK_TIME = 60 * 1000;        //每隔一段时间查看service有没有被kill掉
    private static final int REQUEST_MAIL_INTERVAL = 60 * 1000;    //请求邮件列表时间间隔


    public boolean mRun;
    SocketChannel mSocketChannel;
    byte[] mReadData;
    int mReadSize;
    ByteBuffer mSendByteBuffer;
    private byte[] mSendData;
    private int mSendSize;
    //    private Message mMsg;
    //private Date				mDate;
    private long mLastNetDataTime;    //最后处理网络数据的时间
    private long mLastSessionTime;    //会话最后时间，即客户最后操作时间。
    private long mLastRequestTime;    //最后请求时间，-1表示已有应答

    private global_net_class mGlobalNetClass;

    public NetSendThread(global_net_class GlobalNetClass) {
        mGlobalNetClass = GlobalNetClass;

        mRun = true;

        mSendData = new byte[MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE + 100];
        mSendSize = 0;

        mReadData = new byte[MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE * 2 + 200];
        mReadSize = 0;

        mSendByteBuffer = ByteBuffer.allocateDirect(MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE + 100);

        mLastNetDataTime = System.currentTimeMillis();
        mLastSessionTime = System.currentTimeMillis();
        mLastRequestTime = -1;
    }

    synchronized public boolean isConnected() {
        return mSocketChannel != null && mSocketChannel.isConnected();
    }

    synchronized public void closeNetThread() {
        System.out.println("NetSendThread--->closeNetThread 1");
        /**
         if (mGlobalNetClass == mMyApp.mCertifyNet || mGlobalNetClass == mMyApp.mNetClass)
         mMyApp.mLoginFlag = false;
         */
        mRun = false;
        mGlobalNetClass.mSessionID = 0;
        mGlobalNetClass.mRequestCode = 0;

        if (mSocketChannel == null)
            return;
        System.out.println("NetSendThread--->closeNetThread 2");
        {
            mLastNetDataTime = System.currentTimeMillis();
            mLastSessionTime = System.currentTimeMillis();
            mLastRequestTime = -1;

            mRun = false;
            mSendSize = 0;
            mReadSize = 0;
            mSendByteBuffer.clear();
        }
        {
            try {
                mSocketChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mSocketChannel = null;
        }
    }


    public int addSendDataWithHeartFlag(byte[] data, int offset, int size, boolean flag) {
//        if (!mMyApp.isSchedule) {// 防止重复踢人
//            return 0;
//        }
        synchronized (this) {
            if (size + mSendSize > mSendData.length)    //缓冲不够
            {
                return -1;
            }
            System.arraycopy(data, offset, mSendData, mSendSize, size);
            mSendSize += size;
            data = null;

            mLastNetDataTime = System.currentTimeMillis();
            if (!flag)    //非心跳包时，重设会话时间
            {
                mLastSessionTime = mLastNetDataTime;
                //L.e("qlmobile", "mLastSessionTime:" + mLastSessionTime);
            }
            return mSendSize;
        }
    }

    public int addSendData(byte[] data, int offset, int size) {
        return addSendDataWithHeartFlag(data, offset, size, false);
    }

    @Override
    public void run() {
        System.out.println("NetSendThread start--->mRun = " + mRun);
        boolean sleepflag = false;
        String errMsg = "";
        int errFlag = 0;    //0--弹出Dialog，1--Toast
        while (mRun) {
//                L.w("qlmobile", "run...................");
            errFlag = 0;
            sleepflag = true;
            if (mSendSize > 0)    //有数据需要发送
            {
                System.out.println("send...................");
                if (!mGlobalNetClass.isConnected()) {
                    System.out.println("run--->IsConnected() = " + mGlobalNetClass.isConnected());
                    NetConnect net = new NetConnect();
                    net.setAddr(mGlobalNetClass.mAddrConnect, mGlobalNetClass.mAddrConnectNum);

//                        for (String s :
//                                mAddr) {
//                            L.w("qlmobile", "mAddrConnect start--->mRun = " + s);
//                        }
                    tagConnectInfo info = new tagConnectInfo();
                    boolean ret = net.getSocketChannel(info, 20 * 1000);
                    net.clear();
                    net = null;
                    if (!ret || info.socket == null) {
                        System.out.println("Connect Failed!");
                        errMsg = "网络连接失败！";
                        System.out.println(errMsg);
                        errFlag = 1;
                        mGlobalNetClass.resetConnectAddr();
                        break;
                    }
                    System.out.println("Connect success addr:: " + mGlobalNetClass.mAddrConnect[info.index]);
                    mGlobalNetClass.deleteAddrWithIndex(info.index);
                    mSocketChannel = info.socket;
                    /**
                     System.out.println("mMyApp.mLoginFlag = " + mMyApp.mLoginFlag);
                     if (mMyApp.mLoginFlag == false
                     && mGlobalNetClass == mMyApp.mNetClass) {
                     if (!bReq_144_7) {
                     System.out.println("HQ--->reConnect Success!");
                     byte buffer[] = new byte[4096];
                     int size = globalNetProcess.getLoginData_FromCertify(buffer,
                     0, mMyApp.mLoginData, mMyApp.mUser,
                     mMyApp.mPassWord,
                     mMyApp.mMobilePassport, "phone=" + mMyApp.getPhoneNum(),
                     0, "0");
                     addSendData(buffer, 0, size);
                     } else {
                     System.out.println( "HQ--->reConnect Failed!");
                     bReq_144_7 = false;
                     }
                     }
                     */
                } else {
                    System.out.println("run--->IsConnected() = " + mGlobalNetClass.isConnected());
                }
            }

            if (mSocketChannel != null) {
                //发送数据
                if (mSendSize > 0) {
                    synchronized (this) {
                        mSendByteBuffer.clear();
                        mSendByteBuffer.put(mSendData, 0, mSendSize);
                        mSendByteBuffer.flip();
                    }

                    mLastNetDataTime = System.currentTimeMillis();
                    if (mLastRequestTime == -1) {
                        mLastRequestTime = mLastNetDataTime;
                    }


                    synchronized (this) {
                        try {

                            mSocketChannel.write(mSendByteBuffer);
                            System.out.println("--------------------------------------");
                            System.out.println("sendsize:" + mSendSize + "  mSocketChannel = " + mSocketChannel);
                            System.out.println("--------------------------------------");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            System.out.println("send data IOException...");
                            errFlag = 1;
                            break;
                        }
                        mSendSize = 0;
                    }
                    sleepflag = false;
                }

                int size = 0;
                synchronized (this) {
                    mSendByteBuffer.clear();
                    try {
                        size = mSocketChannel.read(mSendByteBuffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("receive data IOException...");
                        errFlag = 1;
                        break;
                    }
                    mSendByteBuffer.flip();
                }
                synchronized (this) {
                    if (size < 0) {
                        System.out.println("readsize:" + size);
                        break;
                    } else if (size > 0) {
                        mLastRequestTime = -1;
                        sleepflag = false;
                        System.out.println("1.readsize:" + size);
                        if (size + mReadSize > mReadData.length)    //数据有误
                        {
                            System.out.println("数据有误...");
                            errFlag = 1;
                            break;
                        }
                        mSendByteBuffer.get(mReadData, mReadSize, size);
                        mReadSize += size;

                        boolean quitflag = false;
                        while (mRun) {
                            /**
                             isUpdate = false;
                             */
                            size = mGlobalNetClass.decode(mReadData, mReadSize);
                            System.out.println("3.decode size:" + mReadSize + "  ret:" + size);
                            if (size < 0)    //解析数据错误，需关闭会话
                            {
                                System.out.println("解析数据错误");
                                errMsg = "解析数据错误";
                                errFlag = 1;
                                quitflag = true;
                                break;
                            } else if (size > 0) {
                                /**
                                 //发送更新消息
                                 if (isUpdate && mMainHandle != null) {
                                 mMsg = mMainHandle.obtainMessage(
                                 mMsgId, mChildType, mArg2,
                                 mMsgObject);
                                 mMainHandle.sendMessage(mMsg);
                                 System.out.println("发送客户端更新包 mMainType =" + mMainType + " mChildType = " + mChildType + " Size : " + mMyApp.getStockDataList().size()
                                 + " pageid = " + mPageId
                                 + " requestCode = " + mRequestCode);
                                 } else if (mMainHandle == null) {
                                 //		            						L.e("qlmobile", "2 mMainHandle == null");
                                 }
                                 */
                                int left = mReadSize - size;
                                if (left > 0) {
                                    System.arraycopy(mReadData, size,
                                            mReadData, 0, left);
                                    mReadSize = left;
                                } else {
                                    mReadSize = 0;
                                    break;
                                }
                            } else { // 不是完整包
                                break;
                            }
                        }
                        if (quitflag) {
                            break;
                        }
                    }
                }
            } else
                System.out.println("mSocketChannel == null");

            long now = System.currentTimeMillis();
            if (now - mLastNetDataTime >= HEART_TIMER)    //一段时间没有网络数据，需要发心跳包了
            {
                mGlobalNetClass.SendHeartRequest();
            } else if (now - mLastSessionTime >= SESSION_TIMEOUT)    //会话超时，需要关闭会话
            {
                System.out.println("SESSION TIMEOUT!");
                closeNetThread();
                break;
            }

            if (mLastRequestTime > 0 && now - mLastRequestTime >= NETDATA_TIMEOUT)    //请求数据超时
            {
                System.out.println("Recv Data time out!");
                errMsg = "请求数据超时！";
                //bNeedReconnect	=	true;
                break;
            }
            if (sleepflag) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("Thread InterruptedException...");
                }
            }
        }
        closeNetThread();
        /**
         if (mMainHandle != null && errMsg.length() > 0) {//
         Message mMsg = mMainHandle.obtainMessage(MSG_CONNECT_ERROR, errFlag, 0, errMsg);
         mMainHandle.sendMessage(mMsg);
         }
         */

        System.out.println("NetSendThread end");
    }
}
