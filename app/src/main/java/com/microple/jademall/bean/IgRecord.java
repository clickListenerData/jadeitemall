package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/11/5
 * describe:
 */
public class IgRecord {

    private List<FrozenInfoBean> frozen_info;

    public List<FrozenInfoBean> getFrozen_info() {
        return frozen_info;
    }

    public void setFrozen_info(List<FrozenInfoBean> frozen_info) {
        this.frozen_info = frozen_info;
    }

    public static class FrozenInfoBean {
        /**
         * fp_id : 1
         * user_id : 86
         * points : 500.00
         * frozen_time : 2018-10-29 17:09:19
         * surplus_time : 27天 05小时
         */

        private int fp_id;
        private int user_id;
        private String points;
        private String frozen_time;
        private String surplus_time;

        public int getFp_id() {
            return fp_id;
        }

        public void setFp_id(int fp_id) {
            this.fp_id = fp_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getFrozen_time() {
            return frozen_time;
        }

        public void setFrozen_time(String frozen_time) {
            this.frozen_time = frozen_time;
        }

        public String getSurplus_time() {
            return surplus_time;
        }

        public void setSurplus_time(String surplus_time) {
            this.surplus_time = surplus_time;
        }
    }
}
