package zqb.com.greendao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {

    @BindView(R2.id.et_name)
    EditText mEtName;
    @BindView(R2.id.et_age)
    EditText mEtAge;

    public static final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, GreenDaoActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(Utils.getApp(), ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R2.id.btn_add, R2.id.btn_query, R2.id.btn_update, R2.id.btn_delete})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_add) {
            if (StringUtils.isSpace(mEtName.getText().toString()) || StringUtils.isSpace(mEtAge.getText().toString())) {
                ToastUtils.showLong("数据不能为空");
                return;
            }
            User user = new User();
            user.setName(mEtName.getText().toString());
            user.setAge(Integer.valueOf(mEtAge.getText().toString()));
            mDaoSession.getUserDao().insert(user);
        } else if (i == R.id.btn_query) {
            List<User> list = mDaoSession.getUserDao().queryBuilder().orderAsc(UserDao.Properties.Name).build().list();
            ToastUtils.showLong(list.toString());
        } else if (i == R.id.btn_update) {
            mDaoSession.getUserDao().update(new User(6L, "zqb", 999));
        } else if (i == R.id.btn_delete) {
            mDaoSession.getUserDao().delete(new User(2L, "", 0));
        }
    }
}
