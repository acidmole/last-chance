/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.dao;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScoreDao implements ScoreDao {
    private List<String> scoreList;
    private final String file;
    
    public FileScoreDao(String file) throws Exception {
        this.file = file;
        scoreList = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                this.scoreList.add(row);
            }
        } catch (FileNotFoundException e) {
            
        }
    }

    @Override
    public List<String> getTopScores() {
        return this.scoreList;
    }
    
    @Override
    public boolean writeToFile() throws Exception {
        try {
            FileWriter fw = new FileWriter(file);
            for (String row : scoreList) {
                fw.write(row);
            }
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    @Override
    public void addToList(String player, int score) {
        this.scoreList.add(player + ";" + score);
    }
}