package com.example.main;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class De {
    @FXML
    private COULEUR couleur;
    @FXML
    private List<FACE> face = new ArrayList<>();
    @FXML
    private FACE faceObtenue;
    public De() {
        super();
    }

    public void setFace(COULEUR couleur) {
        this.couleur = couleur;
        switch(couleur) {
            case VERT:
                for(int i = 1; i <= 3; i++) {
                    this.face.add(FACE.CERVAL);
                }
                for(int i = 1; i <= 2; i++) {
                    this.face.add(FACE.EMPREINTE);
                }
                for(int i = 1; i <= 1; i++) {
                    this.face.add(FACE.FUSIL);
                }
                break;
            case JAUNE:
                for(int i = 1; i <= 2; i++) {
                    this.face.add(FACE.CERVAL);
                }
                for(int i = 1; i <= 2; i++) {
                    this.face.add(FACE.EMPREINTE);
                }
                for(int i = 1; i <= 2; i++) {
                    this.face.add(FACE.FUSIL);
                }
                break;
            case ROUGE:
                for(int i = 1; i <= 1; i++) {
                    this.face.add(FACE.CERVAL);
                }
                for(int i = 1; i <= 2; i++) {
                    this.face.add(FACE.EMPREINTE);
                }
                for(int i = 1; i <= 3; i++) {
                    this.face.add(FACE.FUSIL);
                }
                break;
        }
    }

    public List<FACE> getFace() {
        return face;
    }

    public FACE LancerLeDe() {
        Collections.shuffle(face);
        return face.get(1);
    }

    public COULEUR getCouleur() {
        return couleur;
    }

    public FACE getFaceObtenue() {
        return faceObtenue;
    }

    public void setFaceObtenue(FACE faceObtenue) {
        this.faceObtenue = faceObtenue;
    }
}
