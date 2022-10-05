/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import models.Categorie;
import models.Menu;
import servicee.ServiceCat;
import servicee.ServiceMenu;
import utils.Datasource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CatController implements Initializable {

    @FXML
    private TableColumn<?, ?> C1;
    @FXML
    private TableColumn<?, ?> C2;
    @FXML
    private Button cc;
    
    
    ObservableList<Categorie> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Categorie> tablepl;
    @FXML
    private TextField nom;
    @FXML
    private TextField id;
    @FXML
    private Button cc1;
    @FXML
    private Button cc11;
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    @FXML
    private AnchorPane recpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
        
    }    
   public void initialiserlist() throws SQLException {
        // TODO
             
          
              try {
            Connection cnx = Datasource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM cate");
            while(rs.next()){
              
              
            list.add(new Categorie(rs.getInt(1),rs.getString(2)));    }
            } catch (SQLException ex) {
            Logger.getLogger(gui.CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
          
           
        
    } 
     private void afficher(){
          C1.setCellValueFactory(new PropertyValueFactory<>("id"));
          C2.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
         
                    
            
        tablepl.setItems(list);
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        ServiceCat c = new ServiceCat();
        c.ajouter(new Categorie(nom.getText()));
           list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
    }

   public Categorie gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Categorie test = (Categorie) tablepl.getSelectionModel().getSelectedItem();
        return test;
    }
  

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = tablepl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    } 
    nom.setText(C2.getCellData(index).toString());
    
    id.setText(C2.getCellData(index).toString());
   
 
    
    }

    @FXML
    private void supprimerrc(ActionEvent event) throws SQLException {
           TableColumn.CellEditEvent edittedcell = null;
        Categorie x = gettempReclamation(edittedcell);

        if (x != null) {

           String i = x.getNom();
            ServiceCat cat = new ServiceCat();

            int s = cat.delete(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("categorie deleted");
                alert.showAndWait();
                  list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
               
               
 
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
    }

    @FXML
    private void Edit(ActionEvent event) {
         try {
             ResultSet rs6 = null;
                 
        Connection cnx = Datasource.getInstance().getCnx();
      
            String value0 = id.getText();
            String value1 = nom.getText();
            
            
          
            
         
            String sql = "update categorie set nom= '"+value1+"'where nom='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            
                list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh();
            JOptionPane.showMessageDialog(null, "Update");
           
             list.clear();
                initialiserlist(); 
                afficher();
                tablepl.refresh(); 
    
   
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    @FXML
    private void Plats(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("AddMenu.fxml"));
           recpane.getChildren().setAll(pane);
    }
    
}
