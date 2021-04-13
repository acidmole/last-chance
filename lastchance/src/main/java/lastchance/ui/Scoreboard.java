/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author hede
 */
public class Scoreboard implements Dashboard {
    
    private int score;
    private Pane layout;
    
    public Scoreboard() {
    
        this.score = 0;
        this.layout = new ScoreboardLayout();
    }
    
    public Node getNode() {
        return this.layout;
    }
    
    @Override
    public int getScore() {
        return this.score;
    }
    
    @Override
    public void setScore(int score) {
       this.score = (score);
    }
    
    public void raiseScore(int amount) {
        this.setScore(this.score + amount);
    }

    @Override
    public boolean quit() {
        return false; // vielä kesken
    }

    @Override
    public boolean restart() {
        return false; //vielä kesken
    }

    
    
}
