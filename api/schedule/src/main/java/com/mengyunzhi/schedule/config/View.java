package com.mengyunzhi.schedule.config;

/**
 * 用于jsonview序列化
 */
public class View {

    //学期的返回格式
    public interface Semester {}

    //行程返回格式
    public interface Schedule {}

    //学生返回格式
    public interface Student {}

    //贡献值返回格式
    public interface Contribution{}

    public interface NoJsonView {}
}
