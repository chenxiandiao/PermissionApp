package com.teletraan.permission.core;

public interface IPermission {

    void granted(); // 已经授权

    void cancel(); // 取消授权

    void denied(); // 拒绝授权

}
