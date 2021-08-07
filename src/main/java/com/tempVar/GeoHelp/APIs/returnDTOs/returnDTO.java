package com.tempVar.GeoHelp.APIs.returnDTOs;

import com.tempVar.GeoHelp.database.entities.PostInfo;

import java.util.List;

public class returnDTO {
    private int status;
    private String msg;
    private Object result;

    public returnDTO(int i, String str, Object res) {
        status = i;
        msg = str;
        result = res;
    }

    public returnDTO() {
        status = 0;
        msg = "success";
        result = new Object();
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setResult(Object obj) {
        this.result = obj;
    }
    public void setResult(List<PostInfo> obj) {
        this.result = obj;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getResult() {
        return result;
    }
}
