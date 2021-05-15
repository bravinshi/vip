package com.goldensky.framework.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictrueSaveUtils {

    //对View控件里面的内容进行截图
    public static Bitmap testViewSnapshot(View view) {
        //使控件可以进行缓存
        view.setDrawingCacheEnabled(true);
        //获取缓存的 Bitmap
        Bitmap drawingCache = view.getDrawingCache();
        //复制获取的 Bitmap
        drawingCache = Bitmap.createBitmap(drawingCache);
        //关闭视图的缓存
        view.setDrawingCacheEnabled(false);

        return drawingCache;
    }

    //保存BitMap图片到本地文件
    public static void saveBitmap(Context context, Bitmap bitmap) {
        // 检查是否有访问本地图库的权限
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
            ToastUtils.showShort("请在权限设置里允许访问相册");
            return;
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ToastUtils.showShort("请在权限设置里允许存储");
            return;
        }
        //获取需要存储到本地的路径
//        File file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = Environment.getExternalStorageDirectory();
        //如果文件夹不存在 就创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        File path = new File(file, System.currentTimeMillis() + ".png");

//        if (!path.exists()) {
//            path.mkdirs();
//        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            // compress - 压缩的意思  将bitmap保存到本地文件中
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream);
            //存储完成后需要清除相关的进程
            fileOutputStream.flush();
            fileOutputStream.close();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(path);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            context.sendBroadcast(intent);
            ToastUtils.showShort("保存成功");
        } catch (FileNotFoundException e) {
            LogUtils.i("error:" + e.toString());
        } catch (IOException e) {
            LogUtils.i("error:" + e.toString());
        }
        LogUtils.i(path.getPath());
    }
}
