/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Commande;
import Entities.Commandeaffiche;

import service.Commandeservice;
import utils.Datasource;
import views.FXMLAfficheController;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
   
     private Connection con ;
        private Statement ste;
            private PreparedStatement pst;
             private ResultSet rs;
      @FXML
    private Label label;
  
   @FXML
    private TextField idlabel;
   
   /* 
    @FXML
    private TextField titrelabel;
   */
  /*  @FXML
    private TextField datelabel;
    
    @FXML
    private TextField panierlabel;
    */
    @FXML
    private Button afficheCommandes;
    
    @FXML
    private Button ajouterCommande;
    
    @FXML
    private Button commandeClient;
    
    @FXML
    private Button ajouterPanier;
    
    
    @FXML
    private Button updateCommande;
     
    //private Statement ste;
      
    @FXML
    private Button afficherPanier;
    
       
    @FXML
    private Button modifierPanier;
    
      @FXML
    private Button chercherCommande;
      
      @FXML
    private Button PDF;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
       // Button button = (Button)event.getSource();
       // texto.appendText(button.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void loadCommandes(ActionEvent event) throws IOException{
        afficheCommandes.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLAffiche.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    @FXML
    public void ajoutCommande(ActionEvent event) throws IOException{
        ajouterCommande.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLajoutCommande.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
  
    
      @FXML
    public void afficherPanier(ActionEvent event) throws IOException{
        afficherPanier.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLAffichePanier.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
     @FXML
    public void ajoutPanier(ActionEvent event) throws IOException{
        ajouterPanier.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLajoutPanier.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
        @FXML
    public void updateCommande(ActionEvent event) throws IOException{
        updateCommande.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLmodifierCommande.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
        @FXML
    public void commandeClient(ActionEvent event) throws IOException{
        commandeClient.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLclientcommande.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
         @FXML
    public void modifierPanier(ActionEvent event) throws IOException{
        modifierPanier.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLmodifierPanier.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
         @FXML
    public void chercherCommande(ActionEvent event) throws IOException{
        chercherCommande.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLchercherCommande.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
    
          @FXML
    public void PDF(ActionEvent event) throws IOException{
        PDF.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLcreatePDF.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }
  /*@FXML
    private void deleteCommande(ActionEvent ae) {

    String sql = "DELETE FROM commande WHERE id=?";
      try {
                             
       PreparedStatement pstmt = con.prepareStatement(sql);
         
            pstmt.setInt(1,Integer.parseInt(idlabel.getText()));
            pstmt.executeQuery();
         
        
        } catch (SQLException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }*/
   

    
  
/* @FXML
    private void deleteCommande(ActionEvent event) throws IOException{
        ajouterCommande.getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("views/FXMLAffiche.fxml"));
                Stage mainstage=new Stage();
                Scene scene=new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();
    }*/
  
    /* @FXML
    private void  deleteCommande (MouseEvent event) throws SQLException, IOException  {
         Commandeservice off = new Commandeservice();
            Commande o = new Commande();
            String tTitre = titrelabel.getText();
           
       String tdate = datelabel.getText();
           int tpanier = Integer.parseInt(panierlabel.getText());}*/
    
           
    /*      public void deleteCommande(ActionEvent ae) throws IOException{
		String sql="delete from employee where titre=?";	
               try {
                                PreparedStatement pstmt = con.prepareStatement(sql);
            
                                  pstmt.executeUpdate(sql);
                           
				
				
				pstmt.setString(1, (titrelabel.getText()));
				rs=pstmt.executeQuery();
				if(rs!=null){
					lavel.setText("Record deleted ");
				}else{
					lavel.setText("please check employee id");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void update(ActionEvent ae) throws IOException{
			Stage primaryStage= new Stage();
			Parent root =FXMLLoader.load(getClass().getResource("/application/Update.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource(arg0))
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
}
       Connection con= Datasource.getInstance().getCnx();
             ste=con.createStatement();
              
           
           Statement ste = con.createStatement();
            ste=con.createStatement();
              
           ResultSet rs=ste.executeQuery("SELECT `id` FROM `categorie` WHERE `nom`='"+cat+"'");
             if(rs.next()){
                      o.setId(rs.getInt(1));
             }
             ste.executeUpdate(rs);  
                     
                      
          o.setTitre(tTitre);
           o.setDate_de_commande(tdate);
           o.setPanierFK(tpanier);
       
           off.insert(o);
            
            
    }*
    
   /* private void ajouter(MouseEvent event) throws SQLException, IOException {
        Commandeservice off = new Commandeservice();
            Commande o = new Commande();
            String tTitre = titrelabel.getText();
           
           String tdate = datelabel.getText();
           int tpanier = Integer.parseInt(panierlabel.getText());
           
         // Connection con=Datasource.getInstance().getCnx();
         //  Statement ste = con.createStatement();
          /* ResultSet rs=ste.executeQuery("SELECT `id` FROM `categorie` WHERE `nom`='"+tTitre+"'");
             if(rs.next()){
                      o.setId(rs.getInt(1));
             }
                        
                        
           o.setTitre(tTitre);
           o.setDate_de_commande(tdate);
           o.setPanierFK(tpanier);
       
           off.insert(o);
    }*/
    
}
