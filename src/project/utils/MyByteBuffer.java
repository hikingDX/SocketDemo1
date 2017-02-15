package project.utils;

public class MyByteBuffer {
	private	MyByteBuffer() {}
	public static byte	getByte(byte[]data, int offset)
	{
		return data[offset];
	}
	public static void	getBytes(byte[] data, int offset, byte[]out, int out_offset, int len)
	{
		System.arraycopy(data, offset, out, out_offset, len);
	}
	public static short	getShort(byte[] data, int offset)
	{
		return (short)( ((data[offset])&0xff) 
						+ ((data[offset+1]<<8)&0xff00) )
						;
	}
	public static int	getInt(byte[] data, int offset)
	{
		return ((data[offset])&0xff) 
						+ ((data[offset+1]<<8)&0xff00)
						+ ((data[offset+2]<<16)&0xff0000)
						+ ((data[offset+3]<<24)&0xff000000)
						;
	}
	public static long	getLong(byte[] data, int offset)
	{
		return ((data[offset])&0xff) 
						+ (((long)data[offset+1]<<8)&0xff00)
						+ (((long)data[offset+2]<<16)&0xff0000)
						+ (((long)data[offset+3]<<24)&0xff000000L)
						+ (((long)data[offset+4]<<32)&0xff00000000L)
						+ (((long)data[offset+5]<<40)&0xff0000000000L)
						+ (((long)data[offset+6]<<48)&0xff000000000000L)
						+ (((long)data[offset+7]<<56)&0xff00000000000000L)
						;
	}

	public static char getChar(byte[] data, int offset) {
		if(offset >= data.length) {
			return 0;
		}
		return (char) (((data[offset]) & 0xff) + ((data[offset + 1] << 8) & 0xff00));
	}
	
	public static void	putByte(byte[]data, int offset, byte value)
	{
		data[offset]	= value;
	}
	public static void	putBytes(byte[]dst, int dst_offset, byte[] src, int src_offset, int len)
	{
		System.arraycopy(src, src_offset, dst, dst_offset, len);
	}
	public static void	putShort(byte[]data, int offset, short value)
	{
		data[offset]	= (byte)(value&0xff);
		data[offset+1]	= (byte)((value>>8)&0xff);
	}
	public static void	putChar(byte[]data, int offset, char value)
	{
		data[offset]	= (byte)(value&0xff);
		data[offset+1]	= (byte)((value>>8)&0xff);
	}
	public static void	putInt(byte[]data, int offset, int value)
	{
		data[offset]	= (byte)(value&0xff);
		data[offset+1]	= (byte)((value>>8)&0xff);
		data[offset+2]	= (byte)((value>>16)&0xff);
		data[offset+3]	= (byte)((value>>24)&0xff);
	}
	public static void	putLong(byte[]data, int offset, long value)
	{
		data[offset]	= (byte)(value&0xff);
		data[offset+1]	= (byte)((value>>8)&0xff);
		data[offset+2]	= (byte)((value>>16)&0xff);
		data[offset+3]	= (byte)((value>>24)&0xff);
		data[offset+4]	= (byte)((value>>32)&0xff);
		data[offset+5]	= (byte)((value>>40)&0xff);
		data[offset+6]	= (byte)((value>>48)&0xff);
		data[offset+7]	= (byte)((value>>56)&0xff);
	}
}
