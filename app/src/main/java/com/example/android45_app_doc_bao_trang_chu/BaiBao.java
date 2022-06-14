package com.example.android45_app_doc_bao_trang_chu;

import java.io.Serializable;

public class BaiBao implements Serializable {
    private int id;
    private String titel;
    private String link;
    private String image;
    private String introduce;
    private String luy;

    public BaiBao(int id, String titel, String link, String image, String introduce, String luy) {
        this.id = id;
        this.titel = titel;
        this.link = link;
        this.image = image;
        this.introduce = introduce;
        this.luy = luy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLuy() {
        return luy;
    }

    public void setLuy(String luy) {
        this.luy = luy;
    }
}
