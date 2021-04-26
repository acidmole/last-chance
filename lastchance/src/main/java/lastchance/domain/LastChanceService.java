/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

import lastchance.dao.FileScoreDao;
import lastchance.dao.ScoreDao;

/**
 *
 * @author hede
 */
public class LastChanceService {
    
    private Scoreboard sb;
    private ScoreDao scoreDao;
    private int hitValue;
    private double robotAppearProbability;
    
    public LastChanceService(ScoreDao scoreDao, String[] args) {
        this.scoreDao = scoreDao;
        this.sb = new Scoreboard();
        this.hitValue = Integer.valueOf(args[0]);
        this.robotAppearProbability = Double.valueOf(args[1]);
    }
    
    public Scoreboard getScoreboard() {
        return this.sb;
    }
    
    public boolean quit(String name) {
        this.scoreDao.addToList(name, this.sb.getScore());
        try {
            this.scoreDao.writeToFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public double addPoints() {
        this.sb.raiseScore(hitValue);
        if (this.sb.getScore() % 100 == 0) {
            this.robotAppearProbability += 0.0005;
        }
        return this.robotAppearProbability;
    }
    
    public double getRobotAppearProbability() {
        return this.robotAppearProbability;
    }
    
    // not yet finished (or even started)
    public boolean restart() {
        this.sb.setScore(0);
        return false;
    }
}
