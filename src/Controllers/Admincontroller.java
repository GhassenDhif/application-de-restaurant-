/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.RestauService;
/**
 *
 * @author GhAlone
 */
public class Admincontroller {
    
    @FXML
    private Pane acpane;
    @FXML
    private BorderPane Bpadmin;
    @FXML
    private Button c;
    @FXML
    private Button ca;
   
RestauService r =new RestauService();
    @FXML
    private AnchorPane am;
    
    
    @FXML
    void changeUser(ActionEvent event) throws IOException {
        Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/modifierUsers.fxml"));
           acpane.getChildren().clear();
     
        acpane.getChildren().add(p);

    }

    @FXML
    
    private void addcateg(ActionEvent event)  throws IOException {
    Pane p = FXMLLoader.load(getClass().getResource("/gui/categorie.fxml"));
       acpane.getChildren().clear();
     
        acpane.getChildren().add(p);

    }

    @FXML
    private void addmenu(ActionEvent event) throws IOException{
         Pane p = FXMLLoader.load(getClass().getResource("/gui/cat.fxml"));
         acpane.getChildren().clear();
        acpane.getChildren().add(p);
    }
    
       @FXML
    void afficherC(ActionEvent event)  throws IOException{
             Pane p = FXMLLoader.load(getClass().getResource("/views/FXMLAffiche.fxml"));
                acpane.getChildren().clear();
            
        acpane.getChildren().add(p);
    }
     
    @FXML
    void ajouterpanier(ActionEvent event) throws IOException{
         Pane p = FXMLLoader.load(getClass().getResource("/views/FXMLAffichePanier.fxml"));
            acpane.getChildren().clear();
        
        acpane.getChildren().add(p);
    }

    @FXML
    private void sta(ActionEvent event)  throws IOException{
        Parent root =FXMLLoader.load(getClass().getResource("/gui/stat.fxml"));    
           acpane.getChildren().clear();
        
                Stage stage = new Stage();
             Scene scene = new Scene(new Group());
        

        
        
        stage.setTitle("statistique sur les Restaurants");
        stage.setWidth(500);
        stage.setHeight(500);
 

                        ObservableList obeListe = FXCollections.observableList(r.stat());

       
        
        final PieChart chart = new PieChart(obeListe);
        chart.setTitle("statistique sur les Restaurants");
        
        int all = r.kolfelkol();
        
        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : chart.getData()) {
        data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                System.out.println(String.valueOf(data.getPieValue()*100/all %.2f) + "%");
                float amine = (float) (data.getPieValue()*100/all);
                caption.setText(String.valueOf(new DecimalFormat("##.##").format(amine)) + "%");
             }
        });
}

        ((Group) scene.getRoot()).getChildren().addAll(chart,caption);
        stage.setScene(scene);
   
                            stage.show();

          
            
           // javafx.scene.image.Image j =new javafx.scene.image.Image("C:\\QRCODE\\"+nom+".png");
          //  Image jj =new Image(getClass().getResourceAsStream("C:\\QRCODE\\"+nom+".png"));
            
       
        
        
    }

   

    }

    
