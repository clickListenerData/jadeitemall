package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/11/7
 * describe:
 */
public class BuyTyle {

    private List<BuyTypeBean> buy_type;

    public List<BuyTypeBean> getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(List<BuyTypeBean> buy_type) {
        this.buy_type = buy_type;
    }

    public static class BuyTypeBean {
        /**
         * bt_id : 1
         * buy_type : send
         * type_name : 邮寄
         */

        private int bt_id;
        private String buy_type;
        private String type_name;

        public int getBt_id() {
            return bt_id;
        }

        public void setBt_id(int bt_id) {
            this.bt_id = bt_id;
        }

        public String getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(String buy_type) {
            this.buy_type = buy_type;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
