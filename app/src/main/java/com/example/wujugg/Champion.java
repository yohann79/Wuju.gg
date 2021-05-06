package com.example.wujugg;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Champion{
    int idChampion;
    String nomChampion;
    String descriptionChampion;
    String competenceA;
    String competenceZ;
    String competenceE;
    String competenceR;
    String passif;
    int idRole;

    public Champion(int idChampion,String nomChampion, String descriptionChampion, String competenceA, String competenceZ, String competenceE, String competenceR, String passif,int idRole) {
        this.idChampion=idChampion;
        this.nomChampion = nomChampion;
        this.descriptionChampion = descriptionChampion;
        this.competenceA = competenceA;
        this.competenceZ = competenceZ;
        this.competenceE = competenceE;
        this.competenceR = competenceR;
        this.passif = passif;
        this.idRole = idRole;
    }

    public int getIdChampion() {
        return idChampion;
    }

    public void setIdChampion(int idChampion) {
        this.idChampion = idChampion;
    }

    public String getNomChampion() {
        return nomChampion;
    }

    public void setNomChampion(String nomChampion) {
        this.nomChampion = nomChampion;
    }

    public String getDescriptionChampion() {
        return descriptionChampion;
    }

    public void setDescriptionChampion(String descriptionChampion) {
        this.descriptionChampion = descriptionChampion;
    }

    public String getCompetenceA() {
        return competenceA;
    }

    public void setCompetenceA(String competenceA) {
        this.competenceA = competenceA;
    }

    public String getCompetenceZ() {
        return competenceZ;
    }

    public void setCompetenceZ(String competenceZ) {
        this.competenceZ = competenceZ;
    }

    public String getCompetenceE() {
        return competenceE;
    }

    public void setCompetenceE(String competenceE) {
        this.competenceE = competenceE;
    }

    public String getCompetenceR() {
        return competenceR;
    }

    public void setCompetenceR(String competenceR) {
        this.competenceR = competenceR;
    }

    public String getPassif() {
        return passif;
    }

    public void setPassif(String passif) {
        this.passif = passif;
    }

    public int getRole() {
        return idRole;
    }

    public void setRole(int idRole) {
        this.idRole = idRole;
    }




}
