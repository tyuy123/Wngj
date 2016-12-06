package com.wngj.wxapi;

import android.content.Context;
import android.util.Log;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wngj.utils.ToastUtils;


/**
 * Created by NiKOo on 2016/10/26.
 * 友盟分享回调接口
 */

public class ShareListener implements UMShareListener {
    private Context mContext;

    public ShareListener(Context context){
        this.mContext = context;
    }

    @Override
    public void onResult(SHARE_MEDIA platform) {
        Log.d("plat","platform"+platform);
        if(platform.name().equals("WEIXIN_FAVORITE")){
            ToastUtils.show(mContext, "收藏成功");
        }else{
            ToastUtils.show(mContext, "分享成功");
        }
    }

    @Override
    public void onError(SHARE_MEDIA platform, Throwable throwable) {
        ToastUtils.show(mContext, "分享失败");
    }

    @Override
    public void onCancel(SHARE_MEDIA platform) {
        ToastUtils.show(mContext, "分享取消");
    }
}
