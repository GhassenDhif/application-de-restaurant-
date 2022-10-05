///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import entite.option;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import service.optionService;
//
///**
// * FXML Controller class
// *
// * @author HP
// */
//public class Option2Controller implements Initializable {
//
//    @FXML
//    private Label tfNom;
//    @FXML
//    private Button btnajouter;
//    @FXML
//    private Button btnmodifier;
//    @FXML
//    private TextField tfcode1;
//    @FXML
//    private TextField tfnom1;
//    @FXML
//    private TextField tfprix1;
//    @FXML
//    private TableView<?> aff_option;
//    @FXML
//    private TableColumn<?, ?> tfcode;
//    @FXML
//    private TableColumn<?, ?> tfnom;
//    @FXML
//    private TableColumn<?, ?> tfprix;
//    @FXML
//    private Button btnsupprimer;
//    @FXML
//    private Button retour;
//    @FXML
//    private Label tfchercher;
//    @FXML
//    private TextField tfrechercher;
//    private optionService optionService;
//    int index=-1;
//    
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//            this.optionService = new optionService();
//        afficher();
//    }    
//    
//    public void afficher(){
//
//        ObservableList optionListe = FXCollections.observableList(optionService.read());
//
//        tfcode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        tfprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        
//        aff_option.setItems(optionListe);      
//    }
//    
//    public void clear(){
//        tfcode1.clear();
//        tfnom1.clear();
//        tfprix1.clear();   
//    }
//    
//    @FXML
//    private void onClick1(ActionEvent event) {
//        int id = Integer.parseInt(tfcode1.getText());
//        String nom = tfnom1.getText();
//        Double prix = Double.parseDouble(tfprix1.getText());
//        option o = new option(id,nom,prix);
//        optionService.inserOptionPst(o);
//        afficher();
//        clear();
//    }
//
//    @FXML
//    private void onClick2(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("suppoption.fxml"));
//            try {
//            Parent root = loader.load();
//            SuppoptionController controller = loader.getController();
//            tfNom.getScene().setRoot(root);
//        }catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @FXML
//    private void onClick3(ActionEvent event) {
//        int code = Integer.parseInt(tfcode1.getText());
//        String nom = tfnom1.getText();
//        double prix = Double.parseDouble(tfprix1.getText());
//        optionService.update(code,nom,prix);
//        afficher();
//        clear();
//    }
// 
//    @FXML
//    private void getSelected(MouseEvent event) {
//        index = aff_option.getSelectionModel().getSelectedIndex();
//        if (index<= -1){
//            return;
//        }
//        tfcode1.setText(String.valueOf(tfcode.getCellData(index).toString()));
//        tfnom1.setText(String.valueOf(tfnom.getCellData(index).toString()));
//        tfprix1.setText(String.valueOf(tfprix.getCellData(index).toString()));      
//        afficher();
//    }
//    
//    @FXML
//    private void retour(ActionEvent event) {
//    try {
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("gestion_evenement.fxml"));
//            Parent root = loader.load();
//            Gestion_evenementController controller = loader.getController();
//            tfnom1.getScene().setRoot(root);
//        }catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
//    }
//    
//    @FXML
//    private void Rechercher(ActionEvent event) {
//    if(!tfrechercher.getText().isEmpty()){
//
//      ObservableList optionListe = FXCollections.observableArrayList(optionService.Rechercher(tfrechercher.getText()));
//
//        tfcode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        tfnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        tfprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//            aff_option.getItems().clear();
//            aff_option.setItems(optionListe);
//        }else 
//        afficher();
//    }
//    
//}
