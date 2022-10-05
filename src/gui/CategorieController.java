/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.stage.Stage;
import utils.Datasource;
import service.SoundClick;
import service.SMS;
import service.Service_mail;
import service.Service_generer_Qr_code;
import service.Service_notif;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import service.CatService;
import entity.Categorie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author amed1
 */
public class CategorieController implements Initializable {

    CatService cs =new CatService();
     PreparedStatement pst;
      private ResultSet rs;
      private Connection conn;  
    String elpath;
    
    @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<?, ?> c1; 
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    private JFXTextField txt2;
    @FXML
    private TextField rech;
    
    Service_notif srvnot =new Service_notif();
    Service_generer_Qr_code srvqr = new Service_generer_Qr_code();
    Service_mail srvmail = new Service_mail();
    pdf pds = new pdf();
    SoundClick sc =new SoundClick();
    @FXML
    private JFXButton tri;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView img;
    
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
        
       
        
    }    

    @FXML
    private void go_table(MouseEvent event) {
       String type=(c2.getCellData(table.getSelectionModel().getSelectedIndex()).toString());
       txt2.setText(type);
       
    }

    @FXML
    private void submit(ActionEvent event)  throws AWTException, SQLException, FileNotFoundException, DocumentException {
        
         SMS srvss=new SMS();
        Categorie c =new Categorie(txt2.getText());
      
        if(testType())
        {
        cs.insert(c);
        sc.playClick();

      //  sc.playClick();
     
         //sc.playClick();
        srvnot.notif("catégorie ajouter ");
        srvqr.create(txt2.getText());
        srvmail.send_mail("mohamedamine.eloudi@esprit.tn ", txt2.getText());
       srvss.SendSMS("WMS", "Categorie Passé avec succes", "21651833422");
         //sc.playClick();
        afficher();
        pds.add("c:");
        clear();
        }
        //thread
        
    }
    
    public void afficher(){

        ObservableList obeListe = FXCollections.observableList(cs.read());

        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        table.setItems(obeListe);
        //c1.setVisible(false);
        
    }

    @FXML
    private void delete(ActionEvent event) {
        
          int k=Integer.valueOf(c1.getCellData(table.getSelectionModel().getSelectedIndex()).toString());
       
        try {
           
        
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette Categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
           cs.supprimerr(k);
           srvnot.notif("supprimer");
            }
                 
           
        } catch (AWTException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
        clear();
        
    }
    
    private void clear(){
    
        txt2.clear();
    
    }

    @FXML
    private void update(ActionEvent event) {
       
        try {
             String t1 =txt2.getText();
        int k =Integer.valueOf(c1.getCellData(table.getSelectionModel().getSelectedIndex()).toString());
        cs.modifierbs(k, t1);
            srvnot.notif("modifier");
        } catch (AWTException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
        clear();
    }

    @FXML
    private void tri(ActionEvent event) {
        
        
        ObservableList tr = FXCollections.observableList(cs.readBytype());

        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        table.setItems(tr); 
        //c1.setVisible(false);
        
    }

    @FXML
    private void ChercherCentreParType(KeyEvent event) {
        if(!rech.getText().isEmpty())
        {
     ObservableList <Categorie> g = FXCollections.observableArrayList();
           g.addAll(cs.ChercherCentreParType(rech.getText()));
           table.getItems().clear();
           table.setItems(g);
        }else 
            afficher();
        
    }

    
/*
    private void stati(ActionEvent event) {
        
    
          String req = "SELECT id, type FROM categorie ORDER BY type ASC";
        XYChart.Series<Integer,String> series = new XYChart.Series<>();
        
        try {
              pst = conn.prepareStatement(req);
         
           
            rs= pst.executeQuery(req);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>( rs.getInt(1), rs.getInt(2) + "categorie"));
            }
            barchart.getData().add(series);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
*/
/*
    private void go(ActionEvent event) throws IOException{
        
      
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("partenaire.fxml"));
        //root = loader.load();
           
       
         
          
          
          
          Parent root =FXMLLoader.load(getClass().getResource("partenaire.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
          
        
      
 
      
        
        
    }
    */
      
    
    private Boolean testType() {
        String erreur;
        int nbNonChar = 0;
        for (int i = 1; i < txt2.getText().trim().length(); i++) {
            char ch = txt2.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txt2.getText().trim().length() >= 3) {
            img.setImage(new Image("image/checkMark.png"));
            return true;
        } else {
            img.setImage(new Image("image/erreurCheckMark.png"));
            erreur ="il faut saisir au moins 3 charctére\n";
            return false;

        }

    }
    
/*
    private void close(ActionEvent event) {
        System.exit(0);
    }
           
    
   */ 
    
    }
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

