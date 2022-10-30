package com.example.madapp;

public class MsgModel {
    private int id;
    private String Msg;

    public MsgModel() {
    }

    public MsgModel(int id, String msg) {
        this.id = id;
        Msg = msg;
    }

    public MsgModel(String msg) {
        Msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
