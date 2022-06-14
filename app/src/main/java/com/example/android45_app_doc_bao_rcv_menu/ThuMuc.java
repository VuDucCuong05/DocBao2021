package com.example.android45_app_doc_bao_rcv_menu;

public class ThuMuc {
    private int id;
    private String ten;
    private int anh;
    private String link;

    public ThuMuc(int id, String ten, int anh, String link) {
        this.id = id;
        this.ten = ten;
        this.anh = anh;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
