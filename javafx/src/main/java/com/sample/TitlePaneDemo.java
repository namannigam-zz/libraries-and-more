package com.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TitlePaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        TilePane root = new TilePane(5, 5);
        root.setPrefColumns(4);
        root.setPrefRows(4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double size = 5 + 30 * Math.random();
                root.getChildren().add(new Rectangle(size, size, (i + j) % 2 == 0 ? Color.RED : Color.BLUE));
            }
        }

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle(root.getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}