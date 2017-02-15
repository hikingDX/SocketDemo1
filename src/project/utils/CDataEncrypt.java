package project.utils;

import java.util.Random;

/**
 * 数据加密类
 */
public final class CDataEncrypt {
	/*#pragma pack(1)
	struct tagEncryptDataHead
	{
		unsigned long	crc32;
		unsigned short	len;	//明文长度
	};
	#pragma pack()*/
	public static final int	EncryptDataHeadLen	= 6;
	public static final int RC5_8_ROUNDS		= 8;
	public static final int RC5_12_ROUNDS		= 12;
	public static final int RC5_16_ROUNDS		= 16;
	public static final int RC5_32_KEY_LENGTH	= 16;

	static final int RC5_32_MASK	= 0xFFFFFFFF;

	static final int RC5_16_P	= 0xB7E1;
	static final int RC5_16_Q	= 0x9E37;
	static final int RC5_32_P	= 0xB7E15163;
	static final int RC5_32_Q	= 0x9E3779B9;
	static final long RC5_64_P	= 0xB7E151628AED2A6BL;
	static final long RC5_64_Q	= 0x9E3779B97F4A7C15L;
	static final byte[]CBC_iv	=	"11111111".getBytes();
	static final int RC5_ENCRYPT	= 1;
	static final int RC5_DECRYPT	= 0;
	
	public static final byte[]	DEFAULT_KEY		= "75kgfa65439gmay8".getBytes();	//初始密钥
	public static final byte[]	HQ_DEFAULT_KEY	= "mgkoayr3ual5fhuf".getBytes();	//行情密码

	static class RC5_32_KEY
	{
		public	int rounds;// Number of rounds 
		public	int data[];
		
		public RC5_32_KEY()
		{
			rounds	= 0;
			data	= new int[2 * (RC5_16_ROUNDS + 1)];
		}
	}
	
	//static RC5_32_KEY		m_rc5_32_key	= new RC5_32_KEY();

	//对数据进行加密(in,in_len)，并输出加密后的数据(out,out_len)，返回加密后数据长度大小，最多可能增加13字节
	//原始数据最长65535
	//返回加密后数据长度，错误时返回负数，如数据错误返回-1，输出缓冲不够返回-2
	//in和out可为同一个缓冲，但需注意相同时原始数据就被破坏了
	public	static int	Encrypt(byte[] in, int in_offset, int in_len, byte[] out, int out_offset, long out_len, byte[] key)
	{
		//先计算空间够不够
		//memset(out, 0, out_len);
		int newsize = ((in_len + EncryptDataHeadLen + 7) >> 3) << 3;

		if (out_len < newsize)
		{
			return -2;
		}
		
		//int datasize	= out_offset;
		//数据，由于in和out有可能是同一对象，则需先拷贝数据再做其它更改。
		System.arraycopy(in, in_offset, out, out_offset + EncryptDataHeadLen, in_len);	
		MyByteBuffer.putShort(out, out_offset+4, (short)in_len);	//长度
		int crc32		= t__CRC32(out, out_offset+4, in_len + EncryptDataHeadLen - 4);
		//System.out.println("crc32:" + crc32);
		MyByteBuffer.putInt(out, out_offset, crc32);
		//datasize		+= EncryptDataHeadLen + in_len;
		//tagEncryptDataHead* head = (tagEncryptDataHead*)out;
		//memmove(out + sizeof(tagEncryptDataHead), in, in_len);
		//head->len		= in_len;
		//head->crc32		= t__CRC32(out + sizeof(unsigned long), in_len + sizeof(tagEncryptDataHead) - sizeof(unsigned long));

		//加密
		RC5_32_KEY	mRc5Key = new RC5_32_KEY();
//		char	iv[sizeof(CBC_iv)+100];
//		strcpy(iv, CBC_iv);
//		if (m_rc5_32_key.rounds == 0)
//		{
//			RC5_32_set_key(m_rc5_32_key, RC5_32_KEY_LENGTH, key, RC5_16_ROUNDS); 
//		}
		RC5_32_set_key(mRc5Key, RC5_32_KEY_LENGTH, key, RC5_16_ROUNDS); 
		
		RC5_32_cbc_encrypt(out, out_offset, out, out_offset, newsize, mRc5Key, CBC_iv, RC5_ENCRYPT);
		
		return newsize;
	}

	//对数据进行解密
	//返回解密后数据长度，错误时返回负数，如解密错误返回-1，输出缓冲不够返回-2
	//out_len不能小于in_len，否则返回错误
	//in和out可为同一个缓冲，但需注意相同时原始数据就被破坏了
	public static int	Decrypt(byte[] in, int in_offset, int in_len, byte[] out, int out_offset, int out_len, byte[] key)
	{
		if (out_len < in_len)
		{
			return -2;
		}

		//解密
		RC5_32_KEY	mRc5Key = new RC5_32_KEY();
		//char	iv[sizeof(CBC_iv)+100];
		//strcpy(iv, CBC_iv);
//		if (m_rc5_32_key.rounds == 0)
//		{
//			RC5_32_set_key(m_rc5_32_key, RC5_32_KEY_LENGTH, key, RC5_16_ROUNDS); 
//		}
		RC5_32_set_key(mRc5Key, RC5_32_KEY_LENGTH, key, RC5_16_ROUNDS); 
		
		RC5_32_cbc_encrypt(in, in_offset, out, out_offset, in_len, mRc5Key, CBC_iv, RC5_DECRYPT);
		
		//tagEncryptDataHead* head = (tagEncryptDataHead*)out;
		int head_crc32		= MyByteBuffer.getInt(out, out_offset);
		int head_len		= MyByteBuffer.getShort(out, out_offset+4);

		//if (head->len > in_len - sizeof(tagEncryptDataHead))
		if (head_len > in_len - EncryptDataHeadLen)
		{
			//System.out.print("in_len:" + in_len + " head_len:" + head_len);
			return -1;
		}

		int	crc32	= t__CRC32(out, out_offset+4, head_len + EncryptDataHeadLen - 4);
		if (head_crc32 != crc32)
		{
			return -3;
		}

		int datasize	= head_len;
		System.arraycopy(out, out_offset + EncryptDataHeadLen, out, out_offset, datasize);
		//memmove(out, out + EncryptDataHeadLen, datasize);
		//memset(out + datasize, 0, out_len - datasize);
		STD.memset(out, out_offset + datasize, out_len - datasize);

		return datasize;

	}
	
	//产生随机字符串
	public static void	CreateRandData(byte[] out, int len)
	{
		final byte[] randstring = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes();

		Random	rand = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < len; i++)
		{
			int temp = rand.nextInt();
			if(temp < 0) 
				temp = -temp;
			int no	= temp % randstring.length;
			out[i]	= randstring[no];
		}
		rand	= null;
	}
	
	//从随机字符串里获取密钥
	public static void	GetKeyFromRandData(byte[] data, int data_len, byte[] key, int key_len)
	{
		if (data_len == 0)
		{
			return;
		}
		int value = (int)(GetValueFromRandData(data, data_len) % data_len);
		for (int i = 0; i < key_len; i++)
		{
			key[i]	= data[value];
			value	= value * 19 % data_len;
		}
	}

	static long GetValueFromRandData(byte[] data, int data_len)
	{
		long value = 0;
		int offset = 0;
		while (data_len >= 4)
		{
			value	+= ((long)MyByteBuffer.getInt(data, offset))&0xffffffff;
			offset	+= 4;
			data_len -= 4;
		}

		if (value == 0)
		{
			return 1;
		}

		return value;
	}
	/************************************/
	/*	Compute the round keys			*/
	/************************************/
	static void RC5_32_set_key(RC5_32_KEY key, int len, byte[] data, int rounds)
	{
		int i,j,m,c,t,ii,jj;
		int L[]	= new int[64];
		int A,B,k;
		int S[] = null;
			
		if((rounds != RC5_16_ROUNDS) && (rounds != RC5_12_ROUNDS) && (rounds != RC5_8_ROUNDS))
		{
			rounds = RC5_16_ROUNDS;
		}
		
		key.rounds = rounds;
		S = key.data;
		j = 0;
		L[0]		= MyByteBuffer.getInt(data, 0);
		L[1]		= MyByteBuffer.getInt(data, 4);
		L[2]		= MyByteBuffer.getInt(data, 8);
		L[3]		= MyByteBuffer.getInt(data, 12);
		//System.out.println(new String(data) + L[0] + " " + L[1] + " " + L[2] + " " + L[3]);
		
		//ii = len - i;
		//使用16字节的密钥，不会执行到这里
		//if (ii)
		//{
		//	k = len & 0x07;
		//	c2ln(data,l,ll,k);
		//	L[j+0] = l;
		//	L[j+1] = ll;
		//}
		
		c = (len + 3) / 4;
		t = (rounds + 1) * 2;
		S[0] = RC5_32_P;
		//System.out.println("S[0]=" + S[0]);
		for (i = 1; i < t; i++)
		{
			S[i] = (S[i-1] + RC5_32_Q) & RC5_32_MASK;
			//System.out.println("S[" + i + "]=" + S[i]);
		}	
		
		j =(t > c) ? t : c;
		j *= 3;
		ii = jj = 0;
		A = B = 0;
		for (i = 0; i < j; i++)
		{
			k = (S[ii] + A + B) & RC5_32_MASK;
			A = S[ii] = ROTATE_l32(k,3);
			//System.out.println("k:" + k + " S[" + ii + "]=" + S[ii]);
			m = A + B;
			k = (L[jj] + A + B) & RC5_32_MASK;
			B = L[jj] = ROTATE_l32(k,m);
			if (++ii >= t)
			{
				ii = 0;
			}

			if (++jj >= c)
			{
				jj = 0;
			}
		}
	}
	
	static int ROTATE_l32(int a, int n)
	{
		n	&= 0x1f;
		return (a << n)	| (a >>> (32 - n) );
	}
	static int ROTATE_r32(int a, int n)
	{
		n	&= 0x1f;
		return (a << (32 - n) ) | (a >>> n);
	}
	static int	t__CRC32(byte[] in, int offset, int len)
	{
		//for (int i = 0; i < len; i++)
		//{
		//	System.out.println("in[" + i + "]=" + in[i+offset]);
		//}
		int ret = 0;
		while (len >= 4)
		{
			int v	= MyByteBuffer.getInt(in, offset);

			ret 	+= v;
			offset 	+= 4;
			len		-= 4;
		}

		int v = 0;
		switch (len)
		{
		case 3:
			v	= (in[offset+2]<<16)&0xff0000;
		case 2:
			v	|= (in[offset+1]<<8)&0xff00;
		case 1:
			v	|= (in[offset])&0xff;
		}
		//System.out.println("v:" + v + " ret:" + ret);
		ret		+= v;

		return ret;
	}

	/****************************************************/
	/*	RC5 Encrypt/Decrypt in the CBC mode				*/
	/*	Note: input data must be the multiple of 8-byte */
	/****************************************************/
	static int RC5_32_cbc_encrypt(byte[] in, int in_offset, byte[] out, int out_offset, int length, RC5_32_KEY RoundsKey, byte[] iv, int encrypt)
	{
		int tin0,tin1;
		int tout0, tout1;
		int xor0,xor1;
		int  l = length;
		int tin[] = new int[2];
		
		if(in == null || length <= 0)
		{
			return -1;
		}

		if (encrypt != 0)
		{
			tout0	= MyByteBuffer.getInt(iv, 0);
			tout1	= MyByteBuffer.getInt(iv, 4);
			//iv -= 8;
			for (l -= 8; l >= 0; l -= 8)
			{
				tin0	= MyByteBuffer.getInt(in, in_offset);
				in_offset	+= 4;
				tin1	= MyByteBuffer.getInt(in, in_offset);
				in_offset	+= 4;

				tin0 ^= tout0;
				tin1 ^= tout1;
				tin[0] = tin0;
				tin[1] = tin1;
				RC5_32_encrypt(tin,RoundsKey);

				tout0 = tin[0]; 
				//l2c(tout0,out);
				MyByteBuffer.putInt(out, out_offset, tout0);
				out_offset	+= 4;
				tout1 = tin[1]; 
				//l2c(tout1,out);
				MyByteBuffer.putInt(out, out_offset, tout1);
				out_offset	+= 4;
			}
		}
		else
		{
			xor0	= MyByteBuffer.getInt(iv, 0);
			xor1	= MyByteBuffer.getInt(iv, 4);
			//c2l(iv,xor0);
			//c2l(iv,xor1);
			//iv -= 8;
			for (l -= 8; l >= 0; l -= 8)
			{
				tin0	= MyByteBuffer.getInt(in, in_offset);
				in_offset	+= 4;
				tin1	= MyByteBuffer.getInt(in, in_offset);
				in_offset	+= 4;
				//c2l(in,tin0); 
				tin[0] = tin0;

				//c2l(in,tin1); 
				tin[1] = tin1;

				RC5_32_decrypt(tin,RoundsKey);
				tout0 = tin[0] ^ xor0;
				tout1 = tin[1] ^ xor1;

				MyByteBuffer.putInt(out, out_offset, tout0);
				out_offset	+= 4;
				MyByteBuffer.putInt(out, out_offset, tout1);
				out_offset	+= 4;
				//l2c(tout0,out);
				//l2c(tout1,out);
				xor0 = tin0;
				xor1 = tin1;
			}
		}

		return 1;
	}
	static void RC5_32_encrypt(int[] d, RC5_32_KEY key)
	{
		//int a,b;
		int[] s;
		
		s = key.data;
		
		d[0] = d[0] + s[0];
		d[1] = d[1] + s[1];
		E_RC5_32(d,s, 2);
		E_RC5_32(d,s, 4);
		E_RC5_32(d,s, 6);
		E_RC5_32(d,s, 8);
		E_RC5_32(d,s,10);
		E_RC5_32(d,s,12);
		E_RC5_32(d,s,14);
		E_RC5_32(d,s,16);

		if (key.rounds == 12)
		{
			E_RC5_32(d,s,18);
			E_RC5_32(d,s,20);
			E_RC5_32(d,s,22);
			E_RC5_32(d,s,24);
		}
		else if (key.rounds == 16)
		{
			// Do a full expansion to avoid a jump
			E_RC5_32(d,s,18);
			E_RC5_32(d,s,20);
			E_RC5_32(d,s,22);
			E_RC5_32(d,s,24);
			E_RC5_32(d,s,26);
			E_RC5_32(d,s,28);
			E_RC5_32(d,s,30);
			E_RC5_32(d,s,32);
		}
	}
	static void RC5_32_decrypt(int[] d, RC5_32_KEY key)
	{
		int[] s;
		
		s = key.data;
		
		if (key.rounds == 16) 
		{
			D_RC5_32(d,s,32);
			D_RC5_32(d,s,30);
			D_RC5_32(d,s,28);
			D_RC5_32(d,s,26);

			// Do a full expansion to avoid a jump 
			D_RC5_32(d,s,24);
			D_RC5_32(d,s,22);
			D_RC5_32(d,s,20);
			D_RC5_32(d,s,18);
		}
		else if (key.rounds == 12)
		{
			D_RC5_32(d,s,24);
			D_RC5_32(d,s,22);
			D_RC5_32(d,s,20);
			D_RC5_32(d,s,18);
		}

		D_RC5_32(d,s,16);
		D_RC5_32(d,s,14);
		D_RC5_32(d,s,12);
		D_RC5_32(d,s,10);
		D_RC5_32(d,s, 8);
		D_RC5_32(d,s, 6);
		D_RC5_32(d,s, 4);
		D_RC5_32(d,s, 2);

		d[0] = d[0] - s[0];
		d[1] = d[1] - s[1];
	}
	static void E_RC5_32(int[] d, int[] s, int n)
	{
		int a = d[0];
		int b = d[1];
		a ^= b;
		a = ROTATE_l32(a,b);	
		a += s[n];				
		a &= RC5_32_MASK;		
		b ^= a;					
		b = ROTATE_l32(b,a);	
		b += s[n+1];			
		b &= RC5_32_MASK;
		d[0]	= a;
		d[1]	= b;
	}
	static void D_RC5_32(int[] d, int[] s, int n)
	{
		int a = d[0];
		int b = d[1];
		b -= s[n+1];			
		b &= RC5_32_MASK;		
		b = ROTATE_r32(b,a);	
		b ^= a;					
		a -= s[n];				
		a &= RC5_32_MASK;		
		a = ROTATE_r32(a,b);	
		a ^= b;
		d[0]	= a;
		d[1]	= b;
	}
}
