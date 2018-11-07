package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/8/31
 * describe:
 */
public class Emeralds {

    /**
     * total_price : 3980
     * goods_num : 1
     * goods_info : [{"goods_id":1,"goods_sn":"3h66872","goods_img":"/uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg"}]
     */

    private String total_price;
    private int goods_num;
    private List<GoodsInfoBean> goods_info;

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }

    public static class GoodsInfoBean {
        /**
         * goods_id : 1
         * goods_sn : 3h66872
         * goods_img : /uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg
         */
        private int ct_id;
        private String incr_id;
        private int goods_id;
        private String goods_sn;
        private String goods_img;

        public String getIncr_id() {
            return incr_id;
        }

        public void setIncr_id(String incr_id) {
            this.incr_id = incr_id;
        }

        public int getCt_id() {
            return ct_id;
        }

        public void setCt_id(int ct_id) {
            this.ct_id = ct_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
}
