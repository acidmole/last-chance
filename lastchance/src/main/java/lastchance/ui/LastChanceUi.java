/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import java.io.FileNotFoundException;
import lastchance.ui.gun.Gun;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lastchance.audio.Jukebox;
import lastchance.dao.FileScoreDao;
import lastchance.domain.LastChanceService;
import lastchance.ui.gun.Gunshot;
import lastchance.ui.robot.Robot;


/**
 *
 * @author hede
 */
public class LastChanceUi extends Application {
    
    private LastChanceService lcService;
    private Jukebox jukebox;
    private boolean paused;
    
    @Override
    public void init() {
        String[] configs = {"10", "0.001"};
        try {
            FileScoreDao scoreDao = new FileScoreDao("scores.txt");
            lcService = new LastChanceService(scoreDao, configs);
            jukebox = new Jukebox("Stakula_Nights.mp3");
        } catch (Exception e) {
        }
        paused = true;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Pane layout = new Pane();
        layout.setPrefSize(800, 600);
        Base base = new Base();
        layout.getChildren().add(base);
        Gun gun = new Gun(395, 495, 12, 160);
        layout.getChildren().add(gun);
        
        ScoreboardLayout sbLayout = new ScoreboardLayout();

        layout.getChildren().add(sbLayout);
        sbLayout.setPrefWidth(800);
        sbLayout.setPrefHeight(50);
        
        ArrayList<Robot> robots = new ArrayList<>();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                robots.forEach(robot -> {
                    robot.move();
                    if(base.intersects(robot.getImage().getBoundsInParent())) {
                        jukebox.close();
                        this.stop();
                    }
                });

                gun.getGunshots().forEach(gunshot -> {
                    gunshot.move();
                    robots.forEach(robot -> {
                        if(gunshot.getCenterY() < 50 || gunshot.getCenterX() < 0 || gunshot.getCenterX() > 800) {
                            gunshot.destroy();
                        } else if(gunshot.intersects(robot.getImage().getBoundsInParent())) {
                            robot.destroy();
                            gunshot.destroy();
                            lcService.addPoints();
                            sbLayout.setScore(lcService.getScoreboard().getScore());
                        }
                    });
                });
                
                List<Gunshot> destroyedGunshots = gun.getGunshots().stream()
                        .filter(gunshot -> gunshot.isDestroyed())
                        .collect(Collectors.toList());
                destroyedGunshots.forEach(gunshot -> {
                    gun.getGunshots().remove(gunshot);
                    layout.getChildren().remove(gunshot);
                });
                
                List<Robot> destroyedRobots = robots.stream()
                        .filter(robot -> robot.isDestroyed())
                        .collect(Collectors.toList());
                destroyedRobots.forEach((robot) -> {
                    robots.remove(robot);
                    layout.getChildren().remove(robot.getImage());
                });
                
                if(lcService.hasNewRobotAppeared()) {
                    Robot robot = new Robot(40, 20, 800, 600);
                    layout.getChildren().add(robot.getImage());
                    robots.add(robot);
                }
                
            }
            
        };
        
        // main scene
        
        Scene mainScene = new Scene(layout);
        
        mainScene.setOnMouseClicked((MouseEvent event) -> {
            if(!paused) {
                layout.getChildren().add(gun.fire());
            }
        });
        
        mainScene.setOnMouseMoved((MouseEvent event) -> {
            if(!paused) {
                gun.rotateWithMouse(event.getX(), event.getY());
            }
        });
        
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(!paused) {
                    if (event.getCode() != null) switch (event.getCode()) {
                        case LEFT:
                            gun.rotateLeft();
                            break;
                        case RIGHT:
                            gun.rotateRight();
                            break;
                        case UP:
                            layout.getChildren().add(gun.fire());
                            break;
                        case ESCAPE:
                            paused = true;
                            timer.stop();
                            jukebox.pause();
                            break;
                        default:
                            break;
                    }
                } else {
                    if(event.getCode() != null) {
                        paused = false;
                        timer.start();
                        try {
                            Jukebox newJukebox = new Jukebox("Stakula_Nights.mp3");
                            newJukebox.start();
                        } catch (FileNotFoundException e) {
                            
                        }
                    }
                }
            }
        });

        
        // paused scene
        Pane pauseLayout = new Pane();
        pauseLayout.setPrefSize(800, 600);
        Scene pauseScene = new Scene(pauseLayout);
        

        stage.setMaxHeight(600);
        stage.setMaxWidth(800);

        stage.setScene(mainScene);
        stage.show();
        paused = false;
        jukebox.start();
        timer.start();
                
        

        
        }
    
    @Override
    public void stop() {
        if(lcService.quit("Niilo N.")) {
            System.out.println("A-ok");
        } else {
            System.out.println("Not ok!");
        }
        jukebox.close();
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
