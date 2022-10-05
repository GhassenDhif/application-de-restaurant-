/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.evenementService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Supp_evenementController implements Initializable {

    @FXML
    private Label tfid;
    @FXML
    private TextField tfId;
    @FXML
    private Button btnsupp;
    
    private evenementService evenementService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.evenementService = new evenementService();
    }    

    @FXML
    private void onSupp(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        //evenement e = new evenement(id);
         
        try {
             evenementService.delete(id);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("gestion_evenement1.fxml"));
            Parent root = loader.load();
        //    Gestion_evenement1Controller controller = loader.getController();
            tfid.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
