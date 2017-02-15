package project.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.DisplayMetrics;

import java.io.InputStream;
import java.math.BigDecimal;

import qianlong.qlmobile.data.Global_Define;

public final class ViewTools {

    //字体
    public static final int TEXTSIZE_L = 20,    //字体大小--大
            TEXTSIZE_M = 14,    //字体大小--中
            TEXTSIZE_S = 10;    //字体大小--小
    //价位
    public final static int j[] = {0, 2500, 5000, 100000, 200000, 1000000, 2000000, 5000000, 10000000, 20000000, 50000000, 99950000};
    public final static int w[] = {0, 10, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000};

    //获取屏幕分辨率
    public static DisplayMetrics getScreenSize(Context context) {

        DisplayMetrics dm = new DisplayMetrics();

        dm = context.getApplicationContext().getResources().getDisplayMetrics();

        return dm;
    }

    //dip/px像素单位转换
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //
    public static int getFontHeight(float fontSize) {

        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        FontMetrics fm = paint.getFontMetrics();

        return (int) (Math.ceil(fm.descent - fm.top) + 2);
    }

    //从文件读取图片
    public static Bitmap getBitmapFromFile(Context context, String fileName) {

        InputStream is = null;
//		int resid = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
        try {
            is = context.getResources().getAssets().open(fileName);
        } catch (Exception e) {
            L.e("qlmobile", "Icon File Not Found...");
            return null;
        }
        Bitmap bm = BitmapFactory.decodeStream(is, null, null).extractAlpha();
        return bm;
    }

    public static Bitmap getBitmapFromFile_NoAlpha(Context context, String fileName) {

        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(fileName);
        } catch (Exception e) {
            L.e("qlmobile", "Icon File Not Found...");
            return null;
        }
        Bitmap bm = BitmapFactory.decodeStream(is, null, null);
        return bm;
    }

    public static void DrawTextRect(Canvas canvas, String text, int left, int top, int right, int bottom, Paint paint, Paint.Align align, int color) {
        paint.setColor(color);
        paint.setTextAlign(align);
        if (align == Paint.Align.LEFT) {
            canvas.drawText(text, left, bottom, paint);
        } else if (align == Paint.Align.RIGHT) {
            canvas.drawText(text, right, bottom, paint);
        } else if (align == Paint.Align.CENTER) {
            canvas.drawText(text, (left + right) >> 1, (bottom + top) >> 1, paint);
        }

    }

    //在指定位置，以指定的颜色、对齐方式显示一串文本，返回显示高度
    public static void DrawText(Canvas canvas, String text, int left, int right, int top, int bottom, Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        int y = top + (int) (bottom - top - fm.ascent) / 2;
        if (bottom <= top) {
            int h = (int) (Math.ceil(fm.descent - fm.top) + 2);
            y = top + (int) (h - fm.ascent) / 2;
        }

        Paint.Align align = paint.getTextAlign();
        if (align == Paint.Align.LEFT) {
            canvas.drawText(text, left, y, paint);
        } else if (align == Paint.Align.RIGHT) {
            canvas.drawText(text, right, y, paint);
        } else if (align == Paint.Align.CENTER) {
            canvas.drawText(text, (left + right) >> 1, y, paint);
        }
    }

    public static void DrawText(Canvas canvas, String text, int left, int top, Paint paint, Paint.Align align, int color) {
        paint.setColor(color);
        paint.setTextAlign(align);
        canvas.drawText(text, left, top, paint);
    }

    //在指定位置，以指定的对齐方式、指定小数位数，显示价格
    //flag: true--使用paint设置
    public static void DrawPrice(Canvas canvas, int x, int y, int price, int now, int yesterday, int dotlen, Paint paint, boolean flag) {
        DrawPrice(canvas, x, y, price, now, yesterday, dotlen, paint, flag, 0);
    }

    //在指定位置，以指定的对齐方式、指定小数位数，显示价格
    //flag: true--使用paint设置
    public static void DrawPrice(Canvas canvas, int x, int y, int price, int now, int yesterday, int dotlen, Paint paint, boolean flag, int xsws) {
        DrawPrice(canvas, x, y, 0, price, now, yesterday, dotlen, paint, flag, xsws);
    }

    public static void DrawPrice(Canvas canvas, int x, int top, int bottom, int price, int now, int yesterday, int dotlen, Paint paint, boolean flag, int xsws) {
        String str = getStringByPrice(price, now, dotlen, xsws);
        if (flag == false) {
            paint.setColor(getColor(price, yesterday));
            paint.setTextAlign(Paint.Align.LEFT);
        }
        DrawText(canvas, str, 0, x, top, bottom, paint);
    }

    //在指定位置，以黄色、右对齐方式、指定宽度，显示量，单位手
    public static void DrawVolume(Canvas canvas, int x, int y, long volume, int market, int unit, Paint paint) {
        DrawVolume(canvas, x, y, 0, volume, market, unit, paint);
    }

    public static void DrawVolume(Canvas canvas, int x, int top, int bottom, long volume, int market, int unit, Paint paint) {
        String str = getStringByVolume(volume, market, unit, 6, false);
        if (paint.getTextAlign() == Paint.Align.LEFT) {
            DrawText(canvas, str, x, 0, top, bottom, paint);
        } else {
            paint.setTextAlign(Paint.Align.RIGHT);
            if (volume == 0) {
                paint.setColor(COLOR.DATA_NULL);
            } else {
                paint.setColor(COLOR.COLOR_VOL);
            }
            DrawText(canvas, str, 0, x, top, bottom, paint);
        }
    }


    //在指定位置，以白色、右对齐方式、指定宽度，显示金额
    public static void DrawAmount(Canvas canvas, int x, int y, long amount, int market, Paint paint) {
        DrawAmount(canvas, x, y, 0, amount, market, paint);
    }

//	//在指定位置，以黄色、右对齐方式、指定宽度，显示量比
//	public	static void	DrawLB(Canvas canvas, int x, int y, int lb, int width, Paint paint)
//	{
//		String str = String.format("%.2f", lb/10000.0);
//
//		paint.setColor(Color.YELLOW);
//		paint.setTextAlign(Paint.Align.RIGHT);
//		canvas.drawText(str, x, y, paint);
//	}
//
//	//在指定位置，以白色、右对齐方式、指定宽度，显示市盈率
//	public	static void	DrawSYL(Canvas canvas, int x, int y, int lb, int width, Paint paint)
//	{
//		String str = String.format("%.2f", lb/10000.0);
//
//		paint.setColor(Color.WHITE);
//		paint.setTextAlign(Paint.Align.RIGHT);
//		canvas.drawText(str, x, y, paint);
//	}

    public static void DrawAmount(Canvas canvas, int x, int top, int bottom, long amount, int market, Paint paint) {
        String str = getStringByVolume(amount, market, 100, 6, false);
        if (paint.getTextAlign() == Paint.Align.LEFT) {
            DrawText(canvas, str,x, 0, top, bottom, paint);
        } else {
            paint.setTextAlign(Paint.Align.RIGHT);
            if (amount == 0) {
                paint.setColor(COLOR.DATA_NULL);
            } else {
                paint.setColor(COLOR.COLOR_VOL);
            }
            DrawText(canvas, str, 0, x, top, bottom, paint);
        }
    }

    //在指定位置，显示涨跌幅
    //flag:是否显示百分号
    //isSign:是否显示符号，只有负数才显示符号
    public static void DrawZDF(Canvas canvas, int x, int y, int zd, int now, int yesterday, boolean flag, boolean isSign, Paint paint, boolean pFlag) {
        String str = getZDF(zd, yesterday, now, flag, isSign);
        if (pFlag == false) {
            paint.setColor(getColor(zd));
            paint.setTextAlign(Paint.Align.RIGHT);
        }
        DrawText(canvas, str, 0, x, y, 0, paint);
    }

    //在指定位置，显示数值
    public static void DrawInt(Canvas canvas, int x, int y, int data, int width, int color, Paint paint) {
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.RIGHT);

        StringBuffer str = new StringBuffer();
        str.append(data);

        canvas.drawText(str.toString(), x, y, paint);
    }

    //根据小数点位数、放大倍数返回数值的字符串
    //flag: data为0时是否显示
    public static String getStringByInt(int data, int dotlen, int unit, boolean flag) {

        if (data == 0 && flag == false) {
            return "----";
        }

        StringBuffer str = new StringBuffer();
        if (dotlen == 0) {
            int add = (data > 0) ? unit / 2 : (-unit / 2);
            str.append((data + add) / unit);
            return str.toString();
        }

        STD.DataToString(str, (long) data * 10000 / unit, dotlen);

        return str.toString();
    }

    public static String getStringByPrice(int price, int now, int dotlen) {
        return getStringByPrice(price, now, dotlen, 0);
    }

    //根据小数点位数返回价格的字符串
    public static String getStringByPrice(int price, int now, int dotlen, int xsws) {
        if (price == 0) {
            return "----";
        }

        int maxdec = 4;
        if (price >= 100000000)    //5.1
        {
            maxdec = 1;
        } else if (price >= 10000000)    //4.2
        {
            maxdec = 2;
        } else if (price >= 1000000)    //3.3
        {
            maxdec = 3;
        }

        if (dotlen > maxdec) {
            dotlen = maxdec;
        }

        if (dotlen < 0) {
            dotlen = 2;
        } else if (dotlen == 0) {
            STD.LongtoString(price);
        }
        String temp = STD.DataToString(price, dotlen);

        temp = handlePoint(temp, xsws);

        return temp;
    }
    /**
     * 根据小数点位数返回价格的字符串
     * Created by [rzy] 2016/9/29 17:34
     **/
    public static String getStringByPrice1(int price, int now, int dotlen, int xsws) {
        if (price == 0) {
            return "----";
        }

        int maxdec = 4;
        if (price >= 100000000)    //5.1
        {
            maxdec = 1;
        } else if (price >= 10000000)    //4.2
        {
            maxdec = 2;
        } else if (price >= 1000000)    //3.3
        {
            maxdec = 3;
        }

        if (dotlen > maxdec) {
            dotlen = maxdec;
        }

        if (dotlen < 0) {
            dotlen = 2;
        } else if (dotlen == 0) {
            STD.LongtoString(price);
        }
        String temp = STD.DataToString1(price, dotlen);

        temp = handlePoint(temp, xsws);

        return temp;
    }
    /**
     * 处理显示位数
     *
     * @param str  所需处理字符串
     * @param xsws 显示位数
     * @return
     */
    private static String handlePoint(String str, int xsws) {

        if (xsws != 0) {
            String temp[] = str.split("\\.");
            if (temp.length > 1) {
                if (null != temp[1]) {
                    String endStr = temp[1];
                    if (endStr.length() > xsws) {
//						double strDouble = Double.parseDouble(str);

                        BigDecimal b = new BigDecimal(str);
                        b = b.setScale(xsws, BigDecimal.ROUND_HALF_UP);
                        return b.toString();
                    }
                }
            }
        }

        return str;
    }

    public static String getRateLongHu(int rate, boolean flag, boolean hasOper) {
        if (rate == 0) {
            return "0.00%";
        }
        StringBuffer str = new StringBuffer();
        STD.DataToString(str, (long) rate * 100, 2);

        if (flag) {
            str.append("%");
        }

        if (hasOper && rate > 0)
            str.insert(0, "+");

        return str.toString();
    }

    // 单位：万元，放大100倍
    public static String getStringByLongHu(long data) {
        if (data == 0) {
            return "0.00";
        }

        StringBuffer out = new StringBuffer();
        if (data < 0) {
            out.insert(0, "-");
            data *= -1;
        }

        data /= 100;
        data += 50;
        data /= 100;

        out.append(data / 100);
        out.append('.');
        out.append(data % 100);

        return out.toString();
    }

    //根据需要显示的宽度返回量的字符串
    //flag: data为0时是否显示
    public static String getStringByVolume(long data, int market, int unit, int width, boolean flag) {
        if (flag && data == 0) {
            return "0";
        }

//		if(unit <= 0)
//			unit = 100;
//		data	= (data + unit/2)/unit;

        StringBuffer out = new StringBuffer();
        if (market == Global_Define.MARKET_HK) {
            IntToWidth_HK(data, width, out);
        } else {
            if (unit <= 0)
                unit = 100;
            data = (data + unit / 2) / unit;

            IntToWidth(data, width, out);
        }

        if (data < 0)
            out.insert(0, "-");

        return out.toString();
    }

    public static String getStringByAmount(long data, int market, int unit, int width, boolean flag) {
        if (flag && data == 0) {
            return "0";
        }
        if (market == Global_Define.MARKET_HK) {
            if (unit <= 0)
                unit = 100;
            data = (data + unit / 2) / unit;
        }

        return getStringByVolume(data, market, unit, width, flag);
    }

    public static String getStringByGu(long data, int market, int unit, int width, boolean flag) {
        if (flag && data == 0) {
            return "0";
        }
        StringBuffer out = new StringBuffer();
        if (unit <= 0)
            unit = 100;
        data = (data + unit / 2) / unit;
//		if (market == Global_Define.MARKET_HK)
//		{
//			IntToWidth_HK(data, width, out);
//		}
//		else
        {
            IntToWidth_Gu(data, width, out);
        }

        if (data < 0)
            out.insert(0, "-");

        return out.toString();
    }

    public static String getStringByGu_unprocess(long data, int market, int unit, int width, boolean flag) {
        if (flag && data == 0) {
            return "0";
        }
        StringBuffer out = new StringBuffer();
//		if(unit <= 0)
//			unit = 100;
//		data	= (data + unit/2)/unit;
////		if (market == Global_Define.MARKET_HK)
////		{
////			IntToWidth_HK(data, width, out);
////		}
////		else
//		{
//			IntToWidth_Gu(data, width, out);
//		}
//
//		if(data < 0)
//			out.insert(0, "-");

        out.append(data);

        return out.toString();
    }

    //不转换单位
    public static String getVolume(long data, int market, int unit, boolean flag) {
        //港股显示单位
        if (market == Global_Define.MARKET_HK) {

            return getStringByVolume(data * unit, market, unit, 6, flag);
        }

        if (flag && data == 0) {
            return "0";
        } else if (data == 0) {
            return "----";
        }
        if (unit <= 0)
            unit = 100;
        data = (data + unit / 2) / unit;

        StringBuffer str = new StringBuffer();
        str.append(data);

        return str.toString();
    }

    //转换字符串
    public static int WidthString(int flag, int width, double data, StringBuffer out) {
        out.delete(0, out.length());
        STD.DataToString(out, (long) (data * 10000), 2);
        //out.append( String.format("%.2f", data) );
        int len = out.length();
        if (width >= (len - 3) || flag >= 2)    //可显示
        {
            int left = width - len + 3;

            if (left < 1) {
                return WidthString(flag + 1, width, data / 10000.0, out);
            } else if (left == 1) {
                out.delete(0, out.length());
                out.append((long) (data + 0.5));
            } else {
                left--;
                if (left < 0) {
                    left = 0;
                } else if (left > 2) {
                    left = 2;
                }
                out.delete(0, out.length());
                STD.DataToString(out, (long) (data * 10000), left);
//				String temp = String.format("%%.%df", left);
//				out.append( String.format(temp, data) );
            }
            return flag;
        }

        if (flag == 0) {
            width -= 1;
        }

        return WidthString(flag + 1, width, data / 10000.0, out);
    }

    public static int IntToWidth_Gu(long data, int width, StringBuffer out) {
        if (data == 0) {
            out.append("----");
            return out.length();
        }

        if (data < 0) {
            //out.append("-");
            return IntToWidth(-data, width - 1, out);
        }
        out.append(data);
        int len = out.length();
        if (width >= len && data < 100)    //可显示
        {
            return 0;
        }

        int flag = WidthString(1, width - 1, data / 100.0, out);

        if (flag == 1)
            out.append("万");
        else if (flag == 2)
            out.append("亿");

        return flag;
    }

    public static int IntToWidth(long data, int width, StringBuffer out) {
        if (data == 0) {
            out.append("----");
            return out.length();
        }

        if (data < 0) {
            //out.append("-");
            return IntToWidth(-data, width - 1, out);
//			return IntToWidth(-data, width, out);
        }
        out.append(data);
        int len = out.length();
        if (width >= len && data < 10000)    //可显示
        {
            return 0;
        }

        int flag = WidthString(1, width - 1, data / 10000.0, out);

        if (flag == 1)
            out.append("万");
        else if (flag == 2)
            out.append("亿");
        else if (flag == 3)
            out.append("万亿");

        return flag;
    }

    public static int WidthString_HK(int flag, int width, double data, StringBuffer out) {
        int len = STD.getDataLength((long) data);
        if (width >= len && data < 1000)    //可显示
        {
            int left = width - len;
            if (flag > 1 && left <= 2)
                STD.DataToString(out, (long) (data * 10000), 1);
            else if (flag > 1 && left > 2)
                STD.DataToString(out, (long) (data * 10000), 2);
            else
                out.append((long) data);
            return flag;
        }

        if (flag == 0) {
            width -= 1;
        }
        if (flag >= 3) {
            return flag;
        }

        return WidthString_HK(flag + 1, width, data / 1000.0, out);
    }

    public static int IntToWidth_HK(long data, int width, StringBuffer out) {
        if (data == 0) {
            out.append("----");
            return out.length();
        }

        if (data < 0) {
            //out.append("-");
            return IntToWidth_HK(-data, width - 1, out);
        }

        if (width > STD.getDataLength(data) && data < 10000)    //可显示
        {
            out.append(data);
            return 0;
        }

        int flag = WidthString_HK(1, width - 1, data / 1000.0, out);

        if (flag == 1)
            out.append("K");
        else if (flag == 2)
            out.append("M");
        else if (flag == 3)
            out.append("B");

        return flag;
    }

    //由涨跌获取涨跌幅
    public static int getZDF(int zd, int yesterday) {
        if (yesterday == 0) {
            return 0;
        }
        double add = (zd > 0) ? 0.5 : (-0.5);
        return (int) (zd * 10000.0 / yesterday + add);
    }

    //由涨跌获取涨跌幅 字符串
    //flag:是否显示百分号
    //isSign:是否显示符号，只有负数才显示符号
    public static String getZDF(int zd, int yesterday, int now, boolean flag, boolean isSign) {
        int zdf = getZDF(zd, yesterday);

        if (zdf == 0 && now == 0) {
            return "----";
        }
        //不显示符号
        if (isSign == false && zdf < 0) {
            zdf = -zdf;
        }

        StringBuffer str = new StringBuffer();
        if (zdf < 1000 || zdf > -1000)
            STD.DataToString(str, (long) zdf * 100, 2);
        else
            STD.DataToString(str, (long) zdf * 100, 1);

        if (flag) {
            str.append("%");
        }
        return str.toString();
    }

    //由成交量获取换手率
    public static int getHSL(long volume, long ltgb) {
        if (ltgb == 0) {
            return 0;
        }
        return (int) (volume * 10000.0 / ltgb + 0.5);
    }

    //由成交量获取换手率 字符串
    //flag:是否显示百分号
    public static String getHSL(long volume, long ltgb, boolean flag) {
        int hsl = getHSL(volume, ltgb);

        return getHSL(hsl, flag);
    }

    public static String getHSL(int hsl, boolean flag) {
        if (hsl == 0) {
            return "----";
        }
        StringBuffer str = new StringBuffer();
        STD.DataToString(str, (long) hsl * 100, 2);

        if (flag) {
            str.append("%");
        }
        return str.toString();
    }

    //by ngj add
    public static String getZHENFU(int zhenfu, boolean flag) {
        if (zhenfu == 0) {
            return "----";
        }
        StringBuffer str = new StringBuffer();
        STD.DataToString(str, (long) zhenfu * 100, 2);

        if (flag) {
            str.append("%");
        }
        return str.toString();
    }

    /**
     * 比率
     * flag:是否显示百分号
     */
    public static String getRate(int rate, boolean flag) {
        if (rate == 0) {
            return "----";
        }
        StringBuffer str = new StringBuffer();
        STD.DataToString(str, (long) rate * 100, 2);

        if (flag) {
            str.append("%");
        }
        return str.toString();
    }

    //显示颜色
    public static int getColor(long data) {
        return getColor(data, 0);
    }

    public static int getColor(long data, long base) {
        int color = COLOR.PRICE_EQUAL;
        if (data == 0 && base != 0) {
            color = COLOR.DATA_NULL;
        } else if (data > base) {
            color = COLOR.PRICE_UP;
        } else if (data < base) {
            color = COLOR.PRICE_DOWN;
        } else {
//			L.e("ViewTools", "data = " + data + ", base = " + base);
        }
        return color;
    }

    public static int getColor(int data) {
        return getColor(data, 0);
    }

    public static int getColor(int data, int base) {
        int color = COLOR.PRICE_EQUAL;
        if (data == 0 && base != 0) {
            color = COLOR.DATA_NULL;
        } else if (data > base) {
            color = COLOR.PRICE_UP;
        } else if (data < base) {
            color = COLOR.PRICE_DOWN;
        } else {
//			L.e("ViewTools", "data = " + data + ", base = " + base);
        }
        return color;
    }

    public static int getTradeColor(int data, int base) {
        int color = Color.rgb(0x00, 0x49, 0xA4);
        if (data == 0 && base != 0) {
            color = Color.rgb(0x00, 0x49, 0xA4);
        } else if (data > base) {
            color = COLOR.PRICE_UP;
        } else if (data < base) {
            color = COLOR.PRICE_DOWN;
        }
        return color;
    }

    public static int getTradeColor(String strdata, String strbase) {
        int color = Color.rgb(0x00, 0x49, 0xA4);
        if (strdata == null || strdata.length() <= 0 || strbase == null || strbase.length() <= 0)
            return color;

        try {
            double data = Double.valueOf(strdata);
            double base = Double.valueOf(strbase);
            if (data == 0.0 && base != 0.0) {
                color = Color.rgb(0x00, 0x49, 0xA4);
            } else if (data > base) {
                color = COLOR.PRICE_UP;
            } else if (data < base) {
                color = COLOR.PRICE_DOWN;
            }
        } catch (Exception e) {
            e.printStackTrace();
            color = Color.rgb(0x00, 0x49, 0xA4);
        }
        return color;
    }

    //买卖气颜色
    public static int getMMQColor(double mmq) {
        int color = Color.WHITE;
        if (mmq == 0)                        //0
            color = Color.rgb(50, 50, 50);    //0x323232
        else if (mmq > 0 && mmq <= 20)        //(0, 20)
            color = Color.rgb(90, 0, 0);    //0x5A0000
        else if (mmq > 20 && mmq <= 40)        //(20, 40)
            color = Color.rgb(180, 0, 0);    //0xB40000
        else if (mmq > 40 && mmq <= 70)        //(40, 70)
            color = Color.rgb(255, 0, 0);    //0xFF0000
        else if (mmq > 70 && mmq <= 100)        //(70, 100)
            color = Color.rgb(255, 0, 255);    //0xFF00FF
        else if (mmq >= -20 && mmq < 0)        //(0, -20)
            color = Color.rgb(0, 90, 0);    //0x005A00
        else if (mmq >= -40 && mmq < -20)    //(-20, -40)
            color = Color.rgb(0, 180, 0);    //0x00B400
        else if (mmq >= -70 && mmq < -40)    //(-40, -70)
            color = Color.rgb(0, 255, 0);    //0x00FF00
        else if (mmq >= -100 && mmq < -70)    //(-70, -100)
            color = Color.rgb(0, 255, 255);    //0x00FFFF

        return color;
    }

    public static String formatDate(String date) {
        if (date.length() < 8) {
            return date;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(date.substring(0, 4)).append("-");
        sb.append(date.substring(4, 6)).append("-");
        sb.append(date.substring(6));
        return sb.toString();
    }

    public static String formatTime(String time) {
        if (time.length() < 5) {
            return time;
        }
        StringBuffer sb = new StringBuffer();
        if (time.length() == 5) {
            time = "0" + time;
        }
        sb.append(time.substring(0, 2)).append(":");
        sb.append(time.substring(2, 4));
//		sb.append(time.substring(4));
        return sb.toString();
    }

    //计算香港价位，输入的价格参数为整型，放大了一百倍。返回整型价位，放大一百倍
    public static int getHKPriceUnit(int price) {
        for (int i = 0; i < j.length; i++) {
            if (price < j[i]) {
                return w[i];
            }
        }
        return 0;
    }

    //根据加减，计算新的价格
    public static int getHKPriceByStep(int price, boolean up) {
        int jw = 0;
        if (up) {
            for (int i = 0; i < j.length; i++) {
                if (price < j[i]) {
                    jw = w[i];
                    price += jw;
                    break;
                }
            }
        } else {
            for (int i = 0; i < j.length; i++) {
                if (price <= j[i]) {
                    jw = w[i];
                    price -= jw;
                    break;
                }
            }
        }

        if (jw > 0) {
            price = price / jw * jw;
        }
        return price;
    }


    public static long getAverage(long num1, long num2) {
        long ret = 0;
        long max = (num1 >= num2) ? num1 : num2;
        long min = (num1 >= num2) ? num2 : num1;
        if (max > 0 && min < 0) {
            if (Math.abs(max) > Math.abs(min))
                ret = (max - min) / 2;
            else
                ret = (-max + min) / 2;
        } else
            ret = (max + min) / 2;

        return ret;
    }
}
