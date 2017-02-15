package project.core;

import project.utils.Global_Define;
import project.utils.MyByteBuffer;
import project.beans.MC_FrameHead;
import project.beans.MHC_SORT_RESPONE;
import project.beans.tagLocalStockData;
import project.utils.STD;
import project.utils.ViewTools;

import java.util.ArrayList;

/**
 * 数据包装包 解包 层
 */
public class CMobileProt {

    private final static String TAG = CMobileProt.class.getSimpleName();

    private static int mPriceTimes;

    private CMobileProt() {

    }

    /**
     * 请求排行数据
     *
     * @param market   市场
     * @param style    股票类别
     * @param sorttype 排序类别
     * @param startpos 起始位置
     * @param num      请求数量
     */
    public static int MakeRequest_17(int SessionID, int RequestCode,
                                     int pageNo, int market, int style, int sorttype, int startpos,
                                     int num, int fix_num, byte[] fix, int push_num, byte[] push,
                                     byte[] data, int offset) {
        int totalsize = MC_FrameHead.MC_FrameHead_LEN + 3 + 2 + 2 + 1
                + fix_num + 1 + push_num;
        if (data.length - offset < totalsize) // 缓存不够
        {
            return -1;
        }

        int datasize = MC_FrameHead.MC_FrameHead_LEN;
        // 添加市场
        data[offset + datasize] = (byte) market;
        datasize += 1;
        // 添加股票类别
        data[offset + datasize] = (byte) style;
        datasize += 1;
        // 添加排序类别
        data[offset + datasize] = (byte) sorttype;
        datasize += 1;
        // 添加起始位置
        MyByteBuffer.putShort(data, offset + datasize, (short) startpos);
        datasize += 2;
        // 添加请求数量
        MyByteBuffer.putShort(data, offset + datasize, (short) num);
        datasize += 2;

        // 请求、 推送字段
        data[offset + datasize] = (byte) (fix_num);
        datasize++;
        MyByteBuffer.putBytes(data, offset + datasize, fix, 0, fix_num);
        datasize += fix_num;

        data[offset + datasize] = (byte) (push_num);
        datasize++;
        MyByteBuffer.putBytes(data, offset + datasize, push, 0, push_num);
        datasize += push_num;

        return MakeEncryptPackage(145, 17, pageNo, SessionID, RequestCode,
                data, offset, datasize, 0);
    }

    /**
     * 返回股票总数
     *
     * @param data
     * @param size
     * @param stock
     * @return
     */
    public static MHC_SORT_RESPONE Analy_17(byte[] data, int size,
                                            ArrayList<tagLocalStockData> stock) {

        MHC_SORT_RESPONE response = new MHC_SORT_RESPONE();
        int offset = 0;

        offset += 3;
        int total = MyByteBuffer.getShort(data, offset);
        offset += 2; // 起始位置
        int startpos = MyByteBuffer.getShort(data, offset);
        offset += 2; // 股票数 字段偏移
        int stocknum = MyByteBuffer.getShort(data, offset);
        offset += 2;

        response.nTotalNum = (short) total;
        response.nStartPos = (short) startpos;
        response.nRecordNum = (short) stocknum;
//        L.d(TAG, "Analy_17--->startpos = " + startpos + ", stocknum = " + stocknum);

        int fieldnum = ((int) data[offset]) & 0xff;
        int fieldid_offset = offset + 1;
        offset += 1 + fieldnum;

        stock.clear();
        for (int n = 0; n < stocknum; n++) {
            tagLocalStockData temp = new tagLocalStockData();
            for (int i = 0; i < fieldnum; i++) // 解析
            {
                offset += HQ_FIELD_FUNC(data, offset, size, data[fieldid_offset
                        + i], temp);
            }
            // 调整价格
            temp.HQ_FIELD_FUNC_PRICE(data, fieldid_offset, fieldnum);

            stock.add(temp);
        }
        return response;//total;
    }

    // 生成一个加密请求包
    // 主要是设置包头
    // datasize为包的长度，含包头和包体
    // encrypt可取0和1，分别表示不加密和加密
    public static int MakeEncryptPackage(int MainType, int ChildType,
                                         int PageNo, int SessionID, int RequestCode, byte[] data,
                                         int offset, int datasize, int encrypt) {
//        L.i(TAG, "send -- MainType: " + MainType + " ChildType: "
//                + ChildType);
        return MakeEncryptPackage((byte) MainType, (byte) ChildType,
                (byte) PageNo, SessionID, RequestCode, data, offset, datasize,
                (byte) encrypt);
    }

    /**
     * 行情字段解析
     *
     * @param data
     * @param offset
     * @param size
     * @param index
     * @param stock
     * @return
     */
    private static int HQ_FIELD_FUNC(byte[] data, int offset, int size,
                                     byte index, tagLocalStockData stock) {
        switch (index) {
            case Global_Define.FIELD_HQ_YESTERDAY:
                if (size - offset < 4) {
                    return 0;
                }
                stock.yesterday = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_OPEN:
                if (size - offset < 4) {
                    return 0;
                }
                stock.open = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_HIGH:
                if (size - offset < 4) {
                    return 0;
                }
                stock.high = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_LOW:
                if (size - offset < 4) {
                    return 0;
                }
                stock.low = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_NOW:
                if (size - offset < 4) {
                    return 0;
                }
                stock.now = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_VOLUME:
                if (size - offset < 8) {
                    return 0;
                }
                stock.volume = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_AMOUNT:
                if (size - offset < 8) {
                    return 0;
                }
                stock.amount = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_CURVOL:
                if (size - offset < 8) {
                    return 0;
                }
                stock.realvol = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_MARKET:
                if (size - offset < 1) {
                    return 0;
                }
                stock.market = data[offset];
                return 1;
            case Global_Define.FIELD_HQ_CODE: {
                if (size - offset < Global_Define.HQ_CODE_LEN) {
                    return 0;
                }
                int len = getByteStringLen(data, offset, Global_Define.HQ_CODE_LEN);
                stock.code = new String(data, offset, len);
                return Global_Define.HQ_CODE_LEN;
            }
            case Global_Define.FIELD_HQ_STOCKTYPE:
                if (size - offset < 1) {
                    return 0;
                }
                stock.zqlb = data[offset];
//			L.e(TAG, "HQ_FIELD_FUNC--->zqlb = " + stock.zqlb);
                return 1;
            case Global_Define.FIELD_HQ_NAME: {
                if (size - offset < (Global_Define.HQ_NAME_LEN << 1)) {
                    return 0;
                }
                stock.name = STD.strcpy(data, offset, Global_Define.HQ_NAME_LEN);
            /*
             * int namelen = Global_Define.HQ_NAME_LEN; char[] name = new
			 * char[namelen]; for (int i = 0; i < namelen; i++) { char c =
			 * MyByteBuffer.getChar(data, offset + (i<<1)); name[i] = c; if (c
			 * == 0) { namelen = i; break; } }
			 *
			 * stock.name = new String(name, 0, namelen);
			 */
                return Global_Define.HQ_NAME_LEN << 1;
            }
            case Global_Define.FIELD_HQ_NAME_HK_8: {
                if (size - offset < (Global_Define.HQ_NAME_LEN << 2)) {
                    return 0;
                }
                stock.name_w = STD.strcpy(data, offset, Global_Define.HQ_NAME_LEN << 1);
            /*
             * int namelen = Global_Define.HQ_NAME_LEN; char[] name = new
			 * char[namelen]; for (int i = 0; i < namelen; i++) { char c =
			 * MyByteBuffer.getChar(data, offset + (i<<1)); name[i] = c; if (c
			 * == 0) { namelen = i; break; } }
			 *
			 * stock.name = new String(name, 0, namelen);
			 */

//			L.e(TAG, "HQ_FIELD_FUNC--->name_w = " + stock.name_w);
                return Global_Define.HQ_NAME_LEN << 2;
            }
            case Global_Define.FIELD_HQ_FLAG:
                if (size - offset < 1) {
                    return 0;
                }
                stock.ldflag = data[offset];
                return 1;
            case Global_Define.FIELD_HQ_LB:
                if (size - offset < 4) {
                    return 0;
                }
                stock.lb = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_HSL:
                if (size - offset < 4) {
                    return 0;
                }
                stock.hsl = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_SYL:
                if (size - offset < 4) {
                    return 0;
                }
                stock.syl = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZD:
                if (size - offset < 4) {
                    return 0;
                }
                stock.zd = MyByteBuffer.getInt(data, offset);
                return 4;

            case Global_Define.FIELD_HQ_AVERAGE:
                if (size - offset < 4) {
                    return 0;
                }
                stock.average = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_UNITANDDOT: {
                if (size - offset < 3) {
                    return 0;
                }
                stock.pricedot = data[offset];
                stock.unit = ((int) MyByteBuffer.getShort(data, offset + 1)) & 0xffff;
                return 3;
            }
            case Global_Define.FIELD_HQ_TRADETIMENUM: {
                if (size - offset < 13) {
                    return 0;
                }
                stock.tradetimenum = data[offset];
                offset++;
                stock.tradetime[0] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.tradetime[1] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.tradetime[2] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.tradetime[3] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.tradetime[4] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.tradetime[5] = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                offset += 2;
                stock.getTradeMinute();

                return 13;
            }

            case Global_Define.FIELD_HQ_HQTIME:
                if (size - offset < 4) {
                    return 0;
                }
                int time = MyByteBuffer.getInt(data, offset);
                stock.hqdate = getDateFromQLTime(time);
                stock.hqtime = getTimeFromQLTime(time);
                return 4;

            case Global_Define.FIELD_HQ_INDEX_WB:
                if (size - offset < 4) {
                    return 0;
                }
                stock.index_wb = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_INDEX_WC:
                if (size - offset < 8) {
                    return 0;
                }
                stock.index_wc = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_INDEX_UP:
                if (size - offset < 2) {
                    return 0;
                }
                stock.index_up = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                return 2;
            case Global_Define.FIELD_HQ_INDEX_SAME:
                if (size - offset < 2) {
                    return 0;
                }
                stock.index_same = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                return 2;
            case Global_Define.FIELD_HQ_INDEX_DOWN:
                if (size - offset < 2) {
                    return 0;
                }
                stock.index_down = (int) MyByteBuffer.getShort(data, offset) & 0xffff;
                return 2;

            case Global_Define.FIELD_HQ_INDEX_HLZ:
                if (size - offset < 2) {
                    return 0;
                }
                stock.index_hlz = (int) MyByteBuffer.getShort(data, offset) /*& 0xffff*/;
                return 2;

            case Global_Define.FIELD_HQ_UPPRICE:
                if (size - offset < 4) {
                    return 0;
                }
                stock.upprice = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_DOWNPRICE:
                if (size - offset < 4) {
                    return 0;
                }
                stock.downprice = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_BUYPRICE:
                if (size - offset < 4) {
                    return 0;
                }
                stock.buyprice[0] = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_SELLPRICE:
                if (size - offset < 4) {
                    return 0;
                }
                stock.sellprice[0] = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_BS5: {
                if (size - offset < 120) {
                    return 0;
                }
                for (int i = 0; i < 5; i++) {
                    stock.buyprice[i] = MyByteBuffer.getInt(data, offset);
                    offset += 4;
                    stock.buyvolume[i] = MyByteBuffer.getLong(data, offset);
                    offset += 8;
                }
                for (int i = 0; i < 5; i++) {
                    stock.sellprice[i] = MyByteBuffer.getInt(data, offset);
                    offset += 4;
                    stock.sellvolume[i] = MyByteBuffer.getLong(data, offset);
                    offset += 8;
                }
                return 2 * 5 * (4 + 8);
            }
            case Global_Define.FIELD_HQ_OUTVOLUME:
                if (size - offset < 8) {
                    return 0;
                }
                stock.out = MyByteBuffer.getLong(data, offset);
                return 8;

            case Global_Define.FIELD_HQ_LTGB:
                if (size - offset < 8) {
                    return 0;
                }
                stock.ltgb = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_ZGB:
                if (size - offset < 8) {
                    return 0;
                }
                stock.zgb = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_YCMGSY:
                if (size - offset < 4) {
                    return 0;
                }
                stock.ycmgsy = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZXMGSY:
                if (size - offset < 4) {
                    return 0;
                }
                stock.zxmgsy = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_AVGVOL5:
                if (size - offset < 8) {
                    return 0;
                }
                stock.volrate = MyByteBuffer.getLong(data, offset);
                return 8;

            case Global_Define.FIELD_HQ_STATUNIT_COUNT: {
                for (int i = 0; i < 8; i++, offset += 4) {
                    stock.BuySellGold[i] = MyByteBuffer.getInt(data, offset);
                }
                return 4 * 8;
            }
            case Global_Define.FIELD_HQ_STATUNIT_VOLUME: {
                for (int i = 0; i < 8; i++, offset += 8) {
                    stock.VolumeGold[i] = MyByteBuffer.getLong(data, offset);
                }
                return 8 * 8;
            }
            case Global_Define.FIELD_HQ_STATUNIT_AMOUNT: {
                for (int i = 0; i < 8; i++, offset += 8) {
                    stock.AmountGold[i] = MyByteBuffer.getLong(data, offset);
                }
                return 8 * 8;
            }

            case Global_Define.FILED_HQ_STATUNIT_AMOUNT_MMC: {
                for (int i = 0; i < 4; i++, offset += 8) {
                    stock.AmountMMC[i] = MyByteBuffer.getLong(data, offset);
                    String ret = ViewTools.getStringByVolume(stock.AmountMMC[i],
                            stock.market, 100, 8, false);
                    System.out.println("stock.AmountMMC[" + i + "] = "
                            + stock.AmountMMC[i] + ", " + ret);
                }
                return 8 * 4;
            }

            case Global_Define.FIELD_HQ_STATUNIT_FLCCBL: {
                for (int i = 0; i < 8; i++, offset += 2) {
                    stock.flccbl[i] = MyByteBuffer.getShort(data, offset);
                    String ret = ViewTools.getRate(stock.flccbl[i], true);
                    System.out.println("stock.flccbl[" + i + "] = " + stock.flccbl[i]
                            + ", " + ret);
                }
                return 2 * 8;
            }

            case Global_Define.FIELD_HQ_L2_VBSVOLUME: {
                stock.vbuyprice = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.vbuyvolume = MyByteBuffer.getLong(data, offset);
                offset += 8;
                stock.vsellprice = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.vsellvolume = MyByteBuffer.getLong(data, offset);
            }
            return (4 + 8) * 2;
            case Global_Define.FIELD_HQ_STATUNIT_MMQ:
                stock.MMQ = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_STATUNIT_ZLZJ:
                stock.ZLZJ = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_STATUNIT_5RZJ:
                stock.RZJ5 = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_STATUNIT_10RZJ:
                stock.RZJ10 = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_STATUNIT_20RZJ:
                stock.RZJ20 = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_STATUNIT_10RNH:
                stock.RNH10 = MyByteBuffer.getByte(data, offset);
                return 1;
            case Global_Define.FIELD_HQ_STATUNIT_ZLLH:
                stock.ZLLH = MyByteBuffer.getShort(data, offset);
                return 2;
            // 黄金版
            case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNTB5:
                stock.BuyAvr5 = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNTS5:
                stock.SellAvr5 = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_STATUNIT_MAINAMOUNT:
                stock.MainBuyAmount = MyByteBuffer.getLong(data, offset);
                stock.MainSellAmount = MyByteBuffer.getLong(data, offset + 8);
                return 8 * 2;
            case Global_Define.FIELD_HQ_STATUNIT_MAINVOLUME:
                stock.MainBuyVolume = MyByteBuffer.getLong(data, offset);
                stock.MainSellVolume = MyByteBuffer.getLong(data, offset + 8);
                return 8 * 2;
            case (byte) Global_Define.FIELD_HQ_MAINCC:
                stock.maincc = MyByteBuffer.getInt(data, offset);
                return 4;
            case (byte) Global_Define.FIELD_HQ_LAST_RADAR_DATETIME:
                if (size - offset < 8) {
                    return 0;
                }
                stock.radar_date = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.radar_time = MyByteBuffer.getInt(data, offset);
                offset += 4;
                return 8;
            case (byte) Global_Define.FIELD_HQ_LAST_RADAR_ID://
                stock.radar_node = MyByteBuffer.getLong(data, offset);
                return 8;
            case (byte) Global_Define.FIELD_HQ_LAST_RADAR_TITLE_S:
                if (size - offset < (Global_Define.HQ_LAST_RADAR_TITLE_S_LEN << 1)) {
                    return 0;
                }
                stock.radar_title = STD.strcpy(data, offset,
                        Global_Define.HQ_LAST_RADAR_TITLE_S_LEN);
                return Global_Define.HQ_LAST_RADAR_TITLE_S_LEN << 1;
            case Global_Define.FIELD_HQ_SJL:// 市净率
                if (size - offset < 4) {
                    return 0;
                }
                stock.sjl = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZHENFU:// 振幅
                if (size - offset < 4) {
                    return 0;
                }
                stock.zhenfu = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZF: // 涨幅
                if (size - offset < 4) {
                    return 0;
                }
                stock.zdf = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_MMPFLAG:// 内外盘标志
                if (size - offset < 1) {
                    return 0;
                }
                stock.mmpflag = MyByteBuffer.getByte(data, offset);
                return 1;
            case Global_Define.FIELD_HQ_CJBS: // 成交笔数
                if (size - offset < 4) {
                    return 0;
                }
                stock.cjbs = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_5ZF: // 五分钟涨幅
                if (size - offset < 4) {
                    return 0;
                }
                stock.zf5 = MyByteBuffer.getInt(data, offset);
                return 4;

            case Global_Define.FIELD_HQ_LTSZ: // 流通市值
                if (size - offset < 8) {
                    return 0;
                }
                stock.ltsz = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_ZSZ:// 总市值
                if (size - offset < 8) {
                    return 0;
                }
                stock.zsz = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_INDEX_QRD: // 强弱度
                if (size - offset < 4) {
                    return 0;
                }
                stock.qrd = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_LTBG: // INT64 流通B股 单位股
                if (size - offset < 8) {
                    return 0;
                }
                stock.ltbg = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_MGZZC: // INT32 每股净资产 放大10000倍
                if (size - offset < 4) {
                    return 0;
                }
                stock.mgjzc = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZZCSYL: // INT32 净资产收益率
                // 放大10000倍:每股收益/每股净资产*100%
                if (size - offset < 4) {
                    return 0;
                }
                stock.zzcsyl = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZCFZL: // INT32 资产负债率 放大10000倍
                if (size - offset < 4) {
                    return 0;
                }
                stock.zcfzl = MyByteBuffer.getInt(data, offset);
                return 4;
            case Global_Define.FIELD_HQ_ZYSR: // INT64 主营收入 单位分，即放大100倍
                if (size - offset < 8) {
                    return 0;
                }
                stock.zysr = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_ZLR: // INT64 净利润 单位分，即放大100倍
                if (size - offset < 8) {
                    return 0;
                }
                stock.zlr = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_ZZC: // INT64 总资产 单位分，即放大100倍
                if (size - offset < 8) {
                    return 0;
                }
                stock.zzc = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_L2_BSQUEUE:// 买卖队列信息BuySellQueue[2]
                if (size - offset < 28) {// 至少28
                    return 0;
                }
                // L.i("tag", "size - offset===="+(size - offset));
                stock.price_buy = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.totalvol_buy = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.volrecord_buy = MyByteBuffer.getInt(data, offset);
                offset += 4;

                stock.volcount_buy = MyByteBuffer.getShort(data, offset);
                offset += 2;

                stock.vol_buy = new short[stock.volcount_buy];
                for (int i = 0; i < stock.volcount_buy; i++) {
                    stock.vol_buy[i] = MyByteBuffer.getShort(data, offset);
                    offset += 2;
                }
                //
                stock.price_sell = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.totalvol_sell = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.volrecord_sell = MyByteBuffer.getInt(data, offset);
                offset += 4;

                stock.volcount_sell = MyByteBuffer.getShort(data, offset);
                offset += 2;
                stock.vol_sell = new short[stock.volcount_sell];
                for (int i = 0; i < stock.volcount_sell; i++) {
                    stock.vol_sell[i] = MyByteBuffer.getShort(data, offset);
                    offset += 2;
                }

                return 2 * (4 + 4 + 4 + 2) + 2
                        * (stock.volcount_buy + stock.volcount_sell);

            case Global_Define.FIELD_HQ_HK_BSBROKER:        //买经纪排位数据 + 卖经纪排位数据
                if (size - offset < 2) {
                    return 0;
                }

                stock.broker_count_buy = MyByteBuffer.getByte(data, offset);
                offset += 1;
                for (int i = 0; i < stock.broker_count_buy; i++) {
                    stock.broker_buy[i] = MyByteBuffer.getShort(data, offset);
                    offset += 2;
                }

                stock.broker_count_sell = MyByteBuffer.getByte(data, offset);
                offset += 1;
                for (int i = 0; i < stock.broker_count_sell; i++) {
                    stock.broker_sell[i] = MyByteBuffer.getShort(data, offset);
                    offset += 2;
                }

                return 1 + stock.broker_count_buy * 2 + 1 + stock.broker_count_sell * 2;

            case Global_Define.FIELD_HQ_L2_BS10:// 十档买卖盘 BuySell10
                if (size - offset < 240) {
                    return 0;
                }
                for (int i = 0; i < 10; i++) {
                    stock.buyprice10[i] = MyByteBuffer.getInt(data, offset);
                    offset += 4;
                    stock.buyvolume10[i] = MyByteBuffer.getLong(data, offset);
                    offset += 8;
                }
                for (int i = 0; i < 10; i++) {
                    stock.sellprice10[i] = MyByteBuffer.getInt(data, offset);
                    offset += 4;
                    stock.sellvolume10[i] = MyByteBuffer.getLong(data, offset);
                    offset += 8;
                }
                return 2 * 10 * (4 + 8);
            case Global_Define.FIELD_HQ_HQTIME_HAVE_SECOND:// 行情时间，带秒 低4字节：YYYYMMDD
                // 高4字段：HHMMSS
                if (size - offset < 8) {
                    return 0;
                }
                stock.hqtime_date = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.hqtime_time = MyByteBuffer.getInt(data, offset);
                offset += 4;
                return 8;
            case Global_Define.FIELD_HQ_XSWS:    //价格显示位数
                if (size - offset < 1) {
                    return 0;
                }
                stock.xsws = data[offset];
                return 1;
            case Global_Define.FIELD_HQ_BVOLUME1:// 买一量 单位股
                if (size - offset < 8) {
                    return 0;
                }
                stock.bvolume1 = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_SVOLUME1:// 卖一量 单位股
                if (size - offset < 8) {
                    return 0;
                }
                stock.svolume1 = MyByteBuffer.getLong(data, offset);
                return 8;
            case Global_Define.FIELD_HQ_INVOLUME:// 内盘总量 单位股
                if (size - offset < 8) {
                    return 0;
                }
                stock.involume1 = MyByteBuffer.getLong(data, offset);
                return 8;

            //股指期货
            case (byte) Global_Define.FIELD_HQ_JRCCS://今日持仓+今日结算价+昨日持仓量+持仓性质
                if (size - offset < 21) {
                    return 0;
                }
                stock.JRCCS.jrccl = MyByteBuffer.getLong(data, offset);
                offset += 8;
                stock.JRCCS.jrjsj = MyByteBuffer.getInt(data, offset);
                offset += 4;
                stock.JRCCS.zrccl = MyByteBuffer.getLong(data, offset);
                offset += 8;
                stock.JRCCS.ccsz = MyByteBuffer.getByte(data, offset);
                offset += 1;

//			L.e("HQ_FIELD_FUNC", "160--->" + stock.JRCCS.jrccl + ", "
//					+ stock.JRCCS.jrjsj + ", "
//					+ stock.JRCCS.zrccl + ", "
//					+ stock.JRCCS.ccsz + ", ");

                return 21;

            case (byte) Global_Define.FIELD_HQ_KPC://开仓+平仓
                if (size - offset < 16) {
                    return 0;
                }
                stock.KPC[0] = MyByteBuffer.getLong(data, offset);
                offset += 8;
                stock.KPC[1] = MyByteBuffer.getLong(data, offset);
                offset += 8;

//			L.e("HQ_FIELD_FUNC", "161--->" + stock.KPC[0] + ", "
//					+ stock.KPC[1]);

                return 16;

            case (byte) Global_Define.FIELD_HQ_ZRJSJ://昨日结算价
                if (size - offset < 4) {
                    return 0;
                }
                stock.ZRJSJ = MyByteBuffer.getInt(data, offset);
                offset += 4;

//			L.e("HQ_FIELD_FUNC", "163--->" + stock.ZRJSJ + ", ");

                return 4;

            case (byte) Global_Define.FIELD_HQ_XKP: //现开+平开
                if (size - offset < 16) {
                    return 0;
                }
                stock.XKP[0] = MyByteBuffer.getLong(data, offset);
                offset += 8;
                stock.XKP[1] = MyByteBuffer.getLong(data, offset);
                offset += 8;

//			L.e("HQ_FIELD_FUNC", "162--->" + stock.XKP[0] + ", "
//					+ stock.XKP[1]);

                return 16;


            default:
                // 有不认识的字段，需要引起注意
                System.out.println("----- HQ_FIELD_FUNC ----- Unknown Field - "
                        + index);
                return 0;
        }
    }

    private static int getDateFromQLTime(int time) // YYYYMMDD
    {
        return ((time >> 20) & 0xfff) * 10000 + ((time >> 16) & 0xf) * 100
                + ((time >> 11) & 0x1f);
    }

    private static int getTimeFromQLTime(int time) // HHMMSS
    {
        return ((time >> 6) & 0x1f) * 10000 + (time & 0x3f) * 100;
    }

    public static int getByteStringLen(byte[] str, int offset, int len) {
        for (int i = 0; i < len; i++) {
            if (str[offset + i] == 0) {
                len = i;
                break;
            }
        }
        return len;
    }

    public static int MakePackage(int MainType, int ChildType, int PageNo,
                                  int SessionID, int RequestCode, byte[] data, int offset,
                                  int datasize, int encrypt) {
        if (data.length - offset < datasize) {
            return -1;
        }
        //
        data[offset] = '#'; // Market
        data[offset + 1] = (byte) (0 + (encrypt << 3)); // 不压缩，不加密，成功,
        // zip,crypt,ErrorFlag,unused
        data[offset + 2] = 1; // PackageNum
        data[offset + 3] = 0; // PackageNo
        short PackageSize = (short) (datasize - MC_FrameHead.MC_FrameHead_LEN);
        short checkcode = CRC16(data, offset
                + MC_FrameHead.MC_FrameHead_LEN, PackageSize);
        MyByteBuffer.putShort(data, offset + 4, checkcode);
        MyByteBuffer.putShort(data, offset + 6, PackageSize);

        int temp = (SessionID & 0x00ffffff)
                + ((PageNo << 24) & 0xff000000);
        MyByteBuffer.putInt(data, offset + 8, temp);
        data[offset + 12] = (byte) MainType;
        data[offset + 13] = (byte) ChildType;

        short value = (short) ((RequestCode & 0x3fff) + (0 << 14)); // RequestCode,DataFlag
        MyByteBuffer.putShort(data, offset + 14, value);

        return datasize;
    }

    private static short CRC16(byte[] data, int offset, int datasize) {
        short retvalue = 0;

        int flag = datasize & 1;
        int len = datasize - flag; // 最后一位清零，相当于变成偶数，为奇数时减一
        short value = 0;

        while (len > 0) {
            value = MyByteBuffer.getShort(data, offset);
            offset += 2;
            retvalue += value;
            len -= 2;
        }

        if (flag != 0) {
            value = (short) (((int) data[offset]) & 0xff);
            retvalue += value;
        }

        return retvalue;
    }

    /**
     * 检测数据是否正确
     *
     * @param data
     * @param size
     * @param head ，返回的头
     * @return：大于0时表示是个完整包，小于0时数据有错误，0时还不是完整包
     */
    public static int CheckData(byte[] data, int size, MC_FrameHead head) {
        if (size <= 0 || head == null) {
            return 0;
        }

        int temp = MyByteBuffer.getInt(data, 0);
        head.Market = (byte) (temp & 0x000000ff);
        if (head.Market != '#') {
            return -1;
        }

        if (size < MC_FrameHead.MC_FrameHead_LEN) // 还不是一个完整包
        {
            return -2;
        }
        /*
         * unsigned char Market; unsigned char zip:3; //0表示不压缩，为1时表示使用LZW8192压缩了
		 * unsigned char crypt:3; //表示数据加密了 unsigned char ErrorFlag:1;
		 * //为1表示错误，错误号为ErrorCode
		 * ，0表示成功，PackageNum和PackageNo分别表示总应//答包及应答包序号，当PackageNo
		 * +1==PackageNum时表示没有后续包 unsigned char unused:1; //未使用 union { unsigned
		 * short ErrorCode; struct { unsigned char PackageNum; unsigned char
		 * PackageNo; }; };
		 *
		 * unsigned short CheckCode; //校验码（对通讯包体进行CRC16校验，在加密压缩前） unsigned short
		 * PackageSize; //包体的长度，加密压缩后
		 *
		 * DWORD SessionID:24; //用于会话ID DWORD PageID:8; //页面ID,
		 * 由客户端定义，服务器原样返回，客户端用于区分不同页面
		 *
		 * unsigned char MainType; unsigned char ChildType; unsigned short
		 * RequestCode:14; //区分不同请求 unsigned short DataFlag:2;
		 * //0表示是请求包，RequestCode指定请求号 //1表示是应答包，RequestCode同请求时
		 * //2表示是推送包，RequestCode无意义
		 */
        {
            head.zip = (byte) ((temp >>> 8) & 0x03);
            head.crypt = (byte) ((temp >>> 11) & 0x03);
            head.ErrorFlag = (byte) ((temp >>> 14) & 0x01);
            head.ErrorCode = (temp >>> 16) & 0xffff;
            head.PackageNum = (short) ((temp >>> 16) & 0xff);
            head.PackageNo = (short) ((temp >>> 24) & 0xff);
        }
        {
            temp = MyByteBuffer.getInt(data, 4);
            head.CheckCode = (short) (temp & 0xffff);
            head.PackageSize = (temp >>> 16) & 0xffff;
        }
        {
            temp = MyByteBuffer.getInt(data, 8);
            head.SessionID = (temp & 0x00ffffff);
            head.PageID = (byte) ((temp >>> 24) & 0xff);
        }
        {
            temp = MyByteBuffer.getInt(data, 12);
            head.MainType = (short) (temp & 0xff);
            head.ChildType = (short) ((temp >>> 8) & 0xff);
            head.RequestCode = (short) ((temp >>> 16) & 0x3fff);
            head.DataFlag = (byte) ((temp >>> 30) & 0x3);
        }

        int packsize = head.PackageSize + MC_FrameHead.MC_FrameHead_LEN;

        if (size < packsize) // 未满一个包
        {
            return 0;
        }

        if (head.CheckCode != CMobileProt.CRC16(data,
                MC_FrameHead.MC_FrameHead_LEN, head.PackageSize)) // 数据有误
        {
            return -3;
        }

        return 1;
    }

}
