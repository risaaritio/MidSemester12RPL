package com.example.rplrus25.midsemester12rpl;

public class ItemObject {
    private String name;
    private String password;
    private String deskripsi;
    private String gambar;
    private String id;



    public ItemObject() {
        this.setName(name);
        this.setPassword(getPassword());
        this.setDeskripsi(deskripsi);
        this.setGambar(gambar);
        this.setId (getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }


    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
