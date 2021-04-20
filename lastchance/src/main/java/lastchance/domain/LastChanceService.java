/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.domain;

import lastchance.dao.FileScoreDao;

/**
 *
 * @author hede
 */
public class LastChanceService {
    
        private Scoreboard sb;
        private FileScoreDao scoreDao;
    
    public LastChanceService(FileScoreDao scoreDao) {
        this.scoreDao = scoreDao;
        this.sb = new Scoreboard();
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
    
    public boolean restart() {
        return false;
    }
}
