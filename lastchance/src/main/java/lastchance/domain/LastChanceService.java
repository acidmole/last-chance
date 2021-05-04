/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

import lastchance.dao.FileScoreDao;
import lastchance.dao.ScoreDao;

/**
 * The service for managing everything but the UI
 */
public class LastChanceService {
    
    private Scoreboard sb;
    private ScoreDao scoreDao;
    private int hitValue;
    private double robotAppearProbability;
    
    /**
     * Constructor sets score value for a succesful gunshot
     * It also sets the probability of generating new robots on every frame
     * 
     * @param scoreDao Data Access Object to store and load the score history
     * @param args arguments to run the app
     */
    public LastChanceService(ScoreDao scoreDao, String[] args) {
        this.scoreDao = scoreDao;
        this.sb = new Scoreboard();
        this.hitValue = Integer.valueOf(args[0]);
        this.robotAppearProbability = Double.valueOf(args[1]);
    }
    
    /**
     * @return scoreboard with the actual score
     */
    public Scoreboard getScoreboard() {
        return this.sb;
    }
    
    /**
     * Quit method writes best game score to hard disk
     *
     * @param name player name to store with score
     * @return if the score storing actually was succesful
     */
    public boolean quit(String name) {
        this.scoreDao.addToList(name, this.sb.getScore());
        try {
            this.scoreDao.writeToFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Add points to ScoreBoard
     *
     * @return tells UI the if probability of generating new robots has risen
     */
    public double addPoints() {
        this.sb.raiseScore(hitValue);
        if (this.sb.getScore() % 100 == 0) {
            this.robotAppearProbability += 0.001;
        }
        return this.robotAppearProbability;
    }
    
    /**
     *
     * @return tells UI the probability of generating new robots
     */
    public double getRobotAppearProbability() {
        return this.robotAppearProbability;
    }
    
    // not yet finished (or even started)

    /**
     * Game restart method
     * 
     * @return if restart and file storing was succesful
     */
    public boolean restart() {
        this.sb.setScore(0);
        return false;
    }
}
