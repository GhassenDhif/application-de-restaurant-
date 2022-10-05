/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Restau;
import java.util.List;

/**
 *
 * @author amed1
 */
public interface IService<T> {
    
   void insert(T t);
   void delete(T t);
   
   void update(T t);
   List<T>read();
    
   List<Restau>readByNom();
    
  //void rechercher(Restau p,String nom);
    
}
