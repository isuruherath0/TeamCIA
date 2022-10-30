package com.example.madapp;

public class cardModel {
    private int id;
    private String cName;
    private String cNumber,cCVC;
    private String cExp;

    public cardModel(){

    }

    public cardModel(int id, String cName, String cNumber, String cCVC, String cExp) {
        this.id = id;
        this.cName = cName;
        this.cNumber = cNumber;
        this.cCVC = cCVC;
        this.cExp = cExp;

    }

    public cardModel(String cName, String cNumber, String cCVC, String cExp) {
        this.cName = cName;
        this.cNumber = cNumber;
        this.cCVC = cCVC;
        this.cExp = cExp;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public String getcCVC() {
        return cCVC;
    }

    public void setcCVC(String cCVC) {
        this.cCVC = cCVC;
    }

    public String getcExp() {
        return cExp;
    }

    public void setcExp(String cExp) {
        this.cExp = cExp;
    }

}
