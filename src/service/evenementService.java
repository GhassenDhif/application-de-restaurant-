/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.evenement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.CharArrayReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import utils.Datasource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.stage.FileChooser;

/**
 *
 * @author HP
 */
public class evenementService{
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
     public evenementService() {
        conn = Datasource.getInstance().getCnx();
    }

    public void inserEvenementPst(evenement e) {
        
        String req = "insert into evenement (nom,date,image) values (?,?,?)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1,e.getNom());
            pst.setString(2,e.getDate());
            pst.setString(3,e.getImage());
            pst.executeUpdate();

            } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int id) {
     
        try {
            PreparedStatement pt =conn.prepareStatement("delete from evenement where id =?");
            pt.setInt(1,id);
            pt.execute();
            //UpdateTable();
            
        } catch (SQLException ex) {
            System.out.println("");
        }   
    }

    public void update(int id , String n, String d, String i)
         
    {
    String req = "UPDATE evenement SET nom=? ,date=? ,image=? WHERE id =?";
   
      try {
          
            pst = conn.prepareStatement(req);
            pst.setString(1,n);
            pst.setString(2,d);
            pst.setString(3,i);
            pst.setInt(4,id);
            pst.execute();
                 
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<evenement> read() {
    
        String req="select * from evenement";
        
        List<evenement> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new evenement(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    

    public evenement readById(evenement e,int id) {
       
        String req="select * from evenement WHERE id= '"+ e.getId() +"'";
        //List<evenement> list=new ArrayList<>();
        
        evenement event=null;
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                event=new evenement(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }
    
    public List<evenement> Rechercher(String nom) {
         String req="select * from `evenement` where nom='"+ nom +"'";
         List<evenement> list = new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new evenement(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}