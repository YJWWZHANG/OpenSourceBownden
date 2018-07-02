package com.dashi1314.wangyiyunxinim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class YunXinActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, YunXinActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yun_xin);
        NimUIKit.login(new LoginInfo("zqb1", "123456"), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                Toast.makeText(YunXinActivity.this, "login success", Toast.LENGTH_LONG).show();
//                NimUIKit.startP2PSession(YunXinActivity.this, "zqb");
            }

            @Override
            public void onFailed(int code) {
                if (code == 302 || code == 404) {
                    Toast.makeText(YunXinActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(YunXinActivity.this, "登录失败: " + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(YunXinActivity.this, "onException", Toast.LENGTH_LONG).show();
            }
        });

//        NIMClient.getService(AuthService.class).login(new LoginInfo("zqb", "3e849ac810079fafba4418d75e90dc04"))
//                .setCallback( new RequestCallback<LoginInfo>() {
//                    @Override
//                    public void onSuccess(LoginInfo param) {
//                        LogUtils.w("zqb", param.toString(), param.describeContents(), param.getAccount());
//                    }
//
//                    @Override
//                    public void onFailed(int code) {
//                        LogUtils.w("zqb", code + "");
//                    }
//
//                    @Override
//                    public void onException(Throwable exception) {
//                        LogUtils.w("zqb", exception.getMessage());
//                    }
//                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
//                });
//
//        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(
//                new Observer<StatusCode>() {
//                    public void onEvent(StatusCode status) {
//                        LogUtils.w("tag", "User status changed to: " + status);
//                        if (status.wontAutoLogin()) {
//                            // 被踢出、账号被禁用、密码错误等情况，自动登录失败，需要返回到登录界面进行重新登录操作
//                        }
//                    }
//                }, true);
//        NIMClient.getService(MsgServiceObserve.class)
//                .observeReceiveMessage(new Observer<List<IMMessage>>() {
//                    @Override
//                    public void onEvent(List<IMMessage> messages) {
//                        // 处理新收到的消息，为了上传处理方便，SDK 保证参数 messages 全部来自同一个聊天对象。
////                        StringBuilder sb = new StringBuilder("");
////                        for (IMMessage imMessage : messages) {
////                            sb.append(imMessage.getFromAccount()).append(" ").append(imMessage.getContent()).append("\n").append(imMessage.get);
////                        }
//
//                        LogUtils.w(new Gson().toJson(messages));
//
//                    }
//                }, true);
//
////// 发送消息。如果需要关心发送结果，可设置回调函数。发送完成时，会收到回调。如果失败，会有具体的错误码。
////        NIMClient.getService(MsgService.class).sendMessage(MessageBuilder.createTextMessage(
////                "zqb", // 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
////                SessionTypeEnum.P2P, // 聊天类型，单聊或群组
////                "haha" // 文本内容
////        ), true).setCallback(new RequestCallback<Void>() {
////            @Override
////            public void onSuccess(Void param) {
////                LogUtils.w("onSuccess");
////            }
////
////            @Override
////            public void onFailed(int code) {
////                LogUtils.w("onFailed");
////            }
////
////            @Override
////            public void onException(Throwable exception) {
////                LogUtils.w("onException", exception.getMessage());
////            }
////        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

//    // 如果返回值为 null，则全部使用默认参数。
//    private SDKOptions options() {
//        SDKOptions options = new SDKOptions();
//
//        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
//        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
//        config.notificationEntrance = YunXinActivity.class; // 点击通知栏跳转到该Activity
//        config.notificationSmallIconId = R.mipmap.ic_launcher_round;
//        // 呼吸灯配置
//        config.ledARGB = Color.GREEN;
//        config.ledOnMs = 1000;
//        config.ledOffMs = 1500;
//        // 通知铃声的uri字符串
//        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
//        options.statusBarNotificationConfig = config;
//
//        // 配置保存图片，文件，log 等数据的目录
//        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
//        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
//        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
//        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim";
//        options.sdkStorageRootPath = sdkPath;
//
//        // 配置是否需要预下载附件缩略图，默认为 true
//        options.preloadAttach = true;
//
//        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
//        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
//        options.thumbnailSize = 1080 / 2;
//
//        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
//        options.userInfoProvider = new UserInfoProvider() {
//            @Override
//            public UserInfo getUserInfo(String account) {
//                return null;
//            }
//
//            @Override
//            public int getDefaultIconResId() {
//                return R.mipmap.ic_launcher_round;
//            }
//
//            @Override
//            public Bitmap getTeamIcon(String tid) {
//                return null;
//            }
//
//            @Override
//            public Bitmap getAvatarForMessageNotifier(String account) {
//                return null;
//            }
//
//            @Override
//            public String getDisplayNameForMessageNotifier(String account, String sessionId,
//                                                           SessionTypeEnum sessionType) {
//                return null;
//            }
//        };
//        return options;
//    }
//
//    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
//    private LoginInfo loginInfo() {
//        return new LoginInfo("zqb", "ddadd739d47b5cabdb585fe63a926511");
//    }

}
