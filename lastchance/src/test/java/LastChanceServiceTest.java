/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private FakeScoreDao scoreDao;
    private LastChanceService lcs;
    
    public LastChanceServiceTest() {
    }
    
    @Before
    public void setUp() {
        try {
            scoreDao = new FakeScoreDao("foo");
        } catch (Exception e) {
        }
        String[] emptyArgs = {"100", "0.0005"};
        lcs = new LastChanceService(scoreDao, emptyArgs);
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
        assertEquals(600, lcs.getScoreboard().getScore());
        
        
    }
}
  
