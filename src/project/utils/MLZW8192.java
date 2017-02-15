package project.utils;

public class MLZW8192 extends MCompressBase {
	private	short[]			mDictory_CodeValue;
	private	short[]			mDictory_ParentCode;
	private	byte[]			mDictory_Character;
	private	byte[]			mStackBuff;
	
	public static final int	MLZW8192_BITS				=			13;
	public static final int	MLZW8192_MAXCODE			=			((1 << MLZW8192_BITS) - 1);
	public static final int	MLZW8192_TABLESIZE			=			9973;
	public static final int	MLZW8192_END				=			256;
	public static final int	MLZW8192_BUMPCODE			=			257;
	public static final int	MLZW8192_FIRSTCODE			=			258;
	public static final int	MLZW8192_UNUSER				=			-1;
	
	public	MLZW8192()
	{
		mDictory_CodeValue	= new short[MLZW8192_TABLESIZE];
		mDictory_ParentCode	= new short[MLZW8192_TABLESIZE];
		mDictory_Character	= new byte[MLZW8192_TABLESIZE];
		mStackBuff			= new byte[MLZW8192_TABLESIZE];
	}

	private void Initialize()
	{
		int					i;

		for ( i=0;i<MLZW8192_TABLESIZE;i++ )
		{
			mDictory_CodeValue[i] = MLZW8192_UNUSER;
			mDictory_ParentCode[i] = 0;
			mDictory_Character[i] = 0;
		}
	}

	private int  FindChildNode(int nParentCode, int lChildCode, int[] lpOutIndex)
	{
		int						lindex;
		int						loffset;
		int						itrytimes = 0;

		lindex = ( lChildCode << ( MLZW8192_BITS - 8) ) ^ nParentCode;
		if ( lindex == 0 )
		{
			loffset = 1;
		}
		else
		{
			loffset = MLZW8192_TABLESIZE - lindex;
		}

		while ( true )
		{
			if ( mDictory_CodeValue[lindex] == MLZW8192_UNUSER )
			{
				lpOutIndex[0] = lindex;
				return(1);
			}

			if ( mDictory_ParentCode[lindex] == (short)nParentCode && mDictory_Character[lindex] == (byte)lChildCode )
			{
				lpOutIndex[0] = lindex;
				return(1);
			}

			if ( lindex >= loffset )
			{
				lindex -= loffset;
			}
			else
			{
				lindex += MLZW8192_TABLESIZE - loffset;
			}

			itrytimes ++;

			if ( itrytimes > (MLZW8192_TABLESIZE+1) )
			{
				return(ERR_MLZW_NOMATCHDIRECT);
			}
		}
	}

	public int  CompressBuf(byte[] lpInBuf,int sInSize,byte[] lpOutBuf,int sOutSize)
	{
		int									istringcode;
		int									iinputptr = 1;
		int[]								lindex = new int[1];
		int									icharecter;
		int									inextcode = MLZW8192_FIRSTCODE;
		int									loutbitptr = 0;
		int									loutbitsize = 9;
		int									inextbumpsize = 512;
		int									slimitsize;
		//byte[]								lpinbuf;
		int									errorcode;
		int									ireturnsize;

		if ( sInSize > 8192 )
		{
			return(ERR_PUBLIC_SLOPOVER);
		}

		//lpinbuf = (unsigned char *)lpInBuf;
		Initialize();
		//memset(lpOutBuf,0,sOutSize);
		slimitsize = Math.min(sOutSize,sInSize);

		istringcode = lpInBuf[0]&0xff;
		while ( iinputptr < sInSize )
		{
			icharecter = (int)lpInBuf[iinputptr]&0xff;
			 if ( (errorcode = FindChildNode(istringcode, icharecter, lindex)) < 0 )
			 {
				return(errorcode);
			 }
			
			if ( mDictory_CodeValue[lindex[0]] != MLZW8192_UNUSER )
			{
				istringcode = mDictory_CodeValue[lindex[0]];
			}
			else
			{
				mDictory_CodeValue[lindex[0]] = (short)inextcode;
				mDictory_ParentCode[lindex[0]] = (short)istringcode;
				mDictory_Character[lindex[0]] = (byte)icharecter;

				if ( (errorcode = PutBitToBuf32(lpOutBuf,slimitsize,loutbitptr,istringcode,loutbitsize)) < 0 )
				{
					return(errorcode);
				}

				inextcode ++;
				istringcode = icharecter;
				loutbitptr += loutbitsize;

				if ( inextcode >= inextbumpsize )
				{
					if ( (errorcode = PutBitToBuf32(lpOutBuf,slimitsize,loutbitptr,MLZW8192_BUMPCODE,loutbitsize)) < 0 )
					{
						return(errorcode);
					}

					loutbitptr += loutbitsize;
					
					loutbitsize ++;
					inextbumpsize = inextbumpsize << 1;
				}
			}

			iinputptr ++;
		}

		if ( (errorcode = PutBitToBuf32(lpOutBuf,slimitsize,loutbitptr,istringcode,loutbitsize)) < 0 )
		{
			return(errorcode);
		}

		if ( (errorcode = PutBitToBuf32(lpOutBuf,slimitsize,loutbitptr + loutbitsize,MLZW8192_END,loutbitsize)) < 0 )
		{
			return(errorcode);
		}

		loutbitptr += loutbitsize + loutbitsize;

		if ( (loutbitptr % 8) != 0 )
		{
			ireturnsize = (loutbitptr >> 3) + 1;
		}
		else
		{
			ireturnsize = loutbitptr >> 3;
		}
		
		if ( ireturnsize >= sInSize )
		{
			return(ERR_MLZW_BUFFULL);
		}
		else
		{
			return(ireturnsize);
		}
	}
	
	int DecodeString(int lCount, int lCode)
	{
		while ( lCode > 255 )
		{
			if ( lCount >= MLZW8192_TABLESIZE || lCode >= MLZW8192_TABLESIZE )
			{
				return(ERR_MLZW_DECODE);
			}
			mStackBuff[lCount] = mDictory_Character[lCode];
			lCode = mDictory_ParentCode[lCode];
			lCount ++;
		}

		mStackBuff[lCount] = (byte)lCode;
		lCount ++;

		return(lCount);
	}
	
	public int  ExpandBuf(byte[] lpInBuf,int sInSize,byte[] lpOutBuf,int sOutSize)
	{
		int[]							loldcode = new int[1];
		int[]							lnewcode = new int[1];
		int								inextcode = MLZW8192_FIRSTCODE;
		int								lcount;
		byte							character;
		int								soutptr = 1;
		int								linbitptr = 9;
		int								linbitsize = 9;
		int								errorcode;
		int								tempbitsize;

		tempbitsize = (sInSize) << 3;

		if ( (errorcode = GetBitFromBuf32(lpInBuf,tempbitsize,0,loldcode,9)) < 0 )
		{
			return(errorcode);
		}

		lpOutBuf[0] = (byte)loldcode[0];
		character = (byte)loldcode[0];

		while ( (errorcode = GetBitFromBuf32(lpInBuf,tempbitsize,linbitptr,lnewcode,linbitsize)) > 0 )
		{
			if ( lnewcode[0] == MLZW8192_END )
			{
				return(soutptr);
			}
			else if ( lnewcode[0] == MLZW8192_BUMPCODE )
			{
				linbitptr += linbitsize;
				linbitsize ++;
				continue;
			}
			
			linbitptr += linbitsize;

			if ( lnewcode[0] >= inextcode )
			{
				mStackBuff[0] = character;
				lcount = DecodeString(1,loldcode[0]);
			}
			else
			{
				lcount = DecodeString(0,lnewcode[0]);
			}

			if ( lcount < 0 )
			{
				return(lcount);
			}
			else if ( lcount > MLZW8192_TABLESIZE )
			{
				return(ERR_MLZW_DECODE);
			}

			character = mStackBuff[lcount-1];

			while ( lcount > 0 )
			{
				if ( soutptr >= sOutSize )
				{
					return(ERR_MLZW_BUFFULL);
				}

				lpOutBuf[soutptr] = mStackBuff[lcount-1];
				lcount --;
				soutptr ++;
			}

			if ( inextcode >= MLZW8192_TABLESIZE )
			{
				return(ERR_MLZW_DECODE);
			}

			mDictory_ParentCode[inextcode] = (short)(loldcode[0]);
			mDictory_Character[inextcode] = character;
			inextcode ++;

			loldcode[0] = lnewcode[0];
		}

		return(errorcode);

	}
}
