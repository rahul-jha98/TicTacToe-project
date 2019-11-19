package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.server.GameHandlerThread;
import sample.server.GameListner;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class Controller extends Pane implements GameListner {
    private GameHandlerThread gameHandlerThread;

    private GUI gui;
    //private Board board;

    private boolean turn;
    public static String type;
    private Alert alert;

    private ArrayList<Button> buttons;

    @FXML
    private ImageView imageView;

    @FXML
    private Label your;

    @FXML
    private Label opponent;


    int yourScore, opponentScore;

    public Controller(){

    }
    public Controller(Socket socket, Parent root) {
        try{
            gameHandlerThread = new GameHandlerThread(socket, this);
        } catch (IOException e) {
            System.out.println("There was an error connecting the game socket");
        }

        Thread t = new Thread(gameHandlerThread);
        t.start();


        gui = new GUI();
        //board = new Board();
        alert = new Alert(Alert.AlertType.CONFIRMATION);


        your = (Label) root.lookup("#your");
        opponent = (Label) root.lookup("#opponent");

        imageView = (ImageView) root.lookup("#imageView");

        buttons = gui.addButtons(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!turn)
                    return;
                System.out.println(turn);
                for (int i = 0; i < buttons.size(); i++) {
                    Button b = buttons.get(i);
                    if (event.getSource().equals(b)) {

                        if (b.getGraphic() == null) {
                            gameHandlerThread.sendMove(type + " " + Integer.toString(i));
                        }
                        else{
                            return;
                        }
                    }
                }
            }

        });



       setYourScore(yourScore);
       setOpponentScore(opponentScore);
    }

    private void setAlert(Player player) {
        String playerWon;

        if (player == null) {
            playerWon = "No player";
        } else {
            playerWon = player.getID();
        }

        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText("Game Over - " + playerWon + " wins!");

    }



    public ArrayList<Button> getButtons() {
        return buttons;
    }



    public void switchTurn() {
        turn = !turn;
    }

    public int getTurn() {
        if (turn) {
            return 1;
        } else {
            return -1;
        }
    }



    private void getAlert(Alert alert) {

        Optional<ButtonType> result;

                result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    gameHandlerThread.sendMove("R");
                } else if(result.get() == ButtonType.CANCEL){
                    System.exit(0);
                }
    }

    private void resetGame() {
        for(Button b : buttons) {
            b.setGraphic(null);
        }

        if(type.equals("X"))
            turn = true;

        else
            turn = false;
    }


    @Override
    public void onMoved(String player, int pos) {
        InputStream input;
        System.out.println("Movement received");
        if(player.equals("X")) {
            input = this.getClass().getResourceAsStream("image/x.png");
        } else {
            input = this.getClass().getResourceAsStream("image/o.png");
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                buttons.get(pos).setGraphic(new ImageView(new Image(input)));
            }
        });

        switchTurn();
    }

    @Override
    public void onPlayerWon(int p) {
        int isWinner = type.equals("X") ? 1 : 2;
        if(p == -1)
            return;

        String data = "No Player";
        if(p == 0) {
        }
        else if(isWinner == p) {
            data = "You";

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setYourScore(++yourScore);
                }
            });

        } else {
            data = "Opponent";

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setOpponentScore(++opponentScore);
                }
            });

        }
        final String text = data;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                alert.setTitle("Tic Tac Toe");
                alert.setHeaderText("Game Over - " + text + " wins!");

                getAlert(alert);
            }
        });


    }

    @Override
    public void setChar(String type) {
        Controller.type = type;
        turn = type.equals("X");
        System.out.println(type + " " + turn);
    }

    @Override
    public void onReset() {
        if(alert != null) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert.setResult(ButtonType.FINISH);
                    alert.close();
                    resetGame();
                }
            });

        }
    }

    public void setYourScore(int x){
        your.setText("Your Score : " + x);
    }

    public void setOpponentScore(int x){
        opponent.setText("Opponent Score : " + x);
    }
}
