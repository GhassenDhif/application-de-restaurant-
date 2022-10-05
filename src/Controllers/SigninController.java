/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Interfaces.mysqlconnect;
import Services.ServiceUtilisateur;
import utils.JavaMailUtil;

import service.RestauService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;
import gui.PartenaireController;

/**
 *
 * @author GhAlone
 */


public class SigninController implements Initializable{
    public static int idu;
    public  static String PHOTOU;
    
       @FXML
    private TextField email;
       
    @FXML
    private Button button;

    @FXML
    private Label link;

    @FXML
    private Label account;

    @FXML
    private ComboBox type;

    @FXML
    private ImageView cancel;
    
   
    
     @FXML
    private PasswordField ppasse;

    @FXML
    private CheckBox sheck;
    @FXML
    private TextField txt_pass;
    public String myid ;
    ServiceUtilisateur u = new ServiceUtilisateur();
        
        Connection conn = null;
        ResultSet rs =null;
        PreparedStatement pst = null;
PartenaireController p = new PartenaireController();
   RestauService r =new RestauService();
       
     @FXML
    private void Login (ActionEvent event) throws Exception{   
        conn= mysqlconnect.ConnectDb();
        String ch;
        String id;
        String sql = "Select * from user where email = ? and roles = ?";    
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, email.getText());
            
            pst.setString(2, type.getValue().toString());
            rs = pst.executeQuery();
           
            if(rs.next() && BCrypt.checkpw(ppasse.getText(), rs.getString("password"))){
                myid = rs.getString("id");
                r.welcome(myid);
                
                p.myid(myid);
                JOptionPane.showMessageDialog(null, "Usermane and Password is Correct");        
                if(type.getValue().equals("[\"ROLE_ADMIN\"]")){
                idu=rs.getInt("id");
                PHOTOU=rs.getString("image");
                Parent root =FXMLLoader.load(getClass().getResource("/Interfaces/Acceuil.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                   
                }else if(type.getValue().equals("[\"ROLE_USER\"]")){
                    idu=rs.getInt("id");
                    PHOTOU=rs.getString("image");
                Parent root =FXMLLoader.load(getClass().getResource("/Interfaces/AceuilUser.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                }else{
                    idu=rs.getInt("id");
                    PHOTOU=rs.getString("image");
                Parent root =FXMLLoader.load(getClass().getResource("/Interfaces/AcceuilPartenaire.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();  
                        }
            }else
                JOptionPane.showMessageDialog(null, "invalide Username or Password");
        }catch(Exception ex){
                System.out.println(ex.getMessage());
        }
         clear();
          System.out.println(u.afficher());
    }
    
    @FXML
    private void exit(MouseEvent event) {
        r.logout(myid);
     System.exit(0);
    }

    
   @FXML
    private void signup(MouseEvent event) {
                                try {
                Parent root =FXMLLoader.load(getClass().getResource("/Interfaces/Sign_up.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
            }
    }
    
   
    
    @FXML 
    private void signup(ActionEvent event){
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("[\"ROLE_ADMIN\"]","[\"ROLE_USER\"]","[\"ROLE_PARTENAIRE\"]");
               //aff mdp
       txt_pass.setVisible(false);
       sheck.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_value, Boolean newValue) -> {
       if(sheck.isSelected()){
           txt_pass.setText(ppasse.getText());
           txt_pass.setVisible(true);
           ppasse.setVisible(false);
           return;
           
       }
      ppasse.setText(txt_pass.getText());
       ppasse.setVisible(true);
       txt_pass.setVisible(false);
       
       });
       //end aff mdp
       
       //mdp oublié
         link.setOnMouseClicked(event -> {
               ServiceUtilisateur op= new ServiceUtilisateur();
               String o = op.recEmail(email.getText());
               int d= op.recId(email.getText());
               System.out.println(o+"  "+ d);
             try {
                 
                 JavaMailUtil.sendMail(o,d);
             } catch (Exception ex) {
                 System.out.println("error");
             }
               System.out.println(o);
              try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/codepasse.fxml"));
        
            Parent root = loader.load();
              PasseController cont = loader.getController();
            cont.setEmail(o);
            cont.setCode(op.recCode(d));
            cont.setId(d);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Mot de passe oublié ?");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      
        });
      //end
    }

    private void clear(){
        
        email.setText("");
        ppasse.setText("");
    }
    
    }