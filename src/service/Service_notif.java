/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Categorie;

/**
 *
 * @author Asus
 */
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Service_notif {

    public void notif(String c) throws AWTException {
        if (SystemTray.isSupported()) {
            Service_notif td = new Service_notif();
            td.displayTray(c);
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(String c) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Conflit");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("", c+" "+" avec succeés", MessageType.INFO);
    }
}
