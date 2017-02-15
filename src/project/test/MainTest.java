package project.test;

import project.core.globalNetProcess;
import project.core.global_net_class;

/**
 * Created by Administrator on 2017/2/10.
 */
public class MainTest {
    public static void main(String[] args) {
        global_net_class globalnetclass = new global_net_class();
        int market = 3;
        int stocktype = 1;
        int sorttype = 1;
        int startpos = 0;
        int num = 20;
        globalnetclass.mRequestCode = 4;
        globalnetclass.mPageId = 17;
        //请求一个行情
        globalNetProcess.RequestSortList(globalnetclass, market, stocktype, sorttype, startpos, num);

    }
}
