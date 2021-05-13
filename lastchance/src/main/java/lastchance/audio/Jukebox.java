/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.FileInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * A class responsible for playing audio files run by threads
 * 
 */
public class Jukebox extends Thread {
   
    private String fileURI;
    private boolean keepPlaying;
    private AdvancedPlayer player;
    
    /**
     * 
     * @param fileURI URI for FileInputStream
     * @throws Exception if file can't be loaded
     */
    public Jukebox(String fileURI) throws Exception{
        this.fileURI = fileURI;
        this.keepPlaying = true;
    }
    
    /**
     * For playing the actual audio file
     */
    @Override
    public void run() {
        
        try {
            System.out.println("Playing.");
            do {
                FileInputStream stream = new FileInputStream(this.fileURI);
                this.player = new AdvancedPlayer(stream);
                this.player.play();
            } while (this.keepPlaying);
        } catch (Exception e) {
            
        }
    }
    
    public void close() {
        System.out.println("Stopping.");
        this.keepPlaying = false;
        this.player.close();
    }
}
