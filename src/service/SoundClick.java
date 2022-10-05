/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.net.URL;
import javafx.scene.media.Media;
//import javafx.media.Media;
import javafx.scene.media.MediaPlayer;







/**
 *
 * @author youss
 */
public class SoundClick {
/********************************************************************************************************************************************************************************************************************/    
 
    public void playClick() {
    
    
        URL urlMusique = getClass().getResource("/Audio/Click.wav");
        Media mediaMusique = new Media(urlMusique.toString());
        MediaPlayer mediaPlayerMusique = new MediaPlayer(mediaMusique);
        mediaPlayerMusique.play();
        mediaPlayerMusique.setAutoPlay(true);
    }  
}
