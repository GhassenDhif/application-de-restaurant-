/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entities.Commandeaffiche;
import Entities.Panier;
import Entities.Panieraffiche;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Panierservice  implements Iservicepa<Panier>{
 private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
  
    private ResultSet rs;
    static final String QUERY = "SELECT * FROM panier";
    public Panierservice(){
           conn=Datasource.getInstance().getCnx();
    }
    /*
    
    public void createAccount(){
    
    traitemeent...
    User user = getUserByUsername(username);
    Panier p = new Panier(prix=0,clientId=user.getId);
    panierService.insert(p);
    */
    
  
    @Override
    public void insert(Panier p){
        
        String req="insert into panier (prix,clientid) values ("+p.getPrix()+","+p.getClientid()+")";
        try {
            ste=conn.createStatement();
               ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } 
    
     public List<Panier> read() {
                    String req="select * from panier";
                    List<Panier> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Panier(rs.getInt("id"), rs.getDouble("prix"),rs.getInt("clientid")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     @Override
    public void delete(int id) {
      try(
         Statement stmt = conn.createStatement();
      ) {		      
         String sql;
          sql = "DELETE FROM panier  WHERE id='" + id + "'";
         stmt.executeUpdate(sql);
         ResultSet rs = stmt.executeQuery(QUERY);
        System.out.println("produit supprimee!");
      } catch (SQLException ex) {
         Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
      } 
   }
    
public void update(Panier t) {
    
        String requete = "UPDATE panier SET prix = 99088 " + " WHERE id = " + t.getId() ;
      
     
        try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.executeUpdate(requete);
            System.out.println("panier modifiee!");
            
        } catch (SQLException ex) {
              Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
@Override    
public void updateperIds(int value1,double value2,int value3 ) {
    
         String requete = "update panier set prix='"+value2+"', clientid= '"+
                    value3+"' where id='"+value1+"' ";
      
        try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.executeUpdate(requete);
            
        } catch (SQLException ex) {
              Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public Panier readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Panier readByClientId(int clientId){
        String requete = "SELECT * FROM panier where clientid='"+clientId+"'";
        Panier p = new Panier();

        try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
            ResultSet rs =pst.executeQuery(requete);
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setPrix(rs.getDouble("prix"));
                p.setClientid(rs.getInt("clientid"));
            }
            
        } catch (SQLException ex) {
              Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
     public List<Panieraffiche> readForTableview() {
 String req="select * from panier";
                    List<Panieraffiche> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Panieraffiche(Integer.toString(rs.getInt("id")), rs.getString("prix"), Integer.toString(rs.getInt("clientid"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Panierservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;


    }
    
}

