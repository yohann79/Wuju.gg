package com.example.wujugg;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Role{
    String nomRole;
    String descritionRole;
    int id;

    public Role(int id,String nomRole, String descritionRole) {
        this.nomRole = nomRole;
        this.descritionRole = descritionRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDescritionRole() {
        return descritionRole;
    }

    public void setDescritionRole(String descritionRole) {
        this.descritionRole = descritionRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
