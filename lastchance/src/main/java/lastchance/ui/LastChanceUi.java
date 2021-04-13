/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author hede
 */
public class LastChanceUi extends Application {
    

    @Override
    public void start(Stage stage) {
        Pane layout = new Pane();
        layout.setPrefSize(800, 600);
        Scoreboard sb = new Scoreboard();
        layout.getChildren().add(sb.getNode());
        layout.getChildren().add(new Base());
        Gun gun = new Gun();
        layout.getChildren().add(gun);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
        
        scene.setOnMousePressed((MouseEvent event) -> {
            if(event.isPrimaryButtonDown())
                gun.rotateLeft();
            
            if(event.isSecondaryButtonDown())
                gun.rotateRight();
        
        });

    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
