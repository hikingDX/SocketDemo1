package project.test;

import project.core.globalNetProcess;
import project.core.global_net_class;

/**
 * Created by Administrator on 2017/2/10.
 */
public class MainTest {
    public static void main(String[] args) {
        global_net_class globalnetclass = new global_net_class();
        globalnetclass.mAddrConnect = new String[]{"221.204.230.104:20000"};
        globalnetclass.mAddrConnectNum=1;
        int market = 3;
        int stocktype = 1;
        int sorttype = 1;//涨跌幅排序
        int startpos = 0;
        int num = 20;
        globalnetclass.mRequestCode = 4;
        globalnetclass.mPageId = 17;
        //请求一个行情
        globalNetProcess.RequestSortList(globalnetclass, market, stocktype, sorttype, startpos, num);

    }
}
