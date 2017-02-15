package project.core;

import project.beans.MC_FrameHead;

/**
 * Created by Administrator on 2016/12/28.
 */
public class global_net_class {
    public int mSessionID;        //会话ID


    public String mAddrConnect[];        //当前正在连的地址列表
    public int mAddrConnectNum;
    private int mAddrNum;
    public String mAddr[];            //存储服务器地址,地址格式为"192.168.3.72:50"

    private NetSendThread mSendThread;

    public Decode mDecode;

    public global_net_class(int mSessionID) {
        //数据包解析类
        mDecode = new Decode(this);
    }


    /**
     * 解析数据包
     * @param data
     * @param size
     * @return
     */
    public int decode(byte[] data, int size) {
        return mDecode.decode(data, size);
    }

    /**
     * 开启连接
     * @param data
     * @param offset
     * @param size
     * @return
     */
    public int addSendData(byte[] data, int offset, int size) {
        if (mSendThread == null || mSendThread.mRun == false) {
            mSendThread = new NetSendThread(this);
            mSendThread.mRun = true;
            mSendThread.start();
        }
        return mSendThread.addSendData(data, offset, size);
    }

    /**
     * 是否已连接
     *
     * @return
     */
    public boolean isConnected() {
        if (mSendThread != null) {
            if (mSendThread.isConnected())
                return true;
        }
        return false;
    }

    /**
     * 关闭连接
     */
    synchronized public void closeConnect() {
        if (mSendThread != null) {
            mSendThread.closeNetThread();
            mSendThread = null;
        }
        //
        if (global_net_class.this == mMyApp.mCertifyNet || global_net_class.this == mMyApp.mNetClass)
            mMyApp.mLoginFlag = false;
    }

    /**
     * 重新设置连接地址
     */
    public void resetConnectAddr() {
        for (int i = 0; i < mAddrNum; i++) {
            mAddrConnect[i] = mAddr[i];
        }
        mAddrConnectNum = mAddrNum;
    }

    public void deleteAddrWithIndex(int index) {
        if (index < 0 || index >= mAddrConnectNum) {
            return;
        }
        for (int i = index + 1; i < mAddrConnectNum; i++) {
            mAddrConnect[i - 1] = mAddrConnect[i];
        }
        mAddrConnectNum--;
    }
    //发送心跳包
    public int SendHeartRequest() {
        byte[] data = new byte[MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE];
        int datasize = MC_FrameHead.MC_FrameHead_LEN;

        int size = 0;
        size = CMobileProt.MakeEncryptPackage((byte) 0, (byte) 0, (byte) 0, mSessionID, 0, data, 0, datasize, (byte) 0);

        System.out.println("-----------SendHeartRequest!------------, this = " + this.toString());
        return mSendThread.addSendDataWithHeartFlag(data, 0, size, true);
    }

}
