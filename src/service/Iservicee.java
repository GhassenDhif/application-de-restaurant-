/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author HP
 */
public interface Iservicee<T> {
   void delete(T t);
   
   void update(T t);
    List<T>read();
    //readById(int id);
    T readById(int id);
    T recherche(int id);
}
