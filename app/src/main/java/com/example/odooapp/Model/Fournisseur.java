package com.example.odooapp.Model;

public class Fournisseur {


    private int id;
    private String name, phone,mobile,email,website;

    public Fournisseur(){}
    public Fournisseur( String name, String phone, String mobile, String email, String website) {

        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.website = website;

    }
    public Fournisseur(int id, String name, String phone, String mobile, String email, String website) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.website = website;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
