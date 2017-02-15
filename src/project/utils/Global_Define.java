package project.utils;

public class Global_Define {
    public static final int HQ_CODE_LEN = 8;
    public static final int HQ_NAME_LEN = 8;        //指8个字符，16个字节
    public static final int HQ_TRADE_TIME_NUM = 8;
    // by ngj add
    public static final int HQ_LAST_RADAR_TITLE_S_LEN = 20;    //
    public static final int MAX_MOBILE_PACKAGE_SIZE = 8192;    //通讯包大小
    public static final int HQ_UNSIGNED_LEN = 40;    //指令24中的“未使用”
    //
    public static final int F10_MAX_PACKAGE_SIZE = 3900;    //F10最大包大小
    // 添加自选股消息
    public static final int MSG_ADD_STOCK = 80;
    public static final int MSG_SHOW_STOCKNAV = 81;
    public static final int MSG_SHOW_TIP_ZHULI = 82;

    //菜单
    public static final int
//            MENU_HOME = 1,    //首页
            MENU_MYSTOCK = 2,    //自选
            MENU_SORT = 3,    //排行
            MENU_INDEX = 4,    //指数
            MENU_ZHULI = 5,    //主力
            MENU_TRADE = 6,    //交易
            MENU_FUND = 7,    //基金
            MENU_NEWS = 8,    //资讯		unused
            MENU_ZHPM = 9,    //综合排名	unused
            MENU_BOARD = 10,    //板块		unused
            MENU_SETTING = 11,    //设置		unused
            MENU_HK = 12,    //港股		unused 2.0
            MENU_MORE = 13,    //更多		unused 2.0
            MENU_MAIL = 14,   //邮件
            MENU_MINE = 15,  //我的
            MENU_HOMEPAGE = 16;  //自营版首页

    //功能定义
    public static final int
            FUNC_SEARCH = 11,    //代码表
            FUNC_MYSTOCK = 12,    //自选股
            FUNC_TREND = 13,    //走势
            FUNC_DETAIL = 14,    //明细
            FUNC_KLINE = 15,    //K线
            FUNC_SORT = 17,    //排行
            FUNC_BOARD = 18,    //板块
            FUNC_BOARD_SORT = 19,    //板块个股排行
            FUNC_INDEX = 21,    //指数
            FUNC_TRADE_HQ = 36,    //交易中取行情,没有推送
            FUNC_RADARLIST = 81,    //雷达标题
            FUNC_RADARCONTENT = 82,    //雷达内容
            FUNC_RADARMULLIST = 96,    //雷达标题
            FUNC_ZHULI_SORT = 100,    //主力排行
            FUNC_FSZL = 101,    //分时主力
            FUNC_TOPWIN = 102,    //TOPWIN
            FUNC_GOLDEN = 103,    //龙虎榜
            FUNC_MYSTOCK_ZHULI = 110,    //自选股主力
            FUNC_PRICEALARM = 120,    //消息提醒

    FUNC_ZY_STOCK_SORT = 66,    //自营版，行情主界面
            FUNC_ZY_STOCK_LANDSCAPE = 67,//自营版，横屏
            FUNC_ZY_STOCK_INFO = 68, //自营版，个股竖屏 StockInfo
            FUNC_ZY_WARN = 69;  //自营版，预警界面
    //二级菜单
    public static final int
            SUBTITLE_HS_ZS = 0,    //沪深指数
            SUBTITLE_HS_A = 1,    //沪深A股
            SUBTITLE_HS_B = 2,    //沪深B股
            SUBTITLE_SH_A = 3,    //上证A股
            SUBTITLE_SH_B = 4,    //上证B股
            SUBTITLE_SH_JJ = 5,    //上证基金
            SUBTITLE_SH_ZQ = 6,    //上证债券
            SUBTITLE_SZ_A = 7,    //深证A股
            SUBTITLE_SZ_B = 8,    //深证B股
            SUBTITLE_SZ_JJ = 9,    //深证基金
            SUBTITLE_SZ_ZQ = 10,    //深证债券
            SUBTITLE_ZXB = 11,    //中小板
            SUBTITLE_CYB = 12,    //创业板
            SUBTITLE_GFZR = 13,    //股份转让
            SUBTITLE_HK_ZS = 14,    //香港指数
            SUBTITLE_HK_ZB = 15,    //香港主板
            SUBTITLE_HK_CY = 16,    //香港创业
            SUBTITLE_HK_WL = 17,    //香港窝轮
            SUBTITLE_HK_NX = 18,    //香港牛熊
            SUBTITLE_HK_GQ = 19,    //香港国企
            SUBTITLE_HK_HC = 20,    //香港红筹

    SUBTITLE_BH = 21,    //B转H股
            SUBTITLE_FXJS = 22,    //风险警示
            SUBTITLE_TSZL = 23,    //退市整理


    SUBTITLE_QH_GZQH = 30,    //股指期货
            SUBTITLE_QH_SH = 31,    //上海商品
            SUBTITLE_QH_DL = 32,    //大连商品
            SUBTITLE_QH_ZZ = 33,    //郑州商品
            SUBTITLE_QH_LDJS = 34,    //伦敦金属
            SUBTITLE_QH_ZJGG = 35,    //芝加哥谷
            SUBTITLE_QH_NYSP = 36,    //纽约商品
            SUBTITLE_QH_NYSY = 37,    //纽约商业
            SUBTITLE_QH_LDSY = 38,    //伦敦石油
            SUBTITLE_QH_NYQH = 39,    //纽约期货
            SUBTITLE_QH_GJS = 40,    //贵金属
            SUBTITLE_QQ_ZS = 41,    //全球指数
            SUBTITLE_QQ_WH = 42,    //国际外汇
            SUBTITLE_QH_GUOZHAIQH = 43,    //国债期货


    SUBTITLE_US_NJS = 44,    //美股纽交所
            SUBTITLE_US_NSDK = 45,    //美股纳斯达克
            SUBTITLE_US_MJS = 46,    //美股美交所
            SUBTITLE_US_MGZS = 47,    //美股指数

    //快捷方式
    SUBTITLE_SYSTEM = 10000,  //系统
            SUBTITLE_LOGIN = 10001,  //登录注销
            SUBTITLE_SQZH = 10002,  //申请账号
            SUBTITLE_QSXX = 10003,  //券商信息
            SUBTITLE_JGTX = 10004;  //价格提醒

    // 股票市场
    public static final int
            MARKET_NONE = 0,        //未知
            MARKET_SH = 1,        //上海
            MARKET_SZ = 2,        //深圳
            MARKET_HK = 3,        //香港
            MARKET_CF = 4,        //股指期货
            MARKET_FX = 5,        //外汇
            MARKET_IC = 6,        //国外期货
            MARKET_QH = 7,        //商品期货
            MARKET_ZB = 8,        //周边市场
            MARKET_BZ = 9,        //板块指数
            MARKET_ZS = 10,        //国际指数

    MARKET_XJP = 11,        //11	新加坡期货	为兼容3x定义
            MARKET_HK_XPQH = 12,        //香港商品期货	为兼容3x定义
            MARKET_HK_LLQH = 13,        //香港利率期货	为兼容3x定义
            MARKET_HK_GZQH = 14,        //香港股指期货	为兼容3x定义，已有数据
            MARKET_HK_GPQH = 15,        //香港股票期货	为兼容3x定义，已有数据
            MARKET_US = 16,        //美股市场

    MARKET_SB = 20,        //三板

    MARKET_SZ_SH = 100,        //沪深市场
            MARKET_TS = 101,        //特殊市场

    MARKET_BK_INDEX = 128,    //指数板块
            MARKET_BK_ZXG = 129;    //自选股板块

    // 股票类别
    public static final int
            STOCK_TYPE_NONE = 0,    //未知
            STOCK_TYPE_INDEX = 1,    //指数
            STOCK_TYPE_A = 2,    //A股
            STOCK_TYPE_B = 3,    //B股
            STOCK_TYPE_ZXQY = 4,    //中小企业
            STOCK_TYPE_FUND = 5,    //基金
            STOCK_TYPE_BOND = 6,    //债券
            STOCK_TYPE_QZ = 7,    //权证
            STOCK_TYPE_CYB = 8,    //创业板
            STOCK_TYPE_SB = 9,    //三板

    STOCK_TYPE_HK_MAIN = 10,    //香港主板
            STOCK_TYPE_HK_CY = 11,    //香港创业
            STOCK_TYPE_HK_MAIN_WARRANTS = 12,    //主板认购权证(窝轮)
            STOCK_TYPE_HK_CY_WARRANTS = 13,    //创业版认购权证
            STOCK_TYPE_HK_CBBC = 14,    //牛熊证
            STOCK_TYPE_HK_FUND = 15,    //香港基金
            STOCK_TYPE_HK_BOND = 16,    //香港债券
            STOCK_TYPE_HK_SYZQ = 17,    //试验证券

    STOCK_TYPE_SH_FXJS = 18,    //风险警示
            STOCK_TYPE_SZ_TSZL = 19,    //退市整理
            STOCK_TYPE_SZ_BZH = 20,    //B转H股

    STOCK_TYPE_GZ_ZJQZ = 40,    //股指期货 1：中金期指
            STOCK_TYPE_GZ_GZQH = 41,    //股指期货	41：国债期货

    STOCK_TYPE_WH_ALL = 90,    //外汇 	90：所有汇率(基本汇率+交叉汇率) 91：基本汇率 92：交叉汇率
            STOCK_TYPE_WH_JBHL = 91,
            STOCK_TYPE_WH_JXHL = 92,

    STOCK_TYPE_GWSP_LDJS = 71,    //国外商品期货	71：伦敦金属72：芝加哥谷73：纽约商品74：纽约商业75：伦敦石油76：纽约期货77：贵金属
            STOCK_TYPE_GWSP_ZJGG = 72,
            STOCK_TYPE_GWSP_NYSP = 73,
            STOCK_TYPE_GWSP_NYSY = 74,
            STOCK_TYPE_GWSP_LDSY = 75,
            STOCK_TYPE_GWSP_NYQH = 76,
            STOCK_TYPE_GWSP_GJS = 77,

    STOCK_TYPE_GNSP_SHSZ = 51,    //国内商品期货	51：上海商指 52：上海期货 53：大连商指 54：大连商品55：郑州商指 56：郑州商品
            STOCK_TYPE_GNSP_SHQH = 52,
            STOCK_TYPE_GNSP_DLSZ = 53,
            STOCK_TYPE_GNSP_DLSP = 54,
            STOCK_TYPE_GNSP_ZZSZ = 55,
            STOCK_TYPE_GNSP_ZZSP = 56,
            STOCK_TYPE_GNSP_SHALL = 57,
            STOCK_TYPE_GNSP_DLALL = 58,
            STOCK_TYPE_GNSP_ZZALL = 59,

    US_NJS = 100,    //纽交所
            US_NSDK = 101,    //纳斯达克
            US_MJS = 102,    //美交所

    STOCK_TYPE_HQZS_ZS = 1,    //国际指数(环球指数)	1：环球指数

    STOCK_TYPE_OTHERS = 127;    //其它

    //板块指数的类别
    public static final int
            BZ_TYPE_ALL = 0,    //所有板块
            BZ_TYPE_YYBK = 1,    //行业板块
            BZ_TYPE_DYBK = 2,    //地域板块
            BZ_TYPE_GNBK = 3;    //概念板块

    //特殊市场的类别
    public static final int
            TS_TYPE_ZXG = 1,    //自选股
            TS_TYPE_DPZS = 2;    //大盘指数

    //145_05，股票字典字段定义
    public static final int
            FIELD_DICT_NAME_UNICODE = 1,        //股票名称，WCHAR(8)
            FIELD_DICT_NAME = 2,        //股票名称，CHAR(8)
            FIELD_DICT_CODE = 3,        //股票代码，CHAR(8)
            FIELD_DICT_CODE_INT_S = 4,        //股票代码，INT32，存储与上一记录的之差，第一个记录为原值
            FIELD_DICT_STOCKTYPE = 5,        //股票类别，UINT8
            FIELD_DICT_NAME_UNICODE_HK_8 = 6;//股票名称，支持香港八个汉字

    //145_10，行情字段定义
    //基本
    public static final int
            FIELD_HQ_YESTERDAY = 1,        //昨收价
            FIELD_HQ_OPEN = 2,        //开盘价
            FIELD_HQ_HIGH = 3,        //最高价
            FIELD_HQ_LOW = 4,        //最低价
            FIELD_HQ_NOW = 5,        //最新价
            FIELD_HQ_VOLUME = 6,        //总量
            FIELD_HQ_AMOUNT = 7,        //总额
            FIELD_HQ_CURVOL = 8,        //现量
            FIELD_HQ_MARKET = 9,        //市场
            FIELD_HQ_CODE = 10,        //代码
            FIELD_HQ_STOCKTYPE = 11,        //类别
            FIELD_HQ_NAME = 12,        //名称
            FIELD_HQ_NAME_HK_8 = 30,        //支持香港八个汉字
            FIELD_HQ_FLAG = 13,        //雷达标志，第0位
            FIELD_HQ_LB = 14,        //量比
            FIELD_HQ_HSL = 15,        //换手率
            FIELD_HQ_SYL = 16,        //市盈率
            FIELD_HQ_ZD = 17,        //涨跌，放大10000倍，由服务器算好，如果客户端没有要求这个字段，则应该自己计算，在早上竞价阶段，价格涨跌根据买一卖一价来计算
            FIELD_HQ_AVERAGE = 18,        //均价，放大10000倍，客户端直接使用这个均价，而不需自己计算
            FIELD_HQ_UNITANDDOT = 19,        //每股手数和价格位数
            FIELD_HQ_TRADETIMENUM = 20,        //交易段数及时间
            FIELD_HQ_HQTIME = 21,        //行情时间
            FIELD_HQ_NAME_ANSI = 22,        //股票名称
            FIELD_HQ_ZF = 23,        //涨幅，放大10000倍

    FIELD_HQ_ZHENFU = 24,        //振幅	放大10000倍
            FIELD_HQ_SJL = 25,        //市净率	放大10000倍

    FIELD_HQ_5ZF = 26,        //五分钟涨幅 放大10000倍

    FIELD_HQ_MMPFLAG = 27,        //内外盘标志 第0、1位表示内外盘标志，0 外盘、1内盘、2平盘，注意：其他无效
            FIELD_HQ_CJBS = 28,        //成交笔数

    FIELD_HQ_HQTIME_HAVE_SECOND = 29,    //行情时间，带秒 低4字节：YYYYMMDD 高4字段：HHMMSS
            FIELD_HQ_XSWS = 31,       //价格显示位数

    //指数
    FIELD_HQ_INDEX_WB = 40,        //委比
            FIELD_HQ_INDEX_WC = 41,        //委差
            FIELD_HQ_INDEX_UP = 42,        //上涨家数
            FIELD_HQ_INDEX_SAME = 43,        //平盘家数
            FIELD_HQ_INDEX_DOWN = 44,        //下跌家数

    FIELD_HQ_INDEX_RATE_UP = 45,        //上涨家数比 上涨家数/总家数 放大10000倍
            FIELD_HQ_INDEX_QRD = 46,        //强弱度 放大1000倍
            FIELD_HQ_INDEX_HLZ = 47,        //大盘红绿柱

    //个股
    FIELD_HQ_BVOLUME1 = 60,        //买一量	单位股
            FIELD_HQ_SVOLUME1 = 61,        //卖一量	单位股
            FIELD_HQ_INVOLUME = 62,        //内盘总量	单位股
    /**
     * 新增VCM和CAS状态
     * by rzy 2016/9/26 11:25
     **/
    FIELD_HQ_TAGHKVCM = 63,//vcm状态
            FIELD_HQ_TAGHKCAS = 64,//cas状态

    FIELD_HQ_UPPRICE = 70,        //涨跌停
            FIELD_HQ_DOWNPRICE = 71,        //涨跌停
            FIELD_HQ_BUYPRICE = 72,        //委买价
            FIELD_HQ_SELLPRICE = 73,        //委卖价
            FIELD_HQ_BPRICE1 = FIELD_HQ_BUYPRICE,    //委买价
            FIELD_HQ_SPRICE1 = FIELD_HQ_SELLPRICE,    //委卖价
            FIELD_HQ_BS5 = 74,        //委买盘 BuySell5
            FIELD_HQ_OUTVOLUME = 75,        //外盘总量
            FIELD_HQ_L2_BS10 = 76,        //十档买卖盘 BuySell10
            FIELD_HQ_L2_VBSVOLUME = 77,        //委买委卖总量信息 StockBuySell[2]
            FIELD_HQ_L2_BSQUEUE = 78,        //买卖队列信息BuySellQueue[2]

    FIELD_HQ_HK_BSBROKER = 79,        //买经纪排位数据 + 卖经纪排位数据

    //财务
    FIELD_HQ_LTGB = 80,        //流通A股
            FIELD_HQ_YCMGSY = 81,        //预测每股收益，放大10000倍
            FIELD_HQ_AVGVOL5 = 82,        //五日均量
            FIELD_HQ_ZGB = 83,        //总股本

    FIELD_HQ_LTSZ = 84,        //流通市值
            FIELD_HQ_ZSZ = 85,        //总市值

    FIELD_HQ_LTBG = 86,        //INT64	流通B股	单位股
            FIELD_HQ_MGZZC = 87,        //INT32	每股净资产	放大10000倍
            FIELD_HQ_ZZCSYL = 88,        //INT32	净资产收益率	放大10000倍:每股收益/每股净资产*100%
            FIELD_HQ_ZCFZL = 89,        //INT32	资产负债率	放大10000倍
            FIELD_HQ_ZYSR = 90,        //INT64	主营收入	单位分，即放大100倍
            FIELD_HQ_ZLR = 91,        //INT64	净利润	单位分，即放大100倍
            FIELD_HQ_ZZC = 92,        //INT64	总资产	单位分，即放大100倍
            FIELD_HQ_ZXMGSY = 93,        //INT32	最新每股收益	放大10000倍

    //黄金眼数据
    FIELD_HQ_STATUNIT_COUNT = 95,    //龙虎数据：成交笔数，四买(庄大中小)+四卖(庄大中小)
            FIELD_HQ_STATUNIT_VOLUME = 96,    //龙虎数据：成交量	四买(庄大中小)+四卖(庄大中小)
            FIELD_HQ_STATUNIT_AMOUNT = 97,    //龙虎数据：成交金额	四买(庄大中小)+四卖(庄大中小)，金额放大100倍
            FILED_HQ_STATUNIT_AMOUNT_MMC = 98,    //龙虎数据：买卖成交金额差	四个（买-卖）（庄大中小），金额放大100倍
            FIELD_HQ_STATUNIT_MAINAMOUNTB5 = 99,    //99	INT64	龙虎数据：5日均主力买额	金额放大100倍，不变化
            FIELD_HQ_STATUNIT_MAINAMOUNTS5 = 100,    //100	INT64	龙虎数据：5日均主力卖额	金额放大100倍，不变化
            FIELD_HQ_STATUNIT_MMQ = 101,    //101	INT32	买卖气	算法：(主力买量-主力卖量)÷总(统计)成交量*100,	存储值：放大10000倍
            FIELD_HQ_STATUNIT_ZLZJ = 102,    //主力增减	当日DDX值，算法：(主力买量 - 主力卖量)/流通盘)*100,存储：放大10000倍,格式：3位小数，正数为红色、负数为绿色、0或杠为白色
            FIELD_HQ_STATUNIT_5RZJ = 103,    //103	INT32	5日增减	最近5日DDX值之和，其它同上
            FIELD_HQ_STATUNIT_10RZJ = 104,    //104	INT32	10日增减
            FIELD_HQ_STATUNIT_20RZJ = 105,    //105	INT32	20日增减
            FIELD_HQ_STATUNIT_10RNH = 106,    //106	INT8	10日内红	DDX技术指标中DDX结果域在10日内为正的天数(包括当天)。当日之前的数据可由服务器统计。
            FIELD_HQ_STATUNIT_ZLLH = 107,    //107	INT16	主力连红	DDX技术指标中的DDX结果域连续为正/负的天数(包括当天)，连续为正天数为正数，连续为负的天数为负数，请注意不可能出现0。当日之前的数据可由服务器统计后直接给客户端。

    FIELD_HQ_STATUNIT_MAINAMOUNT = 108,    //INT64*2	主力买额+主力卖额	金额放大100倍，即单位为分
            FIELD_HQ_STATUNIT_MAINVOLUME = 109,    //INT64*2	主力买量+主力卖量	单位股

    FIELD_HQ_STATUNIT_FLCCBL = 110,    //(INT16+INT16)*(4)	龙虎数据：分类持仓比例	四个（持仓比例+持仓增减）（庄大中小）

    FIELD_HQ_LAST_RADAR_DATETIME = 130,    //最新雷达信息的日期+时间,INT32*2,YYYYMMDD+HHMMSS，客户端可根据本值来判断雷达是否有更新，目前只判断当日雷达
            FIELD_HQ_LAST_RADAR_ID = 131,    //最新雷达信息的编码,INT64
            FIELD_HQ_LAST_RADAR_TITLE_S = 132,    //简化的雷达标题，20个字符(WCHAR)

    FIELD_HQ_JRCCS = 160,    //今日持仓+今日结算价+昨日持仓量+持仓性质
            FIELD_HQ_KPC = 161,    //开仓+平仓
            FIELD_HQ_XKP = 162,    //现开+平开
            FIELD_HQ_ZRJSJ = 163,    //昨日结算价

    FIELD_HQ_MAINCC = 200,    //用于技术指标中的主力持仓指标
    //主力持仓：沪深((至T-1日主力净买量的累加值+T日主力净买)/流通盘+1) *100*e
    //香港((至T-1日主力净买量的累加值+T日主力净买*10)/流通盘+1)*100*e
    //存储值放大100倍

    //
    FIELD_LOCAL_HQDATE = 401,    //行情日期
            FIELD_LOCAL_HQTIME = 402,    //行情时间

    //股指期货
    FIELD_LOCAL_CHICANG = 501, // 持仓
            FIELD_LOCAL_CANGCHA = 502, // 仓差
            FIELD_LOCAL_JIESUAN = 503, // 结算
            FIELD_LOCAL_ZUOCHI = 504, // 昨持
            FIELD_LOCAL_KAICANG = 505, // 开仓
            FIELD_LOCAL_PINGCANG = 506, // 平仓
            FIELD_LOCAL_XIANKAI = 507, // 现开
            FIELD_LOCAL_XIANPING = 508; // 现平


    //145_13，走势图数据字段定义
    public static final int
            FIELD_TREND_PRICE = 1,        //价格
            FIELD_TREND_AVERAGE = 2,        //均价
            FIELD_TREND_VOLUME = 3,        //当前分钟成交量
            FIELD_TREND_AMOUNT = 4,        //当前分钟成交金额
            FIELD_TREND_LB = 5,        //量比*10000

    FIELD_TREND_XJC = 6,        //现价差 放大价格倍数，返回与上一分钟价格之差，第0分钟为原值
            FIELD_TREND_JJC = 7,        //均价差 放大10000倍，返回与上一分钟均价之差，第0分钟为原值
            FIELD_TREND_LBC = 8,        //量比差 放大10000倍，返回与上一分钟量比之差，第0分钟为原值

    FIELD_TREND_VOLUME_S = 9,        //当前分钟成交量，个股时单位为手，指数时单位为千手
            FIELD_TREND_AMOUNT_S = 10,        //当前分钟成交金额,个股时单位为百元，指数时单位为千元

    FIELD_TREND_VBUYVOLUME_S = 14,    //委买总量单位手，存储与上一笔之差
            FIELD_TREND_VSELLVOLUME_S = 15,    //委卖总量单位手，存储与上一笔之差
            FIELD_TREND_DDX_S = 16,    //累计主力动向,DDX: (主力买量 - 主力卖量)/流通盘)*100 存储值放大100倍，即最大值为一万，存储值为与上一笔之差
            FIELD_TREND_BUYAMOUNTRATE_S = 17,    //买资金流速	累计至当前分钟的 主力买额 / (过去5日的平均日主力买额*开盘后分钟数/240)	存储值放大100，存储值为与上一笔之差
            FIELD_TREND_SELLAMOUNTRATE_S = 18,    //卖资金流速	累计至当前分钟的 主力卖额 / (过去5日的平均日主力卖额*开盘后分钟数/240)	存储值放大100，存储值为与上一笔之差

    FIELD_TREND_BBD_S = 19,    //BBD：至当前分钟为止 ∑个股当日主力净买额的累加值，个股时单位为百元，指数时单位为千元
    //只有指数有效， 个股目前无效
    //存储值为与上一笔之差

    FIELD_TREND_TIME_C = 20,    //时间差，时*60+分，存储与上一分钟之差(一般为1)，但每个包第一个分钟数据的时间为原值

    FIELD_TREND_HLZ_C = 21,    //大盘红绿柱，INT16，存储与上一分钟之差

    FIELD_TREND_CC_C = 22,    //持仓量	单位股，存储与上一笔之差
            FIELD_TREND_KC = 23,    //开仓	单位股
            FIELD_TREND_PC = 24;    //平仓	单位股

    //145_15，K线数据字段
    public static final int
            FIELD_KDATA_TIME = 1,        //日期，MHC_KDataTime
            FIELD_KDATA_OPEN = 2,        //开盘价
            FIELD_KDATA_HIGH = 3,        //最高价
            FIELD_KDATA_LOW = 4,        //最低价
            FIELD_KDATA_CLOSE = 5,        //收盘价
            FIELD_KDATA_VOLUME = 6,        //成交量
            FIELD_KDATA_AMOUNT = 7,        //成交金额*100

    FIELD_KDATA_MAINBVOL = 8,        //主力买量，单位股
            FIELD_KDATA_MAINSVOL = 9,        //主力卖量，单位股
            FIELD_KDATA_MAINCC = 10,        //主力持仓，*100

    FIELD_KDATA_TIME_S = 11,        //日期，MHC_KDataTime，存储与上一笔之差
            FIELD_KDATA_OPEN_S = 12,        //开盘价，存储与上一笔之差
            FIELD_KDATA_HIGH_S = 13,        //最高价，存储与上一笔之差
            FIELD_KDATA_LOW_S = 14,        //最低价，存储与上一笔之差
            FIELD_KDATA_CLOSE_S = 15,        //收盘价，存储与上一笔之差
            FIELD_KDATA_VOLUME_S = 16,        //成交量，单位手
            FIELD_KDATA_AMOUNT_S = 17,        //成交金额，单位元

    FIELD_KDATA_MAINBVOL_S = 18,        //主力买量，单位手
            FIELD_KDATA_MAINSVOL_S = 19,        //主力卖量，单位手
            FIELD_KDATA_MAINCC_S = 20,        //主力持仓，*100，存储与上一笔之差

    //FIELD_KDATA_MAINBSC_S	= 21,		//主力净额=主力买额-主力卖额，单位元
    FIELD_KDATA_MAINHYD_S = 22,        //主力活跃度(主力买入量+主力卖出量) / (总成交量*2)，放大10000倍，差值
            FIELD_KDATA_MMQ_S = 23,        //买卖气，当前周期的 (主力买入量 - 主力卖出量) / 总成交量*100
    //存储放大100倍，与上一笔差值
    FIELD_KDATA_DDX = 24,        //DDX：沪深 (当前周期的 (主力买量 - 主力卖量)/流通盘)*100
    //存储放大100倍

    FIELD_KDATA_VOLUME_F = 25,        //成交量，单位股，float
            FIELD_KDATA_AMOUNT_F = 26,        //成交金额，单位分,float
            FIELD_KDATA_AVERAGE_S = 27,        //均价

    FIELD_KDATA_CC_C = 28,    //持仓量	单位股,存储与上一笔之差
            FIELD_KDATA_JSJ = 29,    //结算价	*价格倍数
            FIELD_KDATA_QRBZ = 30;   //强弱标志  (黄金阶梯）(> 0 强)  (==0 正常) (< 0弱)

    //145_16，权息字段定义
    public static final int
            FIELD_WEIGHT_TIME = 1,        //资料日期 YYYYMMDD
            FIELD_WEIGHT_SGS = 2,        //每10000股送股数
            FIELD_WEIGHT_PGS = 3,        //每10000股配股数
            FIELD_WEIGHT_PGJ = 4,        //配股价
            FIELD_WEIGHT_HL = 5,        //每10000股红利
            FIELD_WEIGHT_ZGS = 6,        //每10000股转增股数
            FIELD_WEIGHT_ZGB = 7,        //总股本
            FIELD_WEIGHT_QXRZSJ = 8,        //权息日昨收价
            FIELD_WEIGHT_QYRSPJ = 9,        //权息日前一天收盘价
            FIELD_WEIGHT_LTGB = 10;        //流通股本

    //145_14，明细字段定义
    public static final int
            FIELD_TICK_TIME = 1,         //时间，hour*3600 + minute*60 + second，与上一笔时间之差
            FIELD_TICK_PRICE = 2,        //价格，与上一笔价格之差
            FIELD_TICK_VOLUME = 3,        //成交量，单位手
            FIELD_TICK_FLAG = 4,        //第0、1位表示内外盘标志，0 外盘、1内盘、2平盘，注意：其他无效
            FIELD_TICK_CJBS = 5,        //成交笔数
            FIELD_TICK_TIME_HMS = 6;        //Hour*3600 + minute*60 + minute，存储为与上一笔之差，第一笔为原值

    //雷达字段字义
    public static final int
            HFI_MINE_FIELD = 0,        //请求应答字段
            HFI_MINE_PASSPORT = 1,        //券商通行证
            HFI_MINE_TIME = 20,        //时间，格式HHMMSS
            HFI_MINE_DATE = 21,        //日期，格式YYYYMMDD
            HFI_MINE_NODE = 22,        //唯一编码，唯一代表一条资讯
            HFI_MINE_TITLE = 23,        //资讯标题
            HFI_MINE_CONTENT = 24,        //资讯内容
            HFI_MINE_TIMEOFFSET = 25,        //时间点，用于走势图时的时间点
            HFI_MINE_MARKET = 26,        //证券市场
            HFI_MINE_CODE = 27,        //证券代码
            HFI_MINE_START = 28,        //起始记录
            HFI_MINE_RECORD = 29,        //请求记录个数
            HFI_MINE_FLAG = 30,        //是否推送标志
            HFI_ZX_STYLE = 31,            //资讯类别
            HFI_ZX_LMID = 32,            //资讯栏目ID
            HFI_ZX_LMMC = 33,            //栏目名称
            HFI_ZX_LMSX = 34,            //栏目属性，1表示还有子栏目，2表示还有栏目标题，3表示直接是栏目内容
            HFI_ZX_LMSXZ = 35,            //栏目属性值，为INT64，为子栏目时表示资讯类别，为标题时无意义，为内容时表示内容ID
            HFI_ZX_ADDITIONAL = 36,            //附加值，由服务器返回，请求需要时直接使用返回的值，否则为空
            HFI_ZX_SIMPLE_CONTENT = 37,            //简化的内容，最多100个汉字
            HFI_ZX_LMID_MUCH = 38,            //多个栏目ID
            HFI_ZX_SIMPLE_CONTENT_LENGTH = 39,    //简化内容长度，单位UNICODE字符，最大512，默认100

    // 用于世纪证券 资讯
    HFI_ZX_SGDM = 40,            //新股申购代码
            HFI_ZX_FXSL = 41,            //发行数量(万)
            HFI_ZX_WSFXSL = 42,            //网上发行数量（万）
            HFI_ZX_FXJG = 43,            //发行价格(元)
            HFI_ZX_SYL = 44,            //市盈率
            HFI_ZX_SGRQ = 45,            //申购日期
            HFI_ZX_DJZJ = 46,            //冻结资金（万）
            HFI_ZX_ZQL = 47,            //中签率
            HFI_ZX_SSRQ = 48,            //上市日期
            HFI_ZX_DMLB = 49,            //代码列表
            HFI_ZX_PAGE_ADDR = 50,            //附件地址或网页地址

    HFI_ZX_JJDM = 51,            //基金代码
            HFI_ZX_JJJC = 52,            //基金简称
    //HFI_ZX_JJGSDM			= 53,			//基金公司代码
    //HFI_ZX_JJGSMC			= 54,			//基金公司名称
    //HFI_ZX_ZRJZ				= 55,		//昨日净值
    //HFI_ZX_JJZT				= 56,		//基金状态
    //HFI_ZX_JJZTMC			= 57,			//基金状态名称
    //HFI_ZX_JJFXPJ			= 58,			//基金风险评级

    HFI_ZX_HFNR = 59,            //回复内容
            HFI_ZX_HFRQ = 60,            //回复日期
            HFI_ZX_HFSJ = 61,            //回复时间

    HFI_ZX_BTLB = 62,            //标题类别

    HFI_MINE_ZQMC = 63,            //股票名称
            HFI_MINE_SCMC = 64,            //市场名称

    HFI_MINE_TYPENAME = 65,            //资讯类型名称
            HFI_MINE_XGSG_QQLB = 66,            //新股申购请求类别
            HFI_MINE_XGSG_SGZT = 67,            //新股申购状态

    HFI_ZX_START = 68,            //起始位置
            HFI_ZX_LENGTH = 69,            //长度
            HFI_ZX_TOTAL_LENGTH = 70,            //总长度

    HFI_MINE_CODE_LIST = 71;            //代码列表，格式：01000001,00600000,0200001,前两位为市场，后五位或六位为代码


    //排行类别
    public static final int
            SORT_NOT = 0,    //不排行，根据股票顺序
            SORT_ZDF = 1,    //涨跌幅度
            SORT_ZD = 2,    //涨跌
            SORT_NOW = 3,    //现价
            SORT_HSL = 4,    //换手率
            SORT_SYL = 5,    //市盈率
            SORT_VOLUME = 6,    //成交量
            SORT_AMOUNT = 7,    //成交金额

    SORT_MMQ = 8,    //买卖气
            SORT_ZLZJ = 9,    //主力增减
            SORT_5ZJ = 10,    //5日增减
            SORT_10ZJ = 11,    //10日增减
            SORT_20ZJ = 12,    //20日增减
            SORT_10RH = 13,    //10日内红
            SORT_ZLLH = 14,    //主力连红
            SORT_YESTERDAY = 15,    //昨收
            SORT_HIGH = 16,    //最高
            SORT_LOW = 17,    //最低

    SORT_ZHENFU = 18,    //振幅
            SORT_LB = 19,    //量比
            SORT_WB = 20,    //委比
            SORT_SJL = 21,    //市净率

    SORT_5ZF = 22,    //五分钟涨幅

    SORT_ZDJSB = 23,    //涨跌家数比 目前用于板块指数：(涨家数/总家数)(正序)的排序，如比例相同时，再判断(跌家数/总家数)(倒序)
            SORT_OPEN = 24,    //开盘
            SORT_LTSZ = 25,    //流通市值
            SORT_ZSZ = 26,    //总市值
            SORT_QRD = 27,    //强弱度

    SORT_AVERAGE = 28,    //均价
            SORT_BPRICE1 = 29,    //买一价
            SORT_BVOLUME1 = 30,    //买一量
            SORT_SPRICE1 = 31,    //卖一价
            SORT_SVOLUME1 = 32,    //卖一量
            SORT_INVOLUME = 33,    //内盘
            SORT_OUTVOLUME = 34,    //外盘

    SORT_CODE = 35,    //代码
            SORT_NAME_4 = 36,    //四汉字长名称
            SORT_NAME_8 = 37,    //八汉字长名称
            SORT_JRCC = 38,    //今日持仓
            SORT_JRCCC = 39,    //今日仓差
            SORT_JRJSJ = 40,    //今日结算价
            SORT_ZRCC = 41,    //昨日持仓
            SORT_KC = 42,    //开仓
            SORT_PC = 43,    //平仓
            SORT_MARKETTIME = 44,    //时间
            SORT_CURVOL = 45;    //现量

    //K线周期
    public static final int
            HISTORY_TYPE_DAY = 0x01,
            HISTORY_TYPE_WEEK = 0x02,
            HISTORY_TYPE_MONTH = 0x03,
            HISTORY_TYPE_5MIN = 0x04,
            HISTORY_TYPE_60MIN = 0x05,
            HISTORY_TYPE_15MIN = 0x06,
            HISTORY_TYPE_30MIN = 0x07,
            VIEWTYPE_FENSH = 0x11;


    //K线指标
    public static final int
            HISTORY_TYPE_CJL = 0x01,//成交量
            HISTORY_TYPE_MACD = 0x02,
            HISTORY_TYPE_KDJ = 0x03,
            HISTORY_TYPE_RSI = 0x04,
            HISTORY_TYPE_BIAS = 0x05,
            HISTORY_TYPE_CCI = 0x06,//顺势指标
            HISTORY_TYPE_ZLDX = 0x07;//主力动向

    //包头错误号定义
    //高位定义成1的目的是4X平台的错误号都为负数，高位为1时，就会转换成负数了，另外不要定义在[0,9999]区间，4x平台认为是正确的返回。
    public static final int
            ERROR_CODE_SUCCESS = 0,        //成功
            ERROR_CODE_NORMAL = 0xffff,    //一般错误
            ERROR_CODE_CHECK_USER_FAILED = 0xff01,    //校验帐号失败
            ERROR_CODE_CHECK_TOKEN_FAILED = 0xff02,    //非同一台终端
            ERROR_CODE_PASSPORT_OVERDUE = 0xff03,    //通行证已过期
            ERROR_CODE_YOUKE_LOGIN = 0xff04;    //游客登录价格提醒服务


    //登录字段定义
    public static final int
            FIELD_LOGIN_VERSION = 20,        //软件版本号
            FIELD_LOGIN_MOBILETYPE = 21,        //手机型号
            FIELD_LOGIN_PWD = 23,        //密码
            FIELD_LOGIN_USERTYPE = 24,        //用户类别
            FIELD_LOGIN_UPDATEDATE = 25,        //股票字典更新日期
            FIELD_LOGIN_UPDATETIME = 26,        //股票字典更新时间
            FIELD_LOGIN_CRC = 27,        //股票字典CRC32值

    //FIELD_LOGIN_NOTE			= 28,		//软件升级说明
    FIELD_LOGIN_UPDATE_MESSAGE = 28,        //软件升级说明
            FIELD_LOGIN_USER = 29,        //用户名
            FIELD_LOGIN_UPDATEFLAG = 30,        //字典更新标志

    FIELD_LOGIN_CODES = 31,        //推大盘数据的股票
            FIELD_LOGIN_NETTYPE = 32,        //联网方式
            FIELD_LOGIN_NUMBER = 33,        //手机号
            FIELD_LOGIN_SERVICE_IP = 34,        //新服务器地址
    // 	1、	以";"分隔服务器类别
    // 	2、	以","分隔地址
    // 	3、	以"="分隔参数
    // 	如：
    // 	type=1,addr1=192.168.3.100:20000,addr2=192.168.3.101:20000,addr3=192.168.3.101:20000;
    // 	type=2,addr1=192.168.3.100:20001;
    // 	type=3,addr1=192.168.3.100:80
    // 		实际中没有回车换行符
    // 		一般情况下，一种服务器类别最多返回三个地址，客户端按顺序连接即可
    // 		服务器：
    // 		1表示行情接入服务器；2表示交易接入服务器；3表示web资讯服务器
    FIELD_LOGIN_RIGHTS = 35,        //权限位串	35	CHAR(20)	Y	对应用户的权限位串，每一位对应一种权限
    //可使用如下方式访问：
    //define	IsServiceEnabled(no,userright)	(userright[(no)/8] & (0x01 << ((no) % 8)))
    FIELD_LOGIN_SERVICE_IP_WITH_NAME = 36,    //同34
            FIELD_LOGIN_USERID = 37,        //用户ID	37	INT64	Y	唯一确定一个用户
            FIELD_LOGIN_RET_STATUS_STR = 38,        //状态信息	38	WCHAR(100)	N
            FIELD_LOGIN_RET_STATUS = 39,        //返回状态	39	INT32	Y	0:表示后台正在处理，直接显示38字段信息
    //1:表示注册成功，密码为23字段

    FIELD_LOGIN_IS_RECONNECT = 40,        //是否连接新服务器
    // 0表示不需要，1表示需要，默认为0，当为1时，表示本服务器不能满足指定客户的服务，需要连接新的服务器。
    // 本值为1时，只需取34字段中行情接入服务器地址，其它服务器不会返回
    // 举例：本服务器为普通权限，而客户为L2权限，则服务器会给客户端新的服务地址

    //FIELD_LOGIN_FILENAME		= 41,		//广告文件名，用于广告更新,格式：文件名1,版本号1;文件名2,版本号2，客户端判断版本号不一样时即表示文件不一样，需下载

    FIELD_LOGIN_SYSTEM = 42,        //手机操作系统
            FILED_LOGIN_VIEW_WIDTH = 43,        //可视区域象素宽
            FIELD_LOGIN_VIEW_HEIGHT = 44,        //可视区域象素高
            FIELD_LOGIN_VIEW_LINECHARNUM = 45,    //可视区域一行能显示的字符个数

    FIELD_LOGIN_UPDATE_ADDR = 46,        //软件升级地址
            FIELD_LOGIN_DEVICEID = 47,        //终端硬件唯一码
            FIELD_LOGIN_NOTICE_FLAG = 49,        //0：不显示通知(删除本地通知内容)；1：没有更新(显示本地通知内容)；2：有更新(通知内容由[145、60]返回，不需请求)
            FIELD_LOGIN_WAPTRADE_VER = 50,        //wap委托配置文件版本号201007230
            FIELD_LOGIN_QSDM = 51,        //券商代码，0表示个人环境，其它表示券商环境
            FIELD_LOGIN_SERVER_DATE_TIME = 52,    //服务器返回的日期和时间，格式为低四字节为时间(HHMMSS)，高四字节为日期(YYYYMMDD)
            FIELD_LOGIN_QIANLONG_PASSPORT = 53,    //钱龙通行证，这里用于4x
            FIELD_LOGIN_QS_PASSPORT = 54,    //券商通行证，这里用于4x
            FIELD_LOGIN_FWBM = 55,    //服务器唯一编码 主要用于服务器之间，客户端可忽略

    FIELD_LOGIN_4X_SESSIONID = 56,    //会话ID，用于4x认证 主要用于服务器之间，客户端可忽略
            FIELD_LOGIN_USERPLATFORM_TYPE = 57,    //用户管理平台类别，0表示3x平台，1表示4x平台，默认0 客户端不处理

    FIELD_LOGIN_4X_QSDM = 58,    //4X平台定义的券商代码，客户端忽略，行情服务器向4x认证网关设置登录登出时使用。

    FIELD_LOGIN_SYSTEM_OS = 59,    //FIELD_LOGIN_SYSTEM的高两字节，客户端操作系统版本
            FIELD_LOGIN_SYSTEM_PRODUCT = 60,    //FIELD_LOGIN_SYSTEM的低两字节，客户端的产品号

    FIELD_LOGIN_RECV_MSG_FLAG = 61,    //是否注册接收服务器消息,0不接收，1接收
            FIELD_LOGIN_GATEWAY_CONN_STYLE = 62,    //连接类别 0：普通连接，默认方式，可不用发本请求1：可接收踢人消息

    FIELD_LOGIN_ERRCODE = 63,
            FIELD_LOGIN_ERRMSG = 64,
            FIELD_LOGIN_GATEWAY_USERLIST = 65,
            FIELD_LOGIN_GATEWAY_CONN_NO = 66,

    FIELD_LOGIN_IOS_DEVICETOKEY = 67,    //ios设备令牌

    FIELD_LOGIN_YYBDM = 68,    //手机定义的营业部代码，与FIELD_LOGIN_QSDM一起
            FIELD_LOGIN_IF_CREATE_DEVICETOKEY = 69,//是否需要服务端创建devicetoken，1表示需要，0表示不需要

    FIELD_LOGIN_TG_USERTYPE = 70,    //用于服务平台0：股民1：投顾平台

    FIELD_LOGIN_MOBILE_PASSPORT = 71,    //手机通行证
            FIELD_LOGIN_PMD_TYPE = 72,    //跑马灯类别	72	INT8	N	0文本内容
            FIELD_LOGIN_PMD_VER = 73,    //跑马灯版本号	73	CHAR(32)	N	不需要跑马灯时，送空 送”0”时，必返回跑马灯 格式为：1.00.20130125
            FIELD_LOGIN_PMD_TXT = 74,    //跑马灯内容	74	WCHAR(2048)	N

    FIELD_LOGIN_USE_TIMES = 75,    //使用时长	75	INT32	Y	使用时长，单位秒
            FIELD_LOGIN_SERVER_TYPE = 76,    //服务器类型	76	INT32	Y	由4x平台统一定义
            FIELD_LOGIN_REGISTER_USER = 77,    //注册名称	77	CHAR(32)	Y	由认证网关定义的
            FIELD_LOGIN_REGISTER_PWD = 78,    //注册密码	78	CHAR(32)	Y	由认证网关定义的
            FIELD_LOGIN_LINKNO = 79,    //所在服务器的连接号	79	INT32	N	由行情服务器添加的字段

    FIELD_LOGIN_EXT = 80;    //扩展字段	80	WCHAR(512)	N	以”;”分隔类别
    //以”=”分隔参数
    //目前需送的参数：
    //phone:从设备上自动获取的手机号，包括手机和平板
    //
    //如
    //phone=13812345678;name=...

    //手机权限位定义
    public static final int
            UR_PC_SH_L2 = 5,    //PC上海L2权限
            UR_PC_SZ_L2 = 9,    //PC深圳L2权限

    UR_SH_SZ_L2 = 30,    //黄金眼功能
            UR_GOLDEYE = 30,    //黄金眼功能
            UR_HK_DELAY = 31,    //延时港股权限
            UR_HK_REAL = 32,    //实时港股权限
            UR_USA_REAL = 49;   //美股实时行情权限

    //定义手机平台
    public static final int
            SYSTEM_UNKNOWN = 0,
            SYSTEM_WM_5_6 = 1,
            SYSTEM_WM_PPC = 1,
            SYSTEM_S60_5 = 2,
            SYSTEM_S60_3 = 3,
            SYSTEM_IPHONE = 4,
            SYSTEM_ANDROID = 5,
            SYSTEM_KJAVA_STANDARD = 6,
            SYSTEM_KJAVA_BLACKBERRY = 7,
            SYSTEM_WM_SMARTPHONE = 8,
            SYSTEM_ANDROID_PAD = 9,
            SYSTEM_IPHONE_PAD = 10;

    //定义手机产品
    public static final int
            PRODUCT_STANDARD = 0,        //标准版
            PRODUCT_STANDARD_ANDROID_NEWSIGN = 1,    //用于android手机新签名版
            PRODUCT_STOCKTRADE = 0X100,    //券商版
            PRODUCT_STOCKTRADE_TG = 0x101;    //投顾版本

    //留言字段
    public static final int
            HFI_NOTE_VER = 20,            //客户端版本号
            HFI_NOTE_SYSTEM = 21,            //客户端平台号
            HFI_NOTE_PRODUCT = 22,            //客户端产品号
            HFI_NOTE_MOBILE_STYLE = 23,            //手机型号
            HFI_NOTE_LOGIN_NAME = 24,            //登录名
            HFI_NOTE_EMAIL_OR_TEL = 25,            //留言方式：邮箱或电话
            HFI_NOTE_CONTENT = 26,            //留言内容
            HFI_NOTE_REMARK = 27,            //备注
            HFI_NOTE_START_RECORD = 28,            //起始记录
            HFI_NOTE_RECORD_NUM = 29,            //请求记录
            HFI_NOTE_UPDATETIME = 30;            //留言时间

    //同步自选股定义
    public static final int
            FIELD_53_CZLB = 1,    //操作类别
            FIELD_53_USERNAME = 2,    //用户名
            FIELD_53_UPDATETIME = 3,    //服务器数据时间	时间格式=YYYYMMDD HHMMSS 如20120802 175105
            FIELD_53_ZXGDATA = 4,    //自选股列表
            FIELD_53_UPDATEFLAG = 5,    //更新标志
            FIELD_53_CLIENT_VER = 6,    //软件版本号 格式：V1.00 B001(20090413)
            FIELD_53_SYSTEM_OS = 7,    //手机操作系统	7	FIELD_LOGIN_SYSTEM的高两字节，客户端操作系统版本
            FIELD_53_SYSTEM_PRODUCT = 8,    //手机产品号	8	FIELD_LOGIN_SYSTEM的低两字节，客户端的产品号
            FIELD_53_QSID_4X = 9;    //登录返回的4x券商ID

    //手机邮件
    public static final int
            FIELD_MAIL_FIELD = 0,
            FIELD_MAIL_ID = 20,        //邮件ID
            FIELD_MAIL_SEND_DATE = 21,
            FIELD_MAIL_SEND_TIME = 22,
            FIELD_MAIL_TO_NAME = 23,
            FIELD_MAIL_FROM_NAME = 24,
            FIELD_MAIL_TITLE = 25,
            FIELD_MAIL_CONTENT = 26,
            FIELD_MAIL_REQUEST_NUM = 27,        //请求记录数
            FIELD_MAIL_CONTENT_START = 28,        //请求起始位置
            FIELD_MAIL_CONTENT_PACKAGE_LENGTH = 29,    //本包内容长度
            FIELD_MAIL_CONTENT_TOTAL_LENGTH = 30,    //内容总长度
            FIELD_MAIL_NAME = 31,        //用户名
            FIELD_MAIL_JJR_ID = 32,        //经济人ID
            FIELD_MAIL_JJR_NAME = 33,        //经济人姓名
            FIELD_MAIL_JJR_SEX = 34,        //性别
            FIELD_MAIL_JJR_MOBILE = 35,        //手机号
            FIELD_MAIL_JJR_PHONE = 36,        //固定电话
            FIELD_MAIL_JJR_QQ = 37,        //QQ
            FIELD_MAIL_JJR_MSN = 38,        //MSN
            FIELD_MAIL_JJR_QSDM = 39,        //所属券商代码
            FIELD_MAIL_JJR_ZW = 40,        //职位
            FIELD_MAIL_JJR_QSDZ = 41,        //券商地址
            FIELD_MAIL_SOURCEID = 42,        //原始消息ID
            FIELD_MAIL_TYPE = 43,        //消息类别
            FIELD_MAIL_4XQSDM = 44,        //4X券商代码

            FIELD_MSG_TYPE = 45,        //消息类别
            FIELD_MSG_FWBM = 46,        //服务编码
            FIELD_MSG_DEVICETOKEN = 47,        //DEVICETOKEN

            FIELD_MAIL_USERTYPE = 48,        //角色1：投资顾问2：客户经理
            FIELD_MAIL_ONLINEFLAG = 49,        //在线状态	49	INT8		0：在线，1：不在线

            FIELD_MAIL_TYPEID = 50,        //邮件类别ID id分为,0:普通,1:回执,2:回复,3:长期权限,4:黄金眼,5:经纪人名片，7：短期权限
            FIELD_MAIL_SOURCEINFO = 51,        //邮件原件信息 当为1(回执)、2(回复)时，本字段为原件信息，格式为:(邮件ID,邮件类别ID)，如“141325,0”
            FIELD_MAIL_STYLE = 52,        //类别1：表示是标签组，2：表示是成员
            FIELD_MAIL_NAME_FIRST_CHAR = 53,        //姓名首字母
            FIELD_MAIL_GOLDCODE = 54,        //黄金眼数据 格式为"SZ,000002|01008|短期黄金眼"，市场定义同【自选股同步服务后台的市场定义】

            FIELD_MAIL_PRODUCT_TYPE = 55,        //产品类别
            FIELD_MAIL_EXT1 = 56,        //扩展字段1

            FIELD_MAIL_FROM_CNAME = 57,        //发件人中文名
            FIELD_MAIL_TASKID = 58,        //待办事项ID
            FIELD_MAIL_TASKSTATUS = 59;        //待办事项状态


    //定义用户类别
    public static final int
            USER_STYLE_INVESTOR = 0,        //股民
            USER_STYLE_BROKER = 1;        //投资顾问

    //定义用户角色
    public static final int
            USER_TYPE_ADVISER = 1,        //投资顾问
            USER_TYPE_MANAGER = 2;        //客户经理

    //定义短信平台
    public static final int
            FIELD_SMS_PHONE_NUMBER = 1,        //手机号
            FIELD_SMS_CONTENT = 2;        //短信内容


    //上传设置
    public static final int
            CONFIG_USERNAME = 1,        //用户名
            CONFIG_PWD = 2,        //密码
            CONFIG_SYSTEM_OS = 3,        //FIELD_LOGIN_SYSTEM的高两字节，客户端操作系统版本
            CONFIG_SYSTEM_PRODUCT = 4,        //FIELD_LOGIN_SYSTEM的低两字节，客户端的产品号
            CONFIG_DOWN_FIELD_LIST = 5,        //需要下载的设置列表	5	以';'分隔，如：20;21
            CONFIG_NOT_PUSH_FLAG = 20,        //PC在线是否不推送标志	20	0：表示推送1：表示不推送
            CONFIG_REJECT_USER_LIST = 21,        //拒绝接收消息的经济人列表	21	用';'分隔经济人的用户名，如：bmh888;bmh999
            CONFIG_USER_LIST = 22,        //用户名列表';'(分号)分隔用户名，如：bmh888;bmh999
            CONFIG_DEVICETOKEN = 23,        //devicetoken	23	Ios是token,android是硬件地址
            CONFIG_QSID_4X = 24;        //4x的券商编码


    //自选股上传下载类别
    //1：检测自选股，根据更新甄来完成上传自选股和下载自选股，当服务器新(本地数据时间<服务器数据时间)，返回服务器自选股列表；当客户端新时(本地数据时间<服务器数据时间)，更新服务器自选股列表；无变化时不操作
    //2：强制上传自选股，忽略更新标志
    //3：强制下载自选股，忽略更新标志
    public static final int
            ZXG_CZLB_CHECK = 1,
            ZXG_CZLB_UP = 2,
            ZXG_CZLB_DOWN = 3;

    //自选股更新标志类别
    //0表示一致；
    //1表示本地数据新；
    //2表示服务器数据新，返回了3和4
    public static final int
            ZXG_UPDATEFLAG_SAME = 0,
            ZXG_UPDATEFLAG_CLIENT_NEW = 1,
            ZXG_UPDATEFLAG_SERVER_NEW = 2;


    //券商列表、营业部列表及信息定义
    public static final int
            FIELD_QSXX_QSMC = 20,        //券商名称
            FIELD_QSXX_QSMC_KEY = 21,        //券商名称快捷键
            FIELD_QSXX_QSDM = 22,        //券商代码，同FIELD_LOGIN_QSDM定义
            FIELD_QSXX_YYBMC = 23,        //营业部名称
            FIELD_QSXX_YYBMC_KEY = 24,        //营业部名称快捷键
            FIELD_QSXX_YYBDM = 25,        //营业部代码
            FIELD_QSXX_YYB_SQDH = 26,        //营业部申请帐号联系电话
            FIELD_QSXX_YYB_SQZHXX = 27,        //营业部申请帐号显示信息
            FIELD_QSXX_YYB_INFO_TYPE = 28,        //营业部信息内容类型0：表示信息内容是一串文本(普通文本或者超文本)1：表示是url地址
            FIELD_QSXX_YYB_INFO_CONTENT = 29,        //营业部信息内容
            FIELD_QSXX_YYB_SQZHXXBT = 30;        //营业部申请帐号显示信息标题


    //黄金价格权限字段
    public static final int
            FIELD_GOLD_ACCOUND = 1,
            FIELD_GOLD_OPERATIONSYS = 2,
            FIELD_GOLD_VERSION = 3,
            FIELD_GOLD_PRODUCT = 4,
            FIELD_GOLD_DEVICETOKEN = 5,
            FIELD_GOLD_QSCODE = 6,
            FIELD_GOLD_YYBCODE = 7,
            FIELD_GOLD_STOCKCODE = 8,
            FIELD_GOLD_STOCKNAME = 9,
            FIELD_GOLD_PRICESETFLAG = 10,
            FIELD_GOLD_PRICESETPARAM = 11,
            FIELD_GOLD_PRICEUPPER = 12,
            FIELD_GOLD_PRICELOWER = 13,
            FIELD_GOLD_PRICESCOPE = 14,
            FIELD_GOLD_RETINFO = 15,
            FIELD_GOLD_AUTHORITYFLAG = 16,
            FIELD_GOLD_STOCKGROUP = 17,
            FIELD_GOLD_MARKET = 18,
            FIELD_GOLD_SECURITYTYPE = 19,
            FIELD_GOLD_ULDATE = 20,
            FIELD_GOLD_COUNT = 21,
            FIELD_GOLD_STARTDATE = 22,
            FIELD_GOLD_ENDDATE = 23,
            FIELD_GOLD_ANSWER = 24,
            FIELD_GOLD_MARKETGROUP = 25,
            FIELD_GOLD_SECURITYGROUP = 26,
            FIELD_GOLD_FIELDGROUP = 27,
            FIELD_GOLD_BROKERNAME = 28,
            FIELD_GOLD_DEVICEID = 29,
            FIELD_GOLD_DAYZF = 30,
            FIELD_GOLD_DAYDF = 31,
            FIELD_GOLD_FLAGS = 32;
    //提醒消息字段
    public static final int
            FIELD_MESSAGE_ID = 81,
            FIELD_MESSAGE_TYPE = 82,
            FIELD_MESSAGE_CONTENT = 83,
            FIELD_MESSAGE_DATE = 84,
            FIELD_MESSAGE_TIME = 85,
            FIELD_MESSAGE_GROUP = 86,
            FIELD_MESSAGE_REQUESTNUM = 87, //查询提醒消息，每次请求返回的记录数，默认20
            FIELD_MESSAGE_TRIGGERTIME = 88, //消息触发时间，由HQ推送股票行情获取，黄金买入卖出默认为0
            FIELD_MESSAGE_DEVICETOKEN = 89;

    //消息类型
    public static final int
//            MESSAGETYPE_GOLD_BUY = 0X00,    //黄金阶梯买入
//            MESSAGETYPE_GOLD_SELL = 0X01,    //黄金阶梯卖出
              MESSAGETYPE_PRICE = 0X02,    //价格提醒
//            MESSAGETYPE_RISESCOPE = 0X03,    //涨幅度提醒
//            MESSAGETYPE_FALLSCOPE = 0X04,    //跌幅度提醒
//            MESSAGETYPE_SYSTEM = 0X08,    //系统消息
//            MESSAGETYPE_OTHER = 0X09;    //其他消息（保留）
    MESSAGETYPE_GOLD_BUY = 0X00,    //黄金阶梯买入
            MESSAGETYPE_GOLD_SELL = 0X01,    //黄金阶梯卖出
            MESSAGETYPE_PRICEUP = 0X02,    //价格提醒上涨
            MESSAGETYPE_PRICEDOWN = 0X03, //价格提醒下跌
            MESSAGETYPE_RISESCOPE = 0X04,    //涨幅度提醒
            MESSAGETYPE_FALLSCOPE = 0X05,    //跌幅度提醒
            MESSAGETYPE_SYSTEM = 0X08,    //系统消息
            MESSAGETYPE_OTHER = 0X09;    //其他消息（保留）

    public static final int NULLZDF = 10000;    //空涨跌幅
}


