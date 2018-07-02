package zqb.com.touchevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zqb on 2018/3/10.
 */

public class TouchEventActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, TouchEventActivity.class));
    }

    @BindView(R2.id.rcy_touch_event)
    RecyclerView mRcyTouchEvent;

    private List<String> mStringList = new ArrayList<String>() {
        {
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
            add("aaaaaaaaaaaaa");
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent);
        ButterKnife.bind(this);
        mRcyTouchEvent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRcyTouchEvent.setAdapter(new TouchEventAdapter(R.layout.item_touch_event, mStringList));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
