/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;

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
     * @param startingFrame starting frame for song
     * @throws Exception if file can't be loaded
     */
    public Jukebox(String fileURI, int startingFrame) {
        try {
            this.fileURI = fileURI;
            this.keepPlaying = true;
            this.startingFrame = startingFrame;
            System.out.println(this.startingFrame);
        } catch (Exception e) {
        }
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
        } catch (FileNotFoundException | JavaLayerException e) {
        }
    }
    
    /**
     * Method for pausing music.
     * 
     * @return frame to carry on from after pause
     */
    /**
     * Method for ending music for good. Cannot be restored
     */
    public int pause() {
        try {
            this.keepPlaying = false;
            System.out.println("Stopping.");
            return this.player.pause();
        } catch(Exception e) {
            return 0;
        }
    }
    
    public void play(int frame) {
        try {
            this.keepPlaying = true;
            this.player.play(frame);
        } catch(Exception e) {
            
        }
    }
}
