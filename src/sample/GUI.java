package sample;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GUI {
    
    public final static int BOARD_SIZE = 3;
    private final static String FRAME_TITLE = "Tic Tac Toe";
    public final static int FRAME_HEIGHT = 500;
    public final static int FRAME_WIDTH = 500;
    
    private ArrayList<Button> buttons;
    
    public ArrayList<Button> addButtons(EventHandler<ActionEvent> e) {
        buttons = new ArrayList<Button>();
        
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            Button b = new Button();
            b.setId(Integer.toString(i + 1));
            b.setMinHeight(FRAME_WIDTH / BOARD_SIZE);
            b.setMinWidth(FRAME_WIDTH / BOARD_SIZE);
            b.setOnAction(e);
            b.getStyleClass().add("button");
            buttons.add(b);
        }
        
        return buttons;
    }
    
    public String getButtonText(String id) {
        for (Button b : buttons) {
            if (b.getId().equals(id)) {
                return b.getText();
            }
        }
        return null;
    }
    
    public int getBoardSize() {
        return BOARD_SIZE;
    }
    
    public String getFrameTitle() {
        return FRAME_TITLE;
    }
    
    public int getFrameHeight() {
        return FRAME_HEIGHT;
    }
    
    public int getFrameWidth() {
        return FRAME_WIDTH;
    }
    
}
