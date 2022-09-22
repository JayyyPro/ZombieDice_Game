package com.example.main;

import javafx.fxml.FXML;
public class Joueur {
    @FXML
    private int numero;
    @FXML
    private int nbCerveauxTotaux = 0;
    @FXML
    private int nbCerveauxTour = 0;
    @FXML
    private int nbEmpreinte;
    @FXML
    private int nbFusil;

    public Joueur(int numero) {
        super();
        this.numero = numero + 1;
    }

    public int getNumero() {
        return numero;
    }

    public int getNbCerveauxTotaux() {
        return nbCerveauxTotaux;
    }

    public int getNbCerveauxTour() {
        return nbCerveauxTour;
    }

    public int getNbEmpreinte() {
        return nbEmpreinte;
    }

    public int getNbFusil() {
        return nbFusil;
    }

    public void setNbCerveauxTour(int nbCerveauxTour) {
        this.nbCerveauxTour = nbCerveauxTour;
    }

    public void setNbEmpreinte(int nbEmpreinte) {
        this.nbEmpreinte = nbEmpreinte;
    }

    public void setNbFusil(int nbFusil) {
        this.nbFusil = nbFusil;
    }

    public void setNbCerveauxTotaux(int nbCerveauxTotaux) {
        this.nbCerveauxTotaux = nbCerveauxTotaux;
    }
}
