/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionCommandes;


import Entities.Commande;
import java.sql.*;
import Entities.Panier;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.ArrayList;
import service.Panierservice;
import service.Commandeservice;
import utils.Datasource;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class GestionCommandes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  


    Date r = new Date(2000, 11, 19) ;
  

  //   Panier p1=new Panier(54,11500,99);
    Panierservice pst=new Panierservice();
    //pst.delete(p1);
// pst.insert(p1);
    /*    pst.insert(p1);
            pst.insert(p1);
                pst.insert(p1);
                    pst.insert(p1);*/
    
   //   Panier p3=new Panier(53,99088,99);
    Panierservice psi=new Panierservice();    
 //  psi.delete(p3);
 //  psi.update(p3);
    
    
     Commande p7;
     //   Panier p=Panierservice.readByClientId(); 
       // p7 = new Commande("test2013","2014-04-22",p,getId());
        /*      p7 = new Commande("test10","2002-2-22",0066);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        
      
        p7.setListe_de_produits(list);
         Commandeservice ec = new Commandeservice();
         
         List<Commande> listc = new ArrayList<Commande>();
        ec.RechercheCommande("xpphh");

         
         Commandeservice psu=new Commandeservice();
    psu.insert(p7);*/
    Commande p23 = new Commande(90,"test555","5555-02-22",5);
    Commandeservice psr=new Commandeservice();
    psr.delete(90);
 //  psr.insert(p23);
 //  psr.update( p23);
    // Commande  p10 = new Commande(6,"xpphh",new Date(2010, 4, 20));
    Commandeservice pso=new Commandeservice();
    //pso.delete(p10);
   // pso.update( p23);
   // pso.insert(p10);
    }
    
}

