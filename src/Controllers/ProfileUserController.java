/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author GhAlone
 */
public class ProfileUserController {
    private Pane acpane;
    @FXML
    private Button bt_modifier;
    @FXML
    private ImageView imageView;
    @FXML
    private Button bt_img;
    @FXML
    private Label lid;
    @FXML
    private Label Lnom;
    @FXML
    private Label Lprenom;
    @FXML
    private Label LAdress;
    @FXML
    private Label Lnum;
    @FXML
    private Label Lgenre;
    @FXML
    private ImageView act;

    
    
    void changeUser(ActionEvent event) throws IOException {
        Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/modifierUsers.fxml"));
        acpane.getChildren().add(p);

    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void onChoseFile(ActionEvent event) {
    }

    @FXML
    private void reload(MouseEvent event) {
    }
}
