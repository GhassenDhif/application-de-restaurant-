/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author HP
 */
public class reservationService {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
     public reservationService() {
        conn = Datasource.getInstance().getCnx();
    }

    public void inserReservPst(reservation res) {
        
        String req = "insert into reservation (date,des,evenement_id) values (?,?,27)";
        
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1,res.getDate());
            pst.setString(2,res.getDes());
            pst.executeUpdate();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void delete(int id) {
        
        try {
           PreparedStatement pt =conn.prepareStatement("delete from reservation where id =?");
            pt.setInt(1,id);
            pt.execute();
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
    }

    public void update(int id , String date ,String des){
    String req = "UPDATE `reservation` SET `date`=?,`des`=? WHERE `id`=?";
      try {
            pst = conn.prepareStatement(req);
            pst.setString(1,date);
            pst.setString(2,des);
            pst.setInt(3,id);
            pst.execute();   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<reservation> read() {
        
        String req="select * from `reservation`";
        
        List<reservation> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new reservation(rs.getInt("id"), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    

    public reservation  readById(reservation res,int id) {
      
        String req="select * from reservation  WHERE id= '"+ res.getId() +"'";

        reservation re=null;
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                re=new reservation(rs.getInt("id"), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return re;
    }

      public List<reservation> Rechercher(String des) {
         String req="select * from `reservation` where des='"+ des +"'";
         List<reservation> list = new ArrayList<>();
        try {
            ste=conn.createStatement();
            rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new reservation(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
