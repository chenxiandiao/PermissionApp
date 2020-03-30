package com.teletraan.permission;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.teletraan.permission.core.IPermission;
import com.teletraan.permission.util.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * @author: cxd
 * @date: 2020-03-30 14:12
 * @desc:
 */
public class MyPermissionFragment extends Fragment {
    private static IPermission permissionListener; // 这个Activity  已经授权，取消授权，被拒绝授权

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        Log.e("CXD", String.valueOf(requestCode));
        // 返回的结果，需要去验证一下，是否完全成功了
        if (PermissionUtils.requestPermissionSuccess(grantResults)) { // 真正申请成功了
            // 通过监听接口，告诉外界，已经授权成功
            permissionListener.granted();
//            this.finish();
            return;
        }

        // 如果用户点击了，拒绝，（不再提示打勾） 等操作，告诉外界
        if (!PermissionUtils.shouldShowRequestPermissionRationale(this, permissions)) {
            // 用户拒绝，不再提醒
            // 通过接口监听，告诉外界，被拒绝，（不再提示打勾）
            permissionListener.denied();
//            this.finish();
            return;
        }

        // 如果执行到这里来了，就证明 权限被取消了
        permissionListener.cancel();
//        this.finish();
        return;
    }


    // TODO 把当前整个Fragment暴露给外界使用
    public static void requestPermissionAction(Context context, String[] permissions,
                                               int requestCode, IPermission iPermission) {
        permissionListener = iPermission;

        MyPermissionFragment fragment = getMyPermissionFragment(((FragmentActivity) (context)).getSupportFragmentManager());

        fragment.requestPermissions(permissions, requestCode);
    }

    private static MyPermissionFragment getMyPermissionFragment(final FragmentManager fragmentManager) {
        MyPermissionFragment rxPermissionsFragment = findRxPermissionsFragment(fragmentManager);
        boolean isNewInstance = rxPermissionsFragment == null;
        if (isNewInstance) {
            rxPermissionsFragment = new MyPermissionFragment();
            fragmentManager
                    .beginTransaction()
                    .add(rxPermissionsFragment, "MyPermissionFragment")
                    .commitNow();
        }
        return rxPermissionsFragment;
    }

    private static MyPermissionFragment findRxPermissionsFragment(final FragmentManager fragmentManager) {
        return (MyPermissionFragment) fragmentManager.findFragmentByTag("MyPermissionFragment");
    }
}
