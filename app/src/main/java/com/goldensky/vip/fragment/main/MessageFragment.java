package com.goldensky.vip.fragment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldensky.vip.R;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.databinding.FragmentMessageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends LazyLoadFragment<FragmentMessageBinding, PublicViewModel> {


    @Override
    public void onLazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
}