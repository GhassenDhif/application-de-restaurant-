/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateur;
import Services.ServiceAdmin;
import Services.ServiceUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author GhAlone
 */
public class ProfilePartenaireController  implements Initializable{
    private Pane acpane;

    @FXML
    private Button bt_modifier;

    @FXML
    private Button bt_img;

    private TextField txt_nom;

    private TextField txt_prenom;

    private TextField txt_email;

    private TextField txt_num;

    private TextField txt_genre;
    
    @FXML
    private ImageView imageView;
    
      @FXML
    private Label lid;

    
    private File selectedFile;

    ServiceUtilisateur userser = new ServiceUtilisateur();
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

    
   
  
    
    
    
    public void setuser(Utilisateur user){
    txt_nom.setText(user.getNom());
    txt_prenom.setText(user.getPrenom());
    txt_email.setText(user.getAdress_email());
    txt_num.setText(valueOf(user.getNum_tel()));
    txt_genre.setText(user.getGenre());
    lid.setText(valueOf(user.getId()));


    }
    
    public ImageView getim(){
    return imageView;
    }
    
    @FXML
     public void onChoseFile(ActionEvent event){
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Hey");
        }
    }
     
     
     @FXML
    void modifier(ActionEvent event) {
        Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get("C:\\Users\\GhAlone\\Documents\\NetBeansProjects\\MainJavaFX\\src\\Images/"+selectedFile.getName());
        Utilisateur upuser = new Utilisateur( txt_nom.getText(), txt_prenom.getText(), Integer.parseInt(txt_num.getText()), txt_genre.getText(), txt_email.getText(), to.normalize().toString());
        userser.profil(upuser);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("modification avec succés!");
        alert.show();
    }
    
    
    


    
    
    void changeUser(ActionEvent event) throws IOException {
        Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/modifierUsers.fxml"));
        acpane.getChildren().add(p);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        System.out.println(lid.getText());
    }
}
