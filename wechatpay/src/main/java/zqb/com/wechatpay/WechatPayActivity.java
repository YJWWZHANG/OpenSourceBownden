package zqb.com.wechatpay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * 创建时间:2018/3/25 19:09
 * 作者:zhangqianbin
 * 邮箱:zhangqianbin3@foxmail.com
 * 功能描述:
 * 修改时间:
 * 修改描述:
 */
public class WechatPayActivity extends AppCompatActivity{

    public IWXAPI mWXAPI;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, WechatPayActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechatpay);

        mWXAPI = WXAPIFactory.createWXAPI(Utils.getApp(), "wxd930ea5d5a258f4f");

        String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";
        Toast.makeText(this, "获取订单中...", Toast.LENGTH_SHORT).show();
        OkGo.<String>get(url)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try{
                                JSONObject json = new JSONObject(response.body());
                                if(null != json && !json.has("retcode") ){
                                    PayReq req = new PayReq();
                                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                                    req.appId			= json.getString("appid");
                                    req.partnerId		= json.getString("partnerid");
                                    req.prepayId		= json.getString("prepayid");
                                    req.nonceStr		= json.getString("noncestr");
                                    req.timeStamp		= json.getString("timestamp");
                                    req.packageValue	= json.getString("package");
                                    req.sign			= json.getString("sign");
                                    req.extData			= "app data"; // optional
                                    Toast.makeText(WechatPayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                    mWXAPI.sendReq(req);
                                }else{
                                    Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
                                    Toast.makeText(WechatPayActivity.this, "返回错误"+json.getString("retmsg"), Toast.LENGTH_SHORT).show();
                                }

                        }catch(Exception e){
                            Log.e("PAY_GET", "异常："+e.getMessage());
                            Toast.makeText(WechatPayActivity.this, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
