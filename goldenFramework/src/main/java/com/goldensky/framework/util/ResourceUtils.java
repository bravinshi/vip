package com.goldensky.framework.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 资源文件获取工具类
 */
public class ResourceUtils {
    private static Resources sResources;

    public static Resources getResources() {
        if (sResources == null) {
            sResources = Utils.getApp().getResources();
        }
        return sResources;
    }

    private ResourceUtils() {
        throw new AssertionError();
    }

    /**
     * get an asset using ACCESS_STREAMING mode. This provides access to files
     * that have been bundled with an application as assets -- that is, files
     * placed in to the "assets" directory.
     *
     * @param fileName The name of the asset to open. This name can be hierarchical.
     */
    public static String geFileFromAssets(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get content from a raw resource. This can only be used with resources
     * whose value is the name of an asset files -- that is, it can be used to
     * open drawable, sound, and raw resources; it will fail on string and color
     * resources.
     *
     * @param resId The resource identifier to open, as generated by the appt
     *              tool.
     */
    public static String geFileFromRaw(int resId) {
        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据资源的名字获取其ID值
     */
    public static int getIdByName(Context context, String className, String name) {
        String packageName = context.getPackageName();
        Class r = null;
        int id = 0;
        try {
            r = Class.forName(packageName + ".R");

            Class[] classes = r.getClasses();
            Class desireClass = null;

            for (int i = 0; i < classes.length; ++i) {
                if (classes[i].getName().split("\\$")[1].equals(className)) {
                    desireClass = classes[i];
                    break;
                }
            }

            if (desireClass != null)
                id = desireClass.getField(name).getInt(desireClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static int getInteger(int resId) {
        return getResources().getInteger(resId);
    }

    public static float getDimension(int resId) {
        return getResources().getDimension(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static TransitionDrawable getTransitionDrawable(int resId) {
        return (TransitionDrawable) getResources().getDrawable(resId);
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static Bitmap getBitmap(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }
}
