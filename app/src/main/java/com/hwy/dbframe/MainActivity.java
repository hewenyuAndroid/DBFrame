package com.hwy.dbframe;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hwy.db.DaoSupport;
import com.hwy.db.DaoSupportFactory;
import com.hwy.permission.PermissionHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etName;
    private EditText etAge;
    private EditText etSalary;

    private TextView tvResult;

    private DaoSupport<UserBean> mDaoSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etSalary = findViewById(R.id.et_salary);
        tvResult = findViewById(R.id.tv_result);

        PermissionHelper.requestPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                new PermissionHelper.OnRequestPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // 初始化db文件
                        DaoSupportFactory.getInstance()
                                .init(MainActivity.this,
                                        Environment.getExternalStorageDirectory().getAbsolutePath(),
                                        "admin");
                        mDaoSupport = (DaoSupport<UserBean>) DaoSupportFactory.getInstance().getDao(UserBean.class);
                    }

                    @Override
                    public void onPermissionDenied() {
                        PermissionHelper.showTipsDialog(MainActivity.this);
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void clear() {
        etId.setText("");
        etName.setText("");
        etAge.setText("");
        etSalary.setText("");
    }

    public void insert(View view) {
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        double salary = Double.parseDouble(etSalary.getText().toString());
        mDaoSupport.insert(new UserBean(name, age, salary));
        showToast("插入数据成功");
        clear();
    }

    public void selectAll(View view) {
        List<UserBean> list = mDaoSupport.selectAll();
        tvResult.setText(list.toString());
    }

    public void select(View view) {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String salary = etSalary.getText().toString();

        Map<String, Object> condition = new HashMap<>();
        if (!TextUtils.isEmpty(id)) {
            condition.put(DaoSupport.ID, Integer.parseInt(id));
        }
        if (!TextUtils.isEmpty(name)) {
            condition.put("userName", name);
        }
        if (!TextUtils.isEmpty(age)) {
            condition.put("age", Integer.parseInt(age));
        }
        if (!TextUtils.isEmpty(salary)) {
            condition.put("salary", Double.parseDouble(salary));
        }
        List<UserBean> list = mDaoSupport.select(condition);
        tvResult.setText(list.toString());
    }

    public void delete(View view) {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String salary = etSalary.getText().toString();

        Map<String, Object> condition = new HashMap<>();
        if (!TextUtils.isEmpty(id)) {
            condition.put(DaoSupport.ID, Integer.parseInt(id));
        }
        if (!TextUtils.isEmpty(name)) {
            condition.put("userName", name);
        }
        if (!TextUtils.isEmpty(age)) {
            condition.put("age", Integer.parseInt(age));
        }
        if (!TextUtils.isEmpty(salary)) {
            condition.put("salary", Double.parseDouble(salary));
        }
        mDaoSupport.delete(condition);
        showToast("删除成功");
    }

    public void update(View view) {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String salary = etSalary.getText().toString();

        Map<String, Object> updateValue = new HashMap<>();
        if (!TextUtils.isEmpty(age)) {
            updateValue.put("age", Integer.parseInt(age));
        }
        if (!TextUtils.isEmpty(salary)) {
            updateValue.put("salary", Double.parseDouble(salary));
        }

        Map<String, Object> condition = new HashMap<>();
        if (!TextUtils.isEmpty(name)) {
            condition.put("userName", name);
        } else {
            showToast("请输入ID");
        }

        mDaoSupport.update(updateValue, condition);
        showToast("修改成功");
    }

}
