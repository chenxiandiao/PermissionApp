package com.teletraan.standalone.permission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.teletraan.permission.annotation.Permission;
import com.teletraan.permission.annotation.PermissionCancel;
import com.teletraan.permission.annotation.PermissionDenied;

import androidx.appcompat.app.AppCompatActivity;


// TODO 用户使用角度
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void permissionRequestTest(View view) {
        testRequest();
    }

    @Permission(value = Manifest.permission.READ_EXTERNAL_STORAGE, requestCode = 200)
    public void testRequest() {
        Toast.makeText(this, "权限申请成功...", Toast.LENGTH_SHORT).show();
    }

    @PermissionCancel(requestCode = 200)
    public void testCancel() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(requestCode = 200)
    public void testDenied() {
        Toast.makeText(this, "权限被拒绝（用户勾选了，不再提醒）", Toast.LENGTH_SHORT).show();
    }
}
