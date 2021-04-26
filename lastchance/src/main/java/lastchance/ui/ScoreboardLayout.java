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

/**
 * This class is purely for layout of the scoreboard. 
 * @author hede
 */

public class ScoreboardLayout extends HBox {
    
    private Button pause;
    private Button restart;
    private Label score;

    
    public ScoreboardLayout() {
        super(10);
        this.setSpacing(80);
        this.pause = new Button("Quit");
        this.restart = new Button("Restart");
        this.score = new Label("Pisteet: ");
        
        this.getChildren().add(this.pause);
        this.getChildren().add(this.restart);
        this.getChildren().add(this.score);
        
    }
    
    public void setScore(int score) {
        this.score.setText("Pisteet: " + score);
    }
}