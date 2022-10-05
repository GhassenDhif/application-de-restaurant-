

package views;

import Entities.Commande;
import Entities.Commandeaffiche;
import Entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import Entities.*;
import service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLclientcommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Commandeaffiche> tableview;
    
    @FXML
    private TableColumn<Commandeaffiche,String> ids;
    
    
    @FXML
    private TableColumn<Commandeaffiche,String> titres;
    
    @FXML
    private TableColumn<Commandeaffiche,String> dateCommandes;
    
        @FXML
    private TableColumn<Commandeaffiche,String> prixs;
    
    @FXML
    private TableColumn<Commandeaffiche,String> panierFKs;
    
    @FXML
    private Button deleteButton;
    
       @FXML
    private Button modifierCommande;
       
          @FXML
    private Button ajouterCommande;
          
            
    @FXML
    public TextField titreo;
              
    @FXML
    public TextField dateo ;
    
    @FXML
    public ComboBox<Produit> comboBoxProduit;
    
    @FXML
    public Button addProduitButton;
    
    List<Produit> listProduitIds = new ArrayList<Produit>();
    public Commandeservice serviceCommande = new Commandeservice();
    public Panierservice panierService = new Panierservice();
    public ProduitService produitService = new ProduitService();
    Callback<ListView<Produit>, ListCell<Produit>> factory = lv -> new ListCell<Produit>() {

    @Override
    protected void updateItem(Produit item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getNom());
    }

};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

        comboBoxProduit.setCellFactory(factory);
        comboBoxProduit.setButtonCell(factory.call(null));
        comboBoxProduit.getItems().clear();
        comboBoxProduit.getItems().addAll(
                produitService.read()
        );
        
        /**  **/
        tableview.setEditable(true);
        ObservableList<Commandeaffiche> data = FXCollections.observableArrayList(serviceCommande.readForTableview());
        ids.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("id"));
        titres.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("titre"));
        titres.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCommandes.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("date_de_commande"));
          dateCommandes.setCellFactory(TextFieldTableCell.forTableColumn());
          prixs.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("prix"));
        panierFKs.setCellValueFactory(new PropertyValueFactory<Commandeaffiche,String>("panierFK"));
        titres.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Commandeaffiche, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Commandeaffiche, String> t) {
                    ((Commandeaffiche) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setTitre(t.getNewValue());
                    Commandeaffiche c = tableview.getSelectionModel().getSelectedItem();
                    serviceCommande.updateperIdd(Integer.parseInt(c.getId()),c.getTitre(), c.getDate_de_commande(),Double.parseDouble(c.getPrix()),Integer.parseInt(c.getPanierFK()));

                }
            }
        );
         dateCommandes.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Commandeaffiche, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Commandeaffiche, String> t) {
                    ((Commandeaffiche) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setDate_de_commande(t.getNewValue());
                    Commandeaffiche c = tableview.getSelectionModel().getSelectedItem();
                    serviceCommande.updateperIdd(Integer.parseInt(c.getId()),c.getTitre(), c.getDate_de_commande(),Double.parseDouble(c.getPrix()),Integer.parseInt(c.getPanierFK()));

                }
            }
        );
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(ids,titres,dateCommandes,prixs,panierFKs);
    }    
    
  
    /*public void Login(){
        User user = new User(usernameTextField.getText,passwordTextField.GetText());
        Boolean test= userService.authentification(user.username,user.password);
        if(test){
            LoggedInUser.loggedInUser.setId(user.id);
        }
    }*/
        
    @FXML
    public void ajouterCommande(ActionEvent event)throws  IOException{
        Commande c = new Commande();
        c.setTitre(titreo.getText());
        c.setDate_de_commande(dateo.getText());
        //LoggedInUser.loggedInUser.getId() à remplacer au lieu de 2
     //   c.setPanierFK(panierService.readByClientId(27).getId());
        /*
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);*/
        c.setPrix(
        listProduitIds.stream().mapToDouble(p->{return p.getPrix();}).reduce(0, (a,b)->{return a+b;})
        );
        c.setListe_de_produits(
                listProduitIds.stream().map(p->{return p.getId();}).collect(Collectors.toList())
                        );
        Commandeservice cs = new Commandeservice();
        cs.insert(c);
        System.out.println("commande ajouté");
        refreshTableView();
        listProduitIds.clear();
    }
    
    @FXML 
    public void addProduit(ActionEvent e){
        listProduitIds.add(comboBoxProduit.getValue());
    }
    
    
    public  void refreshTableView() {        
        tableview.getColumns().clear();
        ObservableList<Commandeaffiche> data = FXCollections.observableArrayList(serviceCommande.readForTableview());
        tableview.setItems(data);
        tableview.getColumns().clear();
        tableview.getColumns().addAll(ids,titres,dateCommandes,prixs,panierFKs);

}
}
