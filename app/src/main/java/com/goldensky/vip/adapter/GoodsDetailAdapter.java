package com.goldensky.vip.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goldensky.vip.R;
import com.goldensky.vip.helper.ImageLoaderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/20 14:45
 * 包名： com.goldensky.vip.adapter
 * 类说明：
 */
public class GoodsDetailAdapter extends RecyclerView.Adapter<GoodsDetailAdapter.ImageViewHolder> {

    private List<Integer> pics = new ArrayList<>();

    public void setPics(List<Integer> data) {
        pics.clear();
        pics.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        Log.d("RetrofitLog", "onCreateViewHolder");
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv);

        ImageLoaderHelper.loadImage(imageView, pics.get(position));
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
