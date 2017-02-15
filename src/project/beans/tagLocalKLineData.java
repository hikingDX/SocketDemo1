package project.beans;



public class tagLocalKLineData {

	public int		qldate;
	public int		date;
	public int 		time;
	public int		open;			//开
	public int		high;			//高
	public int		low;			//低
	public int 		close;			//收
	public long		volume;			//成交量
	public long		amount;			//成交额
	public int		ZLCC;			//主力持仓	放大10000倍
	public int		ZLDX;			//主力动向	放大10000倍
	public int		ZLHYD;			//主力活跃度	放大10000倍
	public int		MMQ;			//买卖气		放大10000倍
	public long 	ccl;			//持仓量	单位股,存储与上一笔之差
	public int		jsj;			//结算价	*价格倍数
	public int		qrbz;			//强弱标志  (黄金阶梯）(> 0 强)  (==0 正常) (< 0弱)


	public tagLocalKLineData() {}
	
/*	public tagLocalKLineData(int date, int time, int open, int high, int low, int close, long volume, long amount)
	{
		this.time	= time;
		this.date	= date;
		this.open	= open;
		this.high	= high;
		this.low	= low;
		this.close	= close;
		this.volume	= volume;
		this.amount	= amount;
	}*/
	
	public void Clear()
	{
		this.qldate = 0;
		this.time	= 0;
		this.date	= 0;
		this.open	= 0;
		this.high	= 0;
		this.low	= 0;
		this.close	= 0;
		this.volume	= 0;
		this.amount	= 0;	
		this.ZLCC	= 0;
		this.ZLDX	= 0;
		this.ZLHYD	= 0;
		this.MMQ	= 0;
		this.ccl	= 0;
		this.jsj	= 0;
		this.qrbz	= 0;
	}
	
	public void Copy(tagLocalKLineData data)
	{
		this.qldate = data.qldate;
		this.time	= data.time;
		this.date	= data.date;
		this.open	= data.open;
		this.high	= data.high;
		this.low	= data.low;
		this.close	= data.close;
		this.volume	= data.volume;
		this.amount	= data.amount;
		this.ZLCC	= data.ZLCC;
		this.ZLDX	= data.ZLDX;
		this.ZLHYD	= data.ZLHYD;
		this.MMQ	= data.MMQ;
		this.ccl	= data.ccl;
		this.jsj	= data.jsj;
		this.qrbz	= data.qrbz;
	}
	
	//均线
	public static class tagKAverageInfo {
		
		public int[] 		data;
		public int			para;
		public int			color;

		public tagKAverageInfo() {
			//
			data 	= 	new int[400];
		}
	}
	
	// 权息数据
	public static class tagWeightData {
		public int date; // 资料日期
		public int SGS; // 每10000股送股数 INT32
		public int PDS; // 每10000股配股数 INT32
		public int PGJ; // 配股价 INT32 *10000
		public int HL; 	// 每10000股红利 INT32
		public int ZZGS; // 每10000股转增股数 INT32

		public boolean isQXDate() // 检查是否是权息日
		{
			return (SGS > 0 || (PGJ > 0 && PDS > 0) || HL > 0 || ZZGS > 0);
		}
	}

}
