/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lastchance.dao.FileScoreDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hede
 */
public class FileScoreDaoTest {
    
    FileScoreDao scoreDao;
    FileScoreDao anotherScoreDao;
    
    public FileScoreDaoTest() {
    }
    
    @Before
    public void setUp() {
        scoreDao = new FileScoreDao("foo");
        anotherScoreDao = new FileScoreDao("scores.txt");
        scoreDao.addToList("Jorma", 15);
    }
    
    @Test
    public void addedPlayerIsFoundOnTheList() {
        assertEquals("Jorma;15", scoreDao.getTopScores().get(0));
    }
    
    @Test
    public void fileFoundIsRight() {
        assertFalse(scoreDao.getFileFound());
        assertTrue(anotherScoreDao.getFileFound());
    }
    
    @Test
    public void writeToFileReturnsRightValue() {
        try {
            scoreDao.writeToFile();
            anotherScoreDao.writeToFile();
        } catch(Exception e) {
        }
        assertFalse(scoreDao.getFileFound());
        assertTrue(anotherScoreDao.getFileFound());

    }
    
    
}
