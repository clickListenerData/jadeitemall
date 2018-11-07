package com.microple.jademall.ui.live.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.microple.jademall.BuildConfig
import com.microple.jademall.R
import com.microple.jademall.bean.Follow
import com.microple.jademall.bean.LiveGoods
import com.microple.jademall.bean.LiveShare
import com.microple.jademall.common.App
import com.microple.jademall.common.Constants
import com.microple.jademall.dialog.ShareDialog
import com.microple.jademall.ui.Personal.activity.CustomerServiceAcitivity
import com.microple.jademall.ui.live.adapter.MessageAdapter
import com.microple.jademall.ui.live.mvp.contract.LivePlayerContract
import com.microple.jademall.ui.live.mvp.presenter.LivePlayerPresenter
import com.microple.jademall.uitls.loadHeadImag
import com.tencent.imsdk.*
import com.tencent.imsdk.ext.group.TIMGroupAssistantListener
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo
import com.tencent.imsdk.ext.group.TIMGroupManagerExt
import com.tencent.imsdk.ext.group.TIMUserConfigGroupExt
import com.tencent.imsdk.ext.message.TIMUserConfigMsgExt
import com.tencent.imsdk.ext.sns.TIMFriendshipProxyListener
import com.tencent.imsdk.ext.sns.TIMUserConfigSnsExt
import com.tencent.rtmp.ITXLivePlayListener
import com.tencent.rtmp.TXLiveBase
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayer
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_live_player.*

class LivePlayerActivity : BaseMvpActivity<LivePlayerPresenter>(),LivePlayerContract.View {
    override fun isFollow(follow: Follow) {
        Log.i("isFollow",""+follow.is_follow)
        if (follow.is_follow==1){
            tv_follow.text="已关注"
            tv_follow.isEnabled=false
        }

    }

    override fun follow() {
        showToast("关注成功")
        tv_follow.text="已关注"
        tv_follow.isEnabled=false
    }

    override fun getGoods(liveGoods: LiveGoods) {
        tv_number.text=""+liveGoods.goods.size
        if (liveGoods.goods.size==0){
            iv_goods.isEnabled=false
        }
    }

    var messageAdapter=MessageAdapter()
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): LivePlayerPresenter = LivePlayerPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_live_player
    /**
     * 初始化事件
     */
    override fun initEvent() {
        getPresenter().getGoods(intent.getStringExtra("live_id"))
        iv_goods.setOnClickListener {
            LiveGoodsActivity.startGoodsActivity(this,intent.getStringExtra("live_id"))
        }
        iv_share.setOnClickListener {
            showLoadingDialog()
            getPresenter().liveShare(intent.getStringExtra("live_id"))

        }
        et_send.setOnEditorActionListener { view, i, keyEvent ->
            Log.i("messagesss",""+keyEvent.action)
            if (et_send.text.isNullOrEmpty()){
                    showToast("不能发送空内容")
            }else{
            if (keyEvent.action==0){
                sendMessage(et_send.text.toString(),0)
            }
            }


            return@setOnEditorActionListener  true
        }
        tv_follow.setOnClickListener {
            getPresenter().follow(Constants.getToken(),intent.getStringExtra("supplier_id"))
        }
        iv_service.setOnClickListener {
            CustomerServiceAcitivity.startCustomerServiceAcitivity(this)
        }

    }
    //发送消息
    private fun sendMessage(message:String,index:Int) {
        var textElem= TIMTextElem()
        if (index==0){//平常信息
            textElem.text=Constants.getPersonal().user_name+"："+message
        }else if (index==1){
            textElem.text="欢迎"+Constants.getPersonal().user_name+message
        }else{
            textElem.text=Constants.getPersonal().user_name+message
        }
        var message= TIMMessage()
        message.addElement(textElem)
        Log.i("messagesss","发送")
        var conversation= TIMManager.getInstance().getConversation(TIMConversationType.Group,intent.getStringExtra("group_id"))
        conversation.sendMessage(message, object : TIMValueCallBack<TIMMessage> {
            override fun onSuccess(p0: TIMMessage?) {
                Log.i("messagesss","发送成功")
                et_send.setText("")
                messageAdapter.addData(p0!!)
                rl_message.scrollToPosition(messageAdapter.getItemCount()-1)
                if (index==2){//退出IM
                    TIMManager.getInstance().removeMessageListener(null)
                    TIMGroupManager.getInstance().quitGroup(intent.getStringExtra("group_id"), object : TIMCallBack {
                        override fun onError(p0: Int, p1: String?) {
                            Log.i("quitGroup","p1"+p0)
                        }

                        override fun onSuccess() {
                            Log.i("quitGroup","成功")
                            TIMManager.getInstance().logout(null)
                        }
                    })

                }
            }

            override fun onError(p0: Int, p1: String?) {
//                showToast("发送失败")
            }

        })
    }

    /**
     * 初始化数据状态
     */
    override fun initData() {
        Log.i("supplier_id",intent.getStringExtra("supplier_id"))
        //isExit=0
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        (application as App).addActivity(this)
        hideStatusBar()
        Log.i("onErrorlive",""+Constants.getIsExit())
        Constants.putIsExit(0)
        if (Constants.isLogin()){
            getPresenter().isFollow(Constants.getToken(),intent.getStringExtra("supplier_id"))
        }else{
            tv_follow.visibility=View.GONE
        }
        play()
    }



    var mLivePlayer:TXLivePlayer?=null
    //播放直播
    fun play(){
//        loading_progress.visibility=View.VISIBLE
        val sdkver = TXLiveBase.getSDKVersionStr()
        Log.d("liteavsdk", "liteav sdk version is : $sdkver")
        mLivePlayer=TXLivePlayer(this)
        mLivePlayer?.setPlayerView(video_view)
        val flvUrl = intent.getStringExtra("play_url")//"rtmp://30381.liveplay.myqcloud.com/live/30381_83c9d76844"
        if (intent.getIntExtra("isRecord",0)==2){
            mLivePlayer?.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV)
        }else{
            mLivePlayer?.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP)

        }
        mLivePlayer?.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN)

        mLivePlayer?.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT)
        mLivePlayer?.setPlayListener(object : ITXLivePlayListener {
            override fun onPlayEvent(event: Int, param: Bundle?) {
                Log.i("eventevent","event"+event)
                when(event){
                    TXLiveConstants.PLAY_ERR_NET_DISCONNECT->{
//                        loading_progress.visibility=View.VISIBLE
                        showToast("网络已断开")
                        Log.i("TXLiveConstants","网络已断开")
                    }
                    TXLiveConstants.PLAY_EVT_PLAY_LOADING->{
//                        loading_progress.visibility=View.VISIBLE
                        Log.i("TXLiveConstants","视频播放再加载")
                    }
                    TXLiveConstants.PLAY_EVT_RTMP_STREAM_BEGIN->{
//                        loading_progress.visibility=View.GONE
                        Log.i("TXLiveConstants","视频播放开始")
                    }
                    TXLiveConstants.PLAY_EVT_PLAY_END->{
                        loading_progress.visibility=View.GONE
//                        tv_jieshu.visibility=View.VISIBLE
                        Log.i("TXLiveConstants","视频播放结束")
                    }
                    TXLiveConstants.PLAY_EVT_CONNECT_SUCC->{
                        Log.i("TXLiveConstants","已经连接服务器")
                    }
                    TXLiveConstants.PLAY_ERR_NET_DISCONNECT->{
//                        loading_progress.visibility=View.GONE
                        tv_jieshu.text="直播未开始"


                    }
                    TXLiveConstants.PLAY_EVT_PLAY_BEGIN->{
                        loading_progress.visibility=View.GONE
                        tv_jieshu.visibility=View.GONE
                    }
                    TXLiveConstants.PLAY_WARNING_READ_WRITE_FAIL->{
                        loading_progress.visibility=View.GONE
                        tv_jieshu.visibility=View.VISIBLE
                        tv_jieshu.text="直播未开始"
                    }

                }
            }

            override fun onNetStatus(p0: Bundle?) {
            }

        })
        //聊天室
        if (Constants.isLogin()&&intent.getIntExtra("isRecord",0)==1){
            imLogin()
            rl_message.layoutManager=LinearLayoutManager(this)
            rl_message.adapter=messageAdapter
        }
        if (intent.getIntExtra("isRecord",0)==2){
            et_send.visibility=View.GONE
            iv_share.visibility=View.GONE
        }

        iv_close.setOnClickListener{
            finish()
        }
    }
    //接收消息
    private fun addMessage(){
        TIMManager.getInstance().addMessageListener(messageListener)

    }
    //登录聊天室
    private fun imLogin(){
        var confit= TIMSdkConfig(1400149108)
        confit.setAccoutType("36862")
        var init=TIMManager.getInstance().init(this,confit)
        Log.i("initinit",""+init)
        if (intent.getStringExtra("uri")!=null)
        tv_user.loadHeadImag(intent.getStringExtra("uri"))
        tv_livetitle.text=intent.getStringExtra("live_title")
        if (!init){
            TIMManager.getInstance().init(this,confit)
        }
        setUserCofig()

        TIMManager.getInstance().login(Constants.getImIndent(),Constants.getImuser(), object : TIMCallBack {
            override fun onError(code: Int, desc: String?) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                Log.i("ImLogin", "login failed. code: " + code + " errmsg: " + desc)
            }

            override fun onSuccess() {
                Log.i("ImLogin", "登录成功")
                addMessage()
                joinRoom(intent.getStringExtra("group_id"))

            }
        })
    }
    //加入聊天室
    private fun joinRoom(groupID:String){
        TIMGroupManager.getInstance().applyJoinGroup(groupID,"", object : TIMCallBack {
            override fun onError(p0: Int, p1: String?) {
                Log.i("groupID","加入失败")
            }

            override fun onSuccess() {
                Log.i("groupID","加入成功")

                sendMessage("加入直播间",1)
            }
        })
    }
    var messageListener:TIMMessageListener= TIMMessageListener {
            messageAdapter.addData(it)
            rl_message.scrollToPosition(messageAdapter.getItemCount()-1)
            Log.i("activitysss",this@LivePlayerActivity.toString())
            for (i in 0..it.size-1){
                for (y in 0..it[i].elementCount.toInt()-1){
                    var elem=it[i].getElement(y)
                    if (elem.type==TIMElemType.Text){
                        if (!(elem as TIMTextElem).text.contains("：")){
                            if ((elem as TIMTextElem).text.contains("加入直播间")){
                                tv_message.text=elem.text
                                ll_message.visibility=View.VISIBLE
                            }
                            TIMGroupManagerExt.getInstance().getGroupMembers(intent.getStringExtra("group_id"), object : TIMValueCallBack<List<TIMGroupMemberInfo>> {
                                override fun onError(p0: Int, p1: String?) {
                                }

                                override fun onSuccess(infoList: List<TIMGroupMemberInfo>?) {
                                    Log.i("onErrorlive","成功￥isExit${Constants.getIsExit()}")
                                    if (isExit == 1) return
                                    tv_renshu.text="${infoList!!.size}人观看"
                                    var str_list= arrayListOf<String>()
                                    if (infoList!!.size>5){
                                        for (i in 0..infoList?.size-1){
                                            if (i>infoList?.size-6){
                                                str_list.add(infoList[i].user)
                                            }
                                        }
                                    }else{
                                        for (i in 0..infoList?.size-1){
                                            str_list.add(infoList[i].user)
                                        }
                                    }
                                    Log.i("faceUrl",""+str_list?.size)
                                    //获取进入IM的人的资料
                                    TIMFriendshipManager.getInstance().getUsersProfile(str_list, object : TIMValueCallBack<List<TIMUserProfile>> {
                                        override fun onError(p0: Int, p1: String?) {
                                        }

                                        override fun onSuccess(p1: List<TIMUserProfile>?) {
                                            for (y in 0..guangkan.childCount-1){
                                                guangkan.getChildAt(y).visibility=View.GONE
                                            }
                                            for (i in 0..p1!!.size-1){
                                                guangkan.getChildAt(i).visibility=View.VISIBLE
                                                ( guangkan.getChildAt(i) as ImageView).loadHeadImag(p1[p1!!.size-1-i].faceUrl)

                                            }
                                        }


                                    })

                                }
                            })
                        }
                    }
                }

            }
             true
        }

    //配置用户
    fun setUserCofig(){
        var userConfig=TIMUserConfig().setGroupSettings(TIMGroupSettings()).setFriendshipSettings(TIMFriendshipSettings()).setUserStatusListener(object : TIMUserStatusListener {
            override fun onForceOffline() {
            }

            override fun onUserSigExpired() {
            }
        })
                .setConnectionListener(object : TIMConnListener {
                    override fun onWifiNeedAuth(p0: String?) {
                    }

                    override fun onDisconnected(p0: Int, p1: String?) {
                    }

                    override fun onConnected() {
                    }
                })
                .setGroupEventListener(object : TIMGroupEventListener {
                    override fun onGroupTipsEvent(p0: TIMGroupTipsElem?) {
                    }
                })
                .setRefreshListener(object : TIMRefreshListener {
                    override fun onRefreshConversation(p0: MutableList<TIMConversation>?) {
                    }

                    override fun onRefresh() {
                    }
                    
                })

        var userConfigs=TIMUserConfigMsgExt(userConfig).enableStorage(false).enableReadReceipt(true)
        var userConfigss=TIMUserConfigSnsExt(userConfigs).enableFriendshipStorage(true).setFriendshipProxyListener(object : TIMFriendshipProxyListener {
            override fun OnDelFriends(p0: MutableList<String>?) {
            }

            override fun OnAddFriends(p0: MutableList<TIMUserProfile>?) {
            }

            override fun OnFriendProfileUpdate(p0: MutableList<TIMUserProfile>?) {
            }

            override fun OnAddFriendReqs(p0: MutableList<TIMSNSChangeInfo>?) {
            }
        })
        var userConfitsss= TIMUserConfigGroupExt(userConfigss).enableGroupStorage(true).setGroupAssistantListener(object : TIMGroupAssistantListener {
            override fun onMemberQuit(p0: String?, p1: MutableList<String>?) {
            }

            override fun onGroupUpdate(p0: TIMGroupCacheInfo?) {
            }

            override fun onGroupDelete(p0: String?) {
            }

            override fun onMemberJoin(p0: String?, p1: MutableList<TIMGroupMemberInfo>?) {
            }

            override fun onMemberUpdate(p0: String?, p1: MutableList<TIMGroupMemberInfo>?) {
            }

            override fun onGroupAdd(p0: TIMGroupCacheInfo?) {
            }
        })
        TIMManager.getInstance().userConfig=userConfitsss

    }
    //分享
    override fun liveShare(liveShare: LiveShare) {
        dismissLoadingDialog()
        var dialog=ShareDialog(this)
        dialog.show()
        dialog.setOnBtnClickListener(object : ShareDialog.OnBtnClickListener {
            override fun QQShare() {
                share(SHARE_MEDIA.QQ.toSnsPlatform(),liveShare)
            }

            override fun weiboShare() {
                share(SHARE_MEDIA.SINA.toSnsPlatform(),liveShare)

            }

            override fun wxShare() {
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform(),liveShare)

            }

            override fun wxwcShare() {
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform(),liveShare)

            }

            override fun cancel() {
                dialog.dismiss()

            }

        })
    }

    override fun onPause() {
        super.onPause()
        if(intent.getIntExtra("isRecord",0)==1)
        sendMessage("离开直播间",2)

    }
    var isExit=0
    override fun onDestroy() {
        super.onDestroy()
        Log.i("live_player","onDestroy")
        Constants.putIsExit(2)
        mLivePlayer?.stopPlay(true)
        video_view.onDestroy()
        isExit=1
        TIMManager.getInstance().removeMessageListener(messageListener)
        (application as App).deleteActivity(this)
        Log.i("activitysss",this@LivePlayerActivity.toString())

    }
    companion object {
        fun startLivePlayerActivity(context: Context,live_id:String,play_url:String,group_id:String,live_title:String,isRecord:Int,uri:String,supplier_id:String){
            val intent = Intent(context, LivePlayerActivity::class.java)
            intent.putExtra("live_id",live_id)
            intent.putExtra("play_url",play_url)
            intent.putExtra("group_id",group_id)
            intent.putExtra("live_title",live_title)
            intent.putExtra("isRecord",isRecord)//标记是否为精彩回顾
            intent.putExtra("uri",uri)
            intent.putExtra("supplier_id",supplier_id)
            context.startActivity(intent)
        }
    }
    //只透明状态栏
    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
    private fun share(platform: SnsPlatform,liveShare: LiveShare){
        showLoadingDialog()
        if (liveShare!=null){
            var web = UMWeb(liveShare?.share?.link)
            web.setTitle(liveShare?.share?.live_title)
            web.setThumb( UMImage(this, BuildConfig.DEV_DOMAIN+"/api/"+liveShare?.share?.cover_img))
            web.setDescription(liveShare?.share?.desc)
            ShareAction(this)
                    .withMedia(web)
                    .setPlatform(platform.mPlatform)
                    .setCallback(umShareListener).share()
        }else{
            showToast("没有分享内容")
        }
        dismissLoadingDialog()
    }


    private var umShareListener= object : UMShareListener {
        override fun onResult(p0: SHARE_MEDIA?) {
            showToast("分享成功")
        }

        override fun onCancel(p0: SHARE_MEDIA?) {
            showToast("分享取消")
        }

        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
            showToast("分享失败")
        }

        override fun onStart(p0: SHARE_MEDIA?) {
        }
    }


}
