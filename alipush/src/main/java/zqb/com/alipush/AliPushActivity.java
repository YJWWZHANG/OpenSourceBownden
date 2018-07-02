package zqb.com.alipush;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

/**
 * Created by zqb on 2018/3/21.
 */

public class AliPushActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AliPushActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipush);
        ToastUtils.showLong("阿里云推送已开启");
        initCloudChannel(Utils.getApp());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * 初始化云推送通道
     * @param
     * @param app
     */
    public void initCloudChannel(Application app) {
        PushServiceFactory.init(Utils.getApp());
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.setNotificationLargeIcon(ImageUtils.getBitmap(R.mipmap.ic_launcher_round));
        pushService.setNotificationSmallIcon(R.mipmap.ic_launcher_round);
        pushService.register(Utils.getApp(), new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                LogUtils.w("init cloudchannel success");
                ToastUtils.showLong("" + pushService.getDeviceId());
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                LogUtils.w("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }




}
