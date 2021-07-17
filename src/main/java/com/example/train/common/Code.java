package com.example.train.common;

public class Code {

    public static final int SUCCESS = 200;

    /*
    失败
     */
    public static final int ERROR = 400;

    /*
    任务查询失败或者没有学习任务
     */
    public static  final int ERROR_TASK = 600;

    /*用户名或密码错误*/
    public static final int ERROR_PASSWORD = 600;

    //注册账号已经存在的错误
    public static final int ERROR_USERNAME = 601;

    //博客发表失败
    public static final int ERROR_ARTICLE = 602;

    //头像上传失败
    public static final int UPLOAD_AVATAR_FAILED = 603;

    //获取头像信息失败
    public static final int GET_AVATAR_FAILED = 604;

    //获取博客内容失败
    public static final int GET_ARTICLE_FAILED = 605;

    //获取博客类型失败
    public static final int GET_CLASS_FAILED = 606;

    //获取用户信息失败
    public static final int GET_USER_FAILED = 607;
}