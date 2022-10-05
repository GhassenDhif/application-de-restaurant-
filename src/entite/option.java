/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.util.Date;

/**
 *
 * @author HP
 */
public class option {
    private int code;
    private String nom;
    private double prix;
    

    public option() {
    }

    public option(int code, String nom, double prix) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
    
    }

    public option(String nom, double prix) {
        
        this.nom = nom;
        this.prix = prix;
    
    }

    public int getCode() {
        return code;
    }

   
    public String getNom() {
        return nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "option{" + "code=" + code + ", nom=" + nom + ", prix=" + prix + '}';
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
        final option other = (option) obj;
        if (this.code != other.code) {
            return false;
        }
        return true;
    }
}
