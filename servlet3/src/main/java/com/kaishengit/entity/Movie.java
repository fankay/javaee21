package com.kaishengit.entity;

public class Movie {

    private Integer id;
    private String title;
    private Float rate;
    private String releaseyear;
    private String sendtime;
    private String daoyan;
    private String jianjie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getDaoyan() {
        return daoyan;
    }

    public void setDaoyan(String daoyan) {
        this.daoyan = daoyan;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", releaseyear='" + releaseyear + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", daoyan='" + daoyan + '\'' +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }
}
