package com.mengyunzhi.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author htx
 * @description 系统设置
 * @date 上午10:26 19-6-5
 **/
@Entity
public class SystemSetting {

    @Id
    String key; // 键

    String value; // 值

    public SystemSetting() {

    }

    public SystemSetting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
