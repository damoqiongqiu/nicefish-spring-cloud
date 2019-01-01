package com.fish.user.util;

/**
 * @author 大漠穷秋
 * @version 创建时间：2019-01-01 11:53
 */
public class AjaxResponseEntity {
    private boolean success;
    private String msg;

    public AjaxResponseEntity(){

    }

    public AjaxResponseEntity(boolean success){
        this.success=success;
    }

    public AjaxResponseEntity(boolean success,String msg){
        this.success=success;
        this.msg=msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
