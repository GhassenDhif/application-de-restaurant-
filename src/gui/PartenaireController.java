/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;



import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
//import static com.ibm.icu.impl.PluralRulesLoader.loader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import utils.Datasource;
import service.SoundClick;
import service.SMS;
import service.Service_mail;
import service.Service_generer_Qr_code;
import service.Service_notif;
import service.CatService;
import entity.Categorie;
import entity.Restau;
import service.RestauService;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java_cup.parse_action;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
//import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 * FXML Controller class
 *
 * @author amed1
 */
public class PartenaireController implements Initializable {

    PreparedStatement pst;
      private ResultSet rs;
      private Connection conn;  
    
    
    String elpath;
    CatService cse =new CatService();
    RestauService r =new RestauService();
    @FXML
    private JFXTextField tnom;
    @FXML
    private JFXTextField tlocal;
    @FXML
    private JFXTextField tdesc;
    @FXML
    private JFXButton addb;
    @FXML
    private TableColumn<?, ?> cnom;
    @FXML
    private TableColumn<?, ?> cdate;
    @FXML
    private TableColumn<?, ?> clocal;
    @FXML
    private TableColumn<?, ?> cdesc;
    @FXML
    private TableColumn<?, ?> cid;

    Service_notif srvnot =new Service_notif();
    Service_generer_Qr_code srvqr = new Service_generer_Qr_code();
    Service_mail srvmail = new Service_mail();
    pdf pds = new pdf();
    SoundClick sc =new SoundClick();
    @FXML
    private DatePicker mydatepicker;
    @FXML
    private ComboBox<Categorie> combo;
    @FXML
    private JFXButton tri;
    @FXML
    private Label tdate;
    @FXML
    private TextField rech;
    @FXML
    private AnchorPane rec;
    @FXML
    private TableColumn<Restau, String> cate;
    @FXML
    private TableView<Restau> tablel;
    @FXML
    private JFXButton plusdet;
    @FXML
    private TableColumn<?, ?> cimage;
    @FXML
    private ImageView img;
    @FXML
    private PieChart pc;
 
    
    private String id;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //combo =new ComboBox();
            CatService c =new  CatService();
            System.out.println(r.myid());
            // String req = "select * from utilisateur";
            // combob.getItems().addAll();
            combo.getItems().addAll(c.read());
            // combo.setOnAction(e->System.out.println("aaaa"));
//      String date = mydatepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            afficher();
// nb.setPrefColumnCount(r.nbpartenaireTotal());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
          
    }    
    
    public void myid (String id){
     this.id=id;
        
    }
    public  String getid()
    {
            return this.id;
    }

     @FXML
    private void go_table(MouseEvent event) {
        
             String nom=(cnom.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
  //         String date=(cdate.getCellData(table.getSelectionModel().getSelectedIndex()).toString());
             String loc=(clocal.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
             String ds=(cdesc.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
//             String c =(ccat.getCellData(table.getSelectionModel().getSelectedIndex()).toString());
           
            tnom.setText(nom);
//          tdate.setText(date);
            tlocal.setText(loc);
            tdesc.setText(ds);
         // tid_ca.setText(c);
            
        //  tid_ca.setText(c);
            
            
    }
    
    
  
    
    @FXML
    private void submit(ActionEvent event) throws AWTException, SQLException ,FileNotFoundException ,DocumentException{
       
     //  String erreur="";
       // SMS srvss=new SMS();
         


     
        
        //String date = mydatepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//          int idus = Integer.parseInt(tus.getText());
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      //  Restau re = new Restau(tnom.getText(),tdate.getText(),tlocal.getText(), path , tdesc.getText(),elist.getTypeSelector());
       // Restau m =new Restau(tnom.getText(),date ,tlocal.getText(), elpath , tdesc.getText(),combo.getSelectionModel().getSelectedItem(),tus.getText());
  //dat=df.parse(date.getValue().toString());
       
       
       //Restau m = new Restau(tnom.getText(),date ,tlocal.getText(), elpath , tdesc.getText(),combo.getSelectionModel().getSelectedItem(),Integer.parseInt(r.myid()));
          Restau m;
        try {
            m = new Restau(tnom.getText(),df.parse(mydatepicker.getValue().toString()) ,tlocal.getText(), elpath , tdesc.getText(),combo.getSelectionModel().getSelectedItem(),Integer.parseInt(r.myid()));
             
          if (testNom())
        
        r.insertt(m);
        sc.playClick();
        srvnot.notif("ajoutée"); 
        srvqr.create(tnom.getText());
        srvmail.send_mail("mohamedamine.eloudi@esprit.tn ", tnom.getText());
        pds.add("c:");
       //srvss.SendSMS("WMS", "Categorie Passé avec succes", "21651833422");
        clear();
        afficher();
            
        } catch (ParseException ex) {
            System.out.println("enaa  ");
        }
          
          
         
       
        
      
       
    
    }
    public void afficher() throws SQLException{
        
        
        //ObservableList obeListe = FXCollections.observableList(r.read());
      //  ObservableList<Restau> obeListe =FXCollections.observableArrayList();
      //  obeListe.addAll(r.readtYpe());
     //    ObservableList<Categorie> obeListe1 =FXCollections.observableArrayList();
       // obeListe1.addAll(cse.read());
       System.out.println("-----------------------");
      //  System.out.println(Integer.parseInt(id));
               System.out.println("-----------------------");

        ObservableList obeListe = FXCollections.observableList(r.readtifus(Integer.parseInt(r.myid())));

        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("datef"));
        cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
       //cdate.setDayCellFactory(date);
        clocal.setCellValueFactory(new PropertyValueFactory<>("local"));
        cdesc.setCellValueFactory(new PropertyValueFactory<>("descri"));
      
        
        cate.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        System.out.println(obeListe);
       // ComboBox<Categorie> = FXCollections.observableArrayList("id_cat");
     
        
        
        tablel.setItems(obeListe);
        //c1.setVisible(false);
        
    }
    
    private void clear(){
     tnom.clear();
    //mydatepicker.clear();
    tdate=null;
    tlocal.clear();
    tdesc.clear();
    //combo.equals(false);    
    
    }
    
    

    @FXML
    private void delete(ActionEvent event) throws SQLException, SQLException {
        
        
        int k=Integer.valueOf(cid.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
        
        
        try {
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer ce Resatau");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                 r.supprimerr(k);
           srvnot.notif("supprimer"); 
            }
           // r.supprimerr(k);
           
        } catch (AWTException ex) {
            System.out.println("am");
        
        }
       
        
            afficher();
            clear();
            
            
            
           
    }
            
    /*
       Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous voulez vraiment supprimer ce restaux");
            alert.show();
    */
    
    
    
    
    
    
    

    @FXML
    private void update(ActionEvent event) throws SQLException {
         
        
        if(testNom())
        try {
         String t1 =  tnom.getText(); 
//         String t2 =  tdate.getText();
         String t3 =  tlocal.getText();
         String t4 =  tdesc.getText();
        // int t5 = combo.getSelectionModel().getSelectedItem().getId();
         int k =Integer.valueOf(cid.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
         r.modifierbs(k,t1,t3,t4);
         srvnot.notif("modifier");
        } catch (AWTException ex) {
            System.out.println("ena heya lbhima");
        }
        afficher();
     
      }

    @FXML
    private void improter(ActionEvent event) {
       FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));//importation de l'image ou
        fc.setTitle("Veuillez choisir l'image"); //titre de la
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            String path = selectedFile.getName();
            
            try {
                path = selectedFile.toURI().toURL().toExternalForm();
                System.out.println(path);
                elpath=path;
            } catch (MalformedURLException ex) {
                Logger.getLogger(PartenaireController.class.getName()).log(Level.SEVERE, null, ex);
            }
                          
      //   Image image = new Image(path);
            
           // Image image = new Image(selectedFile.toURI().toString()) {};
            //img.setImage(path) ;
              if(path.equals(""))
            {
                boolean verificationImage = false;
            }
            else 
                  
              { boolean verificationImage = true;
}
        
   
    }

    }
          
//                path = selectedFile.toURI().toURL().toExternalForm();
      
   
    @FXML
    private void getdate(ActionEvent event) {
        
        LocalDate myDate =mydatepicker.getValue();
        String date = myDate.format(DateTimeFormatter.ofPattern("MM-DD-YYYY"));
        //cdate.setText(date);
        
        //String date = mydatepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
    }

    @FXML
    private void select(ActionEvent event) {
     
        //System.out.println(combo.getValue().getType());
        //ccat.setText(combo.getValue().getType());
        
      
       
    }

    @FXML
    private void trier(ActionEvent event) throws SQLException {
        
        
         ObservableList obeListe = FXCollections.observableList(r.readtifusss(Integer.parseInt(r.myid())));

        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("datef"));
        cimage.setCellValueFactory(new PropertyValueFactory<>("image"));
       //cdate.setDayCellFactory(date);
        clocal.setCellValueFactory(new PropertyValueFactory<>("local"));
        cdesc.setCellValueFactory(new PropertyValueFactory<>("descri"));
      
        
        cate.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        System.out.println(obeListe);
       // ComboBox<Categorie> = FXCollections.observableArrayList("id_cat");
     
        
        
        tablel.setItems(obeListe);
        
        
    }
    /*
    public void rech(){
        
        
     RestauService rs =new RestauService();
     
     List<Restau> results = new ArrayList<>();
     
     results=rs.read();
     
     FilterdList<Restau> filteredData =new FilterdList<>(list , b->true);
     
     Restau r =new Restau();
     
     rech.textProperty().addListener((observable , oldValue ,newValue) ->{
     
     if(newValue == null || newValue.isEmpty()) {
     
     return true;
     
             }
     
     String lowercaseFilter = newValue.toLowerCase();
     
     if(Restau.getNom().toLowerCase().indexOf(lowercaseFilter) != -1) {
     
     return true;
 
     }
     
     else  if(Restau.getLocal().toLowerCase().indexOf(lowercaseFilter) != -1) {
     
     return true;
             
     }
     else  if(Restau.getDescr().toLowerCase().indexOf(lowercaseFilter) != -1) {
     
     return true;
             
     }
     else
        
         return false;}
     
     
     
     );
    });
    
    SortedList<Restau> sortedData = new SortedList<>(filteredData);
    
    
    sortedData.comparatorPrperty().bind(table.comparatorProerty());
    table.setItems(sortedData);
        
    }
    */
/*
    private void gotoo(ActionEvent event)throws IOException {
        
          
          Parent root =FXMLLoader.load(getClass().getResource("categorie.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
          
        
      
 
    }
*/
    @FXML
    private void rechhh(ActionEvent event) throws SQLException {
         if(!rech.getText().isEmpty())
        {
     ObservableList <Restau> g = FXCollections.observableArrayList();
           g.addAll(r.ChercherCentreParNom(rech.getText()));
           tablel.getItems().clear();
           tablel.setItems(g);
        }else 
            afficher();
        
    }

    @FXML
    private void plusdetaille(ActionEvent event) {
        
            String nom=(cnom.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            String date=(cdate.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            String loc=(clocal.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            String image=(cimage.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            String ds=(cdesc.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            // String c =(cat.getCellData(tablel.getSelectionModel().getSelectedIndex()).toString());
            
            javafx.scene.image.Image g =new javafx.scene.image.Image(image);
            
           // javafx.scene.image.Image j =new javafx.scene.image.Image("C:\\QRCODE\\"+nom+".png");
          //  Image jj =new Image(getClass().getResourceAsStream("C:\\QRCODE\\"+nom+".png"));
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Plus_detaille.fxml"));
       
        
        
        try {
            Parent root = loader.load();
            
            Plus_detailleController controller = loader.getController();
            controller.setTfNom(nom);
            controller.setTfdate(date);
            controller.setTfLocal(loc);
            controller.setTfDescr(ds);
           controller.setTimage(g);
          // controller.Setqr(); 
            tnom.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
    private Boolean testNom() {
        String erreur;
        int nbNonChar = 0;
        for (int i = 1; i < tnom.getText().trim().length(); i++) {
            char ch = tnom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tnom.getText().trim().length() >= 3) {
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
    /*
    public void start(Stage stage) {
       
        stage.show();
    }
/*
    @FXML
    private void staa(ActionEvent event)  throws  IOException {
          
         Parent root =FXMLLoader.load(getClass().getResource("stat.fxml"));    
                Stage stage = new Stage();
             Scene scene = new Scene(new Group());
        

        
        
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
 

                        ObservableList obeListe = FXCollections.observableList(r.stat());

       
        
        final PieChart chart = new PieChart(obeListe);
        chart.setTitle("Imported Fruits");
        
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
*/
    
      
            }
    
    




    
    
    
   
    
    
    
    

        
        
   
   

