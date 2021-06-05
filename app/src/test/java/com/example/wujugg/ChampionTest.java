package com.example.wujugg;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.InstrumentationInfo;


import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ChampionTest{

    private GestionBD sgbd;
    private ArrayList<Champion> champions;
/*
    @Test
    public void selectChampion(){
        sgbd = new GestionBD();
        sgbd.open();
        sgbd.createChampion("Jhin","Jhin est un psychopathe méticuleux pour qui le meurtre est une forme d'art. Autrefois prisonnier de Ionia, mais libéré par des conspirateurs opérant au sein du conseil dirigeant du pays, ce tueur en série a désormais mis ses talents d'assassin au service de leur complot. Utilisant son pistolet comme un pinceau, Jhin crée des œuvres d'art empreintes de brutalité, aussi terrifiantes pour ses victimes que pour les témoins de ses crimes. Il tire un plaisir sadique de ses performances théâtrales, ce qui en fait un atout de poids quand il s'agit d'envoyer le plus puissant des messages : celui de la terreur."
                ,"GRENADE DANSANTE","FLORAISON MORTELLE","PUBLIC CAPTIF","RAPPEL DE RIDEAU","MURMURE",0);
        champions=sgbd.selectChampion();
        sgbd.close();
        Champion leChampionTest = champions.get(0);
        Champion leChampion = new Champion(0,"Jhin","Jhin est un psychopathe méticuleux pour qui le meurtre est une forme d'art. Autrefois prisonnier de Ionia, mais libéré par des conspirateurs opérant au sein du conseil dirigeant du pays, ce tueur en série a désormais mis ses talents d'assassin au service de leur complot. Utilisant son pistolet comme un pinceau, Jhin crée des œuvres d'art empreintes de brutalité, aussi terrifiantes pour ses victimes que pour les témoins de ses crimes. Il tire un plaisir sadique de ses performances théâtrales, ce qui en fait un atout de poids quand il s'agit d'envoyer le plus puissant des messages : celui de la terreur."
                                            ,"GRENADE DANSANTE","FLORAISON MORTELLE","PUBLIC CAPTIF","RAPPEL DE RIDEAU","MURMURE",0);
        assertEquals(leChampion,leChampionTest);

    }*/


}
