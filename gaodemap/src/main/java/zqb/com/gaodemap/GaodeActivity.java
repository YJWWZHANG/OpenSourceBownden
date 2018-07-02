package zqb.com.gaodemap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间:2018/3/24 23:43
 * 作者:zhangqianbin
 * 邮箱:zhangqianbin3@foxmail.com
 * 功能描述:
 * 修改时间:
 * 修改描述:
 */
public class GaodeActivity extends AppCompatActivity {

    @BindView(R2.id.gmap_content)
    MapView mGmapContent;
    private AMap mAMap;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, GaodeActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaodemap);
        ButterKnife.bind(this);
        mGmapContent.onCreate(savedInstanceState);

        mAMap = mGmapContent.getMap();

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mGmapContent.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mGmapContent.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mGmapContent.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mGmapContent.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
