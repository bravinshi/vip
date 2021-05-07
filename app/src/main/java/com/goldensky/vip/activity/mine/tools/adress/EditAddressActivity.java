package com.goldensky.vip.activity.mine.tools.adress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityEditAddressBinding;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

public class EditAddressActivity extends BaseActivity<ActivityEditAddressBinding, AddressViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_address;
    }
}