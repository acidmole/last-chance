/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class responsible for playing audio files run by threads
 * 
 */
public class Jukebox extends Thread {
   
    private String fileURI;
    private boolean keepPlaying;
    private ImprovedAdvancedPlayer player;
    private int startingFrame;
    
    /**
     * 
     * @param fileURI URI for FileInputStream
     * @throws Exception if file can't be loaded
     */
    public Jukebox(String fileURI) throws FileNotFoundException {
        this.fileURI = fileURI;
        this.keepPlaying = true;
    }
    
    /**
     * For playing the actual audio file
     */
    @Override
    public void run() {
        System.out.println("Here I am");
        try {
            System.out.println("Playing.");
            do {
                FileInputStream stream = new FileInputStream(this.fileURI);
                this.player = new ImprovedAdvancedPlayer(stream);
                this.player.play();
            } while (this.keepPlaying);
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Method for pausing music.
     * 
     * @return frame to carry on from after pause
     */
    public int pause() {
        return(this.player.pause());
    }
    
    /**
     * Method for ending music for good. Cannot be restored
     */
    public void close() {
        System.out.println("Stopping.");
        this.player.close();
    }
}
