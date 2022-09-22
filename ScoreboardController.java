package com.example.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardController extends Application implements Initializable {
    @FXML
    private TableColumn<Joueur, Integer> nbCerveauxScoreboard;
    @FXML
    private TableColumn<Joueur, Integer> numJoueursScoreboard;
    @FXML
    private TableView<Joueur> tableScoreboard;
    @FXML
    public static Stage stage;

    ObservableList<Joueur> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Joueur player : GameController.joueurs) {
            list.add(player);
        }

        numJoueursScoreboard.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("numero"));
        nbCerveauxScoreboard.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("nbCerveauxTotaux"));

        tableScoreboard.setItems(list);
    }

    public void retourAuMenu(ActionEvent event) throws IOException {
        GameController.stage.close();
        Menu.stage.show();//fonctionne mais ne reinitialise rien
    }

    @Override
    public void start(Stage stage) throws IOException {

    }


}
