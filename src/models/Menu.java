/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author USER
 */
public class Menu {
   private  int id ;
   private String Nom ; 
   private int Prix ;
   private int categorie_id ;
   private String Image; 
   private String Description;

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDescription() {
        return Description;
    }

    public Menu(int id, String Nom, int Prix, int categorie_id, String Image, String Description) {
        this.id = id;
        this.Nom = Nom;
        this.Prix = Prix;
        this.categorie_id = categorie_id;
        this.Image = Image;
        this.Description = Description;
    }

    public Menu(String Nom, int Prix, int categorie_id, String Image, String Description) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.categorie_id = categorie_id;
        this.Image = Image;
        this.Description = Description;
        }

    public Menu(String Nom, int Prix, int categorie_id, String Image) {
        this.Nom = Nom;
        this.Prix = Prix;
        this.categorie_id = categorie_id;
        this.Image = Image;
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public int getPrix() {
        return Prix;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public String getImage() {
        return Image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
   
}
