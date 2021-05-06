package com.example.wujugg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class CreerChampion extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    ArrayList<Champion> champions;
    ArrayList<Role> roles;
    private GestionBD sgbd;
    int roleIdSelected ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_champion);
        Spinner listRole = findViewById(R.id.roleList);
        sgbd = new GestionBD(this);
        sgbd.open();
        champions = sgbd.selectChampion();
        roles = sgbd.selectRole();
        List<String> nomRole = new ArrayList<String>();
        for(Role role : sgbd.selectRole()) {
            nomRole.add(role.getNomRole());
        }
        sgbd.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomRole.toArray(new String[0]));


        listRole.setAdapter(adapter);
        listRole.setOnItemSelectedListener(this);
        Button valider = findViewById(R.id.validerCreer);
        valider.setOnClickListener(this);
        Button retourArriereCree = findViewById(R.id.retourArriereCreer);
        retourArriereCree.setOnClickListener(this);
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





    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.validerCreer) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);



            EditText nomChampion = findViewById(R.id.creerNomChampion);
            EditText description = findViewById(R.id.creerDescriptionChampion);
            EditText competenceA = findViewById(R.id.creerCompetenceA);
            EditText competenceZ = findViewById(R.id.creerCompetenceZ);
            EditText competenceE = findViewById(R.id.creerCompetenceE);
            EditText competenceR = findViewById(R.id.creerCompetenceR);
            EditText passif = findViewById(R.id.creerPassif);
            ImageView icon = findViewById(R.id.iconCreerChampion);
            Spinner listRole = findViewById(R.id.roleList);


            sgbd.open();
            sgbd.createChampion(nomChampion.getText().toString(),description.getText().toString(),competenceA.getText().toString(),competenceZ.getText().toString(),competenceE.getText().toString(),competenceR.getText().toString(),passif.getText().toString(),roleIdSelected-1);
            sgbd.close();
            startActivity(i);
        }
        else if (id == R.id.retourArriereCreer){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }


}