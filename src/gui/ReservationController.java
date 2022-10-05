/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.reservationService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReservationController implements Initializable {

    private Label tfNom;
    @FXML
    private TableView<?> aff_reservation;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label tfchercher;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableColumn<?, ?> tfid;
    @FXML
    private TableColumn<?, ?> tfdate;
    @FXML
    private TableColumn<?, ?> tfdes;
    @FXML
    private TextField tfid1;
    @FXML
    private TextField tfdate1;
    @FXML
    private TextField tfdes1;

    private reservationService reservationService;
    int index=-1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.reservationService = new reservationService();
        afficher();
    }    
    public void afficher(){

        ObservableList optionListe = FXCollections.observableList(reservationService.read());

        tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tfdes.setCellValueFactory(new PropertyValueFactory<>("des"));
        
        aff_reservation.setItems(optionListe);      
    }
    
    public void clear(){
        tfid1.clear();
        tfdate1.clear();
        tfdes1.clear();   
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = aff_reservation.getSelectionModel().getSelectedIndex();
        if (index<= -1){
            return;
        }
        tfid1.setText(String.valueOf(tfid.getCellData(index).toString()));
        tfdate1.setText(String.valueOf(tfdate.getCellData(index).toString()));
        tfdes1.setText(String.valueOf(tfdes.getCellData(index).toString()));      
        afficher();
    }

    @FXML
    private void onClick2(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("suppoption.fxml"));
            try {
            Parent root = loader.load();
            SuppoptionController controller = loader.getController();
            tfNom.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void retour(ActionEvent event) {
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("gestion_evenement1.fxml"));
            Parent root = loader.load();
      //      Gestion_evenement1Controller controller = loader.getController();
            tfdate1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        if(!tfrechercher.getText().isEmpty()){

      ObservableList reservationListe = FXCollections.observableArrayList(reservationService.Rechercher(tfrechercher.getText()));

        tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tfdes.setCellValueFactory(new PropertyValueFactory<>("des"));
            aff_reservation.getItems().clear();
            aff_reservation.setItems(reservationListe);
        }else 
        afficher();
    }

    @FXML
    private void onClick1(ActionEvent event) {
        int id = Integer.parseInt(tfid1.getText());
        String date = tfdate1.getText();
        String des = tfdes1.getText();
        reservation res = new reservation(id,date,des);
        reservationService.inserReservPst(res);
        afficher();
        clear();
    }

    @FXML
    private void onClick3(ActionEvent event) {
        int id = Integer.parseInt(tfid1.getText());
        String date = tfdate1.getText();
        String des = tfdes1.getText();
        reservationService.update(id,date,des);
        afficher();
        clear();
    }
    
}
