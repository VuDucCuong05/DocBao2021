package com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap;

public class TaiKhoan {
    private int id;
    private String gmail;
    private String pass;

    public TaiKhoan(int id, String gmail, String pass) {
        this.id = id;
        this.gmail = gmail;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
