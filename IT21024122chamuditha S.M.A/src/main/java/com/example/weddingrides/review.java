package com.example.weddingrides;

public class review {
    private int id;
    private String Myreview;


    public review(){

    }



    public review(int id, String Myreview){
        this.id = id;
        this.Myreview = Myreview;

    }

    public review(String Myreview) {
        this.Myreview = Myreview;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getReview() {
        return Myreview;
    }

    public void setMyreview(String feedback) {
        this.Myreview = Myreview;
    }

}

