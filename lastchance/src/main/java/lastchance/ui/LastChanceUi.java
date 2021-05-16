/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import java.io.FileInputStream;
import lastchance.ui.gun.Gun;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lastchance.audio.Jukebox;
import lastchance.dao.FileScoreDao;
import lastchance.domain.LastChanceService;
import lastchance.ui.gun.Gunshot;
import lastchance.ui.robot.Robot;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;


/**
 *
 * @author hede
 */
public class LastChanceUi extends Application {
    
    private LastChanceService lcService;
    private Jukebox jukebox;
    private boolean paused;
    private int jukeboxPauseFrame;
    private ArrayList<Robot> robots;
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("lastchance.properties"));
        
        jukebox = new Jukebox("Stakula_Nights.mp3", 0);
        FileScoreDao scoreDao = new FileScoreDao("scores.txt");
        lcService = new LastChanceService(scoreDao, properties);
        robots = new ArrayList<>();
        jukeboxPauseFrame = 0;
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
        Scene mainScene = new Scene(layout);

        TextInputDialog scoreDialog = new TextInputDialog();
        scoreDialog.setHeaderText("Tuloksesi oli " + lcService.sortAndFind() + ". paras! Anna vielä nimesi.");

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                robots.forEach(robot -> {
                    robot.move();
                    if(base.intersects(robot.getImage().getBoundsInParent())) {
                        jukebox.pause();
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
        
        // continue button 
        Button cont = new Button("Continue");
        cont.setFont(Font.font("Verdana", 40));
        cont.setLayoutX(300);
        cont.setLayoutY(200);
        cont.setVisible(false);
        layout.getChildren().add(cont);
        
        
        //button action listeners
        cont.setOnAction((event) -> {
            cont.setVisible(false);
            paused = false;
            Jukebox newJukebox = new Jukebox("Stakula_Nights.mp3", 0);
            jukebox = newJukebox;
            jukebox.start();
            timer.start();
        });
        
        sbLayout.getButtonGrid().getQuitButton().setOnAction((event) -> {
            timer.stop();
            Optional<String> result = scoreDialog.showAndWait();
            if(result.isPresent()) {
                lcService.quit(scoreDialog.getEditor().getText());
                this.stop();
                stage.close();
            } else {
                timer.start();
            }
        });
        
        sbLayout.getButtonGrid().getRestartButton().setOnAction((event) -> {
            timer.stop();
            jukebox.pause();
            Optional<String> result = scoreDialog.showAndWait();
            if(result.isPresent()) {
                lcService.quit(scoreDialog.getEditor().getText());
                lcService.restart();
                layout.getChildren().clear();
                layout.getChildren().add(base);
                layout.getChildren().add(gun);
                layout.getChildren().add(sbLayout);
                robots.clear();
                gun.getGunshots().clear();
            }
            Jukebox anotherJukebox = new Jukebox("Stakula_Nights.mp3", 0);
            jukebox = anotherJukebox;
            scoreDialog.showAndWait();
            jukebox.start();
            timer.start();
        });

        
        // main scene
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
        
        mainScene.setOnMouseExited((MouseEvent event ) -> {
            if(!paused) {
                paused = true;
                timer.stop();
                jukeboxPauseFrame = jukebox.pause();
                cont.setVisible(true);
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
                            jukeboxPauseFrame = jukebox.pause();
                            cont.setVisible(true);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        
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
        jukebox.pause();
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

}
