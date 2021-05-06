package com.example.wujugg;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GestionBD {
private SQLiteDatabase maBase;
private BDHelper bdHelper;

    public GestionBD(Context context) {
        this.bdHelper = new BDHelper(context,"WUJUGG",null,1);
    }
    public void open(){
        maBase = bdHelper.getWritableDatabase();
    }
    public void close(){
        maBase.close();
    }

    public void ajoutChampion(Champion champion){
        ContentValues cv = new ContentValues();
        cv.put("idChampion",champion.getIdChampion());
        cv.put("nom",champion.getNomChampion());
        cv.put("description",champion.getDescriptionChampion());
        cv.put("competenceA",champion.getCompetenceA());
        cv.put("competenceZ",champion.getCompetenceZ());
        cv.put("competenceE",champion.getCompetenceE());
        cv.put("competenceR",champion.getCompetenceR());
        cv.put("idRole",champion.getRole());
        cv.put("passif",champion.getPassif());

        maBase.insert("champion",null,cv);
    }
    public ArrayList<Champion> selectChampion(){
        ArrayList<Champion> lesChampions = new ArrayList<>();
        String req = "select * from champion";
        Cursor cursor=maBase.rawQuery(req,null,null);
        while (cursor.moveToNext()){
            lesChampions.add(new Champion(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getInt(8)));
        }
        return lesChampions;
    }

    public void deleteChampion(int position) {
        String req = "DELETE FROM champion WHERE idChampion ='"+position+"'";
        maBase.execSQL(req);

    }
    public void updateChampion(int position, String leNom, String laDesc, String compA, String compZ, String compE, String compR, String passif, int roleIdSelected){
        ContentValues cv = new ContentValues();
        cv.put("nom",leNom);
        cv.put("description",laDesc);
        cv.put("competenceA",compA);
        cv.put("competenceZ",compZ);
        cv.put("competenceE",compE);
        cv.put("competenceR",compR);
        cv.put("passif",passif);
        cv.put("idRole",roleIdSelected);

        maBase.update("champion",cv,"idChampion"+"="+position,null);
    }
    public void insertRole(){
        ArrayList<Role> roles ;
        roles= new DonneeRoleEnDure().remplirRole();
        ContentValues cv = new ContentValues();
        for(Role role : roles){
            cv.put("nom",role.getNomRole());
            cv.put("description",role.getDescritionRole());
            maBase.insert("role",null,cv);
        }
    }
    public ArrayList<Role> selectRole(){
        ArrayList<Role> roles = new ArrayList<>();
        String req = "select * from role";
        Cursor cursor=maBase.rawQuery(req,null,null);
        while (cursor.moveToNext()){
            roles.add(new Role(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        }


        return roles;

    }

    public void createChampion(String nomChampion, String description, String competenceA, String competenceZ, String competenceE, String competenceR, String passif, int roleIdSelected) {
        ContentValues cv = new ContentValues();
        cv.put("nom",nomChampion);
        cv.put("description",description);
        cv.put("competenceA",competenceA);
        cv.put("competenceZ",competenceZ);
        cv.put("competenceE",competenceE);
        cv.put("competenceR",competenceR);
        cv.put("passif",passif);
        cv.put("idRole",roleIdSelected);

        maBase.insert("champion",null,cv);
    }

    public int selectRoleWithName(String name){
        String req = "select idRole from role where nom = '"+name+"'";
        Cursor cursor=maBase.rawQuery(req,null,null);
        int idRole = 0;
        while (cursor.moveToNext()){
           idRole = cursor.getInt(0);
        }

        return idRole;
    }
}
