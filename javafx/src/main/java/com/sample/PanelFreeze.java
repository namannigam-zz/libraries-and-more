package com.sample;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
//import javafx.embed.swing.JFXPanel;

public class PanelFreeze {

    public static void main(String[] args) {
//        JFXPanel dummyPanel;
        TabPane dummyTabPane;
        Scene dummyScene;
        System.out.println("Creating JFX Panel");
//        dummyPanel = new JFXPanel();
        System.out.println("Creating  TabPane");
        dummyTabPane = new TabPane();
        System.out.println("Creating  Scene");
        dummyScene = new Scene(dummyTabPane);
        System.out.println("Setting  Scene");
//        dummyPanel.setScene(dummyScene); //Freezing here
        System.out.println("Scene Created");
    }
}