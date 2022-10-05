/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class Panieraffiche {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty prix;
    private final SimpleStringProperty clientid;
    
    

    public Panieraffiche() {
        this.id=null;
        this.prix=null;
        this.clientid=null;
       
    }


    public Panieraffiche(String id, String prix, String clientid) {
        this.id = new SimpleStringProperty(id);
        this.prix = new SimpleStringProperty(prix);
        this.clientid = new SimpleStringProperty(clientid);
        
    }



    public String getId() {
        return id.get();
    }

    public String getPrix() {
        return prix.get();
    }

    public String getClientid() {
        return clientid.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setPrix(String prix) {
        this.prix.set(prix);
    }

    public void setClientid(String clientid) {
        this.clientid.set(clientid);
    }

    @Override
    public String toString() {
        return "Panieraffiche{" + "id=" + id + ", prix=" + prix + ", clientid=" + clientid + '}';
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
        final Panieraffiche other = (Panieraffiche) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }



    
    
}
