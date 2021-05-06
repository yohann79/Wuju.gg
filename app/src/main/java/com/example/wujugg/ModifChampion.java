package com.example.wujugg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModifChampion extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
ArrayList<Champion> champions;
private GestionBD sgbd;
ArrayList<Role> roles ;
private int roleIdSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sgbd = new GestionBD(this);
        sgbd.open();
        champions =sgbd.selectChampion();
        roles = sgbd.selectRole();
        sgbd.close();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modif_champion);
        Spinner listRole = findViewById(R.id.roleListModif);
        EditText nomChampion = findViewById(R.id.nomChampion);
        EditText description = findViewById(R.id.descriptionChampion);
        EditText competenceA = findViewById(R.id.competenceA);
        EditText competenceZ = findViewById(R.id.competenceZ);
        EditText competenceE = findViewById(R.id.competenceE);
        EditText competenceR = findViewById(R.id.competenceR);
        EditText passif = findViewById(R.id.passif);
        ImageView icon = findViewById(R.id.iconModifChampion);


        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        if (getResources().getIdentifier(champions.get(position).getNomChampion().toLowerCase(),"drawable",getPackageName()) == 0){
            icon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.nullicon));
        }
        else{
            Drawable drawable = ContextCompat.getDrawable(this,
                    getResources().getIdentifier(champions.get(position).getNomChampion().toLowerCase(),
                            "drawable",
                            getPackageName()));

            icon.setImageDrawable(drawable);
        }

        nomChampion.setText(champions.get(position).getNomChampion());
        description.setText(champions.get(position).getDescriptionChampion());
        competenceA.setText(champions.get(position).getCompetenceA());
        competenceZ.setText(champions.get(position).getCompetenceZ());
        competenceE.setText(champions.get(position).getCompetenceE());
        competenceR.setText(champions.get(position).getCompetenceR());
        passif.setText(champions.get(position).getPassif());


        Button retour = findViewById(R.id.retourArriereModif);
        Button valider = findViewById(R.id.valider);
        String test = roles.get(champions.get(position).getRole()).getNomRole();

        List<String> nomRole = new ArrayList<String>();
        for(Role role : roles) {
            nomRole.add(role.getNomRole());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomRole.toArray(new String[0]));
        listRole.setAdapter(adapter);
        listRole.setSelection(adapter.getPosition(nomRole.get(champions.get(position).getRole())));
        listRole.setOnItemSelectedListener(this);

        valider.setOnClickListener(this);
        retour.setOnClickListener(this);
        listRole.setOnItemSelectedListener(this);
    }
    @Override
    public void onClick(View v) {
        Bundle bundle = getIntent().getExtras();

        int position = bundle.getInt("position");
        int id = v.getId();

        if(id == R.id.retourArriereModif) {
            Intent i = new Intent(getApplicationContext(),ChampionDescription.class);
            i.putExtra("position",position);
            startActivity(i);

        }
        else if(id==R.id.valider){
            Intent i = new Intent(getApplicationContext(),ChampionDescription.class);

            Spinner listRole = findViewById(R.id.roleListModif);
            EditText nomChampion = findViewById(R.id.nomChampion);
            EditText description = findViewById(R.id.descriptionChampion);
            EditText competenceA = findViewById(R.id.competenceA);
            EditText competenceZ = findViewById(R.id.competenceZ);
            EditText competenceE = findViewById(R.id.competenceE);
            EditText competenceR = findViewById(R.id.competenceR);
            EditText passif = findViewById(R.id.passif);

            String leNom =nomChampion.getText().toString();
            String laDesc =description.getText().toString();
            String laCompA =competenceA.getText().toString();
            String laCompZ =competenceZ.getText().toString();
            String laCompE =competenceE.getText().toString();
            String laCompR =competenceR.getText().toString();
            String lePassif =passif.getText().toString();

            sgbd.open();
            sgbd.updateChampion(champions.get(position).getIdChampion(),leNom,laDesc,laCompA,laCompZ,laCompE,laCompR,lePassif,roleIdSelected-1);
            sgbd.close();
            i.putExtra("position",position);
            startActivity(i);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nomRoleSelected = (String) parent.getItemAtPosition(position);
        sgbd.open();
        roleIdSelected = sgbd.selectRoleWithName(nomRoleSelected);
        sgbd.close();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}