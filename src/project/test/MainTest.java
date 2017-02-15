package project.test;

import project.core.globalNetProcess;
import project.core.global_net_class;

/**
 * Created by Administrator on 2017/2/10.
 */
public class MainTest {
    public static void main(String[] args) {
        global_net_class global_net_class = new global_net_class(1);
        int market = 3;
        int stocktype = 1;
        int sorttype = 1;
        int startpos = 0;
        int num = 20;
        //请求一个行情
        globalNetProcess.RequestSortList(global_net_class, market, stocktype, sorttype, startpos, num);

    }
}
