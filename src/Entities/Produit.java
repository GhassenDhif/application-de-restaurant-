/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author User
 */
public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int categorie_id;
    private String image;
    private String description;

    public Produit() {
    }

    public Produit(int id, String nom, double prix, int categorie_id, String image, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.categorie_id = categorie_id;
        this.image = image;
        this.description = description;
    }
    
       public Produit(int id, int categorie_id,String nom, String description , String image, double prix) {
        this.id = id;
       this.categorie_id = categorie_id;
    this.nom = nom;
         this.description = description;
        this.image = image;
       
       
              this.prix = prix;
    }

    public Produit(String nom, double prix, int categorie_id, String image, String description) {
        this.nom = nom;
        this.prix = prix;
        this.categorie_id = categorie_id;
        this.image = image;
        this.description = description;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
