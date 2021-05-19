package com.goldensky.vip.activity.mine.tools;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goldensky.framework.constant.PermissionConstants;
import com.goldensky.framework.util.ImageUtils;
import com.goldensky.framework.util.PictrueSaveUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.SuperStBean;
import com.goldensky.vip.databinding.ActivityShareToFriendBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

import okhttp3.ResponseBody;

public class ShareToFriendActivity extends BaseActivity<ActivityShareToFriendBinding, AccountViewModel> implements View.OnClickListener {

    private static final int CHECK_PERMISSION_CAMERA = 1;
    private static final int CHECK_PERMISSION_WRITE_EXTERNAL_STORAGE = 2;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarShareToFriend.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
        mViewModel.getWxAppletCode(AccountHelper.getUserId(), AccountHelper.getInvitationCode());
        if (AccountHelper.getUserPic() != null && !AccountHelper.getUserPic().equals("")) {
            Glide.with(this).load(AccountHelper.getUserPic()).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadShareToFriend);
        } else {
            Glide.with(this).load(DefaultUrlEnum.DEFAULTHEADPIC.value).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadShareToFriend);
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
                if (superStBean != null) {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MODE_PRIVATE) {
            if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permissions[0])) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (Manifest.permission.CAMERA.equals(permissions[1])) {
                        if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                            saveImage();
                        } else {
                            ToastUtils.showShort("请在权限设置里允许访问相机");
                        }
                    }
                } else {
                    ToastUtils.showShort("请在权限设置里允许访问存储");
                }
            }

        }
    }


    private void saveImage() {
        Bitmap bitmap = PictrueSaveUtils.testViewSnapshot(mBinding.clShareToFriend);
        PictrueSaveUtils.saveBitmap(this, bitmap);
    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(ShareToFriendActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(ShareToFriendActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            saveImage();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MODE_PRIVATE);
            }
        }
    }
}