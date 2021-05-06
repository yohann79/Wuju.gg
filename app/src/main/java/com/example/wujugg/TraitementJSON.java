package com.example.wujugg;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.wujugg.Champion;
import com.example.wujugg.GestionBD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TraitementJSON extends AsyncTask {

    private List<Champion> lesChampions = new ArrayList<Champion>();
    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    private GestionBD sgbd;


    // constructeur
    public TraitementJSON(Context context) {

        this.context = context;
        sgbd = new GestionBD(context);
    }

    @Override
    protected Boolean doInBackground(Object... urls) {

        sgbd.open();
        try {
            url = new URL((String) urls[0]);
            Log.i("doInBack", "L'url " + urls[0]);
            String fichier = lectureFichierDistant();
            recPersonnages(fichier);
            Log.i("doInBack", "Le fichier distant : " + fichier);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        sgbd.close();
        return true;
    }

    private String lectureFichierDistant() {
        StringBuilder builder = new StringBuilder();
        try {
            connexion = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ligne = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(connexion.getInputStream())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while ((ligne = br.readLine()) != null) {
                builder.append(ligne).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void recPersonnages(String fichier) {

        JSONArray lesChampion = null;
        JSONObject jsPerso = null;
        String leNom, laDesc,competenceA,competenceZ,competenceE,competenceR,passif;
        int role, idChampion;
        Champion champion;

        if (fichier != null) {
            try {
                jObj = new JSONObject(fichier);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        try {
            lesChampion = jObj.getJSONArray("lesChampions");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lesChampion.length(); i++) {
            try {
                jsPerso = (JSONObject) lesChampion.get(i);
                idChampion = i;
                leNom = jsPerso.getString("name");
                laDesc = jsPerso.getString("desc");
                competenceA = jsPerso.getString("competenceA");
                competenceZ = jsPerso.getString("competenceZ");
                competenceE = jsPerso.getString("competenceE");
                competenceR = jsPerso.getString("competenceR");
                passif = jsPerso.getString("passif");
                role = jsPerso.getInt("role");
                champion = new Champion(idChampion,leNom,laDesc,competenceA,competenceZ,competenceE,competenceR,passif,role);
                lesChampions.add(champion);

                sgbd.ajoutChampion(champion);

                Log.i("doInBack : ","recPerso -> ArrayList : "+lesChampions.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }



}
