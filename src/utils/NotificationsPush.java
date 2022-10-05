/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Dr-Eagle
 */
public class NotificationsPush {
 /********************************************************************************************************************************************************************************************************************/   
    public void notifpush(String titre , String text)
    {
        Image img = new Image("/Images/lol.png");
             Notifications notificationBuilder = Notifications.create()
                .title(titre)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(javafx.util.Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
           }
       });
                   
           notificationBuilder.darkStyle();
            notificationBuilder.show();
    }
    
 /********************************************************************************************************************************************************************************************************************/   
     public void notifpushno(String titre , String text)
    {
        Image img = new Image("/Images/no.png");
             Notifications notificationBuilder = Notifications.create()
                .title(titre)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(javafx.util.Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
           }
       });
                   
           notificationBuilder.darkStyle();
            notificationBuilder.show();
    }
     
     public void notifmain(){
          Image img = new Image("/Images/LOGO.png");
             Notifications notificationBuilder = Notifications.create()
                
                .graphic(new ImageView(img))
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              
           }
       });
                   
           notificationBuilder.darkStyle();
            notificationBuilder.show();
     }
    
}
