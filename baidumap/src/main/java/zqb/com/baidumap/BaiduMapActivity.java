package zqb.com.baidumap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.blankj.utilcode.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间:2018/3/24 22:20
 * 作者:zhangqianbin
 * 邮箱:zhangqianbin3@foxmail.com
 * 功能描述:
 * 修改时间:
 * 修改描述:
 */
public class BaiduMapActivity extends AppCompatActivity {

    @BindView(R2.id.bmap_content)
    MapView mBmapContent;

    public static void launch(Context context) {
        SDKInitializer.initialize(Utils.getApp());
        context.startActivity(new Intent(context, BaiduMapActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mBmapContent.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mBmapContent.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mBmapContent.onPause();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
