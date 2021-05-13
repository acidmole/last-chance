/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.scene.layout.Pane;

/**
 *
 * @author hede
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class is purely for layout of the scoreboard. 
 * @author hede
 */

public class ScoreboardLayout extends HBox {
    
    private Button quit;
    private Button restart;
    private Label score;
    
    public ScoreboardLayout() {
        super(10);
        this.setSpacing(80);
        this.quit = new Button("Quit");
        this.restart = new Button("Restart");
        this.score = new Label("Pisteet: ");
        
        try {
            this.score.setFont(Font.loadFont("file:ErbosDraco1StNbpRegular-99V5.ttf", 60));
        } catch (Exception e) {
            
        }
        
        this.getChildren().add(this.quit);
        this.getChildren().add(this.restart);
        this.getChildren().add(this.score);
        
    }
    
    public void setScore(int score) {
        this.score.setText("Pisteet: " + score);
    }
}