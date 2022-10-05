/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class Commandeaffiche {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty titre;
    private final SimpleStringProperty date_de_commande;
    private final SimpleStringProperty prix;
    private final SimpleStringProperty panierFK;
    

    public Commandeaffiche() {
        this.id=null;
        this.titre=null;
        this.date_de_commande=null;
        this.prix=null;
        this.panierFK=null;
    }


    public Commandeaffiche(String id, String titre, String date_de_commande,String prix,String panierFk1) {
        this.id = new SimpleStringProperty(id);
        this.titre = new SimpleStringProperty(titre);
        this.date_de_commande = new SimpleStringProperty(date_de_commande);
        this.prix = new SimpleStringProperty(prix);
        this.panierFK=new SimpleStringProperty(panierFk1);
    }
  public String getPrix() {
        return prix.get();
    }
    
 public void setPrix(String Prix) {
        this.prix.set(Prix);
    }


    public String getId() {
        return id.get();
    }

    public String getTitre() {
        return titre.get();
    }

    public String getDate_de_commande() {
        return date_de_commande.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public void setDate_de_commande(String date_de_commande) {
        this.date_de_commande.set(date_de_commande);
    }

    public String getPanierFK() {
        return panierFK.get();
    }

    public void setPanierFK(String panierFK) {
        this.panierFK.set(panierFK);
    }


    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", titre=" + titre + ", date_de_commande=" + date_de_commande +  ", prix=" + prix +", panierFK=" + panierFK + '}';
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
        final Commandeaffiche other = (Commandeaffiche) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
