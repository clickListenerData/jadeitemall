package com.microple.jademall.bean;

/**
 * author: xiaoguagnfei
 * date: 2018/11/6
 * describe:
 */
public class JifenDetail {

    /**
     * increment : {"user_id":86,"goods_id":81,"order_sn":"20181016154421","goods":{"goods_id":81,"goods_name":"翡翠手表123","goods_sn":"fcsb123","goods_img":"/uploads/20181024/cfba1cd89c8373a48afe0b872ee20b7e.jpg","goods_price":"110.00"},"order_amount":"110.00","add_time":"2018-10-16 15:38:16","pay_type":"微信支付"}
     */

    private IncrementBean increment;

    public IncrementBean getIncrement() {
        return increment;
    }

    public void setIncrement(IncrementBean increment) {
        this.increment = increment;
    }

    public static class IncrementBean {
        /**
         * user_id : 86
         * goods_id : 81
         * order_sn : 20181016154421
         * goods : {"goods_id":81,"goods_name":"翡翠手表123","goods_sn":"fcsb123","goods_img":"/uploads/20181024/cfba1cd89c8373a48afe0b872ee20b7e.jpg","goods_price":"110.00"}
         * order_amount : 110.00
         * add_time : 2018-10-16 15:38:16
         * pay_type : 微信支付
         */

        private int user_id;
        private int goods_id;
        private String order_sn;
        private GoodsBean goods;
        private String order_amount;
        private String add_time;
        private String pay_type;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public static class GoodsBean {
            /**
             * goods_id : 81
             * goods_name : 翡翠手表123
             * goods_sn : fcsb123
             * goods_img : /uploads/20181024/cfba1cd89c8373a48afe0b872ee20b7e.jpg
             * goods_price : 110.00
             */

            private int goods_id;
            private String goods_name;
            private String goods_sn;
            private String goods_img;
            private String goods_price;

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

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }
        }
    }
}
