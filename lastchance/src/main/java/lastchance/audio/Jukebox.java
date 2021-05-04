/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.FileInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * A class responsible for playing audio files run by threads
 * 
 */
public class Jukebox extends Player implements Runnable {
    
    /**
     * 
     * @param fileURI URI for FileInputStream
     * @throws Exception if file can't be loaded
     */
    public Jukebox(String fileURI) throws Exception{
        super(new FileInputStream(fileURI));
    }
    
    /**
     * For playing the actual audio file
     */
    @Override
    public void run() {
        try {
            super.play();
        } catch (JavaLayerException e) {
        }
    }
    
    /**
     * Stopping if program is closed
     */
    public void stop() {
        super.close();
    }
}
