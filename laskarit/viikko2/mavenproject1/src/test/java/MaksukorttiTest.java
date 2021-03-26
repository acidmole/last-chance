/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class MaksukorttiTest {
    Maksukortti kortti;
    Maksukortti toinenKortti;
    Maksukortti kolmasKortti;    
   
    public MaksukorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10.00);
        toinenKortti = new Maksukortti(2.50);
        kolmasKortti = new Maksukortti(4.00);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    public void negatiivinenSummaEiMuutaKortinSaldoa() {
        kortti.lataaRahaa(-5.50);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
        
    }
    
    public void edullinenLounasOnnistuuJuuriJaJuuri() {
        toinenKortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", toinenKortti.toString());
    }

    public void maukasLounasOnnistuuJuuriJaJuuri() {
        toinenKortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kolmasKortti.toString());
    }
    
}
