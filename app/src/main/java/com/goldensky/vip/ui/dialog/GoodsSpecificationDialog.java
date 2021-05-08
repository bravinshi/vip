package com.goldensky.vip.ui.dialog;

import com.goldensky.framework.ui.dialog.BottomDialog;
import com.goldensky.vip.bean.InventoryBean;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/8 16:03
 * 包名： com.goldensky.vip.ui.dialog
 * 类说明：
 */
public class GoodsSpecificationDialog extends BottomDialog {

    public static class First {
        public String name;
        public List<Second> seconds;
    }

    public static class Second {
        public String content;
        public Integer selectState;// 1 未选中 2 不可选中 3 选中
        public List<InventoryBean> inventories;// 当前可选项存在这些规格中
    }
}
