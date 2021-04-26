/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ScoreboardTest {
    private Scoreboard sb;
    
    public ScoreboardTest() {
    }
    
    @Before
    public void setUp() {
        sb = new Scoreboard();
    }
    
    @Test
    public void scoreboardSetsScoreRight() {
        assertEquals(0, sb.getScore());
        sb.setScore(100);
        assertEquals(100, sb.getScore());
    }
    
    @Test
    public void scoreboardRaisesScoreRight() {
        assertEquals(0, sb.getScore());
        sb.raiseScore(100);
        assertEquals(100, sb.getScore());
    }

}
