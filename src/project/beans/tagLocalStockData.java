package project.beans;



import project.utils.Global_Define;
import project.utils.Color;
import project.utils.ViewTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class tagLocalStockData{
	public final static String TAG = tagLocalStockData.class.getSimpleName();

	public class tagListItemStockData
	{	
		public String 	data;
		public int		color;
		
		public tagListItemStockData()
		{
			data	= new String();
			color	= Color.WHITE;
		}
		
		public tagListItemStockData(String str, int col)
		{
			data	= str;
			color	= col;
		}
	}
	
	//股指期货今日持仓数(今日持仓+今日结算价+昨日持仓量+持仓性质)
	public class  tagJRCCS
	{
		public long		jrccl;			//今日持仓 单位股
		public int		jrjsj;			//今日结算价 放大价格倍数
		public long		zrccl;			//昨日持仓量 单位股
		public byte		ccsz;			//持仓性质
		
		public tagJRCCS()
		{
			clear();
		}
		
		public void copy(tagJRCCS data)
		{
			jrccl = data.jrccl;
			jrjsj = data.jrjsj;
			zrccl = data.zrccl;
			ccsz = data.ccsz;
		}
		
		public void clear()
		{
			jrccl = 0;
			jrjsj = 0;
			zrccl = 0;
			ccsz = 0;
		}
	}
	/**
	 * 港股CAS信息
	 *
	 * @作者 饶正勇
	 * @E-Mail 23938232@qq.com
	 * @创建时间 2016/9/12 15:37
	 **/
	public class tagHKCAS {
		public int ulRefPrice;//市调机制参考价
		public int ulLowerPrice;//市调机制下限价
		public int ulUpperPrice;//市调机制上限价
		public char uchOrdImbDirection;//不能配对买卖盘方向
		public long ulOrdImbQty;//不能配对买卖盘量
		public char CASFlag;// Y:本商品支持CAS N:不支持
		public int B = 66, N = 78, S = 83, Y = 89;

		public tagHKCAS() {
			clear();
		}

		public void clear() {
			ulRefPrice = 0;
			ulLowerPrice = 0;
			ulUpperPrice = 0;
			uchOrdImbDirection = '0';
			ulOrdImbQty = 0;
			CASFlag = '0';
		}

		@Override
		public String toString() {
			return "ulRefPrice:" + ulRefPrice + ",ulLowerPrice:" + ulLowerPrice + ",ulUpperPrice:" + ulUpperPrice + ",uchOrdImbDirection:" +
					uchOrdImbDirection + ",ulOrdImbQty:" + ulOrdImbQty + ",CASFlag:" + CASFlag;
		}
	}

	/**
	 * 港股VCM信息
	 *
	 * @作者 饶正勇
	 * @E-Mail 23938232@qq.com
	 * @创建时间 2016/9/12 15:47
	 **/
	public class tagHKVCM {
		public int ulStartTime;// 市调开始时间
		public int ulEndTime;// 市调结束时间
		public int ulRefPrice;// 市调机制参考价
		public int ulLowerPrice;// 市调机制下限价
		public int ulUpperPrice;// 市调机制上限价
		public char VCMFlag;// Y:本商品支持VCM N:不支持

		public tagHKVCM() {
			clear();
		}

		public void clear() {
			ulStartTime = 0;
			ulEndTime = 0;
			ulRefPrice = 0;
			ulLowerPrice = 0;
			ulUpperPrice = 0;
			VCMFlag = '0';
		}

		@Override
		public String toString() {
			return "ulStartTime:" + ulStartTime + ",ulEndTime:" + ulEndTime + ",ulRefPrice:" + ulRefPrice + ",ulLowerPrice:" + ulLowerPrice + "," +
					"ulUpperPrice:" + ulUpperPrice + ",VCMFlag:" + VCMFlag;
		}
	}

	/**
	 * 市场信息
	 *
	 * @作者 饶正勇
	 * @E-Mail 23938232@qq.com
	 * @创建时间 2016/9/12 16:13
	 **/
	public class MarketInfo {
		public final byte PUSH_MARK_0 = 0, PUSH_MARK_1 = 1;//推送标志(1注册推送,0取消推送)
		public final byte EXPAND_MARK = 0;
		public final Integer[] MARKET_STATUS = {4, 5, 105, 106, 107};
		public byte marketNum;//市场编号(0表示请求的市场编号不支持)
		public byte expandMark;//扩展标志(目前只为0)
		public int marketDate;//市场日期
		public int marketTime;//市场时间
		public int marketStatus;//市场状态(0全日收市,1输入买卖盘(开盘集合竞价),2对盘(开盘集合竞价时段),3持续交易,4对盘(收盘集合竞价时段),5输入买卖盘(收盘集合竞价时段),7暂停,100未开市,101对盘前(开盘集合竞价时段),

		// 102Exchange Intervention,103收市,104取消买卖盘,105参考价定价(收盘集合竞价时段),106不可取消(收盘集合竞价时段),107随机收市(收盘集合竞价时段))
		public MarketInfo() {
			clear();
		}

		public void clear() {
			marketNum = 0;
			expandMark = 0;
			marketDate = 0;
			marketTime = 0;
			marketStatus = 0;
		}

		@Override
		public String toString() {
			return "marketNum:" + marketNum + ",expandMark:" + expandMark + ",marketDate:" + marketDate + ",marketTime:" + marketTime + "," +
					"marketStatus:" + marketStatus;
		}

		/**
		 * 判断市场状态
		 * by rzy 2016/9/26 11:21
		 **/
		public boolean checkMarketStatus() {
			List<Integer> list = Arrays.asList(MARKET_STATUS);
			return list.contains(marketStatus);
		}
	}

	//黄金阶梯，价格提醒相关
	public	boolean		bRightHJJT;
	public	boolean		bRightJGTX;
	
	public	int			yesterday;		//昨日收盘价
	public	int			open;			//今日开盘价
	public	int			high;			//最高价
	public	int			low;			//最低价
	public	int 		now;			//现价
	public	long		volume;			//总成交量
	public	long		amount;			//总成交金额
	public	long		realvol;		//现手
	public	byte		market;			//市场，为本地类型
	public	String		code;			//代码
	
	public	byte		zqlb;			//类别，为本地类型
	public	String		name;			//名称
	public	String		name_w;			//名称(8)
	public	byte		ldflag;			//雷达标志
	public	int			lb;				//量比
	public	int			hsl;			//换手率
	public	int			hqdate;			//YYYYMMDD
	public	int			hqtime;			//HHMMSS
	public 	byte		xsws;			//价格显示位数
	
	public	int			syl;			//市盈率
	public	int			zd;				//涨跌，由服务器计算，但涨跌幅由客户端算
	public	int			average;		//均价，放大10000倍，客户端直接使用这个均价，而不需自己计算
	
	public	byte		pricedot;		//价格位数
	public	int			unit;			//每手股数
	
	public	byte		tradetimenum;	//交易段数,最多三段
	public	int[]		tradetime;		//交易时间 hour*60+minute[6]
	
	public	int			index_wb;		//委比
	public	long		index_wc;		//委差
	public	int			index_up;		//上涨家数
	public	int			index_same;		//平盘家数
	public	int			index_down;		//下跌家数
	public	int			index_hlz;		//大盘红绿柱
	
	public	int			upprice;		//涨停价格
	public	int			downprice;		//跌停价格
	
	public	int			buyprice[];		//五档买价
	public	long		buyvolume[];	//五档买量
	public	int			sellprice[];	//五档卖价
	public	long		sellvolume[];	//五档卖量
	
	public	long		out;			//外盘
	public byte 		mmpflag;		//内外盘标志 第0、1位表示内外盘标志，0 外盘、1内盘、2平盘，注意：其他无效
	
	public	long		ltgb;			//流通股本
	public	long		zgb;			//总股本
	public	int			ycmgsy;			//预测每股收益
	public	int			zxmgsy;			//最新每股收益
	public	long		volrate;		//量比基量，前五日每分钟成交均量
	
//	public	int			bsprice[];		//十档买价+十档卖价
//	public	long		bsvolume[];		//十档买量+十档卖量
	public	int			buyprice10[];	//10档买价
	public	long		buyvolume10[];	//10档买量
	public	int			sellprice10[];	//10档卖价
	public	long		sellvolume10[];	//10档卖量
	
	public	int			vbuyprice;		//委买总量均价
	public	long		vbuyvolume;		//委买总量
	public	int			vsellprice;		//委卖总量均价
	public	long		vsellvolume;	//委卖总量

	public	int			BuySellGold[];	//龙虎数据：成交笔数 四买(庄大中小)+四卖(庄大中小)
	public	long		VolumeGold[];	//龙虎数据：成交量 四买(庄大中小)+四卖(庄大中小)
	public	long		AmountGold[];	//四买(庄大中小)+四卖(庄大中小)，金额放大100倍
	public	long		AmountMMC[];	//龙虎数据：买卖成交金额差	四个（买-卖）（庄大中小），金额放大100倍
	public	int			flccbl[];	//四个（持仓比例+持仓增减）（庄大中小）
	
	public	long		BuyAvr5;		//龙虎数据：5日均主力买额
	public	long		SellAvr5;		//龙虎数据：5日均主力卖额
	public	int			MMQ;			//买卖气
	public	int			ZLZJ;			//主力增减
	public	int			RZJ5;			//5日增减
	public	int			RZJ10;			//10日增减
	public	int			RZJ20;			//20日增减
	public	int			RNH10;			//10日内红
	public	int			ZLLH;			//主力连红
	public	int			maincc;			//主力持仓：沪深((至T-1日主力净买量的累加值+T日主力净买)/流通盘+1) *100*e
	public	long		MainBuyAmount;
	public	long		MainSellAmount;
	public	long		MainBuyVolume;
	public	long		MainSellVolume;
	
	//by ngj add
	public  int 		radar_date;		//雷达日期
	public  int 		radar_time;		//雷达时间
	public	String		radar_title;	//雷达标题
	public	long		radar_node;		//雷达Id
	public	int 		zhenfu;			//振幅
	public	int			sjl;			//市净率
	public	int 		zdf;			//涨跌幅
	public	int 		zf5;			//涨速--5分钟涨幅
	
	public	int 		cjbs;			//成交笔数
	public	long 		ltsz;			//流通市值
	public	long 		zsz;			//总市值
	public	int 		qrd;			//强弱度
	public	long 		ltbg;			//INT64	流通B股	单位股
	public	int 		mgjzc;			//INT32	每股净资产	放大10000倍
	public	int 		zzcsyl;			//INT32	净资产收益率	放大10000倍:每股收益/每股净资产*100%
	public	int 		zcfzl;			//INT32	资产负债率	放大10000倍
	public	long 		zysr;			//INT64	主营收入	单位分，即放大100倍
	public	long 		zlr;			//INT64	净利润	单位分，即放大100倍
	public	long 		zzc;			//INT64	总资产	单位分，即放大100倍
	
	
	public int			price_buy;			//委托价格，放大价格倍数
	public int			totalvol_buy;		//委托总量，单位手
	public int			volrecord_buy;		//委托笔数，指本委托价格上的总委托笔数
	public short		volcount_buy;		//委托分量数量，目前最大50，指返回的分量数量，应该小于等于委托笔数
	public short		vol_buy[];		//各委托分量的值，单位手
	
	public int			price_sell;			//委托价格，放大价格倍数
	public int			totalvol_sell;		//委托总量，单位手
	public int			volrecord_sell;		//委托笔数，指本委托价格上的总委托笔数
	public short		volcount_sell;		//委托分量数量，目前最大50，指返回的分量数量，应该小于等于委托笔数
	public short		vol_sell[];		//各委托分量的值，单位手
	
	public	ArrayList<tagLocalTrendData> trendData;		//走势数据
//	public	ArrayList<tagHisTrendData>	hisTrendData;	//历史走势
	
	public 	static final int			MAX_HIS_NUM = 4;
	public	tagHisTrendData[]			hisTrendData;	//历史走势
	public 	int							hisDays;
	
	public	ArrayList<tagLocalKLineData.tagWeightData>	weightData;		//权息
	
//	public byte hqtime_have_second;//行情时间
	public int hqtime_date;
	public int hqtime_time;
	
	public long bvolume1;//买一量	单位股
	public long svolume1;//卖一量	单位股
	public long involume1;//内盘总量	单位股
	
	//股指期货
	public tagJRCCS    	JRCCS;	//股指期货今日持仓数
	public long	    	KPC[]; //开仓INT64+平仓INT64 单位股
	public long	   	 	XKP[]; //现开INT64+平开INT64 单位股
	public int       	ZRJSJ; 	//昨日结算价 放大价格倍数
	
	//
	public byte		broker_count_buy;     	//最多四十
	public short	broker_buy[];			// broker<0，表示买卖档位。broker>=0，表示经纪号码
	public byte		broker_count_sell;     	//最多四十
	public short	broker_sell[];			// broker<0，表示买卖档位。broker>=0，表示经纪号码

	/**
	 * CAS,VCM状态,市场信息
	 * Created by [rzy] 2016/9/27 9:58
	 **/
	public tagHKCAS tagHKCAS;//cas状态
	public tagHKVCM tagHKVCM;//vcm状态
	public MarketInfo marketInfo;//市场信息
	
	public tagLocalStockData()
	{
		code		= new String();
		name		= new String();
		name_w		= new String();
		
		tradetime	= new int[6];
		
		buyprice	= new int[5];
		buyvolume	= new long[5];
		sellprice	= new int[5];
		sellvolume	= new long[5];

		
		buyprice10	= new int[10];
		buyvolume10	= new long[10];
		sellprice10	= new int[10];
		sellvolume10	= new long[10];
		
		
		vol_buy	= new short[]{};
		vol_sell	= new short[]{};
		BuySellGold	= new int[8];
		VolumeGold	= new long[8];
		AmountGold	= new long[8];
		AmountMMC	= new long[4];
		flccbl = new int[8];

		trendData	= new ArrayList<tagLocalTrendData> ();
//		hisTrendData = new ArrayList<tagHisTrendData> ();
		hisTrendData = new tagHisTrendData[MAX_HIS_NUM];
		for(int i=0; i<MAX_HIS_NUM; i++) {
			hisTrendData[i] = new tagHisTrendData();
		}
	
		weightData 	= new ArrayList<tagLocalKLineData.tagWeightData> ();
		
		
		//股指期货
		JRCCS = new tagJRCCS();
		KPC = new long[2];
		XKP = new long[2];
		ZRJSJ = 0;
		
		//
		broker_buy	= new short[40];
		broker_sell	= new short[40];
		/**
		 * 初始化
		 * rzy
		 */
		tagHKCAS = new tagHKCAS();
		tagHKVCM = new tagHKVCM();
		marketInfo = new MarketInfo();
	}
	
	//数据清零
	public	void	clear()
	{
		bRightHJJT = false;
		bRightJGTX = false;
		
//		code		= "";
//		name		= "";
		yesterday	= 0;
		open = high = low = now	= 0;
		volume		= 0;
		amount		= 0;
		realvol		= 0;
		market		= 0;
		zqlb		= 0;
		ldflag		= 0;
		lb			= 0;
		hsl			= 0;
		hqdate		= 0;
		hqtime		= 0;
		syl			= 0;
		zd			= 0;
		average		= 0;
		pricedot	= 0;
		unit		= 0;
		//by ngj add
		cjbs		= 0;
		sjl			= 0;
		zhenfu		= 0;
		zdf			= 0;
		zf5			= 0;
		ltsz		= 0;
		zsz			= 0;
		qrd			= 0;
		ltbg=0;			//INT64	流通B股	单位股
		mgjzc=0;			//INT32	每股净资产	放大10000倍
		zzcsyl=0;			//INT32	净资产收益率	放大10000倍:每股收益/每股净资产*100%
		zcfzl=0;			//INT32	资产负债率	放大10000倍
		zysr=0;			//INT64	主营收入	单位分，即放大100倍
		zlr=0;			//INT64	净利润	单位分，即放大100倍
		zzc=0;			//INT64	总资产	单位分，即放大100倍
//		price_buy	= 0;
//		totalvol_buy	= 0;
//		volrecord_buy	= 0;
//		volcount_buy	= 0;
//		vol_buy=null;
//		
//		price_sell	= 0;
//		totalvol_sell	= 0;
//		volrecord_sell	= 0;
//		volcount_sell	= 0;
//		vol_sell=null;
		
		
		
		
		tradetimenum= 0;
		tradetime[0] = tradetime[1] = tradetime[2] = tradetime[3] = tradetime[4] = tradetime[5] = 0;
		
		index_wb	= 0;
		index_wc	= 0;
		index_up	= index_same = index_down = 0;
		index_hlz	= 0;
		upprice = downprice = 0;
		out			= 0;
		ltgb		= 0;
		zgb			= 0;
		ycmgsy		= 0;
		zxmgsy		= 0;
		volrate		= 0;
		
		for (int i = 0; i < 5; i++) {
			buyprice[i] = 0;
			buyvolume[i] = 0;
			sellprice[i] = 0;
			sellvolume[i] = 0;
		}
		for (int i = 0; i < 10; i++) {
			buyprice10[i] = 0;
			buyvolume10[i] = 0;
			sellprice10[i] = 0;
			sellvolume10[i] = 0;
		}
		
		MMQ = ZLZJ = RZJ5 = RZJ10 = RZJ20 = ZLLH = 0;
		BuyAvr5 = SellAvr5 = 0;
		MainBuyAmount = MainSellAmount = MainBuyVolume = MainSellVolume = 0;
		maincc = 0;
		
/*		for (int i = 0; i < 20; i++)
		{
			bsprice[i]	= 0;
			bsvolume[i]	= 0;
		}
		vbuyprice = vsellprice = 0;
		vbuyvolume = vsellvolume = 0;

		BuyAvr5 = SellAvr5 = 0;
		MMQ = ZLZJ = RZJ5 = RZJ10 = RZJ20 = ZLLH = 0;
		MainBuyAmount = MainSellAmount = MainBuyVolume = MainSellVolume = 0;
		maincc = 0;*/

		for (int i = 0; i < 8; i++)
		{
			BuySellGold[i]	= 0;
			VolumeGold[i]	= 0;
			AmountGold[i]	= 0;
			
			flccbl[i]	= 0;
		}
		
		for (int i = 0; i < 4; i++)
		{
			AmountMMC[i]	= 0;
		}
		
		//股指期货
		JRCCS.clear();
		for (int i = 0; i < 2; i++)
		{
			KPC[i]	= 0;
			XKP[i]	= 0;
		}
		ZRJSJ = 0;
		
		//
		broker_count_buy = 0;
		broker_count_sell = 0;
		for (int i = 0; i < 40; i++)
		{
			broker_buy[i]	= 0;
			broker_sell[i]	= 0;
		}
		
		
		trendData.clear();
		resetHisTrendData();
	}
	
	//
	public	void	copy(tagLocalStockData data)
	{
		bRightHJJT 	= data.bRightHJJT;
		bRightJGTX 	= data.bRightJGTX;
		
		code		= data.code;
		name		= data.name;
		name_w		= data.name_w;
		yesterday	= data.yesterday;
		open 		= data.open;
		high 		= data.high;
		low 		= data.low;
		now			= data.now;
		volume		= data.volume;
		amount		= data.amount;
		realvol		= data.realvol;
		market		= data.market;
		zqlb		= data.zqlb;
		ldflag		= data.ldflag;
		lb			= data.lb;
		hsl			= data.hsl;
		hqdate		= data.hqdate;
		hqtime		= data.hqtime;
		syl			= data.syl;
		zd			= data.zd;
		average		= data.average;
		pricedot	= data.pricedot;
		unit		= data.unit;
		
		//by ngj add
		cjbs		= data.cjbs;
		sjl			= data.sjl;
		zhenfu		= data.zhenfu;
		zdf			= data.zdf;
		zf5			= data.zf5;
		ltsz		= data.ltsz;
		zsz			= data.zsz;
		qrd			= data.qrd;
		ltbg=data.ltbg;			//INT64	流通B股	单位股
		mgjzc=data.mgjzc;			//INT32	每股净资产	放大10000倍
		zzcsyl=data.zzcsyl;			//INT32	净资产收益率	放大10000倍:每股收益/每股净资产*100%
		zcfzl=data.zcfzl;			//INT32	资产负债率	放大10000倍
		zysr=data.zysr;			//INT64	主营收入	单位分，即放大100倍
		zlr=data.zlr;			//INT64	净利润	单位分，即放大100倍
		zzc=data.zzc;			//INT64	总资产	单位分，即放大100倍
		
//		price_buy	= data.price_buy;
//		totalvol_buy	= data.totalvol_buy;
//		volrecord_buy	= data.volrecord_buy;
//		volcount_buy	= data.volcount_buy;
//		vol_buy=new short[volcount_buy];
//		for(int i=0; i<volcount_buy; i++) {
//			vol_buy[i] = data.vol_buy[i];
//		}
//		
//		price_sell	= 0;
//		totalvol_sell	= data.totalvol_sell;
//		volrecord_sell	= data.volrecord_sell;
//		volcount_sell	= data.volcount_sell;
//		vol_sell=new short[volcount_sell];
//		for(int i=0; i<volcount_sell; i++) {
//			vol_sell[i] = data.vol_sell[i];
//		}
		
		tradetimenum= data.tradetimenum;
		for(int i=0; i<6; i++) {
			tradetime[i] = data.tradetime[i];
		}
		
		index_wb	= data.index_wb;
		index_wc	= data.index_wc;
		index_up	= data.index_up;
		index_same 	= data.index_same;
		index_down 	= data.index_down;
		index_hlz 	= data.index_hlz;
		upprice 	= data.upprice;
		downprice 	= data.downprice;
		out			= data.out;
		ltgb		= data.ltgb;
		zgb			= data.zgb;
		ycmgsy		= data.ycmgsy;
		zxmgsy		= data.zxmgsy;
		volrate		= data.volrate;
		
//		for (int i = 0; i < 5; i++) {
//			buyprice[i] = data.buyprice[i];
//			buyvolume[i] = data.buyvolume[i];
//			sellprice[i] = data.sellprice[i];
//			sellvolume[i] = data.sellvolume[i];
//		}
		for (int i = 0; i < 10; i++) {
			buyprice10[i] = data.buyprice10[i];
			buyvolume10[i] = data.buyvolume10[i];
			sellprice10[i] = data.sellprice10[i];
			sellvolume10[i] = data.sellvolume10[i];
		}
		
		MMQ 	= data.MMQ;
		ZLZJ 	= data.ZLZJ;
		RZJ5 	= data.RZJ5;
		RZJ10 	= data.RZJ10;
		RZJ20 	= data.RZJ20;
		ZLLH 	= data.ZLLH;
		BuyAvr5 = data.BuyAvr5;
		SellAvr5 = data.SellAvr5;
		MainBuyAmount = data.MainBuyAmount;
		MainSellAmount = data.MainSellAmount;
		MainBuyVolume = data.MainBuyVolume;
		MainSellVolume = data.MainSellVolume;
		maincc = data.maincc;
		
		//股指期货
		JRCCS.clear();
		JRCCS.copy(data.JRCCS);
		for (int i = 0; i < 2; i++)
		{
			KPC[i]	= data.KPC[i];
			XKP[i]	= data.XKP[i];
		}
		ZRJSJ = data.ZRJSJ;
		
		//
		broker_count_buy = data.broker_count_buy;
		broker_count_sell = data.broker_count_sell;
		for (int i = 0; i < 40; i++)
		{
			broker_buy[i]	= data.broker_buy[i];
			broker_sell[i]	= data.broker_sell[i];
		}
		
		
		trendData.clear();
		for(int i=0; i<data.trendData.size(); i++) {
			trendData.add(data.trendData.get(i));
		}
		resetHisTrendData();
	}

	/**
	 * 通过字段Id获取数据
	 * by hj
	 * @param id
	 * @return
     */
	public tagListItemStockData getCurData(int id)
	{
		String ret = new String();
		int	col = Color.WHITE;
		
		switch(id)
		{
		case Global_Define.FIELD_HQ_YESTERDAY:	//昨日收盘价
			ret = ViewTools.getStringByPrice(yesterday, now, pricedot, xsws);
			break;
		case Global_Define.FIELD_HQ_OPEN:		//今日开盘价
			ret = ViewTools.getStringByPrice(open, now, pricedot, xsws);
			col = ViewTools.getColor(open, (IsQH() ? ZRJSJ : yesterday));
			break;
		case Global_Define.FIELD_HQ_HIGH:		//最高价
			ret = ViewTools.getStringByPrice(high, now, pricedot, xsws);
			col = ViewTools.getColor(high, (IsQH() ? ZRJSJ : yesterday));
			break;	
		case Global_Define.FIELD_HQ_LOW:		//最低价
			ret = ViewTools.getStringByPrice(low, now, pricedot, xsws);
			col = ViewTools.getColor(low, (IsQH() ? ZRJSJ : yesterday));
			break;	
		case Global_Define.FIELD_HQ_NOW:		//现价
			ret = ViewTools.getStringByPrice(now, now, pricedot, xsws);
			col = ViewTools.getColor(now, (IsQH() ? ZRJSJ : yesterday));
			break;	
		case Global_Define.FIELD_HQ_VOLUME:		//总成交量
			ret = ViewTools.getStringByVolume(volume, market, unit, 6, false);
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_BLUE;
			break;	
		case Global_Define.FIELD_HQ_AMOUNT:		//总成交金额
			if(this.market == Global_Define.MARKET_HK){
				ret = ViewTools.getStringByVolume(amount/100, market, 100, 6, false);
			} else {
				ret = ViewTools.getStringByVolume(amount, market, 100, 6, false);
			}
//			ret = ViewTools.getStringByVolume(amount, market, 100, 6, false);
//			ret = ViewTools.getStringByAmount(amount, market, 100, 6, false);
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;	
		case Global_Define.FIELD_HQ_CURVOL:		//现量
			ret = ViewTools.getStringByVolume(realvol, market, unit, 6, false);
			break;
		case Global_Define.FIELD_HQ_MARKET:		//市场
			ret = String.valueOf(market);
			break;
		case Global_Define.FIELD_HQ_CODE:		//代码
			ret = code;
			col = Color.GRAY;
			break;
		case Global_Define.FIELD_HQ_STOCKTYPE:	//类别
			ret = String.valueOf(zqlb);
			break;
		case Global_Define.FIELD_HQ_NAME:		//名称
			ret = name;
			break;
		case Global_Define.FIELD_HQ_NAME_HK_8:	//名称(8)
			ret = name_w;
			break;
		case Global_Define.FIELD_HQ_FLAG:		//雷达标志
			ret = String.valueOf(ldflag);
			break;
		case Global_Define.FIELD_HQ_LB:			//量比
			ret = ViewTools.getStringByInt(lb, 2, 10000, false);
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
		case Global_Define.FIELD_HQ_HSL:		//换手率
			ret = ViewTools.getRate(hsl, false);
			break;
		case Global_Define.FIELD_HQ_SYL:		//市盈率
			ret = ViewTools.getStringByInt(syl, 2, 10000, false);
			break;
		case Global_Define.FIELD_HQ_ZD:			//涨跌
			ret = ViewTools.getStringByPrice(zd, now, pricedot, xsws);
			col = ViewTools.getColor(zd);
			break;	
		case Global_Define.FIELD_HQ_AVERAGE:	//均价
			ret = ViewTools.getStringByPrice(average, now, pricedot, xsws);
			col = ViewTools.getColor(average, yesterday);
			break;	
//		case Global_Define.FIELD_HQ_UNITANDDOT:	//价格位数
//			ret = String.valueOf(pricedot);
//			break;
//		case Global_Define.FIELD_HQ_UNITANDDOT:	//每手股数
//			ret = String.valueOf(unit);
//			break;
		case Global_Define.FIELD_HQ_ZF:			//涨幅
			ret = ViewTools.getRate(zdf, true);
			break;	
		case Global_Define.FIELD_HQ_ZHENFU:		//振幅
			ret = ViewTools.getRate(zhenfu, false);
			break;	
		case Global_Define.FIELD_HQ_SJL:		//市净率
			ret = ViewTools.getStringByInt(sjl, 2, 10000, false);
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
		case Global_Define.FIELD_HQ_5ZF:		//五分钟涨幅
			ret = ViewTools.getRate(zf5, false);
			col = ViewTools.getColor(zf5);
			break;
		case Global_Define.FIELD_HQ_MMPFLAG:	//内外盘标志 第0、1位表示内外盘标志，0 外盘、1内盘、2平盘，注意：其他无效
			ret = String.valueOf(mmpflag);
			break;
		case Global_Define.FIELD_HQ_CJBS:		//成交笔数
			ret = ViewTools.getStringByVolume(cjbs, market, unit, 6, false);
			break;
		case Global_Define.FIELD_HQ_INDEX_WB:	//委比
			ret = ViewTools.getRate(index_wb, false);
			col = ViewTools.getColor(index_wb);
			break;
		case Global_Define.FIELD_HQ_INDEX_WC:	//委差
			ret = ViewTools.getStringByVolume(index_wc, market, unit, 6, false);
			col = ViewTools.getColor(index_wc);
			break;
			
		case Global_Define.FIELD_HQ_MGZZC:	//每股净资产
			ret = ViewTools.getStringByPrice(mgjzc, now, pricedot, xsws);
			col = ViewTools.getColor(mgjzc, yesterday);
			break;
		case Global_Define.FIELD_HQ_ZZCSYL:	//净资产收益率
			ret = ViewTools.getRate(zzcsyl, false);
			break;
		case Global_Define.FIELD_HQ_ZGB:	//总股本
//			L.d("aaaaa", "unit = " + unit);
//			ret = String.valueOf(zgb);
			ret = ViewTools.getStringByGu(zgb, market, unit, 6, false);
			break;
		case Global_Define.FIELD_HQ_LTGB:	//流通股本
//			ret = String.valueOf(ltgb);
			ret = ViewTools.getStringByGu(ltgb, market, unit, 6, false);
			break;

		case Global_Define.FIELD_HQ_LTSZ: 	//流通市值
//			ret = ViewTools.getStringByGu(ltsz, market, unit, 6, false);
			//港股的流通市值显示单位和沪深一样
			ret=ViewTools.getStringByVolume(ltsz, 
					(market==Global_Define.MARKET_HK) ? Global_Define.MARKET_SH : market,
					100, 6, false);
        	col=ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
		case Global_Define.FIELD_HQ_ZSZ:	//总市值
//			ret = ViewTools.getStringByGu(zsz, market, unit, 6, false);
			ret=ViewTools.getStringByVolume(zsz, market, 100, 6, false);
        	col=ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
			
		case Global_Define.FIELD_HQ_INDEX_QRD: //强弱度
			ret = ViewTools.getRate(qrd, false);
			col = ViewTools.getColor(qrd);
			break;
		
		case Global_Define.FIELD_HQ_INDEX_UP:	//上涨家数
			ret = String.valueOf(index_up);
			col	= Color.PRICE_UP;
			break;
		case Global_Define.FIELD_HQ_INDEX_SAME:	//平盘家数
			ret = String.valueOf(index_same);
			break;
		case Global_Define.FIELD_HQ_INDEX_DOWN:	//下跌家数
			ret = String.valueOf(index_down);
			col	= Color.PRICE_DOWN;
			break;		
		case Global_Define.FIELD_HQ_INDEX_HLZ:	//大盘红绿柱
			ret = String.valueOf(index_hlz);
			col = ViewTools.getColor(index_hlz, 0);
			break;		
		case Global_Define.FIELD_HQ_BVOLUME1:	//买一量	单位股
			ret = ViewTools.getStringByVolume(bvolume1, market, unit, 6, false);
			col = ViewTools.getColor(buyprice[0], yesterday);
			break;
		case Global_Define.FIELD_HQ_SVOLUME1:	//卖一量	单位股
			ret = ViewTools.getStringByVolume(svolume1, market, unit, 6, false);
			col = ViewTools.getColor(sellprice[0], yesterday);
			break;
		case Global_Define.FIELD_HQ_INVOLUME:	//内盘总量	单位股
			ret = ViewTools.getStringByVolume(involume1, market, unit, 6, false);
			col = ret.startsWith("--")?Color.WHITE:Color.PRICE_DOWN;
			break;		
		case Global_Define.FIELD_HQ_UPPRICE:	//涨停价格
			ret = ViewTools.getStringByPrice(upprice, now, pricedot, xsws);
			col = ViewTools.getColor(upprice, yesterday);
			break;
		case Global_Define.FIELD_HQ_DOWNPRICE:	//跌停价格
			ret = ViewTools.getStringByPrice(downprice, now, pricedot, xsws);
			col = ViewTools.getColor(downprice, yesterday);
			break;
		case Global_Define.FIELD_HQ_BUYPRICE:	//委买价
			ret = ViewTools.getStringByPrice(buyprice[0], now, pricedot, xsws);
			col = ViewTools.getColor(buyprice[0], (IsQH() ? ZRJSJ : yesterday));
			break;
		case Global_Define.FIELD_HQ_SELLPRICE:	//委卖价
			ret = ViewTools.getStringByPrice(sellprice[0], now, pricedot, xsws);
			col = ViewTools.getColor(sellprice[0], (IsQH() ? ZRJSJ : yesterday));
			break;
		case Global_Define.FIELD_HQ_OUTVOLUME:	//外盘总量	单位股
			ret = ViewTools.getStringByVolume(out, market, unit, 6, false);
			col = ret.startsWith("--")?Color.WHITE:Color.PRICE_UP;
			break;
			
		case Global_Define.FIELD_HQ_STATUNIT_MMQ:	//买卖气
			ret = ViewTools.getStringByInt(MMQ, 2, 10000, false);
			col = ViewTools.getMMQColor(MMQ/10000.0);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_ZLZJ:	//主力增减
			ret = ViewTools.getStringByInt(ZLZJ, 2, 10000, false);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_5RZJ:	//5日增减
			ret = ViewTools.getStringByInt(RZJ5, 3, 10000, false);
			col = ViewTools.getColor(RZJ5, 0);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_10RZJ:	//10日增减
			ret = ViewTools.getStringByInt(RZJ10, 3, 10000, false);
			col = ViewTools.getColor(RZJ10, 0);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_20RZJ:	//20日增减
			ret = ViewTools.getStringByInt(RZJ20, 3, 10000, false);
			col = ViewTools.getColor(RZJ20, 0);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_10RNH:	//10日内红
			ret = String.valueOf(RNH10);
			break;
		case Global_Define.FIELD_HQ_STATUNIT_ZLLH:	//主力连红
			ret = String.valueOf(ZLLH);
			break;
		
		//
		case Global_Define.FIELD_LOCAL_HQDATE:
			ret = ViewTools.formatDate(String.valueOf(hqdate));
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
		case Global_Define.FIELD_LOCAL_HQTIME:
			ret = ViewTools.formatTime(String.valueOf(hqtime));
//			L.d(TAG, "getCurData--->FIELD_LOCAL_HQTIME = " + hqtime);
			col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
			
		//股指期货
		case Global_Define.FIELD_LOCAL_CHICANG:	// 持仓
//			ret = ViewTools.getStringByVolume(JRCCS.jrccl, market, unit, 6, false);
			ret = ViewTools.getStringByGu_unprocess(JRCCS.jrccl, market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;			
		case Global_Define.FIELD_LOCAL_CANGCHA:	// 仓差
//			ret = ViewTools.getStringByVolume((JRCCS.jrccl-JRCCS.zrccl), market, unit, 6, false);
			ret = ViewTools.getStringByGu_unprocess((JRCCS.jrccl-JRCCS.zrccl), market, unit, 6, false);
			col = ret.startsWith("--")?Color.WHITE:ViewTools.getColor((int)(JRCCS.jrccl-JRCCS.zrccl));
			break;			
		case Global_Define.FIELD_LOCAL_JIESUAN:	// 结算
	    	ret = ViewTools.getStringByPrice(JRCCS.jrjsj, now, pricedot, xsws);
			col = ViewTools.getColor(JRCCS.jrjsj, ZRJSJ);
			break;			
		case Global_Define.FIELD_LOCAL_ZUOCHI:	// 昨持
//			ret = ViewTools.getStringByVolume(JRCCS.zrccl, market, unit, 6, false);
			ret = ViewTools.getStringByGu_unprocess(JRCCS.zrccl, market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;			
		case Global_Define.FIELD_LOCAL_KAICANG:	// 开仓
			ret = ViewTools.getStringByGu_unprocess(KPC[0], market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;			
		case Global_Define.FIELD_LOCAL_PINGCANG:	// 平仓
			ret = ViewTools.getStringByGu_unprocess(KPC[1], market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;
		case Global_Define.FIELD_LOCAL_XIANKAI:	// 现开
			ret = ViewTools.getStringByGu_unprocess(XKP[0], market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;			
		case Global_Define.FIELD_LOCAL_XIANPING:	// 现平
			ret = ViewTools.getStringByGu_unprocess(XKP[1], market, unit, 6, false);
	    	col = ret.startsWith("--")?Color.WHITE:Color.COLOR_YELLOW;
			break;		
		case Global_Define.FIELD_HQ_ZRJSJ:		//昨日结算价
			ret = ViewTools.getStringByPrice(ZRJSJ, now, pricedot, xsws);
			col = ViewTools.getColor(ZRJSJ, (IsQH() ? ZRJSJ : yesterday));
			break;

			/**
             * Test hj
			 */
		default:
			ret = "----";
			col = Color.WHITE;
			break;
		}

		tagListItemStockData item = new tagListItemStockData(ret, col);
		return item;
	}
	

	//判断是否指数
	public boolean IsIndex() {
		//
//		L.e("tagLocalStockData", "IsIndex--->zqlb = " + zqlb);
		return zqlb == Global_Define.STOCK_TYPE_INDEX;
	}
	
	//判断是否期货
	public boolean IsQH() {
		return market == Global_Define.MARKET_CF
				|| market == Global_Define.MARKET_IC
				|| market == Global_Define.MARKET_QH;
	}
	//判断是否股指期货
	public boolean IsQH_GuZhi() {
		return market == Global_Define.MARKET_CF;
	}
	//判断是否商品期货
	public boolean IsQH_ShangPin() {
		return market == Global_Define.MARKET_QH;
	}
	//判断是否国外期货
	public boolean IsQH_GuoWai() {
		return market == Global_Define.MARKET_IC;
	}
	
	//判断是否沪深，Level2只有沪深才有买卖队列
	public boolean isHuShen(){
		return market == Global_Define.MARKET_SH || market == Global_Define.MARKET_SZ;
	}
	public boolean isHK(){
		return market == Global_Define.MARKET_HK;
	}
	
	public boolean isUS(){
		return market == Global_Define.MARKET_US;
	}

	//判断是否国际指数
	public boolean isQQZS(){
		return market == Global_Define.MARKET_ZS;
	}
	//判断是否外汇
	public boolean IsWaiHui() {
		return market == Global_Define.MARKET_FX;
	}
	//判断是否板块指数
	public boolean IsBZ() {
		return market == Global_Define.MARKET_BZ;
	}

	/**
	 * hj:获取股票的板块 根据zqlb获取
	 * @return
     */
	public String getBanKuai(){
		if (IsBZ()) {
			switch (Integer.valueOf(zqlb)) {
				case Global_Define.BZ_TYPE_DYBK:
					return "[地]";
				case Global_Define.BZ_TYPE_GNBK:
					return "[概]";
				case Global_Define.BZ_TYPE_YYBK:
					return "[行]";
				default:
					break;
			}
		}
		return null;
	}
	
	//获取市场代码缩写
	public String getMarket() {
		if(market == Global_Define.MARKET_SZ){
        	return "sz";
        }
        else if(market == Global_Define.MARKET_HK) {
        	return "hk";
        }
        else {
        	return "sh";
        }
	}
		
	//收取网络数据后，再转换价格成本地格式，放大10000倍
	public	void	HQ_FIELD_FUNC_PRICE(byte index[], int offset, int num)
	{
		int pricetimes	= getPriceTimes();
		for (int i = 0; i < num; i++)
		{
			HQ_FIELD_FUNC_PRICE(index[offset+i], pricetimes);
		}
	}
	public	void HQ_FIELD_FUNC_PRICE(int index, int pricetimes)
	{
		switch (index)
		{
		case Global_Define.FIELD_HQ_YESTERDAY:
			yesterday	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_OPEN:
			open		*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_HIGH:
			high		*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_LOW:
			low		*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_NOW:
			now		*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_ZD:
			zd		*= pricetimes;
			break;

		case Global_Define.FIELD_HQ_UPPRICE:
			upprice 	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_DOWNPRICE:
			downprice *= pricetimes;
			break;
		case Global_Define.FIELD_HQ_BUYPRICE:	//买卖价和五档和十档不能有两个字段同时出现，否则会出现问题
			buyprice[0] 	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_SELLPRICE:
			sellprice[0] 	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_BS5:
		{
			for (int i = 0; i < 5; i++)
			{
				buyprice[i]	*= pricetimes;
			}
			for (int i = 0; i < 5; i++)
			{
				sellprice[i]	*= pricetimes;
			}
			break;
		}
//		case Global_Define.FIELD_HQ_L2_BS10:
//			{
//				for (int i = 0; i < 20; i++)
//				{
//					bsprice[i]	*= pricetimes;
//				}
//				break;
//			}
		case Global_Define.FIELD_HQ_L2_VBSVOLUME:
			vbuyprice	*= pricetimes;
			vsellprice	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_AVGVOL5:
			volrate	/= getTradeMinute();
			break;
		case Global_Define.FIELD_HQ_L2_BSQUEUE://买卖队列信息BuySellQueue[2]
			price_buy	*= pricetimes;
			price_sell	*= pricetimes;
			break;
		case Global_Define.FIELD_HQ_L2_BS10://十档买卖盘 BuySell10
			for(int i=0;i<10;i++){
				buyprice10[i]	*= pricetimes;
				sellprice10[i]	*= pricetimes;
			}
			break;
		
		case (byte)Global_Define.FIELD_HQ_JRCCS:	// 结算
	    	JRCCS.jrjsj	*= pricetimes;
			break;		
		case (byte)Global_Define.FIELD_HQ_ZRJSJ://昨日结算价
			ZRJSJ		*= pricetimes;
			break;
		
		}
	}
	

	public void  HQ_FIELD_FUNC_UPDATE(tagLocalStockData in, byte index)
	{
		switch ((int)index)
		{
		case Global_Define.FIELD_HQ_YESTERDAY:
			yesterday	= in.yesterday;
			break;
		case Global_Define.FIELD_HQ_OPEN:
			open		= in.open;
			break;
		case Global_Define.FIELD_HQ_HIGH:
			high		= in.high;
			break;
		case Global_Define.FIELD_HQ_LOW:
			low			= in.low;
			break;
		case Global_Define.FIELD_HQ_NOW:
			now			= in.now;
			break;
		case Global_Define.FIELD_HQ_VOLUME:
			volume		= in.volume;
			break;
		case Global_Define.FIELD_HQ_AMOUNT:
			amount		= in.amount;
			break;
		case Global_Define.FIELD_HQ_CURVOL:
			realvol		= in.realvol;
			break;
		case Global_Define.FIELD_HQ_MARKET:
			market		= in.market;
			break;
		case Global_Define.FIELD_HQ_CODE:
			code = in.code;
			break;

		case Global_Define.FIELD_HQ_STOCKTYPE:
			zqlb		= in.zqlb;
			break;
		case Global_Define.FIELD_HQ_NAME:
			name = in.name;
			break;
		case Global_Define.FIELD_HQ_NAME_HK_8:		//名称(8)
			name_w = in.name_w;
			break;
		case Global_Define.FIELD_HQ_FLAG:
			ldflag		= in.ldflag;
			break;
		case Global_Define.FIELD_HQ_LB:
			lb			= in.lb;
			break;
		case Global_Define.FIELD_HQ_HSL:
			hsl			= in.hsl;
			break;
		case Global_Define.FIELD_HQ_SYL:
			syl			= in.syl;
			break;
		case Global_Define.FIELD_HQ_ZD:
			zd			= in.zd;
			break;

		case Global_Define.FIELD_HQ_AVERAGE:
			average		= in.average;
			break;
		case Global_Define.FIELD_HQ_UNITANDDOT:
			{
				pricedot	= in.pricedot;
				unit		= in.unit;
				break;
			}
		case Global_Define.FIELD_HQ_TRADETIMENUM:
			{
				tradetimenum	= in.tradetimenum;
				tradetime[0]	= in.tradetime[0];
				tradetime[1]	= in.tradetime[1];
				tradetime[2]	= in.tradetime[2];
				tradetime[3]	= in.tradetime[3];
				tradetime[4]	= in.tradetime[4];
				tradetime[5]	= in.tradetime[5];
				
				for (int i = 1; i < tradetimenum; i+=2)
				{
					if (tradetime[i] < tradetime[i-1]) {
						//如果收盘时间小于开盘时间，可能是跨天了，收盘时间加24小时
						tradetime[i] += 24*60;
					}
				}
				break;
			}

		case Global_Define.FIELD_HQ_HQTIME:
			hqtime	= in.hqtime;
			hqdate	= in.hqdate;
			break;
		case Global_Define.FIELD_HQ_INDEX_WB:
			index_wb	= in.index_wb;
			break;
		case Global_Define.FIELD_HQ_INDEX_WC:
			index_wc	= in.index_wc;
			break;
		case Global_Define.FIELD_HQ_INDEX_UP:
			index_up	= in.index_up;
			break;
		case Global_Define.FIELD_HQ_INDEX_SAME:
			index_same	= in.index_same;
			break;
		case Global_Define.FIELD_HQ_INDEX_DOWN:
			index_down	= in.index_down;
			break;
		case Global_Define.FIELD_HQ_INDEX_HLZ:
			index_hlz	= in.index_hlz;
			break;

		case Global_Define.FIELD_HQ_UPPRICE:
			upprice		= in.upprice;
			break;
		case Global_Define.FIELD_HQ_DOWNPRICE:
			downprice	= in.downprice;
			break;
		case Global_Define.FIELD_HQ_BUYPRICE:
			buyprice[0]	= in.buyprice[0];
			break;
		case Global_Define.FIELD_HQ_SELLPRICE:
			sellprice[0]	= in.sellprice[0];
			break;
		case Global_Define.FIELD_HQ_BS5:
			{
				for (int i = 0; i < 5; i++)
				{
					buyprice[i]	= in.buyprice[i];
					buyvolume[i] = in.buyvolume[i];
				}
				for (int i = 0; i < 5; i++)
				{
					sellprice[i]	= in.sellprice[i];
					sellvolume[i]	= in.sellvolume[i];
				}
				break;
			}
		case Global_Define.FIELD_HQ_OUTVOLUME:
			out		= in.out;
			break;

		case Global_Define.FIELD_HQ_L2_VBSVOLUME:
			vbuyprice	= in.vbuyprice;
			vbuyvolume	= in.vbuyvolume;
			vsellprice	= in.vsellprice;
			vsellvolume	= in.vsellvolume;
			break;

		case Global_Define.FIELD_HQ_LTGB:
			ltgb	= in.ltgb;
			break;
		case Global_Define.FIELD_HQ_ZGB:
			zgb	= in.zgb;
			break;
		case Global_Define.FIELD_HQ_YCMGSY:
			ycmgsy	= in.ycmgsy;
			break;
		case Global_Define.FIELD_HQ_ZXMGSY:
			zxmgsy	= in.zxmgsy;
			break;
		case Global_Define.FIELD_HQ_AVGVOL5:
			volrate	= in.volrate;
			break;

		case Global_Define.FIELD_HQ_STATUNIT_COUNT:
//			System.arraycopy(in.BuySellGold, 0, out.BuySellGold, 0, 8);
			BuySellGold = in.BuySellGold;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_VOLUME:
//			System.arraycopy(in.VolumeGold, 0, out.VolumeGold, 0, 8);
			VolumeGold = in.VolumeGold;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_AMOUNT:
//			System.arraycopy(in.AmountGold, 0, out.AmountGold, 0, 8);
			AmountGold = in.AmountGold;
			break;
		case Global_Define.FILED_HQ_STATUNIT_AMOUNT_MMC:
			AmountMMC = in.AmountMMC;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_FLCCBL:
			flccbl = in.flccbl;
			break;
			
		case Global_Define.FIELD_HQ_STATUNIT_MMQ:
			MMQ	= in.MMQ;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_ZLZJ:
			ZLZJ	= in.ZLZJ;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_5RZJ:
			RZJ5	= in.RZJ5;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_10RZJ:
			RZJ10	= in.RZJ10;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_20RZJ:
			RZJ20	= in.RZJ20;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_10RNH:
			RNH10	= in.RNH10;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_ZLLH:
			ZLLH	= in.ZLLH;
			break;
			//黄金版
		case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNTB5:
			BuyAvr5	= in.BuyAvr5;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNTS5:
			SellAvr5	= in.SellAvr5;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNT:
			MainBuyAmount	= in.MainBuyAmount;
			MainSellAmount	= in.MainSellAmount;
			break;
		case Global_Define.FIELD_HQ_STATUNIT_MAINVOLUME:
			MainBuyVolume	= in.MainBuyVolume;
			MainSellVolume	= in.MainSellVolume;
			break;
		case Global_Define.FIELD_HQ_MAINCC:
			maincc	= in.maincc;
			break;
			
			//by ngj add
		case Global_Define.FIELD_HQ_ZHENFU://振幅
			zhenfu	= in.zhenfu;
			break;
		case Global_Define.FIELD_HQ_ZF:		//涨跌幅
			zdf	= in.zdf;
			break;
		case Global_Define.FIELD_HQ_5ZF:	//五分钟涨幅
			zf5	= in.zf5;
			break;
		case Global_Define.FIELD_HQ_SJL://市净率
			sjl		= in.sjl;
			break;
		case (byte)Global_Define.FIELD_HQ_LAST_RADAR_ID:
			radar_node = in.radar_node;
			break;
		case (byte)Global_Define.FIELD_HQ_LAST_RADAR_TITLE_S:
			radar_title = in.radar_title;
			break;
//			Global_Define.FIELD_HQ_LTSZ, //流通市值
//			Global_Define.FIELD_HQ_ZSZ,//总市值
//			Global_Define.FIELD_HQ_INDEX_QRD, //强弱度
//			Global_Define.FIELD_HQ_YESTERDAY //昨收
		case Global_Define.FIELD_HQ_MMPFLAG:
			mmpflag=in.mmpflag;
			break;
		case Global_Define.FIELD_HQ_CJBS: //成交笔数
			cjbs=in.cjbs;
			break;
		case Global_Define.FIELD_HQ_LTSZ: //流通市值
			ltsz=in.ltsz;
			break;
		case Global_Define.FIELD_HQ_ZSZ://总市值
			zsz=in.zsz;
			break;
		case Global_Define.FIELD_HQ_INDEX_QRD: //强弱度
			qrd=in.qrd;
			break;
		case Global_Define.FIELD_HQ_LTBG: //INT64	流通B股	单位股
			ltbg=in.ltbg;
			break;
		case Global_Define.FIELD_HQ_MGZZC: //INT32	每股净资产	放大10000倍
			mgjzc=in.mgjzc;
			break;
		case Global_Define.FIELD_HQ_ZZCSYL: //INT32	净资产收益率	放大10000倍:每股收益/每股净资产*100%
			zzcsyl=in.zzcsyl;
			break;
		case Global_Define.FIELD_HQ_ZCFZL: //INT32	资产负债率	放大10000倍
			zcfzl=in.zcfzl;
			break;
		case Global_Define.FIELD_HQ_ZYSR: //INT64	主营收入	单位分，即放大100倍
			zysr=in.zysr;
			break;
		case Global_Define.FIELD_HQ_ZLR: //INT64	净利润	单位分，即放大100倍
			zlr=in.zlr;
			break;
		case Global_Define.FIELD_HQ_ZZC: //INT64	总资产	单位分，即放大100倍
			zzc=in.zzc;
			break;
		case Global_Define.FIELD_HQ_L2_BS10://十档买卖盘 BuySell10
			for (int i = 0; i < 10; i++)
			{
				buyprice10[i]	= in.buyprice10[i];
				buyvolume10[i] = in.buyvolume10[i];
			}
			for (int i = 0; i < 10; i++)
			{
				sellprice10[i]	= in.sellprice10[i];
				sellvolume10[i]	= in.sellvolume10[i];
			}
			break;
		case Global_Define.FIELD_HQ_L2_BSQUEUE://买卖队列信息BuySellQueue[2]
			price_buy	= in.price_buy;
			totalvol_buy	= in.totalvol_buy;
			volrecord_buy	= in.volrecord_buy;
			volcount_buy	= in.volcount_buy;
			for (int i = 0; i < volcount_buy; i++)
			{
				vol_buy[i]	= in.vol_buy[i];
			}
			//
			price_sell	= in.price_sell;
			totalvol_sell	= in.totalvol_sell;
			volrecord_sell	= in.volrecord_sell;
			volcount_sell	= in.volcount_sell;
			for (int i = 0; i < volcount_sell; i++)
			{
				vol_sell[i]	= in.vol_sell[i];
			}
			break;
		case Global_Define.FIELD_HQ_HK_BSBROKER:		//买经纪排位数据 + 卖经纪排位数据
			broker_count_buy = in.broker_count_buy;
			for (int i = 0; i < broker_count_buy; i++)
			{
				broker_buy[i]	= in.broker_buy[i];
			}
			
			broker_count_sell = in.broker_count_sell;
			for (int i = 0; i < broker_count_sell; i++)
			{
				broker_sell[i]	= in.broker_sell[i];
			}
			break;
		case Global_Define.FIELD_HQ_HQTIME_HAVE_SECOND://行情时间，带秒 低4字节：YYYYMMDD 高4字段：HHMMSS
			hqtime_date=in.hqtime_date;
			hqtime_time=in.hqtime_time;
			break;
		case Global_Define.FIELD_HQ_BVOLUME1://买一量	单位股
			bvolume1=in.bvolume1;
			break;
		case Global_Define.FIELD_HQ_SVOLUME1://卖一量	单位股
			svolume1=in.svolume1;
			break;
		case Global_Define.FIELD_HQ_INVOLUME://内盘总量	单位股
			involume1=in.involume1;
			break;
			
		//股指期货
		case (byte)Global_Define.FIELD_HQ_JRCCS://今日持仓+今日结算价+昨日持仓量+持仓性质
			JRCCS = in.JRCCS;
			break;
			
		case (byte)Global_Define.FIELD_HQ_KPC://开仓+平仓
			for(int i=0; i<2; ++i)
			{
				KPC[i] = in.KPC[i];
			}
			break;
			
		case (byte)Global_Define.FIELD_HQ_ZRJSJ://昨日结算价
			ZRJSJ = in.ZRJSJ;
			break;
			
		case (byte)Global_Define.FIELD_HQ_XKP: //现开+平开
			for(int i=0; i<2; ++i)
			{
				XKP[i] = in.XKP[i];
			}
			break;
						
		default:
			//有不认识的字段，需要引起注意
			System.out.println( "----- HQ_FIELD_FUNC_UPDATE ----- Unknown Field - "+index);
			break;
		}
	}

	//获取价格倍数，目的是将所有网络数据价格转换成本地放大10000倍，如网络价格放大了1000倍，则本函数返回10
	public	static int		getPriceTimes(int dot)
	{
		if (dot == 1)
		{
			return 1000;
		}
		else if (dot == 2)
		{
			return 100;
		}
		else if (dot == 3)
		{
			return 10;
		}
		else if (dot == 0)
		{
			return 10000;
		}
		else
		{
			return 1;
		}
	}
	//
	public	int		getPriceTimes()
	{
		return tagLocalStockData.getPriceTimes(pricedot);
	}
	
	//计算量比
	public	int		calculate_lb() 
	{ 
		long d = volrate * getTimePoint(hqtime/10000*60 + hqtime/100%100);
		
		if (d < 1)
		{
			return 0;
		}
		else
		{
//			int tradeMinute = getTradeMinute();
			return (int)(volume * 10000 / d);
		}
	}

//	/**
//	* 获取对应时间的交易分钟数，返回值从1开始
//	*/
//	public	int		getTimePoint(int hour, int minute) 
//	{ 
//		return getTimePoint(hour*60 + minute); 
//	}
//	public	int		getTimePoint(int time)
//	{
//		if (tradetimenum > 0)	//计算第一段
//		{
//			if (time < tradetime[0])	//OPEN1
//			{
//				return 1;
//			}
//			else if (time < tradetime[1])	//CLOSE1
//			{
//				return time - tradetime[0] + 1;
//			}
//		}
//
////		int firstnum	= tradetime[1] - tradetime[0] + 1;
//		int firstnum	= 0;
//		if (tradetime[0]>tradetime[1])
//			firstnum 	= tradetime[1]+24*60 - tradetime[0] + 1;
//		else
//			firstnum	= tradetime[1] - tradetime[0] + 1;
//		
//		if (tradetimenum > 1)	//计算第二段
//		{
//			if (time <= tradetime[2])		//OPEN2
//			{
//				return firstnum;
//			}
//			else if (time < tradetime[3])	//CLOSE2
//			{
//				return firstnum + time - tradetime[2];
//			}
//		}
//		else
//		{
//			return firstnum;
//		}
//
////		int secondnum	= tradetime[3] - tradetime[2] + firstnum;
//		int secondnum	= 0;
//		if (tradetime[2]>tradetime[3])
//			secondnum 	= tradetime[3]+24*60 - tradetime[2] + firstnum;
//		else
//			secondnum	= tradetime[3] - tradetime[2] + firstnum;
//
//		if (tradetimenum > 2)	//计算第三段，最多三段
//		{
//			if (time <= tradetime[4])		//OPEN3
//			{
//				return secondnum;
//			}
//			else if (time < tradetime[5])	//CLOSE3
//			{
//				return secondnum + time - tradetime[4];
//			}
//		}
//		else
//		{
//			return secondnum;
//		}
//
////		int thirdnum	= tradetime[5] - tradetime[4] + secondnum;
//		int thirdnum	= 0;
//		if (tradetime[4]>tradetime[5])
//			thirdnum 	= tradetime[5]+24*60 - tradetime[4] + secondnum;
//		else
//			thirdnum	= tradetime[5] - tradetime[4] + secondnum;
//
//		return thirdnum;
//	}
	
	/**
	* 获取对应时间的交易分钟数，返回值从1开始
	*/
	public	int		getTimePoint(int hour, int minute) 
	{ 
		return getTimePoint(hour*60 + minute); 
	}
	public	int		getTimePoint(int time)
	{
		int pos = 0;
		tagTimePointRet TPData = getTimePointBase(time);
		pos = TPData.pos;	//
        if (TPData.ret_val < 0)
        {
            TPData = getTimePointBase(time+24*60);
            if (TPData.ret_val == 0)
            {
                pos = TPData.pos;
            }
        }
//        else
//        	pos = TPData.pos;
        
        return pos;
	}
	class tagTimePointRet
	{
		public int ret_val = 0;
		public int pos = 0;
		
		public tagTimePointRet(int ret_val, int pos)
		{
			this.ret_val = ret_val;
			this.pos = pos;
		}
	}
	tagTimePointRet getTimePointBase(int time)
	{
		tagTimePointRet TPData = new tagTimePointRet(0, 0);
		
	    if (tradetimenum > 0)	//计算第一段
	    {
	        if (time < tradetime[0])	//OPEN1
	        {
	        	TPData.pos = 1;
	        	TPData.ret_val = -1;
	        	return TPData;
//	            pos = 1;
//	            return -1;
	        }
	        else if (time <= tradetime[1])	//CLOSE1
	        {
	        	TPData.pos = time - tradetime[0] + 1;
				TPData.ret_val = 0;
	        	return TPData;
//	            pos =  time - tradetime[0] + 1;
//	            return 0;
	        }
	    }
	    
	    int firstnum	= tradetime[1] - tradetime[0] + 1;
	    
	    if (tradetimenum > 1)	//计算第二段
	    {
	        if (time <= tradetime[2])		//OPEN2
	        {
	        	TPData.pos = firstnum;
	        	TPData.ret_val = -1;
	        	return TPData;
//	            pos =  firstnum;
//	            return -1;
	        }
	        else if (time <= tradetime[3])	//CLOSE2
	        {
	        	TPData.pos = firstnum + time - tradetime[2];
	        	TPData.ret_val = 0;
	        	return TPData;
//	            pos = firstnum + time - tradetime[2];
//	            return 0;
	        }
	    }
	    else
	    {
	    	TPData.pos = firstnum;
        	TPData.ret_val = -1;
        	return TPData;
//	        pos = firstnum;
//	        return -1;
	    }
	    
	    int secondnum	= tradetime[3] - tradetime[2] + firstnum;
	    
	    if (tradetimenum > 2)	//计算第三段，最多三段
	    {
	        if (time <= tradetime[4])		//OPEN3
	        {
	        	TPData.pos = secondnum;
	        	TPData.ret_val = -1;
	        	return TPData;
//	        	pos = secondnum;
//	            return -1;
	        }
	        else if (time <= tradetime[5])	//CLOSE3
	        {
	        	TPData.pos = secondnum + time - tradetime[4];
	        	TPData.ret_val = 0;
	        	return TPData;
//	            pos = secondnum + time - tradetime[4];
//	            return 0;
	        }
	    }
	    else
	    {
	    	TPData.pos = secondnum;
        	TPData.ret_val = -1;
        	return TPData;
//	        pos = secondnum;
//	        return -1;
	    }
	    
	    int thirdnum	= tradetime[5] - tradetime[4] + secondnum;

	    TPData.pos = thirdnum;
    	TPData.ret_val = -1;
    	return TPData;
//	    pos =  thirdnum;
//	    return -1; 
	}
	
	
	
	
	public	int		getMinuteFromPoint_New(int index)
	{
		int ret = getMinuteFromPoint(index);
		if (ret>=24*60)
		{
			ret -= 24*60;
		}
		return ret;
	}
	//根据走势分钟点转换成对应的时间,返回hour*60 + minute
	//index从0开始
	public	int		getMinuteFromPoint(int index)
	{
		if (index <= 0)
		{
			return tradetime[0];	//OPEN1
		}

		int firstnum	= 0;
		int secondnum	= 0;
		int thirdnum	= 0;
		if (tradetime[0]>tradetime[1])
			firstnum 	= tradetime[1]+24*60 - tradetime[0] + 1;
		else
			firstnum	= tradetime[1] - tradetime[0] + 1;

		if (tradetime[2]>tradetime[3])
			secondnum 	= tradetime[3]+24*60 - tradetime[2] + firstnum;
		else
			secondnum	= tradetime[3] - tradetime[2] + firstnum;

		if (tradetime[4]>tradetime[5])
			thirdnum 	= tradetime[5]+24*60 - tradetime[4] + secondnum;
		else
			thirdnum	= tradetime[5] - tradetime[4] + secondnum;

//		int firstnum	= tradetime[1] - tradetime[0] + 1;
//		int secondnum	= tradetime[3] - tradetime[2] + firstnum;
//		int thirdnum	= tradetime[5] - tradetime[4] + secondnum;
		if (index >= thirdnum)	//目的是防止数据错误
		{
			index = thirdnum - 1;
		}

		if (index < firstnum)
		{
			return tradetime[0] + index;
		}

		if (index < secondnum)
		{
			return tradetime[2] + index - firstnum+1;
		}

		return tradetime[4] + index - secondnum+1;

	}

	//获取某市场一天的交易分钟数
	public	int		getTradeMinute()
	{
		int num = 0;
		if (tradetime[0]>tradetime[1])
			num 	+= tradetime[1]+24*60 - tradetime[0] + 1;
		else
			num 	+= tradetime[1] - tradetime[0] + 1;
		
		if (tradetime[2]>tradetime[3])
			num 	+= tradetime[3]+24*60 - tradetime[2];
		else
			num 	+= tradetime[3] - tradetime[2];
		
		if (tradetime[4]>tradetime[5])
			num += tradetime[5]+24*60 - tradetime[4];
		else
			num += tradetime[5] - tradetime[4];
			
		return num;
//		return (tradetime[1] - tradetime[0] + 1) + (tradetime[3] - tradetime[2]) + (tradetime[5] - tradetime[4]);
	}
	
	public	int		getTimeDay()
	{
		return hqdate;
	}
	public	int		getTimeMinutes()
	{
		return hqtime/10000*60 + hqtime/100%100;
	}

	// 获取走势数
	public int getTrendNum() {
		return trendData.size();
	}
	//
	public tagLocalTrendData getTrendData(int index) {
		if (index >= trendData.size() || index < 0) {
	    	return null;
		}
		return trendData.get(index);
	}
	//
	public void resetTrendData() {
		trendData.clear();
	}
	
	// 历史走势
	public int getHisTrendNum() {
		//return hisTrendData.size();
		return 	hisDays;
	}
	public void resetHisTrendData() {
		for(int i=0; i<MAX_HIS_NUM; i++) {
			hisTrendData[i].date = 0;
			hisTrendData[i].yesterday = 0;
			hisTrendData[i].requestCode = 0;
			hisTrendData[i].trenddata.clear();
		}
		hisDays = 0;
	}
//	//
//	public tagLocalTrendData getHisTrendData(int index) {
//		if (index >= hisTrendData.size() || index < 0) {
//			return null;
//		}
//		return hisTrendData.get(index);
//	}
	//
	
}
