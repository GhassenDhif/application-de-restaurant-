/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Utilisateur;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Datasource;

/**
 *
 * @author GhAlone
 */

public class ServiceAdmin implements AService<Utilisateur> {
            boolean x;
            
              Connection cnx=Datasource.getInstance().getCnx();

    @Override
    public boolean connecter(Utilisateur a) {
            boolean ok=true;      
                try {
                String req="select email , password from user where ( email=? and password=? )";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setString(1,a.getAdress_email());
                pst.setString(2,a.getMot_pass());
                ResultSet rs = pst.executeQuery();
                 if(rs.next()){ 
                    System.out.println("connected");
                }   
            } catch (SQLException ex) {
                System.out.println("connexion echouer"); 
            }
        return ok;    }

    @Override
    public void modifier(Utilisateur a) {
      try {
                String req="update user set nom=?,prenom=?,num_tel=?,password=?,genre=?,email=?,roles=?,image=? where id=? ";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setString(1,a.getNom());
                pst.setString(2,a.getPrenom());
                pst.setInt(3,a.getNum_tel());
                pst.setString(4,a.getMot_pass());
                pst.setString(5,a.getGenre());
                pst.setString(6,a.getAdress_email());
                pst.setString(7,a.getRoles());
                pst.setString(8,a.getImage());
                pst.setInt(9,a.getId());
                pst.executeUpdate();
                System.out.println("modifier");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());         
    }
    }

    @Override
    public List<Utilisateur> afficher() {
          List<Utilisateur> list =new ArrayList<>();

        try {
            String req = "select * from user where roles='[\"ROLE_ADMIN\"]' " ;
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add
        (new Utilisateur(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       } 
        return list;
        }

    @Override
    public void delete(Utilisateur a) {
                
                try {
                    String req="DELETE from user where id=? and roles!='[\"ROLE_ADMIN\"]' ";
                    PreparedStatement pst=cnx.prepareStatement(req);
                    pst.setInt(1,a.getId());
                    pst.executeUpdate();
                    System.out.println("deleted");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
                }
    }
   
    

