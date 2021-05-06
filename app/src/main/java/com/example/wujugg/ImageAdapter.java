package com.example.wujugg;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter{
    // cette classe permet de remplir les "cases" de la grille une par une
    // elle est instanciée dans la MainActivity
ArrayList<Champion> champions;
    private GestionBD sgbd;


    private Context mContext; //le contexte d'exécution
    public ImageAdapter(Context c) {
        mContext=c;

        sgbd = new GestionBD(mContext);
    }

    public int getCount() {
    ImageAdapter ia = new ImageAdapter(mContext);
        // on retourne le nombre d'élèments du tableau
        // qui correspond au nombre de cellules à implémenter dans la grid
        sgbd.open();
        champions = sgbd.selectChampion() ;
        sgbd.close();
        return champions.size();
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }
    public static Drawable getDrawable(String name, Context context) {
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        if (resourceId == 0){
            return ContextCompat.getDrawable(context,R.drawable.nullicon);
        }else {
            return context.getResources().getDrawable(resourceId);
        }

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        // initialisation et paramétrage d'une case de la grille
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400,400 ));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }
        else {
            // si lagrille est intialisée, on peut instancier chaque "case" (une par une)
            imageView = (ImageView) convertView;
        }
        ImageAdapter ia = new ImageAdapter(mContext);
        //attribution des fichiers "image"
        sgbd.open();
       String leNom= sgbd.selectChampion().get(position).getNomChampion();
       sgbd.close();
        imageView.setImageDrawable(ia.getDrawable(leNom.toLowerCase(),mContext));

        return imageView;
    }
}
