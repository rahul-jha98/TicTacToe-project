
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.ArrayList;
import java.util.Optional;

public class Main  {


    public static Board board;

    private String mode = "twoplayer";

    private static Alert alert;

    private ArrayList<Button> buttons;

    public Main() {


        board = new Board();
        alert = new Alert(AlertType.CONFIRMATION);

    }

    private void setAlert(Player player) {
        String playerWon;

        if (player == null) {
            playerWon = "No player";
        } else {
            playerWon = player.getXO();
        }

        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText("Game Over - " + playerWon + " wins!");
        alert.setContentText(
                "X Wins: " + board.getXWins() + "\n" +
                        "O Wins: " + board.getOWins() + "\n" +
                        "Draws: " + board.getDraws() + "\n\n" +
                        "Play Again?"
        );

        getAlert(alert);
    }

    private void getAlert(Alert alert) {
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            resetGame();
        } else {
            System.exit(0);
        }
    }




    private void resetGame() {
        board.resetBoard();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setGraphic(null);
        }
    }



    public static void main(String[] args) {


        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }

}
