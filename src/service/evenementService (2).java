///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package service;
//
//import entite.evenement;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import utils.Datasource;
//
///**
// *
// * @author HP
// */
//public class evenementService {
//    private Connection conn;
//    private Statement ste;
//    private PreparedStatement pst;
//    private ResultSet rs;
//    
//     public evenementService() {
//        conn = Datasource.getInstance().getCnx();
//    }
//
//    public void inserEvenementPst(evenement e) {
//        
//        String req = "insert into evenement (date_debut,date_fin,sujet,nbre_place) values (?,?,?,?)";
//        
//        try {
//            pst = conn.prepareStatement(req);
//            pst.setDate(1,e.getDate_debut());
//            pst.setDate(2,e.getDate_fin());
//            pst.setString(3, e.getSujet());
////            pst.setString(4, e.getNom_resto());
//            pst.setInt(4, e.getNbre_place());
//            pst.executeUpdate();
//
//            } catch (SQLException ex) {
//            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void delete(int id) {
//     
//        try {
//            PreparedStatement pt =conn.prepareStatement("delete from evenement where id =?");
//            pt.setInt(1,id);
//            pt.execute();
//            //UpdateTable();
//            
//        } catch (SQLException ex) {
//            System.out.println("amine");
//        }   
//    }
//
//    public void update(int id , String m ,int p)
//       
////     {  String pnom =e.getNom_resto(); 
//   
//    {
//    String req = "UPDATE evenement SET sujet=? ,nbre_place=? WHERE id =?";
//   
//      try {
//          
//            pst = conn.prepareStatement(req);
//            pst.setString(1,m);
//            pst.setInt(2,p);
//            pst.setInt(3,id);
//            //pst.setInt(5, r.getId_cat());
//            //pst.setString(6, e.getNom_resto());            
//           //   pst.setInt(3,i);
//           pst.execute();
//           
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public List<evenement> read() {
//    
//        String req="select * from evenement";
//        
//        List<evenement> list=new ArrayList<>();
//        try {
//            ste=conn.createStatement();
//            rs= ste.executeQuery(req);
//            while(rs.next()){
//                list.add(new evenement(rs.getInt("id"), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
//            }
//            } catch (SQLException ex) {
//            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
//
//    public evenement readById(evenement e,int id) {
//       
//        String req="select * from evenement WHERE id= '"+ e.getId() +"'";
//        //List<evenement> list=new ArrayList<>();
//        
//        evenement event=null;
//        try {
//            ste=conn.createStatement();
//            rs= ste.executeQuery(req);
//            while(rs.next()){
//                event=new evenement(rs.getInt("id"), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
//            }
//            } catch (SQLException ex) {
//            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return event;
//    }
//    
//    public List<evenement> Rechercher(String sujet) {
//         String req="select * from `evenement` where sujet='"+ sujet +"'";
//         List<evenement> list = new ArrayList<>();
//        try {
//            ste=conn.createStatement();
//            rs= ste.executeQuery(req);
//            while(rs.next()){
//                list.add(new evenement(rs.getInt(1), rs.getDate(2), rs.getDate(3),rs.getString(4),rs.getInt(5)));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;
//    }
//}