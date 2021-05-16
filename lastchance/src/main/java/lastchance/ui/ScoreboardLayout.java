/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

/**
 *
 * @author hede
 */
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class is purely for layout of the scoreboard. 
 * @author hede
 */

public class ScoreboardLayout extends HBox {
    
    private Label score;
    private ButtonGrid buttonGrid;
    public ScoreboardLayout() {
        super();
        this.score = new Label("Pisteet: ");
        buttonGrid = new ButtonGrid();
        try {
            this.score.setFont(Font.loadFont("file:ErbosDraco1StNbpRegular-99V5.ttf", 60));
        } catch (Exception e) {
        }
        this.getChildren().add(buttonGrid);
        this.getChildren().add(this.score);
    }
    
    public void setScore(int score) {
        this.score.setText("Pisteet: " + score);
    }
    
    public ButtonGrid getButtonGrid() {
        return this.buttonGrid;
    }
    
}