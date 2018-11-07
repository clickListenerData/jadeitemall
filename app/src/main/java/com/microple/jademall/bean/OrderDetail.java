package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/9/5
 * describe:
 */
public class OrderDetail {


    /**
     * order_detail : {"order_id":11,"order_sn":"20180816614523","order_status":2,"consignee":"我也没钱","mobile":"1333333333","address":"房地产那你还广泛的地方好好过哥哥反反复复","shipping_no":"3366006247874","shipping_name":"申通","pay_type":0,"goods_price":"3980.00","shipping_price":null,"total_price":"3980.00","add_time":"2018-09-05 09:20:52","shipping_time":null,"confirm_time":null,"user_id":11,"supplier_id":1,"buy_type":0,"logistics":[{"time":"2018-06-27 17:59:39","ftime":"2018-06-27 17:59:39","context":"快递柜-已签收","location":""},{"time":"2018-06-27 17:03:24","ftime":"2018-06-27 17:03:24","context":"快件派送至快递柜【城市花园小区广场电房对面（原e栈）】，派件员:郭章文(16602097509,)","location":""},{"time":"2018-06-27 14:25:50","ftime":"2018-06-27 14:25:50","context":"广东广州番禺罗家分部-郭章文(16602097509,)-派件中","location":""},{"time":"2018-06-27 13:40:50","ftime":"2018-06-27 13:40:50","context":"已到达-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 12:41:48","ftime":"2018-06-27 12:41:48","context":"已到达-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 12:23:49","ftime":"2018-06-27 12:23:49","context":"广东番禺转运中心-已发往-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 09:01:54","ftime":"2018-06-27 09:01:54","context":"广东广州转运中心-已装袋发往-广东番禺转运中心","location":""},{"time":"2018-06-27 06:37:46","ftime":"2018-06-27 06:37:46","context":"广东深圳罗湖中转部-已装袋发往-广东广州中转部","location":""},{"time":"2018-06-27 03:39:54","ftime":"2018-06-27 03:39:54","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:34:59","ftime":"2018-06-27 03:34:59","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:29:25","ftime":"2018-06-27 03:29:25","context":"广东深圳罗湖中转部-已发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:29:25","ftime":"2018-06-27 03:29:25","context":"广东深圳罗湖中转部-已进行装袋扫描","location":""},{"time":"2018-06-27 03:29:21","ftime":"2018-06-27 03:29:21","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 00:24:29","ftime":"2018-06-27 00:24:29","context":"罗湖华强北营业点-王正华1-已收件","location":""}],"supplier_name":"广州微标杆互联网科技有限公司","pay_name":"微信支付","label":"积分返还","goods_info":{"goods_id":5,"goods_name":"糯冰种飘翠福瓜挂件","goods_sn":"3k68584","goods_price":"3980.00","goods_img":"/uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg"}}
     */

    private OrderDetailBean order_detail;

    public OrderDetailBean getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(OrderDetailBean order_detail) {
        this.order_detail = order_detail;
    }

    public static class OrderDetailBean {
        /**
         * order_id : 11
         * order_sn : 20180816614523
         * order_status : 2
         * consignee : 我也没钱
         * mobile : 1333333333
         * address : 房地产那你还广泛的地方好好过哥哥反反复复
         * shipping_no : 3366006247874
         * shipping_name : 申通
         * pay_type : 0
         * goods_price : 3980.00
         * shipping_price : null
         * total_price : 3980.00
         * add_time : 2018-09-05 09:20:52
         * shipping_time : null
         * confirm_time : null
         * user_id : 11
         * supplier_id : 1
         * buy_type : 0
         * logistics : [{"time":"2018-06-27 17:59:39","ftime":"2018-06-27 17:59:39","context":"快递柜-已签收","location":""},{"time":"2018-06-27 17:03:24","ftime":"2018-06-27 17:03:24","context":"快件派送至快递柜【城市花园小区广场电房对面（原e栈）】，派件员:郭章文(16602097509,)","location":""},{"time":"2018-06-27 14:25:50","ftime":"2018-06-27 14:25:50","context":"广东广州番禺罗家分部-郭章文(16602097509,)-派件中","location":""},{"time":"2018-06-27 13:40:50","ftime":"2018-06-27 13:40:50","context":"已到达-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 12:41:48","ftime":"2018-06-27 12:41:48","context":"已到达-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 12:23:49","ftime":"2018-06-27 12:23:49","context":"广东番禺转运中心-已发往-广东广州番禺罗家分部","location":""},{"time":"2018-06-27 09:01:54","ftime":"2018-06-27 09:01:54","context":"广东广州转运中心-已装袋发往-广东番禺转运中心","location":""},{"time":"2018-06-27 06:37:46","ftime":"2018-06-27 06:37:46","context":"广东深圳罗湖中转部-已装袋发往-广东广州中转部","location":""},{"time":"2018-06-27 03:39:54","ftime":"2018-06-27 03:39:54","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:34:59","ftime":"2018-06-27 03:34:59","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:29:25","ftime":"2018-06-27 03:29:25","context":"广东深圳罗湖中转部-已发往-广东番禺中转部","location":""},{"time":"2018-06-27 03:29:25","ftime":"2018-06-27 03:29:25","context":"广东深圳罗湖中转部-已进行装袋扫描","location":""},{"time":"2018-06-27 03:29:21","ftime":"2018-06-27 03:29:21","context":"广东深圳罗湖中转部-已装袋发往-广东番禺中转部","location":""},{"time":"2018-06-27 00:24:29","ftime":"2018-06-27 00:24:29","context":"罗湖华强北营业点-王正华1-已收件","location":""}]
         * supplier_name : 广州微标杆互联网科技有限公司
         * pay_name : 微信支付
         * label : 积分返还
         * goods_info : {"goods_id":5,"goods_name":"糯冰种飘翠福瓜挂件","goods_sn":"3k68584","goods_price":"3980.00","goods_img":"/uploads/20180808/1a3c3ec77e6c798ace6b001e5bf3a123.jpg"}
         */

        private int order_id;
        private String order_sn;
        private int order_status;
        private String consignee;
        private String mobile;
        private String address;
        private String shipping_no;
        private String shipping_name;
        private int pay_type;
        private String goods_price;
        private Object shipping_price;
        private String total_price;
        private String add_time;
        private Object shipping_time;
        private Object confirm_time;
        private int user_id;
        private int supplier_id;
        private int buy_type;
        private String supplier_name;
        private String pay_name;
        private String label;
        private GoodsInfoBean goods_info;
        private List<LogisticsBean> logistics;
        private int is_refund;
        private int pick_type;
        private String service_fee;
        private int pick_pay;
        private String pick_pay_name;
        private String pick_time;
        private String buy_type_name;

        public String getBuy_type_name() {
            return buy_type_name;
        }

        public void setBuy_type_name(String buy_type_name) {
            this.buy_type_name = buy_type_name;
        }

        public String getPick_time() {
            return pick_time;
        }

        public void setPick_time(String pick_time) {
            this.pick_time = pick_time;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public int getPick_pay() {
            return pick_pay;
        }

        public void setPick_pay(int pick_pay) {
            this.pick_pay = pick_pay;
        }

        public String getPick_pay_name() {
            return pick_pay_name;
        }

        public void setPick_pay_name(String pick_pay_name) {
            this.pick_pay_name = pick_pay_name;
        }

        public int getPick_type() {
            return pick_type;
        }

        public void setPick_type(int pick_type) {
            this.pick_type = pick_type;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShipping_no() {
            return shipping_no;
        }

        public void setShipping_no(String shipping_no) {
            this.shipping_no = shipping_no;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public Object getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(Object shipping_price) {
            this.shipping_price = shipping_price;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Object getShipping_time() {
            return shipping_time;
        }

        public void setShipping_time(Object shipping_time) {
            this.shipping_time = shipping_time;
        }

        public Object getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(Object confirm_time) {
            this.confirm_time = confirm_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public List<LogisticsBean> getLogistics() {
            return logistics;
        }

        public void setLogistics(List<LogisticsBean> logistics) {
            this.logistics = logistics;
        }

        public static class GoodsInfoBean {
            /**
             * goods_id : 5
             * goods_name : 糯冰种飘翠福瓜挂件
             * goods_sn : 3k68584
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

        public static class LogisticsBean {
            /**
             * time : 2018-06-27 17:59:39
             * ftime : 2018-06-27 17:59:39
             * context : 快递柜-已签收
             * location :
             */

            private String time;
            private String ftime;
            private String context;
            private String location;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }
    }
}
