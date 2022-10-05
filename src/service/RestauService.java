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
import javafx.scene.chart.PieChart;


/**
 *
 * @author amed1
 */
public class RestauService implements IService<Restau>{
    
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public RestauService() {
        conn = Datasource.getInstance().getCnx();
    }

  
    @Override
    public void insert(Restau r) {
     
        java.sql.Date Date = new java.sql.Date(r.getDatef().getTime());
        //System.out.println(r.toString());
      ///  System.out.println("categorie"+r.getcat().toString());
      String req = "insert into partenaire (nom,datef,local,image,descri,categorie_id) values (?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
            pst.setDate(2, Date);
            pst.setString(3, r.getLocal());
           // pst.setString(4, r.getImage());
            pst.setString(4, r.getImage());
            pst.setString(5, r.getDescr());
            pst.setInt(6, r.getcat().getId());
            // pst.setInt(6, 70);
                // pst.setInt(6,    r.getcat());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("amine");
        }
   
    }
    
    
    public void welcome (String id)
    {
         try {
            PreparedStatement pt= conn.prepareStatement("update user set is_verified=? where id =? ");
            pt.setInt(1,1);
            pt.setString(2,id);
           
            
            pt.execute();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         
         
    }
    
    
    
    
    
    
    public void logout (String id)
    {
         try {
            PreparedStatement pt= conn.prepareStatement("update user set is_verified=? where id =? ");
            pt.setInt(1,0);
            pt.setString(2,id);
           
            
            pt.execute();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

    public void insertt(Restau r) {
        
     java.sql.Date Date = new java.sql.Date(r.getDatef().getTime());
        
      String req = "insert into partenaire (nom,datef,local,image,descri,categorie_id,id_us) values (?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
            pst.setDate(2,Date);
            pst.setString(3, r.getLocal());
            pst.setString(4, r.getImage());
            pst.setString(5, r.getDescr());
            pst.setInt(6, r.getcat().getId());
            pst.setInt(7,r.getId_us());
             //pst.setInt(6, r.getus().getId());
          //  pst.setObject(6, r.getcat().getId());
            // pst.setInt(6, 70);
                // pst.setInt(6,    r.getcat());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    
    @Override
    public void delete(Restau r) {
       
        
          String req = "delete from partenaire where nom=?";
         try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getNom());
           int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
    System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
       public void supprimerr(int id_)
    {
        try {
            PreparedStatement pt =conn.prepareStatement("delete from partenaire where id=?" );
            pt.setInt(1,id_);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifierbs (int id,String t1, String t2,String t3)
    {
        String req ="update partenaire set nom= ? ,local= ?,descri= ? where id=?";
        try {
             
            pst = conn.prepareStatement(req);
            pst.setString(1,t1);
            pst.setString(2,t2);
            pst.setString(3,t3);
            pst.setInt(4,id);
            

            pst.execute();
            //pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
       
  
     public void modifierb (int id,String t1)
    {
        try {
            PreparedStatement pt= conn.prepareStatement("update partenaire set nom= ?  where id=?");
            pt.setString(1,t1);           
            pt.setInt(2,id);
            

            pt.execute();
        } catch (SQLException ex) {
            System.out.println("lena lghalta");
        }

    }
       
     
       
       
    /*
    @Override
    public void update(Restau r) {
      
     

 String pnom =r.getNom(); 
   
     
    String req = "update partenaire SET datef=?,local=?,image=?,descr=?,id_cat=? WHERE nom=?";
   
      try {
          
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getDatef());            
            pst.setString(2, r.getLocal());
            pst.setString(3, r.getImage());
            pst.setString(4, r.getDescr());

          //  pst.setInt(5, r.getId_cat());
            pst.setString(6, pnom);            
           //   pst.setInt(3,i);
           int rowsDeleted = pst.executeUpdate();
           
           
            if (rowsDeleted > 0) {
            System.out.println("A user was updated successfully!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
     
    /*
    public List<Restau> read() {
        
             //   String req="select * from partenaire";
                String req ="SELECT * FROM partenaire INNER JOIN categorie ON categorie.id = partenaire.id_cat";
                    List<Restau> list=new ArrayList<>();
                   Restau r = new Restau();
                   //int a =r.getcat().getId();
                  
                //   int a= ca.readid(m.getType()).getId();
                    
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
              CatService ca = new CatService();
                    
                    //ca.read(m.getId());
            while(rs.next()){

         Categorie m =new Categorie(rs.getInt("id"),rs.getString("type"));

              //  System.out.println(m);
        list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getString("datef"), rs.getString("local")
                ,rs.getString("image"), rs.getString("descr"),ca.readd(rs.getInt("id_cat"))));
            }
        } catch (SQLException ex) {
            System.out.println("tabel");
        }
        
        System.out.println(list);
        return list;
    
    }

     */
     
    
     
     public List<Restau> read() {
        
                String req="select * from partenaire";
                    List<Restau> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                    CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getDate("datef"), rs.getString("local"), rs.getString("image"), rs.getString("descri"),ca.readd(rs.getInt("categorie_id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    } 
     
     
     
     public List<Restau> readtYpe() {
        
                String req="SELECT * FROM partenaire INNER JOIN categorie ON categorie.id = partenaire.categorie_id";
                    List<Restau> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                    CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getDate("datef"), rs.getString("local"), rs.getString("image"), rs.getString("descri"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
     

     
     public List<PieChart.Data> stat() {
        
                String req="SELECT categorie.type,COUNT(categorie.id) as nbr FROM categorie left join partenaire on categorie.id=partenaire.categorie_id group by categorie.id";
                    List<PieChart.Data> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                  //  CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new PieChart.Data(rs.getString("type"), rs.getInt("nbr")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    } 
     
     public int kolfelkol() {
        
                String req="SELECT categorie.type,COUNT(categorie.id) as nbr FROM categorie left join partenaire on categorie.id=partenaire.categorie_id\n" +
"";
              
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                  //  CatService ca = new CatService();
                   // Categorie m =new Categorie();
                  int nub =  0; 
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                nub = rs.getInt("nbr");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nub;
    
    } 
     
     
     
     
     
     
     
     public List<Restau> readtifus(int id) throws SQLException {
        
                    List<Restau> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                    CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
            
                PreparedStatement req= conn.prepareStatement("SELECT * FROM partenaire INNER JOIN categorie ON categorie.id = partenaire.categorie_id where partenaire.id_us = ?");
            req.setInt(1,id);
            ResultSet rs= req.executeQuery();
            
            //ste=conn.createStatement();
           // rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getDate("datef"), rs.getString("local"), rs.getString("image"),rs.getString("descri"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
     
     public String myid() throws SQLException {
        
                 
                   // Categorie m =new Categorie();
           String amine="";
     
        try {
            
                PreparedStatement req= conn.prepareStatement("SELECT user.id from user where is_verified=1");
            ResultSet rs= req.executeQuery();
            //ste=conn.createStatement();
           // rs= ste.executeQuery(req);
            while(rs.next()){
                amine = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amine;
    
    }
     
     
     
     public List<Restau> readtifusss(int id) throws SQLException {
        
                    List<Restau> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                    CatService ca = new CatService();
                   // Categorie m =new Categorie();
                    
        try {
            
                PreparedStatement req= conn.prepareStatement("SELECT * FROM partenaire INNER JOIN categorie ON categorie.id = partenaire.categorie_id where partenaire.id_us = ? ORDER BY nom ASC");
            req.setInt(1,id);
            ResultSet rs= req.executeQuery();
            
            //ste=conn.createStatement();
           // rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getDate("datef"), rs.getString("local"), rs.getString("image"), rs.getString("descri"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
/*
    @Override
    public List<Restau> readByNom() {
        
         String req="select * from partenaire  ORDER BY nom ASC";
                    List<Restau> list1=new ArrayList<>();
         try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list1.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getString("datef"), rs.getString("local"), rs.getString("image"), rs.getString("descr"),rs.getInt("id_cat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list1;
        
        
    }
    
    
    
    
    
    
    
    
    public Restau ChercherCentreParNom(String nom) {
        List<Restau> l = this.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getNom().equals(nom)) {
                return l.get(i);
            }
        }
        return null;
    }

    @Override
    public void insert(Restau t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    */


    
    @Override
    public void update(Restau t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public List<Restau> readByNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Restau> readn() {
       
         String req="select * from partenaire  ORDER BY nom ASC";
                    List<Restau> list=new ArrayList<>();
                      CatService ca = new CatService();
                    Categorie m =new Categorie();
         try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Restau
        (rs.getInt("id"),rs.getString("nom"),rs.getDate("datef"),rs.getString("local"), rs.getString("image"),rs.getString("descri"),ca.readd(rs.getInt("categorie_id"))));
                
                        
            }
        } catch (SQLException ex) {
            Logger.getLogger(CatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
           
    }
    
    
    public Restau ChercherCentreParNom(String nom) {
        
        List<Restau> l = this.read();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getNom().equals(nom)) {
                return l.get(i);
            }
        }
        return null;
    }
    
  
    /*
    
      public List<Restau> readuser() {
      
           String req="select * from utilisateur";
                    List<utilisateur> list=new ArrayList<>();
                 //   Restau r = new Restau();
                   // int a =r.getcat().getId();
                   // Categorie m =new Categorie();
                    
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
             list.add(new Restau(rs.getInt("id"), rs.getString("nom"), rs.getString("datef"), rs.getString("local"), rs.getString("image"), rs.getString("descr"),ca.readd(rs.getInt("id_cat"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
      
      
      }
*/
    /*
    public int nbpartenaireTotal() {
        int nbtotal = 0;
        try {
            String req = "Select * from partenaire";
           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nbtotal = nbtotal + 1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nbtotal == 0) {
            System.out.println("Il y a aucune abonnement");
        }
        return nbtotal;
    }*/
      }
    
    
    
