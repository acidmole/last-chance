/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hede
 */
public class FileScoreDao {
    private ArrayList<String> scoreList;
    private final String file;
    private boolean fileFound;
    
    /**
     *
     * @param file where the scores are archived
     */
    public FileScoreDao(String file) {
        this.file = file;
        scoreList = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                this.scoreList.add(row);
                this.fileFound = true;
            }
        } catch (FileNotFoundException e) {
            this.fileFound = false;
        }
    }

    /**
     *
     * @return list of top scores 
     */
    public ArrayList<String> getTopScores() {
        return this.scoreList;
    }
    
    /**
     * A method for saving scores.
     * 
     * @return if save succeeded
     * @throws Exception if file is not writable
     */
    public boolean writeToFile() throws Exception {
        if(this.fileFound) {
            try {
                FileWriter fw = new FileWriter(file);
                for (String row : scoreList) {
                    fw.write(row + "\n");
                }
                fw.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /** 
     * Adds a score with players name to score list.
     * 
     * @param player player's name
     * @param score players score
     * 
     * @see getFileFound()
     */
    public void addToList(String player, int score) {
        this.scoreList.add(player + ";" + score);
    }
    
    /**
     * 
     * @return if a high score file was found
     */
    public boolean getFileFound() {
        return this.fileFound;
    }
}