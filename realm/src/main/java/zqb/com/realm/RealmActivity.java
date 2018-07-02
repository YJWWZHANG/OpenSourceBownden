package zqb.com.realm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by zqb on 2018/3/21.
 */

public class RealmActivity extends AppCompatActivity {

    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.et_age)
    EditText mEtAge;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, RealmActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        ButterKnife.bind(this);

        Realm.init(Utils.getApp());

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R2.id.btn_add, R2.id.btn_query, R2.id.btn_update, R2.id.btn_delete})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_add) {
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (StringUtils.isSpace(mEtName.getText().toString()) || StringUtils.isSpace(mEtAge.getText().toString())) {
                        ToastUtils.showLong("数据不能为空");
                        return;
                    }
                    User user = realm.createObject(User.class);
                    user.setName(mEtName.getText().toString());
                    user.setAge(Integer.valueOf(mEtAge.getText().toString()));
                    Dog dog = realm.createObject(Dog.class);
                    dog.setName("小二");
                    user.setDog(dog);
                    ToastUtils.showLong("增加完成");
                }
            });

        } else if (i == R.id.btn_query) {
            RealmQuery<User> query = Realm.getDefaultInstance().where(User.class);
            List<User> data = query.findAll();
            ToastUtils.showLong(data.toString());
        } else if (i == R.id.btn_update) {
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<User> data = realm.where(User.class).equalTo("name", "Gavin").findAll();
                    if (data.size() == 0) {
                        ToastUtils.showLong("未查询到对应的记录");
                        return;
                    }

                    for (User user : data) {
                        user.setAge(10);
                    }

                    ToastUtils.showLong("更新完成");
                }
            });
        } else if (i == R.id.btn_delete) {
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<User> results = realm.where(User.class).findAll();
                    if (results.size() == 0) {
                        ToastUtils.showLong("无数据");
                        return;
                    }
                    //删除第一条数据
                    results.deleteFirstFromRealm();
                    ToastUtils.showLong("删除第一条成功");
//                    results.deleteFromRealm(0);
                    //删除最后一条数据
                    //results.deleteLastFromRealm();
                }
            });

        }

    }
}
