package project.beans;

public class MHC_SORT_RESPONE  
{
	public byte				market;						//市场代码
	public byte				style;						//股票类别
	public byte				sorttype;					//排序类别,最高位表示排序方向，低七位可表示0-127种排序类别；
															//最高位为0时表示从高到低，1表示从低到高
	public short			nTotalNum;					//本分类股票数
	public short			nStartPos;					//起始位置
	public short			nRecordNum;					//记录数
	//MHC_AppointData		field;
	//股票一
	//股票二
	
	public MHC_SORT_RESPONE()
	{
		nTotalNum	= 0;
		nStartPos	= 0;
		nRecordNum	= 0;
	}
}
