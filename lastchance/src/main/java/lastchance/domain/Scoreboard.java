/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

/**
 *
 * Keeps track for the score
 */
public class Scoreboard {
    
    private int score;
    
    public Scoreboard() {
        this.score = 0;
    }
    
    /**
     *
     * @return the actual score
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * A method for e.g. restarting the game
     * 
     * @param score new score
     */
    public void setScore(int score) {
        this.score = (score);
    }
    
    /**
     * 
     * @param amount amount to be added to the score
     */
    public void raiseScore(int amount) {
        this.setScore(this.score + amount);
    }
}