# 基于AspectJ的动态权限管理

## 使用说明
>AspectJ 是 Android 平台上一种比较高效和简单的实现 AOP 技术的方案
## 引入 AspectJ
1. 在project的build.gradle下buildscript/dependencies下添加
    ```gradle
        classpath 'org.aspectj:aspectjtools:1.8.9'
        classpath 'org.aspectj:aspectjweaver:1.8.9'
    ```
2. 需要在主模块build.gradle dependencies目录下添加,并加入tpermission模块
   ```gradle
   implementation 'org.aspectj:aspectjrt:1.8.13'
   ```

## Thanks
基于网易课堂老师提供的代码改造