package com.example.wujugg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RoleMain extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
ArrayList<Role> roles;
private GestionBD sgbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_main);
        ListView listView = findViewById(R.id.listRole);
        Button retour = findViewById(R.id.retourArriereRole);
        sgbd = new GestionBD(this);
        sgbd.open();
        roles = sgbd.selectRole();
        sgbd.close();


        ArrayList<String> lesNoms = new ArrayList<String>();
        for (Role role : roles){
            lesNoms.add(role.getNomRole());
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lesNoms);
        listView.setAdapter(aa);

        retour.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.retourArriereRole){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);;
            startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(),RoleDescription.class);

        i.putExtra("position",position);
        startActivity(i);
    }
}