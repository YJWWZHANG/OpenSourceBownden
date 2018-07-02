package zqb.com.opensourcebownden;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabl_main)
    TabLayout mTablMain;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mVpMain.setAdapter(new VpMainAdapter(getSupportFragmentManager()));
        mTablMain.setupWithViewPager(mVpMain);
    }

}
