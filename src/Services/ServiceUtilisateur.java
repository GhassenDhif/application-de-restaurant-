/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.Datasource;

/**
 *
 * @author GhAlone
 */
public class ServiceUtilisateur implements UService<Utilisateur> {

    boolean x;

    Connection cnx = Datasource.getInstance().getCnx();

    /**
     *
     * @param u
     */
    @Override
    public boolean connecter(Utilisateur u) {
        boolean ok = true;
        try {
            String req = "select email , password , roles from user where ( email=? and password=? and roles=? )";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            if (rs == null) {
                ok = false;
            }
        } catch (SQLException ex) {
            System.out.println("connexion echouer");
        }
        return ok;
    }

    @Override
    public void profil(Utilisateur u) {
        try {
            String req = "update user set nom=?,prenom=?,num_tel=?,genre=?,email=?, where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(6, u.getId());
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setInt(3, u.getNum_tel());
            pst.setString(4, u.getGenre());
            pst.setString(5, u.getAdress_email());
            pst.setString(6, u.getImage());
            pst.executeUpdate();
            System.out.println("modifier profil");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void changer_pass(Utilisateur u) {
        try {
            String req = "update user set password=? where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(2, u.getId());
            pst.setString(1, u.getMot_pass());
            pst.executeUpdate();
            System.out.println("password se change");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Utilisateur u) {
        try {
            String req = "update user set nom=?,prenom=?,num_tel=?,password=?,genre=?,email=?,roles=?,image=? where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(9, u.getId());
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setInt(3, u.getNum_tel());
            pst.setString(4, u.getMot_pass());
            pst.setString(5, u.getGenre());
            pst.setString(6, u.getAdress_email());
            pst.setString(7, u.getRoles());
            pst.setString(8, u.getImage());
            pst.executeUpdate();
            System.out.println("modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void inscrir(Utilisateur u) {
        try {
            String req = "insert into user (nom,prenom,num_tel,password,genre,email,roles,image) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setInt(3, u.getNum_tel());
            pst.setString(4, u.getMot_pass());
            pst.setString(5, u.getGenre());
            pst.setString(6, u.getAdress_email());
            pst.setString(7, u.getRoles());
            pst.setString(8, u.getImage());
            pst.executeUpdate();
            System.out.println("s'inscrire");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
//    public ObservableList<Utilisateur> afficher() {
//                    ObservableList <Utilisateur> list = FXCollections.observableArrayList();
//
//        try {
//            String req = "SELECT * FROM user ";
//            PreparedStatement pst=cnx.prepareStatement(req);
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()){
//                list.add
//        (new Utilisateur(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"),rs.getInt("num_tel"),rs.getString("email"),rs.getString("genre"),rs.getString("password"),rs.getString("image")));
//            }   
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());       } 
//        return list;
//    }
    public List<Utilisateur> afficher() {
        List<Utilisateur> list = new ArrayList<>();
        try {
            String request = "SELECT* FROM user";
            PreparedStatement st = cnx.prepareStatement(request);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("num_tel"), rs.getString("email"), rs.getString("genre"), rs.getString("password"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public String recEmail(String user) {
        String req = "select email from user where email ='" + user + "'";
        String res = "null";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res = rs.getString("email");
                return res;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public int recId(String user) {
        String req = "select id from user where email ='" + user + "'";
        int res = 0;
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res = rs.getInt("id");
                return res;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String envoyerCode(int id) {
        Random r = new Random();
        return "" + r.nextInt(100) + id + r.nextInt(100);

        //return ;
    }

    public boolean updateCode(String code, int id) {

        String qry = "UPDATE user SET code = '" + code + "' WHERE id = " + id;

        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            if (pst.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String recCode(int id) {
        String req = "select code from user where id ='" + id + "'";
        String res = "null";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res = rs.getString("code");
                return res;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public boolean update(String pwd, int id) {

        String qry = "UPDATE user SET password = '" + pwd + "' WHERE id = " + id;

        try {
            PreparedStatement pst = cnx.prepareStatement(qry);

            if (pst.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
