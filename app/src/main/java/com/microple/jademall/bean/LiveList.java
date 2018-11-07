package com.microple.jademall.bean;

import java.util.List;

/**
 * author: xiaoguagnfei
 * date: 2018/9/20
 * describe:
 */
public class LiveList {

    private List<AppointLiveBean> appoint_live;
    private List<LiveNowBean> live_now;
    private List<RecoLiveBean> reco_live;
    private List<ReplayListBean> replay_list;

    public List<AppointLiveBean> getAppoint_live() {
        return appoint_live;
    }

    public void setAppoint_live(List<AppointLiveBean> appoint_live) {
        this.appoint_live = appoint_live;
    }

    public List<LiveNowBean> getLive_now() {
        return live_now;
    }

    public void setLive_now(List<LiveNowBean> live_now) {
        this.live_now = live_now;
    }

    public List<RecoLiveBean> getReco_live() {
        return reco_live;
    }

    public void setReco_live(List<RecoLiveBean> reco_live) {
        this.reco_live = reco_live;
    }

    public List<ReplayListBean> getReplay_list() {
        return replay_list;
    }

    public void setReplay_list(List<ReplayListBean> replay_list) {
        this.replay_list = replay_list;
    }

    public static class AppointLiveBean {
        /**
         * id : 1
         * user_id : 3
         * live_id : 1
         * desc : 测试测试
         * start_time : 2018-09-20 12:00
         * add_time : 0
         * message : 您预约的3g66572号切石直播即将于1小时后开始。
         * bizid : 31624
         * streamId : 4c24b1219e
         * play_url : ["rtmp://31624.liveplay.myqcloud.com/live/31624_4c24b1219e","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.flv","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.m3u8"]
         */

        private int id;
        private int user_id;
        private String live_id;
        private String desc;
        private String start_time;
        private int add_time;
        private String message;
        private String bizid;
        private String streamId;
        private List<String> play_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getLive_id() {
            return live_id;
        }

        public void setLive_id(String live_id) {
            this.live_id = live_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getBizid() {
            return bizid;
        }

        public void setBizid(String bizid) {
            this.bizid = bizid;
        }

        public String getStreamId() {
            return streamId;
        }

        public void setStreamId(String streamId) {
            this.streamId = streamId;
        }

        public List<String> getPlay_url() {
            return play_url;
        }

        public void setPlay_url(List<String> play_url) {
            this.play_url = play_url;
        }
    }

    public static class LiveNowBean {
        /**
         * live_id : 2
         * live_title : 3k68584号切石直播
         * push_url : rtmp://31624.livepush.myqcloud.com/live/31624_2c50db987f?bizid=31624&txSecret=f8ea114db305e41ec336aef60a091466&txTime=5BA2727F
         * desc : 测试
         * live_time : 2018-09-20 10:00
         * supplier_id : 1
         * cover_img : /uploads/20180918/81dcad2de5b548ecdee253bafd97ea9e.jpg
         * live_code : 31624_2c50db987f
         * end_time : 2018-09-20 20:00
         * bizid : 31624
         * streamId : 2c50db987f
         * play_url : ["rtmp://31624.liveplay.myqcloud.com/live/31624_2c50db987f","http://31624.liveplay.myqcloud.com/live/31624_2c50db987f.flv","http://31624.liveplay.myqcloud.com/live/31624_2c50db987f.m3u8"]
         */

        private int live_id;
        private String live_title;
        private String push_url;
        private String desc;
        private String live_time;
        private int supplier_id;
        private String cover_img;
        private String live_code;
        private String end_time;
        private String bizid;
        private String streamId;
        private List<String> play_url;
        private String group_id;

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public int getLive_id() {
            return live_id;
        }

        public void setLive_id(int live_id) {
            this.live_id = live_id;
        }

        public String getLive_title() {
            return live_title;
        }

        public void setLive_title(String live_title) {
            this.live_title = live_title;
        }

        public String getPush_url() {
            return push_url;
        }

        public void setPush_url(String push_url) {
            this.push_url = push_url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getLive_code() {
            return live_code;
        }

        public void setLive_code(String live_code) {
            this.live_code = live_code;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBizid() {
            return bizid;
        }

        public void setBizid(String bizid) {
            this.bizid = bizid;
        }

        public String getStreamId() {
            return streamId;
        }

        public void setStreamId(String streamId) {
            this.streamId = streamId;
        }

        public List<String> getPlay_url() {
            return play_url;
        }

        public void setPlay_url(List<String> play_url) {
            this.play_url = play_url;
        }
    }

    public static class RecoLiveBean {
        /**
         * live_id : 1
         * live_title : 3g66572号切石直播
         * push_url : rtmp://31624.livepush.myqcloud.com/live/31624_4c24b1219e?bizid=31624&txSecret=a05c5e8dde275598c5a2b39d83134bb7&txTime=5BA120FF
         * desc : 测试测试测试
         * live_time : 2018-09-20 12:00
         * supplier_id : 1
         * cover_img : /uploads/20180918/81dcad2de5b548ecdee253bafd97ea9e.jpg
         * live_code : 31624_4c24b1219e
         * end_time : 2018-09-20 21:00
         * bizid : 31624
         * streamId : 4c24b1219e
         * play_url : ["rtmp://31624.liveplay.myqcloud.com/live/31624_4c24b1219e","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.flv","http://31624.liveplay.myqcloud.com/live/31624_4c24b1219e.m3u8"]
         */

        private int live_id;
        private String live_title;
        private String push_url;
        private String desc;
        private String live_time;
        private int supplier_id;
        private String cover_img;
        private String live_code;
        private String end_time;
        private String bizid;
        private String streamId;
        private List<String> play_url;

        public int getLive_id() {
            return live_id;
        }

        public void setLive_id(int live_id) {
            this.live_id = live_id;
        }

        public String getLive_title() {
            return live_title;
        }

        public void setLive_title(String live_title) {
            this.live_title = live_title;
        }

        public String getPush_url() {
            return push_url;
        }

        public void setPush_url(String push_url) {
            this.push_url = push_url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getLive_code() {
            return live_code;
        }

        public void setLive_code(String live_code) {
            this.live_code = live_code;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBizid() {
            return bizid;
        }

        public void setBizid(String bizid) {
            this.bizid = bizid;
        }

        public String getStreamId() {
            return streamId;
        }

        public void setStreamId(String streamId) {
            this.streamId = streamId;
        }

        public List<String> getPlay_url() {
            return play_url;
        }

        public void setPlay_url(List<String> play_url) {
            this.play_url = play_url;
        }
    }

    public static class ReplayListBean {
        /**
         * lr_id : 1
         * channel_id : 2121_15919131751
         * file_id : 9192487266581821586
         * video_url : http://200025724.vod.myqcloud.com/200025724_ac92b781a22c4a3e937c9e61c2624af7.f0.flv
         * cover_img : /uploads/20180918/81dcad2de5b548ecdee253bafd97ea9e.jpg
         * start_time : 2018-09-19 12:00
         * end_time : 2018-09-19 19:00
         */

        private int lr_id;
        private String channel_id;
        private String file_id;
        private String video_url;
        private String cover_img;
        private String start_time;
        private String end_time;
        private String video_name;
        private String desc;
        private String supplier_id;

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getVideo_name() {
            return video_name;
        }

        public void setVideo_name(String video_name) {
            this.video_name = video_name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLr_id() {
            return lr_id;
        }

        public void setLr_id(int lr_id) {
            this.lr_id = lr_id;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
