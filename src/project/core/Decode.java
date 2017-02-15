package project.core;

import project.utils.CDataEncrypt;
import project.utils.Global_Define;
import project.utils.MLZW8192;
import project.beans.MC_FrameHead;
import project.utils.STD;

import static project.beans.MC_FrameHead.MC_FrameHead_LEN;

/**
 * 数据包解析类
 * Created by Administrator on 2017/2/13.
 */
public class Decode {
    public static final int MSG_CONNECT_FAIL = -1;        //连接失败
    public static final int MSG_READDATA_ERROR = -2;        //接受数据错误
    public static final int MSG_CONNECT_ERROR = -100;        //连接失败
    public static final int MSG_UPDATE_DATA = 100;        //更新界面消息
    public static final int MSG_PUSH_DATA = 101;        //
    public static final int MSG_RET_ERROR = 102;        //
    public static final int MSG_SEARCH = 103;        //
    public static final int MSG_RELOGIN = 104;        //踢人消息
    public static final int MSG_UPDATE_VERSION = 105;        //更新版本
    public static final int MSG_TIMEOUT = 106;        //请求超时
    public static final int MSG_RT_COMMENT = 108;        //盘中实时点评消息
    public static final int MSG_PUSH_INDEX = 109;        //指数推送
    public static final int MSG_ADAPTER_BUTTON_CLICK = 110;    //添加删除自选（搜索界面）
    public static final int MSG_REFRESH_BACKFROMSEARCH = 111;    //（搜索界面）返回刷新界面
    public static final int MSG_UPDATE_BUTTON_BACKFROMSEARCH = 112;    //（搜索界面）返回刷新个股界面添加删除按钮
    public static final int MSG_START_PUSHMAIL_SERVICE = 1900;//开启推送服务
    public static final int MSG_ZY_EVENTBUS_PUSH_DATA_LZ = 113;//自营版沪深推送
    public static final int MSG_ZY_EVENTBUS_PUSH_DATA_ZS = 114;//自营版指数推送

    global_net_class global_net_class;

    //解压类
    private MLZW8192 mLZW8192;
    //协议
    public int mChildType, mMainType;
    private boolean isUpdate;//控制Handle是否发送消息
    private Object mMsgObject;
    private int mArg2;    //附加数据
    private int mMsgId = MSG_UPDATE_DATA;

    public Decode(project.core.global_net_class global_net_class) {
        this.global_net_class = global_net_class;
        //创建解压数据包类
        mLZW8192 = new MLZW8192();
    }

    /**
     * 解析协议数据
     *
     * @param data
     * @param size
     * @return：返回0表示不满一个包，返回负数表示有错误，返回正数表示处理的字节数
     */
    protected int decode(byte[] data, int size) {

        MC_FrameHead head = new MC_FrameHead();
        /**
         * 1.解析头部
         */
        int ret = decodeHeader(data, size, head);
        if (ret < 0) {
            return ret;
        }
        int packagesize = head.PackageSize;


        /**
         * 2.解密数据包
         */
        int datasize = decodeCrypt(packagesize, head, data);
        if (datasize <= 0) {
            return datasize;
        }

        /**
         * 3.解压
         */
        //解压后的数据
        byte[] expanddata = new byte[MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE + 100];
        int tmp = datasize;
        datasize = decodeExpandData(packagesize, head, data, expanddata);
        if (datasize < 0) {
            return datasize;
        } else if (datasize == 0) {
            //说明没有压缩
            datasize = tmp;
        }

        /**
         * 4.解析错误
         */
        tmp = decodeError(head, expanddata, packagesize);
        if (tmp > 0) {
            return tmp;
        }

        /**
         * 5.解析协议
         */
        decodeDataByProtocol(head);
    }

    private void decodeDataByProtocol(MC_FrameHead head) {
        mMsgObject = "";
        mArg2 = 0;

        if (head.DataFlag == 2)
            mMsgId = MSG_PUSH_DATA;
        else
            mMsgId = MSG_UPDATE_DATA;

        mChildType = head.ChildType;
        mMainType = head.MainType;
        mArg2 = mMainType;
        MDBF mdbf = null;
        switch (head.MainType) {
            /**
             * 心跳包
             */
            case 0: {
                isUpdate = false;
                break;
            }
            /**
             * 价格提醒
             */
            case 150: {
                switch (head.ChildType) {
                    case 0: {

                    }
                    case 1: {

                    }
                    case 2: {

                    }
                }
            }
            case 144: {
                switch (head.ChildType) {

                }
            }

            case 145: {
                switch (head.ChildType) {
                    case 17:        //排行
                        if (head.PageID != mPageId) {
                            isUpdate = false;
                            L.e("qlmobile", "decode--->" + head.ChildType
                                    + ", pageId = " + mPageId + ", head.PageID = " + head.PageID);
                            break;
                        }
                        mMyApp.mSort_Response = CMobileProt.Analy_17(expanddata, datasize, mMyApp.getStockDataList());
                        //通过PageId区分是否为自营版请求数据
                        if (EventBus.getDefault() != null && head.PageID == Global_Define.FUNC_ZY_STOCK_SORT) {
                            EventBus.getDefault().post(new ResponseEvent(head.RequestCode, head.MainType, head.ChildType, head.PageID, mMsgId, mMyApp.getStockDataList()));
                            L.d(TAG, "EventBus posddata 145.17--->head.RequestCode= " + head.RequestCode + ", head.PageID = " + head.PageID
                                    + ", data.size = " + mMyApp.getStockDataList().size());
                            isUpdate = false;
                            break;
                        } else {
                            isUpdate = true;
                            break;
                        }
                }
            }
        }

        expanddata = null;
        return packagesize + MC_FrameHead_LEN;
    }

    /**
     * 解析错误
     *
     * @param head
     * @param expanddata
     * @param packagesize
     * @return
     */
    private int decodeError(MC_FrameHead head, byte[] expanddata, int packagesize) {
        //返回错误
        if (head.ErrorFlag == 1) {
            System.out.println("[" + head.MainType + ", " + head.ChildType + "], head.ErrorFlag == 1!!!");
            mChildType = head.ChildType;
            mMainType = head.MainType;

            if (head.ErrorCode == Global_Define.ERROR_CODE_CHECK_TOKEN_FAILED //非同一台终端
                    || head.ErrorCode == Global_Define.ERROR_CODE_YOUKE_LOGIN)    //价格提醒权限申请时账号未登录
            {
                mMsgId = MSG_RET_ERROR;
                mMsgObject = STD.strcpy(expanddata, 0, packagesize);
                mArg2 = head.ErrorCode;
                System.out.println("MSG_RET_ERROR--->" + mMsgObject);
                isUpdate = true;
            } else {
                mArg2 = mMainType;
            }

            //认证失败，把用户名复位最后一次登录成功的那个用户名
            if (head.MainType == 144 && (head.ChildType == 5 || head.ChildType == 6)) {
                mMyApp.mUser = mMyApp.mUser_Last;
            }

            //登录失败 关闭连接
            if (head.MainType == 144 && (head.ChildType == 0 || head.ChildType == 7)) {
                //
                global_net_class.closeConnect();
                //mMyApp.mLoginFlag = true;
                //mIsReconnect=true;//登录不上，重新链接
            }

            //雷达为空的错误不处理
            if (head.MainType == 145 && (head.ChildType == 80 || head.ChildType == 81 || head.ChildType == 96)) {
                mMsgId = MSG_UPDATE_DATA;
                mMsgObject = "";
                isUpdate = true;
            } else {
                mMsgId = MSG_RET_ERROR;
                mMsgObject = STD.strcpy(expanddata, 0, packagesize);
                System.out.println("MSG_RET_ERROR--->" + mMsgObject);
                isUpdate = true;
            }

            //自选股下载失败
            if (head.MainType == 145 && head.ChildType == 52) {

                System.out.println("MyStock Download Error... " + mMsgObject);
                //启动定时下载
                mMyApp.startDownloadTimer();
                isUpdate = false;
            }

            expanddata = null;
            return packagesize + MC_FrameHead_LEN;
        }
        return 0;
    }


    /**
     * @param packagesize
     * @param head
     * @param data
     * @param expanddata
     * @return 返回datasize 如果解压不成功返回负数，解压成功为正数，没有解压返回0
     */
    private int decodeExpandData(int packagesize, MC_FrameHead head, byte[] data, byte[] expanddata) {
        if (head.zip == 1) {
            byte[] zipdata = new byte[packagesize + 100];
            System.arraycopy(data, MC_FrameHead_LEN, zipdata, 0, packagesize);
            int ret = mLZW8192.ExpandBuf(zipdata, packagesize, expanddata, MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE);
            zipdata = null;
            if (ret <= 0) {
                System.out.println("ExpandBuf Error!!!");
                expanddata = null;
                return -12;
            }
            return ret;
        } else if (head.zip != 0) {       //不支持的压缩方式

            System.out.println("head.zip not support...");
            expanddata = null;
            return -13;
        } else {
            /**
             * 如果没有压缩 将expanddata赋值为data
             */
            System.arraycopy(data, MC_FrameHead_LEN, expanddata, 0, packagesize);
            return 0;
        }

    }

    /**
     * 解密数据包
     *
     * @param packagesize
     * @param head
     * @param data
     * @return datasize
     */
    private int decodeCrypt(int packagesize, MC_FrameHead head, byte[] data) {
        int offset = 0;//初始化偏移量
        offset += MC_FrameHead_LEN;
        //解密
        int datasize = packagesize;
        if (head.crypt == 1) {
            datasize = CDataEncrypt.Decrypt(data, offset, datasize,
                    data, offset, datasize, CDataEncrypt.HQ_DEFAULT_KEY);
            if (datasize < 0) {
                //解密错误
                System.out.println("Decrypt Error!!! " + datasize);
                return datasize;
            }
        } else if (head.crypt != 0) {    //不支持的加密方式
            System.out.println("head.crypt not support...");
            return -11;
        }
        return datasize;
    }

    /**
     * 解析头部
     *
     * @param data
     * @param size
     * @param head
     * @return
     */
    private int decodeHeader(byte[] data, int size, MC_FrameHead head) {
        /**
         * 1.判断头部大小是否满足
         * 如果不满足返回0
         */
        if (size < MC_FrameHead_LEN) {
            System.out.println("size < MC_FrameHead_LEN!");
            return 0;
        }
        /**
         * 2.判断数据包完整性
         * 大于0时表示是个完整包，小于0时数据有错误，0时还不是完整包
         */
        int ret = CMobileProt.CheckData(data, size, head);
        if (ret <= 0) {
            if (ret < 0) {
                System.out.println("CheckData Error!!! " + ret);
            }
            return ret;
        }
        /**
         * 3.多包解析
         */
        boolean bMultiPackage = false;
        if (head.PackageNum > 1 && head.PackageNo > 0) {
            System.out.println("Multi-Package! Num = " + head.PackageNum + ", No = " + head.PackageNo);
            bMultiPackage = true;
        }

        System.out.println("2.recieve -- MainType: " + head.MainType + " ChildType: " + head.ChildType);

        /**
         * 4.sessionID的判断 如果不是当前回话 结束返回
         */
        if (global_net_class.mSessionID == 0) {
            global_net_class.mSessionID = head.SessionID;
        } else if (global_net_class.mSessionID != head.SessionID) {
            System.out.println("SessionID Error!!! ----" + global_net_class.mSessionID + " ===== " + head.SessionID);
            return -10;
        }
        return ret;
    }
}
