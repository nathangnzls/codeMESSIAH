package net.netne.cmessiah.codemessiah;

import android.graphics.Bitmap;

public class SelectUser {
    String name;
    public Bitmap getThumb() {
        return thumb;
    }
    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
    Bitmap thumb;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    String phone;
    public Boolean makeCall() {
        return call;
    }
    public void makeCall(Boolean checkedBox) {
        this.call = checkedBox;
    }
    Boolean call = false;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
