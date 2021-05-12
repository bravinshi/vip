package com.goldensky.vip.viewmodel.order;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.api.account.AccountService;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.CommentReqBean;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderCommentViewModel extends PublicViewModel {
    public static final String RESULT_CODE_KEY = "RESULT_CODE_KEY";
    public static final String RESULT_MSG_KEY = "RESULT_MSG_KEY";
    public MutableLiveData<Map<String, String>> commitResult = new MutableLiveData<>();


    public void orderComment(String evaluatescore, String evaluatecontent, String evaluatepic, String l_uid, String secondorderid) {
        CommentReqBean reqBean = new CommentReqBean(evaluatecontent, evaluatepic, evaluatescore, l_uid, secondorderid);
        RetrofitAgent.create(OrderService.class)
                .orderComment(reqBean)
                .subscribe(new Observer<NetResponse<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NetResponse<Object> value) {
                        if (value != null) {
                            Map<String, String> map = new HashMap<>();
                            if (value.getStatus() == 1) {
                                map.put(RESULT_CODE_KEY, "1");
                                map.put(RESULT_MSG_KEY, "");
                            } else {
                                map.put(RESULT_CODE_KEY, "0");
                                String msg = value.getMessage();
                                if (StringUtils.isEmpty(msg)) {
                                    map.put(RESULT_MSG_KEY, "评价失败");
                                } else {
                                    map.put(RESULT_MSG_KEY, msg);
                                }
                            }

                            commitResult.postValue(map);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //星级评价数据
    public List<Integer> stars = new ArrayList<>();

    public void initImages() {
        for (int i = 0; i < 5; i++) {
            stars.add(R.mipmap.pingjia_icon_star_line);
        }
    }

    public void updateStars(int level) {
        stars.clear();
        if (level == 0) {
            stars.add(R.mipmap.pingjia_icon_star_red);
            for (int i = 0; i < 4; i++) {
                stars.add(R.mipmap.pingjia_icon_star_line);
            }
        } else if (level == 4) {
            for (int i = 0; i < 5; i++) {
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
