package com.tempVar.GeoHelp.APIs.returnDTOs;

import com.tempVar.GeoHelp.database.entities.PostInfo;

import java.util.List;

public class posterReturnDTO {
    private int status;
    private String msg;

    public posterReturnDTO(int i, String str) {
        status = i;
        msg = str;
    }

    public posterReturnDTO() {
        status = 0;
        msg = "success";
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
