package com.example.wujugg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoleDescription extends AppCompatActivity implements View.OnClickListener {
ArrayList<Role> roles;
private GestionBD sgbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_description);
        Button retour = findViewById(R.id.retourRoleMain);
        TextView leNom = findViewById(R.id.nomRole);
        TextView laDesc = findViewById(R.id.descriptionRole);
        TextView idRole = findViewById(R.id.idRole);
        sgbd = new GestionBD(this);
        sgbd.open();
        roles = sgbd.selectRole();
        sgbd.close();
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        Log.i("oui","é"+position);
        leNom.setText(roles.get(position).getNomRole());
        laDesc.setText(roles.get(position).getDescritionRole());
        idRole.setText(String.valueOf(roles.get(position).getId()));
        retour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.retourRoleMain){
            Intent i = new Intent(getApplicationContext(),RoleMain.class);


            startActivity(i);
        }
    }
}