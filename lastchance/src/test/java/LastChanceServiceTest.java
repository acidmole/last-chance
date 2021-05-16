/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.util.Properties;
import lastchance.dao.FileScoreDao;
import lastchance.domain.LastChanceService;
import lastchance.domain.Scoreboard;
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
public class LastChanceServiceTest {
    
    private Scoreboard sb;
    private FileScoreDao scoreDao;
    private LastChanceService lcs;
    private Properties p;
    
    public LastChanceServiceTest() {
        p = new Properties();
    }
    
    @Before
    public void setUp() {
        try {
            scoreDao = new FileScoreDao("foo");
            p.load(new FileInputStream("lastchance.properties"));
        } catch (Exception e) {
        }
        lcs = new LastChanceService(scoreDao, p);
        lcs.getScoreboard().setScore(500);
        lcs.quit("Jorma");
    }
    
    @Test
    public void quitSavesInformationCorrectly() {
        assertEquals("Jorma;500", scoreDao.getTopScores().get(0));
    }
    
    @Test
    public void addPointsWorksProperly() {
        lcs.addPoints();
        assertEquals(510, lcs.getScoreboard().getScore());
    }
    
    @Test
    public void restartNullifiesPoints() {
        lcs.restart();
        assertEquals(0, lcs.getScoreboard().getScore());
    }
    
}
  
