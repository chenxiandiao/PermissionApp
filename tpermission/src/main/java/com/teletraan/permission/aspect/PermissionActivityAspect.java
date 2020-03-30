//package com.teletraan.permission.aspect;
//
//// TODO 专门处理权限的 Aspect
//
//import android.content.Context;
//
//
//import com.teletraan.permission.MyPermissionActivity;
//import com.teletraan.permission.annotation.Permission;
//import com.teletraan.permission.annotation.PermissionCancel;
//import com.teletraan.permission.annotation.PermissionDenied;
//import com.teletraan.permission.core.IPermission;
//import com.teletraan.permission.util.PermissionUtils;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
//import androidx.fragment.app.Fragment;
//
//@Deprecated
//@Aspect
//public class PermissionActivityAspect {
//
//    @Pointcut
//            ("execution(@com.teletraan.permission.annotation.Permission * *(..)) && @annotation(permission)")
//    public void pointActionMethod(Permission permission) {/* 法内部不做任何事情，只为了@Pointcut服务*/}
//
//    // 对方法环绕监听
//    @Around("pointActionMethod(permission)")
//    public void aProceedingJoinPoint(final ProceedingJoinPoint point, final Permission permission) throws Throwable {
//        // 先定义一个上下文操作环境
//        Context context = null;
//
//        final Object thisObject = point.getThis(); // 如果有兼容问题，thisObject == null
//
//        // 给context 初始化
//        if (thisObject instanceof Context) {
//            context = (Context) thisObject;
//        } else if (thisObject instanceof Fragment) {
//            context = ((Fragment) thisObject).getActivity();
//        }
//
//        // 判断是否为null
//        if (null == context || permission == null) {
//            throw new IllegalAccessException("null == context || permission == null is null");
//        }
//
//
//        // 调用权限处理的Activity 申请 检测 处理权限操作  permission.value() == Manifest.permission.READ_EXTERNAL_STORAGE
//        final Context finalContext = context;
//        MyPermissionActivity.requestPermissionAction
//                (context, permission.value(), permission.requestCode(), new IPermission() {
//                    @Override
//                    public void granted() { // 申请成功 授权成功
//                        // 让被 @Permission 的方法 正常的执行下去
//                        try {
//                            point.proceed();
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void cancel() { // 被拒绝
//                        // 调用到 被 @PermissionCancel 的方法
//                        PermissionUtils.invokeAnnotation(thisObject, PermissionCancel.class, permission.requestCode());
//                    }
//
//                    @Override
//                    public void denied() { // 严重拒绝 勾选了 不再提醒
//                        // 调用到 被 @PermissionDenied 的方法
//                        PermissionUtils.invokeAnnotation(thisObject, PermissionDenied.class, permission.requestCode());
//
//                        // 不仅仅要提醒用户，还需要 自动跳转到 手机设置界面
//                        PermissionUtils.startAndroidSettings(finalContext);
//                    }
//                });
//    }
//}