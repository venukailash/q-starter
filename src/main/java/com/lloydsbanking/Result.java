package com.lloydsbanking;

import java.util.List;

public class Result {
    public Integer number;
    public String string;
    public List<Integer> list;
    public String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }
    public List<Integer> getList() {
        return list;
    }
    public void setList(List<Integer> list) {
        this.list = list;
    }


    
}
