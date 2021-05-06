package com.example.wujugg;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.controls.templates.ControlTemplate;

import java.util.ArrayList;

public class DonneeRoleEnDure {
    public ArrayList<Role> remplirRole(){
        ArrayList<Role> roles = new ArrayList<>();
        Role mid = new Role(1,"Millieu","Il s’agit de la voie du milieu et également la plus courte en terme de distance à parcourir. Cela rend la midlane difficile à gérer en terme de gestion des sbires car ils arrivent vite sous la tourelle. ");
        Role adc = new Role(0,"ADC","Il s’agit de la voie du milieu et également la plus courte en terme de distance à parcourir. Cela rend la midlane difficile à gérer en terme de gestion des sbires car ils arrivent vite sous la tourelle. ");
        Role support = new Role(2,"Support","Le support est joué sur la botlane (voie du bas en français). Les champions pouvant évoluer à ce rôle ont la capacité de supporter un allié afin de l'aider à s'équiper le plus rapidement possible, tout en lui évitant de mourir. ");
        Role top = new Role(3,"Top lane","La toplane se situe sur la partie haute de la carte. C'est généralement la lane des tanks ou des combattant. De manière générale ce sont des champions faibles en début de partie qui ont besoin de farm pour révéler tout leur potentiel et devenir vraiment puissants. Cependant, ce n'est pas une vérité absolue, car il est tout à fait possible de jouer des personnages puissants en début de partie sur la toplane.");
        Role jungle = new Role(4,"Jungle","jungle est l’action de tuer les monstres neutres, qui sont des créatures situées entre les voies de la faille de l’Invocateur. La Jungle est la partie de la Faille de l’Invocateur qui n’est pas occupée par les voies ou les bases de l’équipe, y compris la rivière qui la divise.");
        roles.add(adc);
        roles.add(mid);
        roles.add(support);
        roles.add(top);
        roles.add(jungle);
        return roles;
    }


}
