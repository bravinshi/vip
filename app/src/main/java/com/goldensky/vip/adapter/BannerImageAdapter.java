package com.goldensky.vip.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goldensky.vip.helper.ImageLoaderHelper;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 11:45
 * 包名： com.goldensky.vip.adapter
 * 类说明：
 */
public class BannerImageAdapter extends BannerAdapter<String, BannerImageAdapter.BannerViewHolder> {

    public BannerImageAdapter(List<String> pics) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(pics);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
        ImageLoaderHelper.loadImage(holder.imageView, data);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
