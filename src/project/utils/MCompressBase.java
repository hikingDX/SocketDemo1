package project.utils;

public class MCompressBase {
	MCompressBase() {}
	//该类库将错误分为3个级别，操作系统错误、公共类库错误、应用层错误
	//对于前两种错误采用错误代码表示，并且通过函数返回值一层一层向上传递，一直传到应用层。应用层利用日志系统将他们表现出来。
	//所以，对于绝大部分需要判断的函数都采用返回int的做法，>=0表示正常返回，<0表示错误的发生
	//------------------------------------------------------------------------------------------------------------------------------
	public static final int MERROR_SYSTEM		=		0x80000000;					//操作系统错误
	public static final int	MERROR_USER			=		0xA0000000;					//自定义错误
	//------------------------------------------------------------------------------------------------------------------------------
	//自定义错误的详细描述
	//..............................................................................................................................
	public static final int	ERR_PUBLIC_NOMEMORY	=		(MERROR_USER + 1);			//没有足够的内存可以分配
	public static final int	ERR_PUBLIC_SLOPOVER	=		(MERROR_USER + 2);			//参数错误或越界
	public static final int	ERR_PUBLIC_NOINSTANCE	=	(MERROR_USER + 3);			//对象没有初始化
	public static final int	ERR_MSTRING_NOMATCH	=		(MERROR_USER + 4);			//没有匹配的元素
	public static final int	ERR_MDATETIME_INVALID	=	(MERROR_USER + 5);			//无效的时间或日期
	public static final int	ERR_MFILE_READALL	=		(MERROR_USER + 6);			//不能按照规定长度从文件中读取数据
	public static final int	ERR_MFILE_WRITEALL	=		(MERROR_USER + 7);			//不能按照规定长度写入文件数据
	public static final int	ERR_MFILE_NOTSIZE	=		(MERROR_USER + 8);			//不能从文件中读取超过指定长度的字符串
	public static final int	ERR_MFILE_LINUXCREATTIME=	(MERROR_USER + 9);			//LINUX操作系统不支持设置文件创建时间
	public static final int	ERR_MFILE_LINUXCOPYFILE	=	(MERROR_USER + 10);			//文件已经存在且设置为不被拷贝
	public static final int	ERR_MFILE_LINUXMATCH=		(MERROR_USER + 11);			//查找文件匹配参数错误
	public static final int	ERR_MSHAREMEM_NOINS	=		(MERROR_USER + 12);			//对象没有实例化
	public static final int	ERR_MSOCKET_TIMEOUT	=		(MERROR_USER + 13);			//接收数据或发送数据超时
	public static final int	ERR_MLZW_BUFFULL	=		(MERROR_USER + 14);			//缓冲已满无法存放更多的数据
	public static final int	ERR_MLZW_NODATA		=		(MERROR_USER + 15);			//没有数据可以获取无法读取需要的数据
	public static final int	ERR_MLZW_NOMATCHDIRECT	=	(MERROR_USER + 16);			//在字典中没有找到匹配的数据且无更多的字典空间
	public static final int	ERR_MLZW_DECODE		=		(MERROR_USER + 17);			//解码失败无法匹配
	public static final int	ERR_MLOG_FILEHEAD	=		(MERROR_USER + 18);			//日志文件头部格式错误
	public static final int	ERR_MLOG_VERSION	=		(MERROR_USER + 19);			//日志文件版本不兼容
	public static final int	ERR_MLOOPBUF_FULL	=		(MERROR_USER + 20);			//空间已满
	public static final int	ERR_MLOOPBUF_EMPTY	=		(MERROR_USER + 21);			//没有任何数据
	public static final int	ERR_MRDBFFILE_HEAD	=		(MERROR_USER + 22);			//DBF文件头部错误
	public static final int	ERR_MRDBFFILE_FIELDCHG	=	(MERROR_USER + 23);			//DBF字段信息发生变化
	public static final int	ERR_MRDBFFILE_FIELDREAD	=	(MERROR_USER + 24);			//DBF读取字段信息发生错误
	public static final int	ERR_MRDBFFILE_DATAREAD	=	(MERROR_USER + 25);			//DBF读取数据发生错误
	public static final int	ERR_MRDBFFILE_NONAME=		(MERROR_USER + 26);			//DBF没有匹配的字段名称
	public static final int	ERR_MRDBFFILE_FIRST	=		(MERROR_USER + 27);			//DBF已经为第一条纪录
	public static final int	ERR_MRDBFFILE_LAST	=		(MERROR_USER + 28);			//DBF已经为最后一条纪录
	public static final int	ERR_MDATABASE_NULL	=		(MERROR_USER + 29);			//读取数据库字段数据为NULL
	public static final int	ERR_MDATABASE_OPEN	=		(MERROR_USER + 30);			//打开数据库发生错误
	public static final int	ERR_MDATABASE_EXECUTE	=	(MERROR_USER + 31);			//执行SQL语句发生错误
	public static final int	ERR_MSELFRISEARRAY_LIMIT=	(MERROR_USER + 32);			//超过限定空间不能再增加元素
	public static final int	ERR_MQLTCPPROTOCOL_SIZE	=	(MERROR_USER + 33);			//数据包长度错误
	public static final int	ERR_MQLTCPPROTOCOL_COMPRESS=	(MERROR_USER + 34);			//压缩数据包错误
	public static final int	ERR_MQLTCPPROTOCOL_PACKET=	(MERROR_USER + 35);			//错误的数据包
	public static final int	ERR_MQLTCPPROTOCOL_REQUEST=	(MERROR_USER + 36);			//加入请求队列失败
	public static final int	ERR_MSRVCOMM_TEIMOUT	=	(MERROR_USER + 37);			//发送数据超时
	public static final int	ERR_MSRVCOMM_LISTENFULL	=	(MERROR_USER + 38);			//队列已满不能创建更多的监听端口
	public static final int	ERR_MSRVCOMM_INVALIDLINK=	(MERROR_USER + 39);			//无效的连接号
	public static final int	ERR_MSRVCOMM_LINKFULL	=	(MERROR_USER + 40);			//连接已满无法加入更多的连接
	public static final int	ERR_MCOMPRESSBASE_FULL	=	(MERROR_USER + 41);			//缓冲已满，无法添加压缩数据
	public static final int	ERR_MCOMPRESSBASE_EMPTY	=	(MERROR_USER + 42);			//没有数据可以获取无法读取需要的数据
	public static final int	ERR_MHUFFMAN_OVER		=	(MERROR_USER + 43);			//编码长度超过了最大长度
	public static final int	ERR_MFINANCE_VALUE		=	(MERROR_USER + 44);			//金融数据类型值错误，不在有效范围
	public static final int	ERR_STEPMSG_MAXSIZE		=	(MERROR_USER + 45);			//STEP消息已经超过了设定的最大长度
	public static final int	ERR_STEPMSG_TAGMAXSIZE	=	(MERROR_USER + 46);			//STEP消息中TAG的NAME或VALUE超过了设定的最大长度限制
	public static final int	ERR_STEPMSG_HEADNOTAG	=	(MERROR_USER + 47);			//解析STEP消息头部错误，缺少必要字段
	public static final int	ERR_STEPMSG_CHECKCODE	=	(MERROR_USER + 48);			//解析STEP消息头部错误，校验码错误
	public static final int	ERR_QLSTEP_READTAGCODE	=	(MERROR_USER + 49);			//读取STEP发生错误[读取CODE错误]
	public static final int	ERR_QLSTEP_READTAGVALUE	=	(MERROR_USER + 50);			//读取STEP发生错误[读取VALUE错误]
	public static final int	ERR_QLSTEP_WRITEERROR	=	(MERROR_USER + 51);			//写入STEP发生错误[超过最大长度]
	public static final int	ERR_IPSEARCH_READFILEERR=	(MERROR_USER + 52);			//读取IPSearch数据库文件发生错误[文件错误]
	public static final int	ERR_IPSEARCH_MEMORY		=	(MERROR_USER + 53);			//读取IPSearch数据库文件发生错误[分配内存错误]
	
	int  PutBitToBuf32(byte[] lpInBuf, int lInSize, int lBitOffset, int lValue, int sBitSize)
	{
		int								lbyteoffset;
		int								lbytestart;
		
		//判断参数
		if ( lpInBuf == null || sBitSize > 32 || sBitSize == 0 )
		{
			return(ERR_PUBLIC_SLOPOVER);
		}
		
		//计算写入的偏移
		lbyteoffset = lBitOffset >> 3;
		lbytestart = lBitOffset - ( (lBitOffset >> 3) << 3 );
		
		//判断是否可以写入
		if ( (lbyteoffset + 4) >= lInSize )
		{
			return(ERR_MCOMPRESSBASE_FULL);
		}

		//将写入的值只保留写入部分
		lValue = lValue & (0xFFFFFFFF >> (32 - sBitSize));
		
		//开始写入
		if ( ( 8 - lbytestart ) >= sBitSize )
		{
			lpInBuf[lbyteoffset] = (byte)((lpInBuf[lbyteoffset] | ( lValue << ( 8 - lbytestart - sBitSize ) ) )&0xff);
			return(1);
		}
		else
		{
			lpInBuf[lbyteoffset] = (byte)((lpInBuf[lbyteoffset] | ( lValue >> ( sBitSize - 8 + lbytestart ) ))&0xff);
			
			lBitOffset += 8 - lbytestart;
			sBitSize -= 8 - lbytestart;

			return(PutBitToBuf32(lpInBuf,lInSize,lBitOffset,lValue,sBitSize));
		}
		
	}

	int  GetBitFromBuf32(final byte[] lpInBuf, int lInBitSize, int lBitOffset, int[] lValue, int sBitSize)
	{
		int									lbyteoffset;
		int									lbytestart;
		byte								cinvalue;
		int[]								ltempvalue = new int[1];
		int									errorcode;
		
		//判断参数是否正确
		if ( lpInBuf == null || lValue == null )
		{
			return(ERR_PUBLIC_SLOPOVER);
		}

		//判断是否能够读取
		if ( (lBitOffset + sBitSize) > lInBitSize )
		{
			return(ERR_MLZW_NODATA);
		}

		//计算读取数据的偏移位置
		lValue[0] = 0;
		lbyteoffset = lBitOffset >> 3;
		lbytestart = lBitOffset - ( (lBitOffset >> 3) << 3 );
		
		if ( ( 8 - lbytestart ) >= sBitSize )
		{
			cinvalue = lpInBuf[lbyteoffset];
			lValue[0] = ((cinvalue&(0xFF>>lbytestart ))&0xff) >> ( 8 - lbytestart - sBitSize );
			
			return(1);
		}
		else
		{
			cinvalue = lpInBuf[lbyteoffset];
			lValue[0] = (cinvalue&(0xFF>>lbytestart ))&0xff;
			
			lBitOffset += 8 - lbytestart;
			sBitSize -= 8 - lbytestart;
			
			if ( (errorcode = GetBitFromBuf32(lpInBuf,lInBitSize,lBitOffset,ltempvalue,sBitSize)) < 0 )
			{
				return(errorcode);
			}
			
			lValue[0] = (lValue[0] << sBitSize) | ltempvalue[0];
			return(1);
		}
	}
}
