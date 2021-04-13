/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.scene.Node;

/**
 *
 * @author hede
 */
public interface Dashboard {
    
    int getScore();
    void setScore(int score);
    boolean quit();
    boolean restart();
    Node getNode();
    
}
