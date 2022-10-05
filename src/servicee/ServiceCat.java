/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Categorie;
import models.Menu;
import utils.Datasource;

/**
 *
 * @author USER
 */
public class ServiceCat {
    
    Connection cnx = Datasource.getInstance().getCnx();

    private Statement ste;
    private PreparedStatement pst;
 
    public void ajouter(Categorie r) {
          try {
                       
            String req = "INSERT INTO `cate` (`nom`) values (?)";
            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, r.getNom());
          
            pre.executeUpdate();
            System.out.println("Menu Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
     public int delete(String nom) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `cate` WHERE nom='"+nom+"'";
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
}
