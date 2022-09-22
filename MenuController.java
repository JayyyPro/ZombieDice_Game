package com.example.main;

import com.example.main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private RadioButton joueurs2;
    @FXML
    private RadioButton joueurs3;
    @FXML
    private RadioButton joueurs4;
    @FXML
    private RadioButton joueurs5;
    @FXML
    public static ToggleGroup groupJoueurs = new ToggleGroup();
    @FXML
    private RadioButton difficulteF;
    @FXML
    private RadioButton difficulteM;
    @FXML
    private RadioButton difficulteD;
    @FXML
    public static ToggleGroup groupDifficulte = new ToggleGroup();
    @FXML
    public static Stage stage;

    @FXML
    public void onJouerButtonClick(ActionEvent event) throws IOException {
        Menu.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Menu.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.setTitle("Zombie Dice Game");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        joueurs2.setToggleGroup(groupJoueurs);
        joueurs3.setToggleGroup(groupJoueurs);
        joueurs4.setToggleGroup(groupJoueurs);
        joueurs5.setToggleGroup(groupJoueurs);

        difficulteF.setToggleGroup(groupDifficulte);
        difficulteM.setToggleGroup(groupDifficulte);
        difficulteD.setToggleGroup(groupDifficulte);
    }
}