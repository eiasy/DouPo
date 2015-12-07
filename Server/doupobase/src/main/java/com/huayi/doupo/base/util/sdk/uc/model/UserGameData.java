package com.huayi.doupo.base.util.sdk.uc.model;

import java.util.Map;

/**
 * 玩家游戏数据
 * <br>
 * <br>========================== 
 * <br>公司：优视科技-游戏中心 
 * <br>开发：liusl@ucweb.com 
 * <br>创建时间：2015年5月4日
 * <br>==========================
 */
public class UserGameData {

    private String category;
    
    private Map<String, String> content;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
