package com.example.weddingrides;

public class complain {

    private int id;
    private String complaint;



    public complain(){

    }



    public complain(int id, String complain){
        this.id = id;
        this.complaint = complaint;

    }

    public complain(String complaint) {
        this.complaint = complaint;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getComplaint() {
        return complaint;
    }

    public void setcomplaint(String feedback) {
        this.complaint = complaint;
    }
}