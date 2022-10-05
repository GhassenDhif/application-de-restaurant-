/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Categorie;
import entity.Restau;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;
import java.lang.String;
import java.util.*;
import javafx.collections.FXCollections;


/**
 *
 * @author amed1
 */
public class CatService implements IService<Categorie>{

     private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CatService() {
        conn = Datasource.getInstance().getCnx();
    }

    
    
    
    @Override
    public void insert(Categorie c) {
       
        
      String req = "insert into categorie (type) values (?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getType());
          
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
        
        
    }

    @Override
    public void delete(Categorie c) {
    
        
          String req = "delete from categorie where id=?";
         try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, c.getId());
           int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
    System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void updateid(Categorie c ) {

        

 String b = "llll";
     
    String req = "update categorie SET  type=? WHERE type=?";
   
      try {
          
          pst = conn.prepareStatement(req);
                       
           pst.setString(1,b);
           pst.setString(2,c.getType());
           System.out.println(pst); 
           //   pst.setInt(3,i);
           int rowsDeleted = pst.executeUpdate();
           
           
            if (rowsDeleted > 0) {
            System.out.println("A user was updated successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }

         
    }

    @Override
    public List<Categorie> read() {
        
          String req="select * from categorie";
                    List<Categorie> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Categorie(rs.getInt("id"), rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
        
    }

    
    public List<Categorie> readBytype() {
      
         String req="select * from categorie  ORDER BY type ASC";
                    List<Categorie> list1=new ArrayList<>();
         try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list1.add(new Categorie(rs.getInt("id"), rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list1;
           
    }

    @Override
    public List<Restau> readByNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void supprimer(int id) {
     try {
              PreparedStatement pt =conn.prepareStatement("DELETE FROM categorie where id =?");
           // PreparedStatement pst = Datasource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,id );
            pst.execute();
            //System.out.println("Panier supprimer");
           int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
    System.out.println("A categorie was deleted successfully!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void update(Categorie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
       Categorie readd(int id) {
   String req="select * from categorie where id=?";
        Categorie r=null;
        try {
            pst=conn.prepareStatement(req);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
             if (resultSet.next()) {              
                    r = new Categorie(resultSet.getInt(1),resultSet.getString(2)
                          );
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
        return r;  }

       
       
       
       
       
    
       public List<Categorie> tries(String s) {

                  String req="SELECT * FROM `categorie` ORDER BY `type`.`"+s+"` ASC"; //  SELECT *FROM client FULL JOIN login ON client.id_client = login.id_user 
                    List<Categorie> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Categorie(rs.getInt("id"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
       
       
       
       
       
       
       
       
       
       
       
    /*
    
      Categorie affichera(){
        
        Categorie c = new Categorie();
                String req="select *from categorie where type =?";
       
       Categorie cv;
       int a;
       try{
           pst=conn.prepareStatement(req);
           pst.setString(1,type);
           rs=pst.executeQuery();
           if (rs.next()) {              
              cv = new Categorie(rs.getInt(1),rs.getString(2)
                          );
                }
           
          
       }
         catch (Exception ex){
                 
                     System.out.println(ex.getMessage());
         }
           return  cv;
    }
    */
    
    
       
        public List<Categorie> chercher(String s) {
        
                  String req="select * from categorie WHERE type=%'"+s+"'% "; //  SELECT *FROM client FULL JOIN login ON client.id_client = login.id_user 
                    List<Categorie> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Categorie(rs.getInt("id"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
 
       
       
       
       
       
       
    
    /*
    
    public List<Categorie> afficherPDF() {
        List<Categorie> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM categorie";
           ste=conn.createStatement();
            rs= ste.executeQuery(req);

            while (rs.next()) {
                list.add(new Categorie(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    */
    /*
    public List<Categorie> ChercherListActParCategorie(Categorie categorie) {
        List<Categorie> l = this.afficherPDF();
        List<Categorie> nl = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getType().toUpperCase().contains(categorie.toUpperCase())) {
                nl.add(l.get(i));
            }
        }
        return nl;

    }
    */
    
    public Categorie ChercherCentreParType(String type) {
        
        List<Categorie> l = this.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getType().equals(type)) {
                return l.get(i);
            }
        }
        return null;
    }
    /*
     public List<Categorie> CentreListeTrierParTypeReverse() {
        List<Categorie> l = this.read();
        Collections.sort(l, new Categorie());
        Collections.reverse(l);
        return l;
    }
    
    
    
    
*/
   
     public Categorie readid(String type) {
       
                String req="select *from categorie where type=? limit 1";
                    Categorie ca= new Categorie();
                    
                    
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1,type);
          
            rs = pst.executeQuery();

            while(rs.next()){
              ca =  new Categorie(rs.getInt("id"),rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
      
    }
     
     
     public void modifierbs (int id,String t1)
     {
     
     try{
          PreparedStatement pt= conn.prepareStatement("update categorie set type=? where id=?");
            pt.setString(1, t1);
            pt.setInt(2, id);
            pt.execute();
            

            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     
     }
     
     
     
     
       public void supprimerr(int id_)
    {
        try {
            PreparedStatement pt =conn.prepareStatement("delete from categorie where id=?" );
            pt.setInt(1,id_);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
     }
    
   

