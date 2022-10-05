/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entities.Commande;

import Entities.Commandeaffiche;
import Entities.Panier;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author User
 */

public class Commandeservice  implements  Iserviceco<Commande>{ 
   private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
  
    private ResultSet rs;
    static final String QUERY = "SELECT id, titre, date_de_commande,prix test? FROM commande WHERE panierFK=";
    public Commandeservice(){
           conn=Datasource.getInstance().getCnx();
    }
   @Override
    public void insert(Commande c){
        
       
        
        String req="insert into commande (titre,date_de_commande,prix,panierFK) values ('"+c.getTitre()+"','"+c.getDate_de_commande()+"','"+c.getPrix()+"',"+c.getPanierFK()+")";
        
        try {
            
            ste=conn.createStatement();
               ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        Panierservice ps= new Panierservice();
        //LoggedInUser.loggedInUser.getId() à remplacer au lieu de 0
        Panier p = ps.readByClientId(0);
        p.setPrix(p.getPrix()+c.getPrix());
        ps.updateperIds(p.getId(),p.getPrix(),p.getClientid());
        Commande commande=this.readByDate(c.getDate_de_commande());
        c.getListe_de_produits().stream().forEach(pFK->{
            String query= "INSERT INTO commande_produits (commandeFK,produitFK) VALUES ("+commande.getId()+","+pFK+")";
            try {
            System.out.println(c.getId());
            ste=conn.createStatement();
              int test = ste.executeUpdate(query);
                System.out.println("insertion completed "+test);
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    } 
     @Override
     public List<Commande> read() {
                    String req="select * from commande";
                    List<Commande> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"),rs.getDouble("prix"),rs.getInt("panierFK")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override 
    public void delete(int id) {
      try(
         Statement stmt = conn.createStatement();
      ) {		      
         String sql;
          sql = "DELETE FROM commande  WHERE id='" + id + "'";
        
      stmt.executeUpdate(sql);
        System.out.println("commande supprimee!");
      } catch (SQLException ex) {
         Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
      } 
   }
@Override    
public void update(Commande t) {
    
        String requete = "UPDATE commande SET titre = 'xxxhh' " + " WHERE id = " + t.getId() ;
     
        try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.executeUpdate(requete);
            System.out.println("commande modifiee!");
            
        } catch (SQLException ex) {
              Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
public void updateperIdd(int value1, String value2, String value3,double value4,int value5) {
    
         String requete = "update commande set titre='"+value2+"', date_de_commande= '"+value3+"', prix= '"+
               
                   value4+"', panierFK= '"+value5+"' where id='"+value1+"' ";
      
        try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.executeUpdate(requete);
            
        } catch (SQLException ex) {
              Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    @Override
    public Commande readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Commande readByDate(String date){
        String req="select * from commande where date_de_commande='"+date+"'";
                    Commande command=new Commande();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                command.setId(rs.getInt("id"));
                command.setTitre(rs.getString("titre"));
                command.setPrix(rs.getDouble("prix"));
                command.setPanierFK(rs.getInt("panierFK"));
            }
             System.err.println(command);
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return command;
       
    }
    public List<Commande> RechercheCommande(String titre) {
       
         
         
         List<Commande> listE = new ArrayList<>();
         
        try {
            
            Commande e = new Commande();
            
            Statement st = conn.createStatement();
            
            String requete = "SELECT * FROM commande WHERE titre = '"+titre+"'";
            
            System.out.println("Commande trouvee!");
            
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                
                  listE.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"), rs.getDouble("prix"),rs.getInt("panierFK")));
         
                
            }
            System.out.println(listE); 
            
        } catch (SQLException ex) {
             Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listE;

    }

    public List<Commandeaffiche> readForTableview() {
 String req="select * from commande";
                    List<Commandeaffiche> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Commandeaffiche(Integer.toString(rs.getInt("id")), rs.getString("titre"), rs.getString("date_de_commande"),Double.toString(rs.getDouble("prix")),Integer.toString(rs.getInt("panierFK"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;


    }
    public List<Commande> RechercheCommandei(int id) {
       
         
         
         List<Commande> listE = new ArrayList<>();
         
        try {
            
            Commande e = new Commande();
            
            Statement st = conn.createStatement();
            
            String requete = "SELECT * FROM commande WHERE id = '"+id+"'";
            
            System.out.println("Commande trouvee!");
            
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                
                 // listE.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"),rs.getInt("panierFK")));
                 listE.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"), rs.getDouble("prix"), rs.getInt("panierFK")));   
                
            }
            System.out.println(listE); 
            
        } catch (SQLException ex) {
             Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listE;

    }

 
  /*  public List<Commande> RechercheCommanded(String date_de_commande) {
       
         
         
         List<Commande> listE = new ArrayList<>();
         
        try {
            
            Commande e = new Commande();
            
            Statement st = conn.createStatement();
            
            String requete = "SELECT * FROM commande WHERE date_de_commande = '"+date_de_commande+"'";
            
            System.out.println("Commande trouvee!");
            
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                
                  listE.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"),rs.getInt("panierFK")));
         
                
            }
            System.out.println(listE); 
            
        } catch (SQLException ex) {
             Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listE;

    }
public List<Commande> RechercheCommandep(int panierFK) {
       
         
         
         List<Commande> listE = new ArrayList<>();
         
        try {
            
            Commande e = new Commande();
            
            Statement st = conn.createStatement();
            
            String requete = "SELECT * FROM commande WHERE panierFK = '"+panierFK+"'";
            
            System.out.println("Commande trouvee!");
            
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                
                  listE.add(new Commande(rs.getInt("id"), rs.getString("titre"), rs.getString("date_de_commande"),rs.getInt("panierFK")));
         
                
            }
            System.out.println(listE); 
            
        } catch (SQLException ex) {
             Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listE;

    }*/

  /*  public List<Commandeaffiche> readForTableview() {
 String req="select * from commande";
                    List<Commandeaffiche> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Commandeaffiche(Integer.toString(rs.getInt("id")), rs.getString("titre"), rs.getString("date_de_commande"),Integer.toString(rs.getInt("panierFK"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;


    }*/
    
    
 /*   public List<Commandeaff> readForTableview() {
 String req="select * from commande";
                    List<Commandeaff> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new Commandeaff(Integer.toString(rs.getInt("id")), rs.getString("titre"), rs.getString("date_de_commande"),Integer.toString(rs.getInt("panierFK"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;


    }
 */
    /*
    
  public void inserta(Commandeaffiche p){
        
       
        
        String req="insert into commande (titre,date_de_commande,panierFK) values ('"+p.getTitre()+"','"+p.getDate_de_commande()+"',"+p.getPanierFK()+")";
        
        try {
            
            ste=conn.createStatement();
               ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        Commande commande=this.readByDate(p.getDate_de_commande());
      /*  p.getListe_de_produits().stream().forEach(pFK->{
            String query= "INSERT INTO commande_produits (commandeFK,produitFK) VALUES ("+commande.getId()+","+pFK+")";
            try {
            
            ste=conn.createStatement();
              int test = ste.executeUpdate(query);
                System.out.println("insertion completed "+test);
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    }*/ 

    @Override
    public void updateperId(int value1, String value2, String value3, double value4, int value5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

