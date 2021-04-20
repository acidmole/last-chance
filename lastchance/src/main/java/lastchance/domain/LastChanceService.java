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
    
    public LastChanceService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
        this.sb = new Scoreboard();
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
    
    // not yet finished (or even started)
    public boolean restart() {
        return false;
    }
}
