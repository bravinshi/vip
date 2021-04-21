package com.goldensky.vip.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeBean implements MultiItemEntity {
     private int hItemType; //1-轮播 2-消息 3-为你推荐 4-今日爆款 5-优惠专区 6-金天优选
     private String itemTitle;
     private String singleUrl;
     private String message;
     private List<ProductBean> productList;
     private List bannerUrls;
     private Integer singleImgId;

     private List<List<ProductBean>> jtyxProductList;//金天优选

     public HomeBean(int hItemType) {
         this.hItemType = hItemType;
     }

    public int gethItemType() {
        return hItemType;
    }

    public void sethItemType(int hItemType) {
        this.hItemType = hItemType;
    }

    public String getItemTitle() {
         return itemTitle;
     }

     public void setItemTitle(String itemTitle) {
         this.itemTitle = itemTitle;
     }

     public String getSingleUrl() {
         return singleUrl;
     }

     public void setSingleUrl(String singleUrl) {
         this.singleUrl = singleUrl;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

    public Integer getSingleImgId() {
        return singleImgId;
    }

    public void setSingleImgId(Integer singleImgId) {
        this.singleImgId = singleImgId;
    }

    public List<ProductBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductBean> productList) {
        this.productList = productList;
    }

    public List<List<ProductBean>> getJtyxProductList() {
        return jtyxProductList;
    }

    public void setJtyxProductList(List<List<ProductBean>> jtyxProductList) {
        this.jtyxProductList = jtyxProductList;
    }

    @Override
    public int getItemType() {
        return hItemType;
    }

    public static  class  ProductBean implements MultiItemEntity {
         private String imgUrl;
         private String productName;
         private String productPrice;
         private Integer productType; //1-推荐  2-爆款 3-金天优选
        private String originPrice;

         private Integer imgId;

         public ProductBean(Integer productType, Integer imgId, String productName, String productPrice) {
             this.productType = productType;
             this.imgId = imgId;
             this.productName = productName;
             this.productPrice = productPrice;
         }

        public Integer getProductType() {
            return productType;
        }

        public void setProductType(Integer productType) {
            this.productType = productType;
        }

        public String getImgUrl() {
             return imgUrl;
         }

         public void setImgUrl(String imgUrl) {
             this.imgUrl = imgUrl;
         }

         public String getProductName() {
             return productName;
         }

         public void setProductName(String productName) {
             this.productName = productName;
         }

         public String getProductPrice() {
             return productPrice;
         }

         public void setProductPrice(String productPrice) {
             this.productPrice = productPrice;
         }

         public Integer getImgId() {
             return imgId;
         }

         public void setImgId(Integer imgId) {
             this.imgId = imgId;
         }

        public String getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(String originPrice) {
            this.originPrice = originPrice;
        }

        @Override
        public int getItemType() {
            return productType;
        }
    }

}
