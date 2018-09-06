package com.microple.jademall.bean;

/**
 * author: xiaoguagnfei
 * date: 2018/9/5
 * describe:
 */
public class CustiomerDetail {

    /**
     * returns : {"as_id":1,"aftersales_bn":"2018082487460","goods_id":1,"status":0,"reason":"不喜欢","desc":"","add_time":"2018-08-24 09:32:28","refunds_fee":"99","goods_info":{"goods_id":1,"goods_name":"糯冰种飘翠福瓜挂件","goods_sn":"3h66872","goods_price":"3980.00","goods_img":"/uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg"},"status_desc":"待审核"}
     */

    private ReturnsBean returns;

    public ReturnsBean getReturns() {
        return returns;
    }

    public void setReturns(ReturnsBean returns) {
        this.returns = returns;
    }

    public static class ReturnsBean {
        /**
         * as_id : 1
         * aftersales_bn : 2018082487460
         * goods_id : 1
         * status : 0
         * reason : 不喜欢
         * desc :
         * add_time : 2018-08-24 09:32:28
         * refunds_fee : 99
         * goods_info : {"goods_id":1,"goods_name":"糯冰种飘翠福瓜挂件","goods_sn":"3h66872","goods_price":"3980.00","goods_img":"/uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg"}
         * status_desc : 待审核
         */

        private int as_id;
        private String aftersales_bn;
        private int goods_id;
        private int status;
        private String reason;
        private String desc;
        private String add_time;
        private String refunds_fee;
        private GoodsInfoBean goods_info;
        private String status_desc;

        public int getAs_id() {
            return as_id;
        }

        public void setAs_id(int as_id) {
            this.as_id = as_id;
        }

        public String getAftersales_bn() {
            return aftersales_bn;
        }

        public void setAftersales_bn(String aftersales_bn) {
            this.aftersales_bn = aftersales_bn;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getRefunds_fee() {
            return refunds_fee;
        }

        public void setRefunds_fee(String refunds_fee) {
            this.refunds_fee = refunds_fee;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public String getStatus_desc() {
            return status_desc;
        }

        public void setStatus_desc(String status_desc) {
            this.status_desc = status_desc;
        }

        public static class GoodsInfoBean {
            /**
             * goods_id : 1
             * goods_name : 糯冰种飘翠福瓜挂件
             * goods_sn : 3h66872
             * goods_price : 3980.00
             * goods_img : /uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg
             */

            private int goods_id;
            private String goods_name;
            private String goods_sn;
            private String goods_price;
            private String goods_img;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
