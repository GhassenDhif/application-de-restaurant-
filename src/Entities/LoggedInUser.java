/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Utilisateur
 */
public class LoggedInUser {
    public static Utilisateur loggedInUser;
    /* mise a jour du loggedInUser lors de l'authentification*/
    public static void clearUtilisateur(){
        LoggedInUser.loggedInUser.setId(0);
        LoggedInUser.loggedInUser.setName("");
        LoggedInUser.loggedInUser.setPrenom("");
        LoggedInUser.loggedInUser.setNum_tel(0);
        LoggedInUser.loggedInUser.setAdress_email("");
    }
}
