/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import models.Menu;
import org.controlsfx.control.Notifications;
import servicee.ServiceMenu;
import utils.Datasource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddMenuController implements Initializable {

    private TextField ftNom;
    @FXML
    private Button btn;
    @FXML
    private TableView<Menu> tablepl;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    private TableColumn<?, ?> col_adresse;
    @FXML
    private TableColumn<?, ?> col_domaine;
    @FXML
    private Button btnu;
    @FXML
    private ComboBox<String> ftrestaurant;
    @FXML
    private TextField iddd;
     private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    
    
    ObservableList<Menu> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane recpane;
    @FXML
    private TextField prix;
     @FXML
    private TextField filterField;
    @FXML
    private TextField nom;
    @FXML
    private TextArea desc;
    @FXML
    private Button tfphoto;
    @FXML
    private TableColumn<?, ?> cprix;
    @FXML
    private TableColumn<?, ?> dsc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(AddMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher();
    }    
    
     public void initialiserlist() throws SQLException {
        // TODO
             
          
              try {
            Connection cnx = Datasource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM menu");
            while(rs.next()){
                Preferences UserId = Preferences.userRoot();
              
            list.add(new Menu (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getString(6)));    }
            } catch (SQLException ex) {
            Logger.getLogger(gui.AddMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
          
              Connection cnx = Datasource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM cate ");
            // Now add the comboBox addAll statement
           while (rs.next()){
            ftrestaurant.getItems().addAll(rs.getString("nom"));
           
           }
        
    }  
     private void afficher(){
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
          cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          col_domaine.setCellValueFactory(new PropertyValueFactory<>("cat_id"));
        
         dsc.setCellValueFactory(new PropertyValueFactory<>("descp"));
        
         
                    
            
        tablepl.setItems(list);
    }

    @FXML
   private void Ajouter(ActionEvent event) throws SQLException {
                 ServiceMenu r = new ServiceMenu();
                 ResultSet rs6 = null;
                 
        Connection cnx = Datasource.getInstance().getCnx();
        int p= Integer.valueOf(prix.getText()) ;
        String v= String.valueOf(ftrestaurant.getValue());
         rs6 = cnx.createStatement().executeQuery("SELECT id FROM cate where nom='"+v+"'");  
     
        rs6.next();
   int id1 = rs6.getInt("id");
        r.ajouter(new Menu(nom.getText(),p,id1,tfphoto.getText(),desc.getText()));
            Notifications notificationBuilder = Notifications.create()
                
                        .title("Menu Ajoutée")
                        .text("Saved in your DATABASE").darkStyle()
             .graphic(null)
   // .graphic(null)
                        
                        .hideAfter(Duration.seconds(15))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               System.out.println("Clicked on notification");
            }
        });
                notificationBuilder.darkStyle();
                notificationBuilder.show();  
           
        list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
    }
   @FXML
   
 public void rechercher(){
    ServiceMenu re= new ServiceMenu() ;
    List<Menu>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<Menu> filteredData = new FilteredList<>(list , b -> true);
		Menu r = new Menu();
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(menu -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (menu.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (menu.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (menu.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Menu> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablepl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablepl.setItems(sortedData);
               
        
    }
   @FXML
    private void pdfreport(ActionEvent event) throws DocumentException, FileNotFoundException {
            Menu service = new Menu();
           Document pdfReport = new Document() ;
           PdfWriter.getInstance(pdfReport, new FileOutputStream("menu.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("menu"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
          
          PdfPTable my_report_table = new PdfPTable(9);
            PdfPCell  tableCellColumn = new PdfPCell(new Phrase("Cat id"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("desc"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("nom"));
            my_report_table.addCell(tableCellColumn);
       
            
            
            double h= 0;
            tablepl.getItems().forEach((Menu e) -> {
                
               PdfPCell tableCell = new PdfPCell(new Phrase(e.getCategorie_id()));
                my_report_table.addCell(tableCell);
                
                
                tableCell = new PdfPCell(new Phrase(e.getDescription()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getNom()));
                my_report_table.addCell(tableCell);
                
                
                
                
                
                 
            });
            /* Attach report table to PDF */
            pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            
            pdfReport.close();
            
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: Bureau");
            alertReservation.showAndWait();
    }

  public Menu gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Menu test = (Menu) tablepl.getSelectionModel().getSelectedItem();
        return test;
    }
  

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = tablepl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    iddd.setText(col_id.getCellData(index).toString());
    ftrestaurant.setValue(col_domaine.getCellData(index).toString());
    prix.setText(cprix.getCellData(index).toString());
   
    desc.setText(dsc.getCellData(index).toString());
    
    nom.setText(col_nom.getCellData(index).toString());
    
    }
       @FXML
 public void Edit () throws SQLException{   
        try {
             ResultSet rs6 = null;
                 
        Connection cnx = Datasource.getInstance().getCnx();
        int p= Integer.valueOf(prix.getText()) ;
        String v= String.valueOf(ftrestaurant.getValue());
         rs6 = cnx.createStatement().executeQuery("SELECT id FROM cate where nom='"+v+"'");  
     
        rs6.next();
   int id1 = rs6.getInt("id");
            String value0 = iddd.getText();
            String value1 = nom.getText();
            
            
            String value2 = ftrestaurant.getValue();
            
             String value4 = desc.getText();
            String value5 = prix.getText();
            
         
            String sql = "update menu set nom= '"+value1+"',cat_id= '"+id1+"',prix= '"+value5+"',descp= '"+value4+"' where id='"+value0+"' ";
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
   private void delete(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Menu x = gettempReclamation(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceMenu cat = new ServiceMenu();

            int s = cat.deletemenu(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("menu deleted");
                alert.showAndWait();
                  list.clear();
        initialiserlist(); 
               
                tablepl.refresh();
               
                  iddd.setText("");           }       } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
  

    private void rest(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutRestaurant.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void Plats(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Cat.fxml"));
           recpane.getChildren().setAll(pane);
    }

   @FXML
    private void uploadphoto(ActionEvent event) {
        FileChooser F = new FileChooser();
        F.setTitle("Choisir une image");
        F.getExtensionFilters().addAll();
        File f = F.showOpenDialog(recpane.getScene().getWindow());
        if(f != null){
            tfphoto.setText(f.toString());
        }
    }

  
    
    
}
