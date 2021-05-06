package com.example.wujugg;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;

public class ChampionDescription extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Champion> champions ;
    ArrayList<Role> roles;
    ChampionDescription championDesc;
    private GestionBD sgbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_champion_description);

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        this.championDesc = this;
        sgbd = new GestionBD(this);

        sgbd.open();
        champions = sgbd.selectChampion();
        roles = sgbd.selectRole();
        sgbd.close();

        ImageView iconChampion=findViewById(R.id.championImage) ;
        TextView nomChampion=findViewById(R.id.nomChampion);
        TextView description= findViewById(R.id.descriptionChampion);
        TextView competenceA= findViewById(R.id.competenceA);
        TextView competenceZ= findViewById(R.id.competenceZ);
        TextView competenceE= findViewById(R.id.competenceE);
        TextView competenceR= findViewById(R.id.competenceR);
        TextView passif= findViewById(R.id.passif);
        TextView role = findViewById(R.id.role);
        Button retour = findViewById(R.id.retourArriereDesc);
        Button modif = findViewById(R.id.modif);
        Button delete = findViewById(R.id.delete);


        nomChampion.setText(champions.get(position).getNomChampion());
        description.setText(champions.get(position).getDescriptionChampion());
        competenceA.setText(champions.get(position).getCompetenceA());
        competenceZ.setText(champions.get(position).getCompetenceZ());
        competenceE.setText(champions.get(position).getCompetenceE());
        competenceR.setText(champions.get(position).getCompetenceR());
        passif.setText(champions.get(position).getPassif());
        role.setText(roles.get(champions.get(position).getRole()).getNomRole());

        if (getResources().getIdentifier(champions.get(position).getNomChampion().toLowerCase(),"drawable",getPackageName()) == 0){
            iconChampion.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.nullicon));
        }

        else{
            Drawable drawable = ContextCompat.getDrawable(this,
                    getResources().getIdentifier(champions.get(position).getNomChampion().toLowerCase(),
                            "drawable",
                            getPackageName()));
            iconChampion.setImageDrawable(drawable);
        }

        modif.setOnClickListener(this);
        retour.setOnClickListener(this);
        delete.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();


        int position = bundle.getInt("position");
        int id = v.getId();
        // modification d'un champion
        if (id == R.id.modif){
            Intent i = new Intent(getApplicationContext(),ModifChampion.class);
            i.putExtra("position",position);
            startActivity(i);
        }
        //retour main activity
        else if (id == R.id.retourArriereDesc){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        //delete champions
        else if (id == R.id.delete){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(championDesc);

            alertDialog.setTitle("voulez-vous vraiment supprimer "+champions.get(position).getNomChampion()+" ?");
            alertDialog.setIcon(R.drawable.meharret);

            alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sgbd.open();
                    sgbd.deleteChampion(champions.get(position).getIdChampion());
                    sgbd.close();
                    i.putExtra("delete",1);
                    startActivity(i);
                }
            });
            alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }


}