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

public class reservation {
    private int id;
    private String date;
    private String des;
    

    public reservation() {
    }

    public reservation(int id, String date, String des) {
        this.id = id;
        this.date = date;
        this.des = des;
    
    }

    public reservation(String date, String des) {
        
        this.date = date;
        this.des = des;
    
    }

    public int getId() {
        return id;
    }

   
    public String getDate() {
        return date;
    }

    public String getDes() {
        return des;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", date=" + date + ", description=" + des + '}';
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
        final reservation other = (reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
