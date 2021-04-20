/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

public class Scoreboard {
    
    private int score;
    
    public Scoreboard() {
    
        this.score = 0;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = (score);
    }
    
    public void raiseScore(int amount) {
        this.setScore(this.score + amount);
    }
}