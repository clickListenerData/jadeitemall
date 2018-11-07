package com.microple.jademall.bean;

/**
 * author: xiaoguagnfei
 * date: 2018/9/10
 * describe:
 */
public class Pick {

    /**
     * user_address : {"ua_id":3,"user_id":3,"consigner":"jk","phone":"15464679655","address":"测试","is_default":1}
     * goods_sn : 3h66873
     * type : 成品
     * remark : null
     * service_fee : 1000
     */

    private UserAddressBean user_address;
    private String goods_sn;
    private String type;
    private String remark;
    private String service_fee;
    private int cut_status;
    private String freight_price;

    public int getCut_status() {
        return cut_status;
    }

    public void setCut_status(int cut_status) {
        this.cut_status = cut_status;
    }

    public String getFreight_price() {
        return freight_price;
    }

    public void setFreight_price(String freight_price) {
        this.freight_price = freight_price;
    }

    public UserAddressBean getUser_address() {
        return user_address;
    }

    public void setUser_address(UserAddressBean user_address) {
        this.user_address = user_address;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getService_fee() {
        return service_fee;
    }

    public void setService_fee(String service_fee) {
        this.service_fee = service_fee;
    }

    public static class UserAddressBean {
        /**
         * ua_id : 3
         * user_id : 3
         * consigner : jk
         * phone : 15464679655
         * address : 测试
         * is_default : 1
         */

        private String ua_id;
        private int user_id;
        private String consigner;
        private String phone;
        private String address;
        private int is_default;

        public String getUa_id() {
            return ua_id;
        }

        public void setUa_id(String ua_id) {
            this.ua_id = ua_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getConsigner() {
            return consigner;
        }

        public void setConsigner(String consigner) {
            this.consigner = consigner;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }
    }
}
