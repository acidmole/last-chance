package lastchance.dao;

import java.util.List;


public interface ScoreDao {
    
    List<String> getTopScores();
    
    boolean writeToFile() throws Exception;
    
    void addToList(String player, int score);
}