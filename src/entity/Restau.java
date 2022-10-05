/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
import entity.Categorie;
import java.util.logging.Logger;
import java.util.Date;

//import marytts.cart.CART;

/**
 *
 * @author amed1
 */
public class Restau {
    
    private int id;
    private String nom;
    private Date datef;
    private String local;
    private String image;
    private String descri;
    private Categorie id_cat ; 
    private int id_us;
    private String type;
    private String nbr;
    private int connect;
   // private String cat;

    public int getConnect() {
        return connect;
    }

    public void setConnect(int connect) {
        this.connect = connect;
    }

    public Restau(int id, String nom, Date datef, String local, String image, String descr, String type) {
        this.id = id;
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.image = image;
        this.descri = descr;
        this.type=type;
    }

   

    public Restau(String type, String nbr) {
            this.type = type;
              this.nbr = nbr;    
    }
    

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   

    public Restau(int id) {
        this.id = id;
    }

    public int getId_us() {
        return id_us;
    }

    public void setId_us(int id_us) {
        this.id_us = id_us;
    }

    public Restau(String nom, Date datef, String local, String image, String descr, Categorie id_cat, int id_us) {
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.image = image;
        this.descri = descr;
        this.id_cat = id_cat;
        this.id_us = id_us;
    }

    
     //this.id_cat.setId(id_cat);
    
//    public Restau(int id, String nom, String datef, String local, String image, String descr) {
//        this.id = id;
//        this.nom = nom;
//        this.datef = datef;
//        this.local = local;
//        this.image = image;
//        this.descr = descr;
//
//    }

    public Restau(String nom, Date datef, String local, String image, String descr) {
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.image = image;
        this.descri = descr;
    }
  
    
    
    
//    public Restau(String nom, String datef, String local, String image, String descr, String id_cat) { /*Categorie id_cat*/
//    
//        this.nom = nom;
//        this.datef = datef;
//        this.local = local;
//        this.image = image;
//        this.descr = descr;
//        this.id_cat.setType(id_cat);
//        //this.id_cat.setId(id_cat);
//    }

    public Restau(String nom, String local, String descr, Categorie id_cat) {
        this.nom = nom;
        this.local = local;
        this.descri = descr;
        this.id_cat = id_cat;
    }

    public Restau(int id, String nom, Date datef, String local, String image, String descr, Categorie id_cat) {
        this.id = id;
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.image = image;
        this.descri = descr;
        this.id_cat = id_cat;
    }
    
    
    
    
    
    
    
    public Restau(String nom, Date datef, String local, String image, String descr, Categorie id_cat) { /*Categorie id_cat*/
    
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.image = image;
        this.descri = descr;
        this.id_cat=id_cat;
        //this.id_cat.setId(id_cat);
    }
    
//    public Restau(String nom, String datef, String local, String image, String descr) {
//    
//        this.nom = nom;
//        this.datef = datef;
//        this.local = local;
//        this.image = image;
//        this.descr = descr;
//     
//    }



    public Restau() {
       
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatef() {
        return datef;
    }

    public void setDatef(Date datef) {
        this.datef = datef;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescr() {
        return descri;
    }

    public void setDescr(String descr) {
        this.descri = descr;
    }

   /* public Categorie getcat() {
        return id_cat;
    }
*/
    
      public Categorie getcat() {
        return id_cat;
    }
    
    public void setId_cat(Categorie id_cat) {
        this.id_cat = id_cat;
    }

    @Override
    public String toString() {
        return "Restau{" + "id=" + id + ", nom=" + nom + ", datef=" + datef + ", local=" + local + ", image=" + image + ", descr=" + descri + ", id_cat=" + id_cat + '}';
    }

    
/*
    public void setId_cat(Categorie id_cat) {
        this.id_cat = id_cat;
    }
*/
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restau other = (Restau) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_us != other.id_us) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.datef, other.datef)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.descri, other.descri)) {
            return false;
        }
        if (!Objects.equals(this.id_cat, other.id_cat)) {
            return false;
        }
        return true;
    }

    public Restau(String nom, Date datef, String local, String descr) {
        this.nom = nom;
        this.datef = datef;
        this.local = local;
        this.descri = descr;
    }

    
    
    
    
}
