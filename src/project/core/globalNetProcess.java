package project.core;

import project.utils.Global_Define;
import project.beans.MC_FrameHead;

/**
 * 请求层
 * 请求的套路:
 * step1:初始化
 * Created by Administrator on 2017/2/15.
 */
public class globalNetProcess {
    /**
     * 请求排行列表
     */
    public static int RequestSortList(global_net_class netClass, int market, int style, int sorttype, int startpos, int num) {
        return RequestSortList(netClass, market, style, sorttype, startpos, num, Global_Define.FUNC_SORT);
    }

    public static int RequestSortList(global_net_class netClass, int market, int style, int sorttype, int startpos, int num, int pageid) {
        byte[] data = new byte[MC_FrameHead.MAX_MOBILE_PACKAGE_SIZE];
        int size = 0;

        //市场、代码、现价、涨跌、金额、量、换手
        //股票名称、类别、昨收、价格倍数
//        byte push[] = netClass.mMyApp.hq_cfg_reqids;
//        byte fix[] = netClass.mMyApp.hq_cfg_reqids_fix;
        byte push[] =
                {
                        Global_Define.FIELD_HQ_MARKET,
                        Global_Define.FIELD_HQ_CODE,
                        Global_Define.FIELD_HQ_NAME,
                        Global_Define.FIELD_HQ_STOCKTYPE,
                        Global_Define.FIELD_HQ_NOW,
                        Global_Define.FIELD_HQ_YESTERDAY,
                        Global_Define.FIELD_HQ_ZD,
                        Global_Define.FIELD_HQ_UNITANDDOT,
                        Global_Define.FIELD_HQ_AMOUNT,
                        Global_Define.FIELD_HQ_VOLUME,
                        Global_Define.FIELD_HQ_HSL,
                        Global_Define.FIELD_HQ_ZF
                };
        byte fix[] = {};

        netClass.mPageId = pageid;
        size = CMobileProt.MakeRequest_17(netClass.mSessionID, netClass.mRequestCode++, netClass.mPageId, market, style, sorttype, startpos, num,
                fix.length, fix, push.length, push, data, 0);

        /**
         *  data相当于引用数据类型即一个类 所以 在上面的请求操作中，data进行了赋值
         */
        netClass.addSendData(data, 0, size);

        return 0;
    }
}
