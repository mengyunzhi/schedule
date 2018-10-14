package com.mengyunzhi.schedule.other;

/**
 * @author liyiheng
 * @date 2018/10/14
 * 操作PullRequest得到的信息
 */
public class PayLoad {

    private int number;               // #xxx
    private PullRequest pull_request; // pull_request的类
    private String body;              // 备注

    public static PayLoad create() {
        PayLoad payLoad = new PayLoad();
        PullRequest pullRequest = PullRequest.create();
        payLoad.setPull_request(pullRequest);
        return payLoad;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }
}
