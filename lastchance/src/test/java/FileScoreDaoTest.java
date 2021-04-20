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
    
    public FileScoreDaoTest() {
    }
    
    @Before
    public void setUp() {
        try {
            scoreDao = new FileScoreDao("foo");
        } catch (Exception e) {
        }
        scoreDao.addToList("Jorma", 15);
    }
    
    @Test
    public void addedPlayerIsFoundOnTheList() {
        assertEquals("Jorma;15", scoreDao.getTopScores().get(0));
    }
}
