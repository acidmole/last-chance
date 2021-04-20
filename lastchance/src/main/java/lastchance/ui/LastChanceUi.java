/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import lastchance.ui.gun.Gun;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lastchance.ui.gun.Gunshot;
import lastchance.ui.robot.Robot;

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
        
        Image image = new Image("file:robo.png");
        
        List<Robot> robots = new ArrayList<>();
        robots.add(new Robot(image, 40, 20, 800, 600));
        robots.add(new Robot(image, 40, 20, 800, 600));
        robots.add(new Robot(image, 40, 20, 800, 600));
        robots.forEach(robot -> layout.getChildren().add(robot.getImage()));
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
            
                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.LEFT) {
                            gun.rotateLeft();
                        }
                        if (event.getCode() == KeyCode.RIGHT) {
                            gun.rotateRight();
                        }
                        if (event.getCode() == KeyCode.UP) {
                            layout.getChildren().add(gun.fire());
                        }
                    }
                });
                robots.forEach(robot -> robot.move());
                gun.getGunshots().forEach(gunshot -> gunshot.move());
                gun.getGunshots().forEach(gunshot -> {
                    for(Robot robot : robots) {
                        if(gunshot.intersects(robot.getImage().getBoundsInParent())) {
                            robot.destroy();
                            gunshot.destroy();
                        }
                    }
                });
                List<Gunshot> destroyedGunshots = gun.getGunshots().stream()
                        .filter(gunshot -> gunshot.isDestroyed())
                        .collect(Collectors.toList());
                destroyedGunshots.forEach(gunshot -> {
                    gun.getGunshots().remove(gunshot);
                    System.out.println(gunshot.isDestroyed());
                    layout.getChildren().remove(gunshot);
                });
                
                List<Robot> destroyedRobots = robots.stream()
                        .filter(robot -> robot.isDestroyed())
                        .collect(Collectors.toList());
                destroyedRobots.forEach((robot) -> {
                    robots.remove(robot);
                    layout.getChildren().remove(robot.getImage());
                });
            }
        }.start();

    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
