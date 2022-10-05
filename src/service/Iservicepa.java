/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
/**
 *
 * @author User
 */
public interface Iservicepa<T> {
  
    void insert(T t);
     void update(T t);
      List<T>read();
        void updateperIds(int value1, double value2, int value3 );
       void delete(int id);
      T readById(int id);
    
}
