package com.example.weddingrides;

public class feed {

    private int id;
    private String feedback;


    public feed(){

    }



    public feed(int id, String feedback){
        this.id = id;
        this.feedback = feedback;

    }

    public feed(String feedback) {
        this.feedback = feedback;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
