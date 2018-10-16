package com.mengyunzhi.schedule.other;

import java.util.Calendar;

/**
 * @author liyiheng
 * @date 2018/10/14
 */
public class PullRequest {
    private String url;       // #xxx匹配的url
    private String title;    // 标题
    private User user;
    private Calendar created_at;     //创建时间

    public static PullRequest create() {
        PullRequest pullRequest = new PullRequest();
        User user = new User();
        pullRequest.setUser(user);
        return pullRequest;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
