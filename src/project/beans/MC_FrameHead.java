package project.beans;

/**
 * 包头类
 */
public class MC_FrameHead {
	public static final int MC_FrameHead_LEN = 16;        //通讯头长度
	public static final int MAX_MOBILE_PACKAGE_SIZE = 8000;    //通讯包大小

	public	byte			Market;		//协议标识，目前定义为'#'，表示手机协议2.0
	public	byte			zip;
	public	byte			crypt;
	public	byte			ErrorFlag;
	public	int				ErrorCode;
	public	short			PackageNum;
	public	short			PackageNo;
	public	short			CheckCode;
	public	int				PackageSize;
	public	int				SessionID;
	public	byte			PageID;
	public	short			MainType;
	public	short			ChildType;
	public	short			RequestCode;
	public	byte			DataFlag;
	/*unsigned char							Market;			
	unsigned char							zip:3;			//0表示不压缩，为1时表示使用LZW8192压缩了
	unsigned char							crypt:3;			//表示数据加密了
	unsigned char							ErrorFlag:1;		//为1表示错误，错误号为ErrorCode，0表示成功，PackageNum和PackageNo分别表示总应//答包及应答包序号，当PackageNo+1==PackageNum时表示没有后续包
	unsigned char							unused:1;		//未使用
	union  {
		unsigned short						ErrorCode;
		struct {
			unsigned char					PackageNum;
			unsigned char					PackageNo;
		};
	};
	unsigned short							CheckCode;		//校验码（对通讯包体进行CRC16校验，在加密压缩前）
	unsigned short							PackageSize;	//包体的长度，加密压缩后
	DWORD									SessionID:24;		//用于会话ID
	DWORD									PageID:8;		//页面ID, 由客户端定义，服务器原样返回，客户端用于区分不同页面
	unsigned char							MainType;
	unsigned char							ChildType;
	unsigned short							RequestCode:14;	//区分不同请求
	unsigned short							DataFlag:2;		//0表示是请求包，RequestCode指定请求号
	//1表示是应答包，RequestCode同请求时
	//2表示是推送包，RequestCode无意义
	*/
}
