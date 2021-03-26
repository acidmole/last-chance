/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hede
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(800);
    }
    
    @Test
    public void rahatJaLounaatAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoToimiiOikein() {
        int vaihtorahat = kassa.syoEdullisesti(500);
        assertEquals(260, vaihtorahat);
        assertEquals(100240, kassa.kassassaRahaa());
        vaihtorahat = kassa.syoMaukkaasti(500);
        assertEquals(100, vaihtorahat);
        assertEquals(100640, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        
        vaihtorahat = kassa.syoMaukkaasti(300);
        assertEquals(300, vaihtorahat);
        vaihtorahat = kassa.syoEdullisesti(100);
        assertEquals(100, vaihtorahat);
        assertEquals(100640, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiOstoToimiiOikein() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(160, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        
        assertTrue(!kassa.syoEdullisesti(kortti));
        assertTrue(!kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(160, kortti.saldo());
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausToimiiOikein() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1800, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(1800, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    
}
