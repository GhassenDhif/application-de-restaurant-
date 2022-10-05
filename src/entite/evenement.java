/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class evenement {

  
     private int id;
     private String date;
     private String nom;
     private String image;
     private reservation res;   
     
     
    public evenement() {
    }

    public evenement(int id, String nom, String date, String image) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.image = image;
    }

    public evenement(String nom, String date,  String image) {
        
        this.nom = nom;
        this.date = date;
        this.image = image;
    
    }
    
    public evenement(String nom, String date) {
        
        this.nom = nom;
        this.date = date;
    
    }

    public evenement(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", son nom =" + nom + ", sa date =" + 
                date + "image =" + image + '}';
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
        final evenement other = (evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
