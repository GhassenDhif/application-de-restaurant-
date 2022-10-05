/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import Entities.*;
import service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLAffichePanierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Panieraffiche> tableview;
    
    @FXML
    private TableColumn<Panieraffiche,String> ids;
    
    
    @FXML
    private TableColumn<Panieraffiche,String> prixs;
    
    @FXML
    private TableColumn<Panieraffiche,String> clientids;
    
       @FXML
    public TextField prixField;
    
    
    
    @FXML
    private Button deleteButton;
    
    public Panierservice servicePanier = new Panierservice();
    @FXML
    private Button ajouterPanier;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableview.setEditable(true);
        ObservableList<Panieraffiche> data = FXCollections.observableArrayList(servicePanier.readForTableview());
        ids.setCellValueFactory(new PropertyValueFactory<Panieraffiche,String>("id"));
        prixs.setCellValueFactory(new PropertyValueFactory<Panieraffiche,String>("prix"));
         prixs.setCellFactory(TextFieldTableCell.forTableColumn());
        clientids.setCellValueFactory(new PropertyValueFactory<Panieraffiche,String>("clientid"));
       
        
        prixs.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Panieraffiche, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Panieraffiche, String> t) {
                    ((Panieraffiche) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPrix(t.getNewValue());
                    Panieraffiche c = tableview.getSelectionModel().getSelectedItem();
                    servicePanier.updateperIds(Integer.parseInt(c.getId()),Double.parseDouble(c.getPrix()),Integer.parseInt(c.getClientid()));

                }
            }
        );
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(ids,prixs,clientids);
    }    
    
    @FXML
    public void supprimerPanier(){
        int id=Integer.parseInt(tableview.getSelectionModel().getSelectedItem().getId());
        servicePanier.delete(id);
        System.out.println("commande supprimé");
        refreshTableView();
    }
    
      @FXML 
    public void ajouterPanier(ActionEvent event)throws  IOException{
        Panier c = new Panier();
        c.setPrix(Double.parseDouble(prixField.getText()));
        
        Panierservice cs = new Panierservice();
        
        cs.insert(c);
        System.out.println("panier ajouté");
        refreshTableView();
    }
    
    public  void refreshTableView() {        
        tableview.getColumns().clear();
        ObservableList<Panieraffiche> data = FXCollections.observableArrayList(servicePanier.readForTableview());
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(ids,prixs,clientids);

}
    
}
