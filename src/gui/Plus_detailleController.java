/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Alert;
import gui.PartenaireController;
import gui.CategorieController;
import service.RestauService;
import service.CatService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


/**
 * FXML Controller class
 *
 * @author amed1
 */
public class Plus_detailleController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField tnom;
    @FXML
    private JFXTextField tut;
    @FXML
    private JFXTextField tcat;
    @FXML
    private JFXTextField tdesc;
    @FXML
    private JFXTextField tlocal;
    @FXML
    private JFXTextField tdate;
    @FXML
    private ImageView im;
    @FXML
    private ImageView qr;
    @FXML
    private JFXButton exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
   // qr.setImage(new Image("image/ammmm.png"));
    public void setTfNom (String value) {
        this.tnom.setText(value);
    }
    public void setTfdate(String value){
        this.tdate.setText(value);
   }

    public void setTfLocal(String value) {
        this.tlocal.setText(value);
    }

    public void setTfDescr(String value) {
        this.tdesc.setText(value);
    }
    public void setTfcat (String value){
        this.tcat.setText(value);
    }
    
     public void setTimage (javafx.scene.image.Image value){
        this.im.setImage(value);
    }
     
     public void Setqr (javafx.scene.image.Image value){
           
         //this.qr.setImage(g);
     
     


     } 
    @FXML
    private void back(ActionEvent event) throws IOException{
       
          
          Parent root =FXMLLoader.load(getClass().getResource("/gui/partenaire.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
          
        
      
 
        
    }

    @FXML
    private void close(ActionEvent event) {
        
        System.exit(0);
    }
    
}
