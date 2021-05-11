package com.goldensky.vip.viewmodel.order;

import com.goldensky.vip.R;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class OrderCommentViewModel extends PublicViewModel {
    public List<Integer> stars = new ArrayList<>();

    public void initImages() {
        for (int i = 0; i < 5; i ++) {
            stars.add(R.mipmap.pingjia_icon_star_line);
        }
    }

    public void updateStars(int level) {
       stars.clear();
       if (level == 0) {
           stars.add(R.mipmap.pingjia_icon_star_red);
           for (int i = 0; i < 4; i ++) {
               stars.add(R.mipmap.pingjia_icon_star_line);
           }
       } else if (level == 4) {
           for (int i = 0; i < 5; i ++) {
               stars.add(R.mipmap.pingjia_icon_star_red);
           }
       } else if (level == 1) {
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_line);
           stars.add(R.mipmap.pingjia_icon_star_line);
           stars.add(R.mipmap.pingjia_icon_star_line);

       } else if (level == 2) {
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_line);
           stars.add(R.mipmap.pingjia_icon_star_line);
       } else if (level == 3) {
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_red);
           stars.add(R.mipmap.pingjia_icon_star_line);
       }
    }
}
