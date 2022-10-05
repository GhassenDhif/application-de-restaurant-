/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.Commande;
import Entities.Commandeaffiche;
import service.Commandeservice;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import static jdk.nashorn.internal.runtime.Debug.id;


/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLcreatePDFController implements Initializable {

    @FXML
    Button addPfdButton = new Button();
    
        @FXML
    public TextField idz;
              
    @FXML
    public TextField titrez ;
    
        @FXML
    public TextField datez;
        
          @FXML
    public TextField prixz;
              
    @FXML
    public TextField panierFKz ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    

    
    @FXML
    public void actionPerformed(ActionEvent a) {
       
             Document doc=new Document();       
        try {
           
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\amed1\\Desktop\\pdf132.pdf"));
            doc.open();
            doc.add(new Paragraph("C´est l´impression de la commande"));
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            
            PdfPCell cell;
            
             cell=new PdfPCell (new Phrase ("id",FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase ("titre",FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase ("date de commande",FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
            cell=new PdfPCell (new Phrase ("prix",FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase ("panierFK",FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
            
                /////////////////////////////////////////////////////
            cell=new PdfPCell (new Phrase (idz.getText().toString(),FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase (titrez.getText().toString(),FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase (datez.getText().toString(),FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
            
             cell=new PdfPCell (new Phrase (prixz.getText().toString(),FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
              cell=new PdfPCell (new Phrase (panierFKz.getText().toString(),FontFactory.getFont("Comic sans MS",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            
            doc.add(table);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            Image img = Image.getInstance("C:\\Users\\amed1\\Desktop\\plat.jpg");
            doc.add(img);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\amed1\\Desktop\\pdf132.pdf"));
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (DocumentException ex){
            ex.printStackTrace();            
        }
        catch(MalformedURLException ex){
                       
        }
        catch (IOException ex) {
            ex.printStackTrace(); 
        }
      
        
        
        
    }       
}