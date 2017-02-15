package project.beans;

import java.util.ArrayList;

/**
 * 历史走势
 */
public class tagHisTrendData {
	
	public 	int			date;			//日期
	public	int			yesterday;		//昨日收盘价
	public 	ArrayList<tagLocalTrendData>	trenddata;
	
	public int 			requestCode;	//用于区别多日历史走势
	
	public tagHisTrendData() {
		
		trenddata	= new ArrayList<tagLocalTrendData> ();
	}
	
	// 历史走势
	public int getTrendNum() {
		return trenddata.size();
	}
	//
	public tagLocalTrendData getTrendData(int index) {
		if (index >= trenddata.size() || index < 0) {
			return null;
		}
		return trenddata.get(index);
	}
	//
	public void resetTrendData() {
		trenddata.clear();
	}

}
