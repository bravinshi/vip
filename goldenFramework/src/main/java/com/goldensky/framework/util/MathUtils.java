package com.goldensky.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 创建日期：2020/10/20 13:41
 * 描述:
 * 作者:wuzhenbo
 */
public class MathUtils {

    public static String getDistance(double var1, double var2) {

        double resD = var1 - var2;
        if (resD < 0) {
            resD = 0;
        }
        return MathUtils.getResultTwo(resD + "");
    }

    //保留两位小数,默认四舍五入
    public static String getResultTwo(String s) {
        double sDouble = 0.0;
        try {
            sDouble = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return String.format("%.2f", sDouble);
    }

    /**
     * double去掉科学计数法，1.2345678945612E11
     * 当Double的值很大时，显示的结果会变成带E的科学计数法显示，在报表的数据显示的时候不方便阅读，需要去掉E，将原数据显示
     *
     * @param d
     * @return
     */
    public static String getResultNoE(Double d) {
//        BigDecimal bg = new BigDecimal(d + "");
//        //String toPlainString() 返回不带指数字段的此,不用科学计数法表示。
//        if (bg == null) {
//            return "";
//        }
//        return bg.toPlainString();
//        NumberFormat nf = NumberFormat.getInstance();
//        //设置保留多少位小数
//        nf.setMaximumFractionDigits(2);
//        // 取消科学计数法
//        nf.setGroupingUsed(false);
//        //返回结果
//        return nf.format(d);
        NumberFormat nf = NumberFormat.getInstance();

        nf.setGroupingUsed(false);
        return nf.format(d);
    }

    public static String bigDecimalString (Double value, int scale) {
        return new BigDecimal(Double.toString(value)).setScale(scale, RoundingMode.UP).toString();
    }
}
