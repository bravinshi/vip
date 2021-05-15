package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goldensky.framework.util.ImageUtils;
import com.goldensky.framework.util.PictrueSaveUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.SuperStBean;
import com.goldensky.vip.databinding.ActivityShareToFriendBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

import java.io.InputStream;

import okhttp3.ResponseBody;

public class ShareToFriendActivity extends BaseActivity<ActivityShareToFriendBinding, AccountViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarShareToFriend.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
        mViewModel.getWxAppletCode(AccountHelper.getUserId(),AccountHelper.getInvitationCode());
        if(AccountHelper.getUserPic()!=null&&!AccountHelper.getUserPic().equals("")){
            Glide.with(this).load(AccountHelper.getUserPic()).into(mBinding.ivHeadShareToFriend);
        }else {
            Glide.with(this).load(DefaultUrlEnum.DEFAULTHEADPIC.value).into(mBinding.ivHeadShareToFriend);
        }
        mViewModel.getSuperSt(AccountHelper.getUserSuperiorId());
    }

    @Override
    public void observe() {
        mViewModel.codeLive.observe(this, new Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody body) {
                InputStream inputStream = body.byteStream();
                Bitmap bitmap = ImageUtils.getBitmap(inputStream);
                Glide.with(ShareToFriendActivity.this).load(bitmap).into(mBinding.ivCodeShareToFriend);
            }
        });
        mViewModel.mSuperStBean.observe(this, new Observer<SuperStBean>() {
            @Override
            public void onChanged(SuperStBean superStBean) {
               if(superStBean!=null){
                   mBinding.nameShareToFirend.setText(superStBean.getEnterprisename());
               }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_share_to_friend;
    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_DENIED&&ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_DENIED){
            //判断为没有权限，换起权限申请询问
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            //判断已经获取权限后的操作
            Bitmap bitmap = PictrueSaveUtils.testViewSnapshot(mBinding.clShareToFriend);
            PictrueSaveUtils.saveBitmap(this,bitmap);
        }

    }
}