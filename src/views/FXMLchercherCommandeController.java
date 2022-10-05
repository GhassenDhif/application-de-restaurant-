/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Commandeaffiche;
import service.Commandeservice;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLchercherCommandeController implements Initializable {

     
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





    @FXML
    private TableView<Commandeaffiche> tableview;
    
    @FXML
    private TableColumn<Commandeaffiche,String> idq;
    
    
    @FXML
    private TableColumn<Commandeaffiche,String> titreq;
    
    @FXML
    private TableColumn<Commandeaffiche,String> dateq;
    
     @FXML
    private TableColumn<Commandeaffiche,String> prixq;
    
    @FXML
    private TableColumn<Commandeaffiche,String> panierFKq;
    
        @FXML
    public TextField idr;
        
   
    
    @FXML 
    public Button modifierCommande;
    
    
  /*  @FXML
    private Button deleteButton;*/
    
    public Commandeservice serviceCommande = new Commandeservice();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Commandeaffiche> data = FXCollections.observableArrayList(serviceCommande.readForTableview());
        idq.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("id"));
        titreq.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("titre"));
        dateq.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("date_de_commande"));
        prixq.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("prix"));
        panierFKq.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("panierFK"));
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(idq,titreq,dateq,prixq,panierFKq);
    } 
    
      @FXML
   public void chercherCommande(ActionEvent event)throws  IOException {
    
   
     
             int value1 = Integer.parseInt(idr.getText());
               serviceCommande.RechercheCommandei(value1) ;
        
   }
   
       
    
   /* public  void refreshTableView() {        
        tableview.getColumns().clear();
        ObservableList<Commandeaffiche> data = FXCollections.observableArrayList(serviceCommande.readForTableview());
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(idq,titreq,dateq,panierFKq);

}*/
    
}

