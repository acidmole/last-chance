/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastchance.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author hede
 */
public class ButtonGrid extends VBox{
    private Button quit;
    private Button restart;
    
    public ButtonGrid() {
        super();
        this.setSpacing(10);
        this.quit = new Button("Quit");
        this.restart = new Button("Restart");
        this.getChildren().add(this.quit);
        this.getChildren().add(this.restart);
    }
    
    public Button getQuitButton() {
        return this.quit;
    }
    
    public Button getRestartButton() {
        return this.restart;
    }

}
