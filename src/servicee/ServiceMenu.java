/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Menu;
import utils.Datasource;

/**
 *
 * @author USER
 */
public class ServiceMenu {
    
    Connection cnx = Datasource.getInstance().getCnx();

    private Statement ste;
    private PreparedStatement pst;
  public List<Menu> afficher() {
        List<Menu> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM menu";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               
            list.add(new Menu (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getString(6)));       
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
    public void ajouter(Menu r) {
          try {
                       
            String req = "INSERT INTO `menu` (`nom`, `prix`, `cat_id`,`image`,`descp`) values (?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, r.getNom());
            pre.setInt(2, r.getPrix());
            pre.setInt(3, r.getCategorie_id());

            pre.setString(4, r.getImage());
            
            pre.setString(5, r.getDescription() );
            pre.executeUpdate();
            System.out.println("Menu Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
    
     public int deletemenu(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `menu` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
}
