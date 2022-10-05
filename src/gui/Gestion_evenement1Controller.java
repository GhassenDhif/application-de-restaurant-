/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.evenementService;
import service.reservationService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Gestion_evenement1Controller implements Initializable {

    private Label tfNom;
    @FXML
    private TextField tfid1;
    @FXML
    private TextField tfnom1;
    @FXML
    private TextField tfimage1;
    @FXML
    private TextField tfdate1;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private TableView<?> aff_evenement;
    @FXML
    private TableColumn<?,?> tfid;
    @FXML
    private TableColumn<?,?> tfdate;
    @FXML
    private TableColumn<?,?> tfnom;
    @FXML
    private TableColumn<?, ?> tfimage;
    @FXML
    private Button btnsuppr;
    @FXML
    private TextField tfchercher;
    @FXML
    private Label tfrecherche;
    private evenementService evenementService;
    private reservationService reservationService;
    int index=-1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.evenementService = new evenementService();
        this.reservationService = new reservationService();
     afficher();
    }    

    public void afficher(){

        ObservableList evenementListe = FXCollections.observableList(evenementService.read());

        tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tfimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        aff_evenement.setItems(evenementListe);
    }
    
    public void clear(){
        tfid1.clear();
        tfnom1.clear();
        tfdate1.clear();
        tfimage1.clear(); 
    }
    
    @FXML
    private void onClick1(ActionEvent event) {
         int id = Integer.parseInt(tfid1.getText());
        String nom = tfnom1.getText();
        String date = tfdate1.getText();
        String image = tfimage1.getText();
        evenement e = new evenement(nom,date,image);
        evenementService.inserEvenementPst(e);
        //mailutil.send_mail("raoudha.bettaibi@esprit.tn ", tfNom.getText());
        afficher();
        clear();
        
    }

    @FXML
    private void onClick2(ActionEvent event) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("supp_evenement.fxml"));
            try {
            Parent root = loader.load();
            Supp_evenementController controller = loader.getController();
            tfNom.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        afficher();
        clear();
   
    }

    @FXML
    private void  onClick3(ActionEvent event)   {
        
        try{
          String n = tfnom1.getText();
          String d = tfdate1.getText();
          String i= tfimage1.getText();
          int id = Integer.parseInt(tfid1.getText());
          evenementService.update(id,n,d,i);
          afficher(); 
        }
        catch(Exception ex){
        System.out.println(ex.getMessage());
        }
        afficher();
        clear();
}

    private void onCreate(ActionEvent event) {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        try {
            Parent root = loader.load();
            ReservationController controller = loader.getController();
            tfid1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        afficher();
        clear();
    }
    
    @FXML
    void getSelected (MouseEvent event){
        
        int index = aff_evenement.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        tfid1.setText(tfid.getCellData(index).toString());
        tfdate1.setText(tfdate.getCellData(index).toString());
        tfnom1.setText(tfnom.getCellData(index).toString());
        tfimage1.setText(tfimage.getCellData(index).toString());

    }

    @FXML
    private void Rechercher(ActionEvent event) {
    if(tfchercher.getText().trim().length() > 0){
 ObservableList evenementListe = FXCollections.observableArrayList(evenementService.Rechercher(tfchercher.getText()));

        tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfimage.setCellValueFactory(new PropertyValueFactory<>("image"));

            aff_evenement.getItems().clear();
            aff_evenement.setItems(evenementListe);
        }else 
        afficher();
    }
    

    @FXML
    private void submit(TableColumn.CellEditEvent<?,?> event) {
       ObservableList evenementListe = FXCollections.observableList(evenementService.read());

        tfid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tfimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        
      afficher();
      clear();
    }
   
}
