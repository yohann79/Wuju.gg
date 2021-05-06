package com.example.wujugg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

private GridView gridView;
    TraitementJSON tAsync;

MainActivity mainActivity;
ArrayList<Champion> champions ;
ArrayList<Role> roles;
    private GestionBD sgbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mainActivity=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        sgbd = new GestionBD(this);


        sgbd.open();
        if (sgbd.selectRole().size() == 0){
            sgbd.insertRole();
        }


        if(sgbd.selectChampion().size() == 0){
            tAsync = new TraitementJSON(this);
            tAsync.execute("https://gist.githubusercontent.com/yohann79/70d3f4e9b234ba674f30b8a89ae55a57/raw/4c560ee6c506b6a0b3d8f8e6e3b3caab4612b582/LesChampion.json");
        }
        sgbd.close();




        gridView = (GridView) findViewById(R.id.championList);
        Button creerChamp= findViewById(R.id.creerChampion);
        Button roleDesc = findViewById(R.id.role);
        ImageAdapter ia = new ImageAdapter(this);


        gridView.setAdapter( ia);

        gridView.setOnItemClickListener(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mainActivity);


        if (bundle != null) {
            int deleted = bundle.getInt("delete");
             if (deleted == 1) {
                alertDialog.setMessage("champion correctement supprimmer");
                alertDialog.show();
            }
        }

        creerChamp.setOnClickListener(this);
        roleDesc.setOnClickListener(this);

    }



    @Override    // implémentation de la méthode onClick de l'interface OnItemClickListener
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // position = l'icône sur laquelle l'utilisateur a cliqué
        Intent intent = new Intent(getApplicationContext(),ChampionDescription.class);
        sgbd.open();
        champions = sgbd.selectChampion();
        sgbd.close();
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //creer un champion
        if (id == R.id.creerChampion){
            Intent i = new Intent(getApplicationContext(),CreerChampion.class);
            startActivity(i);
        }
        //voir les roles
        else if (id == R.id.role){
            Intent i = new Intent(getApplicationContext(), RoleMain.class);
            i.putExtra("lesRoles",roles);
            startActivity(i);
        }
    }

}