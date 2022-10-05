/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.RestauService;


/**
 * FXML Controller class
 *
 * @author amed1
 */
public class PartenaireaddRController implements Initializable {
    @FXML
    private BorderPane Bpadmin;
    @FXML
    private Button gererPro;
    @FXML
    private Button gererRes;
    @FXML
    private Button gerer;
    @FXML
    private Button GererPub;
    @FXML
    private Button GererCat;
    @FXML
    private AnchorPane contentArray;
    @FXML
    private Pane acpane;
    @FXML
    private Label l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeUser(ActionEvent event) throws IOException {
         Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/profilePar.fxml"));
      acpane.getChildren().clear();

        acpane.getChildren().add(p);

    }

    @FXML
    private void addR(ActionEvent event)  throws IOException {
         Pane p = FXMLLoader.load(getClass().getResource("/gui/partenaire.fxml"));
 acpane.getChildren().clear();
        acpane.getChildren().add(p);
        
    }

    @FXML
    private void addEvent(ActionEvent event) throws IOException  {
     Pane p = FXMLLoader.load(getClass().getResource("/gui/gestion_evenement1.fxml"));
 acpane.getChildren().clear();
        acpane.getChildren().add(p);
    }
    
}


