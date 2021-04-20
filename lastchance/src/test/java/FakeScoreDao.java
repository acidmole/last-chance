
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lastchance.dao.ScoreDao;

public class FakeScoreDao implements ScoreDao {
    
    private List<String> scoreList;
    private final String file;
    
    public FakeScoreDao(String file) throws Exception {
        this.file = file;
        scoreList = new ArrayList<>();
    }

    @Override
    public List<String> getTopScores() {
        return this.scoreList;
    }
    
    @Override
    public boolean writeToFile() throws Exception {
        return true;    
    }
    
    @Override
    public void addToList(String player, int score) {
        this.scoreList.add(player + ";" + score);
    }
}
