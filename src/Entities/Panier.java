/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Panier {
  
    private int id;
    
    private double prix;
    private int clientid;
    
    
    
    public Panier() {
    }
    
    public Panier(int id,   double prix ,int clientid) {
        this.id = id;
 
        this.clientid = clientid;
        this.prix = prix;
    }
    public Panier( double prix,int clientid) {
        
 
      this.clientid = clientid;
        this.prix = prix;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }
    

    public int getId() {
        return id;
    }

   



    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", prix=" + prix + ", clientid=" + clientid + '}';
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
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    }  

