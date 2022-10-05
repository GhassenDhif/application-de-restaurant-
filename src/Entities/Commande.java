/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class Commande {
     private int id;
    private String titre;
    private String date_de_commande;
    private double prix;

    
    private int panierFK;
    private List<Integer> liste_de_produits;

    public Commande() {
    }

    public Commande(int id, String titre, String date_de_commande, double p, int panierFK, List<Integer> liste_de_produits) {
        this.id = id;
        this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.prix = p;
        this.panierFK = panierFK;
        this.liste_de_produits = liste_de_produits;
    }
    

     public Commande(int id, String titre, String date_de_commande,double prix) {
        this.id = id;
        this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.liste_de_produits=new ArrayList<Integer>();
    }
    public Commande(int id, String titre, String date_de_commande,int panierFk) {
        this.id = id;
        this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.panierFK=panierFk;
        this.liste_de_produits=new ArrayList<Integer>();
    }
    

    public Commande(String titre, String date_de_commande,double prix,int panierFk) {
        this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.panierFK=panierFk;
        this.liste_de_produits=new ArrayList<Integer>();

    }

    public Commande(String titre, String date_de_commande, double prix, int panierFK, List<Integer> liste_de_produits) {
        
        this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.prix = prix;
        this.panierFK = panierFK;
        this.liste_de_produits = liste_de_produits;
    }
    
     public Commande(int id ,String titre, String date_de_commande, double prix, int panierFK) {
        this.id = id;
         this.titre = titre;
        this.date_de_commande = date_de_commande;
        this.prix = prix;
        this.panierFK = panierFK;
      
    }
     
    
     
    
    

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate_de_commande() {
        return date_de_commande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate_de_commande(String date_de_commande) {
        this.date_de_commande = date_de_commande;
    }
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getPanierFK() {
        return panierFK;
    }

    public void setPanierFK(int panierFK) {
        this.panierFK = panierFK;
    }

    public List<Integer> getListe_de_produits() {
        return liste_de_produits;
    }

    public void setListe_de_produits(List<Integer> liste_de_produits) {
        this.liste_de_produits = liste_de_produits;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", titre=" + titre + ", date_de_commande=" + date_de_commande + ", prix=" + prix + ", panierFK=" + panierFK +'}';
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
        final Commande other = (Commande) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
    }


