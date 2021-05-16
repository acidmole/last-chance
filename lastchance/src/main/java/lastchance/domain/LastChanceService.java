/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import lastchance.dao.FileScoreDao;

/**
 * The service for managing everything but the UI
 */
public class LastChanceService {
    
    private Scoreboard sb;
    private FileScoreDao scoreDao;
    private final int hitValue;
    private double robotAppearProbability;
    private final double originalRobotAppearProbability;
    Random rnd;
    
    /**
     * Constructor sets score value for a succesful gunshot
     * It also sets the probability of generating new robots on every frame
     * 
     * @param scoreDao Data Access Object to store and load the score history
     * @param properties
     */
    public LastChanceService(FileScoreDao scoreDao, Properties properties) {
        this.scoreDao = scoreDao;
        this.sb = new Scoreboard();
        this.hitValue = Integer.valueOf(properties.getProperty("hitValue"));
        this.robotAppearProbability = Double.valueOf(properties.getProperty("robotAppearProbability"));
        this.originalRobotAppearProbability = this.robotAppearProbability;
        rnd = new Random();
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
     * @return if UI has to draw a new robot
     */
    public boolean hasNewRobotAppeared() {
        return (rnd.nextDouble() < this.robotAppearProbability);
    }
    
    /**
     * Game restart method, restarting scoreboard and robot appearing probability
     * 
     * @return if restart and file storing was succesful
     */
    public boolean restart() {
        this.robotAppearProbability = originalRobotAppearProbability;
        this.sb.setScore(0);
        return false;
    }
    
    public int sortAndFind() {
        ArrayList<Integer> justScores = new ArrayList<>();
        
        for(String scoreAndName : scoreDao.getTopScores()) {
            String[] score = scoreAndName.split(";");
            int scoreInt = Integer.valueOf(score[1]);
            justScores.add(scoreInt);
        }
        
        Collections.sort(justScores, Collections.reverseOrder());
        
        int i=1;
        for(Integer score : justScores) {
            if(score > this.sb.getScore()) {
                i++;
            } else break;
        }
        return i;
    }
}
