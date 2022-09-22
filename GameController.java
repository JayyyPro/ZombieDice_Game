package com.example.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GameController extends Application implements Initializable {

    @FXML
    private int nbDeVert;
    @FXML
    private int nbDeJaune;
    @FXML
    private int nbDeRouge;
    @FXML
    private int nbJoueurs;
    @FXML
    private int i = 0;
    @FXML
    public List<De> plateau = new ArrayList<>();
    @FXML
    public List<FACE> temp = new ArrayList<>();
    @FXML
    public List<De> tempDe = new ArrayList<>();
    @FXML
    public static final List<Joueur> joueurs = new ArrayList<>();
    @FXML
    public static final List<Joueur> listeJoueursTriee = new ArrayList<>();
    @FXML
    private Label nbCervalTour;
    @FXML
    private Label nbCervalTotal;
    @FXML
    private Label numeroJoueur;
    @FXML
    private Label affichageDeUn;
    @FXML
    private Label affichageDeDeux;
    @FXML
    private Label affichageDeTrois;
    @FXML
    private Label affichageNbTours;
    @FXML
    public De DeUn;
    @FXML
    public De DeDeux;
    @FXML
    public De DeTrois;
    @FXML
    public FACE faceDeUn;
    @FXML
    public FACE faceDeDeux;
    @FXML
    public FACE faceDeTrois;
    @FXML
    public static int actuel = 1;
    @FXML
    private int nbTours = 1;
    @FXML
    private boolean conditionJoueurGagne = false;
    @FXML
    public static Stage stage;
    @FXML
    private ImageView affichageImageDeUn = null;
    @FXML
    private ImageView affichageImageDeDeux = null;
    @FXML
    private ImageView affichageImageDeTrois = null;
    @FXML
    private Image cerveau = new Image(getClass().getResourceAsStream("image/cerveau.png"));
    @FXML
    private Image empreinte = new Image(getClass().getResourceAsStream("image/empreinte.png"));
    @FXML
    private Image fusil = new Image(getClass().getResourceAsStream("image/fusil.png"));
    @FXML
    private Image pointInterrogation = new Image(getClass().getResourceAsStream("image/pointInterrogation.png"));
    @FXML
    private boolean plusAssezDeDes = false;

    @Override
    public void start(Stage stage) throws IOException {

    }

    public static void main(String[] args) {
        launch();
    }

    public void lancerLesDesGame(ActionEvent event) throws IndexOutOfBoundsException, IOException {
        Collections.shuffle(plateau);

        try {
            if(faceDeUn != FACE.EMPREINTE) {
                DeUn = plateau.remove(0);
                tempDe.add(DeUn);
                DeUn.setFaceObtenue(faceDeUn);
            }
            if(faceDeDeux != FACE.EMPREINTE) {
                DeDeux = plateau.remove(1);
                tempDe.add(DeDeux);
                DeDeux.setFaceObtenue(faceDeDeux);
            }
            if(faceDeTrois != FACE.EMPREINTE) {
                DeTrois = plateau.remove(2);
                tempDe.add(DeTrois);
                DeTrois.setFaceObtenue(faceDeTrois);
            }
        } catch (IndexOutOfBoundsException e) {
            plusAssezDeDes = true;
            for(De de : tempDe) {
                //System.out.println("face de chaque de boucle plateau: " + de.getFaceObtenue());
                if(de.getFaceObtenue() == FACE.CERVAL) {
                    plateau.add(de);
                    //System.out.println("taille de plateau: " + plateau.size());
                }
            }
            //System.out.println("remise des DES CERVEAUX sur le plateau");
        }

        faceDeUn = DeUn.LancerLeDe();
        faceDeDeux = DeDeux.LancerLeDe();
        faceDeTrois = DeTrois.LancerLeDe();

        affichageCouleur(DeUn, affichageDeUn);
        affichageCouleur(DeDeux, affichageDeDeux);
        affichageCouleur(DeTrois, affichageDeTrois);

        ajouterValeurDes();

        //resultatDeUn.setText(faceDeUn.toString());
        //resultatDeDeux.setText(faceDeDeux.toString());
        //resultatDeTrois.setText(faceDeTrois.toString());

        affichageImageDeUn.setImage(enFonctionFace(faceDeUn));
        affichageImageDeDeux.setImage(enFonctionFace(faceDeDeux));
        affichageImageDeTrois.setImage(enFonctionFace(faceDeTrois));

        if(joueurActuel().getNbFusil() >= 3) {
            joueurActuel().setNbCerveauxTour(0);
            finDuTour(event);
        }

        nbCervalTour.setText("" + joueurActuel().getNbCerveauxTour());

        //System.out.println("Plateau: " + plateau.size());
        //System.out.println("Sur la table: " + tempDe.size());
    }

    public Image enFonctionFace(FACE face) {
        switch(face) {
            case CERVAL: return cerveau;
            case EMPREINTE: return empreinte;
            case FUSIL: return fusil;
        }
        return null;
    }

    @FXML
    public void ajouterValeurDes() {
        temp.clear();
        temp.add(0, faceDeUn);
        temp.add(1, faceDeDeux);
        temp.add(2, faceDeTrois);
        List<FACE> faces = new ArrayList<>();
        faces.add(0, FACE.CERVAL);
        faces.add(1, FACE.EMPREINTE);
        faces.add(2, FACE.FUSIL);

        for(FACE facesBases : faces) {
            for(FACE faceDe : temp) {
                setter(faceDe, facesBases);
            }
        }
    }

    public void setter(FACE faceDuDe, FACE faceAttendu) {
        switch (faceAttendu) {
            case CERVAL: if(faceDuDe == FACE.CERVAL) joueurActuel().setNbCerveauxTour(joueurActuel().getNbCerveauxTour() + 1); break;
            case EMPREINTE: if(faceDuDe == FACE.EMPREINTE) joueurActuel().setNbEmpreinte(joueurActuel().getNbEmpreinte() + 1); break;
            case FUSIL: if(faceDuDe == FACE.FUSIL) joueurActuel().setNbFusil(joueurActuel().getNbFusil() + 1); break;
        }
    }

    public void finDuTour(ActionEvent event) throws IOException {
        if(plusAssezDeDes == true) {
            //System.out.println("inside");
            for(De de : tempDe) {
                if(de.getFaceObtenue() != FACE.CERVAL) {
                    plateau.add(de);
                }
            }
        } else {
            //System.out.println("out");
            for(De de : tempDe) {
                plateau.add(de);
            }
        }
        tempDe.clear();
        plusAssezDeDes = false;

        affichageImageDeUn.setImage(pointInterrogation);
        affichageImageDeDeux.setImage(pointInterrogation);
        affichageImageDeTrois.setImage(pointInterrogation);

        joueurActuel().setNbCerveauxTotaux(joueurActuel().getNbCerveauxTotaux() + joueurActuel().getNbCerveauxTour());//changer le nombre de cerveaux totaux

        if(joueurActuel().getNbCerveauxTotaux() >= 13 || conditionJoueurGagne == true) {//termine le tour + afficher les scores
            conditionJoueurGagne = true;
            if(joueurActuel() == dernierJoueur()) {
                affichageScoreboard();
            }
        }

        if(joueurActuel() == dernierJoueur()) {//passer au tour suivant //ne bug pas
            nbTours++;
            actuel = 0;
        }
        joueurSuivant();//passer au joueur suivant

        nbCervalTour.setText("0");//afficher le nombre de cerveaux du tour a 0
        nbCervalTotal.setText("" + joueurActuel().getNbCerveauxTotaux());//afficher le nombre de cerveaux totaux
        numeroJoueur.setText("" + actuel);//afficher le numero du joueur actuel
        joueurActuel().setNbCerveauxTour(0);//mettre le nombre de cerveaux du tour a 0
        joueurActuel().setNbEmpreinte(0);
        joueurActuel().setNbFusil(0);
        affichageNbTours.setText("" + nbTours);//afficher le numero du tour
    }

    @FXML
    public void affichageScoreboard() throws IOException {
        MenuController.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("scoreboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Zombie Dice Scoreboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void joueurSuivant() {
        actuel = actuel + 1;
    }

    @FXML
    public Joueur joueurActuel() {
        return joueurs.get(actuel - 1);
    }

    @FXML
    public Joueur dernierJoueur() {
        return joueurs.get(joueurs.size() - 1);
    }

    @FXML
    public void affichageCouleur(De de, Label affichage) {
        switch (de.getCouleur()) {
            case VERT: affichage.setTextFill(Paint.valueOf("green"));
                break;
            case JAUNE: affichage.setTextFill(Paint.valueOf("yellow"));
                break;
            case ROUGE: affichage.setTextFill(Paint.valueOf("red"));
                break;
        }
    }

    @FXML
    public String getTextFromGroup(String stringbizarre) {
        switch (stringbizarre) {
            //nombre de joueurs
            case "RadioButton[id=joueurs2, styleClass=radio-button]'2'" : return "2";
            case "RadioButton[id=joueurs3, styleClass=radio-button]'3'" : return "3";
            case "RadioButton[id=joueurs4, styleClass=radio-button]'4'" : return "4";
            case "RadioButton[id=joueurs5, styleClass=radio-button]'5'" : return "5";
            //difficulte
            case "RadioButton[id=difficulteF, styleClass=radio-button]'Facile: 8 - 3 - 2'" : return "Facile: 8 - 3 - 2";
            case "RadioButton[id=difficulteM, styleClass=radio-button]'Moyen: 6 - 4 - 3'" : return "Moyen: 6 - 4 - 3";
            case "RadioButton[id=difficulteD, styleClass=radio-button]'Difficile: 4 - 5 - 4'" : return "Difficile: 4 - 5 - 4";
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueurs.clear();

        setDiffic(getTextFromGroup(MenuController.groupDifficulte.getSelectedToggle().toString()));
        setJoueurs(getTextFromGroup(MenuController.groupJoueurs.getSelectedToggle().toString()));

        numeroJoueur.setText("1");
        affichageNbTours.setText("1");
        nbCervalTour.setText("0");
        nbCervalTotal.setText("0");

        affichageImageDeUn.setImage(pointInterrogation);
        affichageImageDeDeux.setImage(pointInterrogation);
        affichageImageDeTrois.setImage(pointInterrogation);
    }

    @FXML
    public void setDiffic(String diffic) {
        switch (diffic) {
            case "Facile: 8 - 3 - 2": nbDeVert = 8; nbDeJaune = 3; nbDeRouge = 2; break;
            case "Moyen: 6 - 4 - 3": nbDeVert = 6; nbDeJaune = 4; nbDeRouge = 3; break;
            case "Difficile: 4 - 5 - 4": nbDeVert = 4; nbDeJaune = 5; nbDeRouge = 4; break;
        }

        for(i = 0; i <= nbDeVert - 1; i++) {
            plateau.add(i, new De());
        }
        for(i = nbDeVert; i <= nbDeVert + nbDeJaune - 1; i++) {
            plateau.add(i, new De());
        }
        for(i = nbDeVert + nbDeJaune; i <= nbDeVert + nbDeJaune + nbDeRouge - 1; i++) {
            plateau.add(i, new De());
        }

        for(i = 0; i <= nbDeVert - 1; i++) {
            (plateau.get(i)).setFace(COULEUR.VERT);
        }
        for(i = nbDeVert; i <= nbDeVert + nbDeJaune - 1; i++) {
            (plateau.get(i)).setFace(COULEUR.JAUNE);
        }
        for(i = nbDeVert + nbDeJaune; i <= nbDeVert + nbDeJaune + nbDeRouge - 1; i++) {
            (plateau.get(i)).setFace(COULEUR.ROUGE);
        }
    }

    public void setJoueurs(String nbrJoueurs) {
        switch (nbrJoueurs) {
            case "2": nbJoueurs = 2; break;
            case "3": nbJoueurs = 3; break;
            case "4": nbJoueurs = 4; break;
            case "5": nbJoueurs = 5; break;
        }

        for(i = 0; i < nbJoueurs; i++) {
            Joueur player = new Joueur(i);
            joueurs.add(i, player);
        }
        //System.out.println(joueurs.size());
    }
}
