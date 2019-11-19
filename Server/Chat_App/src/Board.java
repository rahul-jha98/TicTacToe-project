import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

    private final static int BOARD_DIM = 3;

    private final static Integer[][] STRIKE = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    private boolean xTurn = true;

    private HashMap<Integer, Player> board;
    private ArrayList<Integer> xMoves, oMoves;
    private ScoreBoard scores;

    public Board() {
        // Initialization
        board = new HashMap<Integer, Player>();
        xMoves = new ArrayList<>();
        oMoves = new ArrayList<>();
        scores = new ScoreBoard();
        scores.setDraws(0);
        scores.setO_wins(0);
        scores.setX_wins(0);
        resetBoard();

    }
    public void resetBoard(){
        xMoves.clear();
        oMoves.clear();
        board.clear();

        for(int i = 0; i < 9; i++){
            board.put(i + 1, null);
        }
    }

    public boolean is_grid_free(Integer i) {
        if(board.get(i) == null) return true;
        return false;
    }

    public boolean is_board_full() {
        for(Integer i : board.keySet()){
            if(board.get(i) == null) return false;
        }
        return true;
    }

    public void updateMoves() {
        for(Integer i : board.keySet()){
            if(board.get(i) != null){
                if(board.get(i).getXO().equals("X")) xMoves.add(i);
                else oMoves.add(i);
            }
        }
    }

    public boolean xWon() {
        for( Integer[] row : STRIKE){
            if (xMoves.containsAll(Arrays.asList(row))){
                scores.setX_wins(scores.getX_wins() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean oWon() {
        for( Integer[] row : STRIKE){
            if (oMoves.containsAll(Arrays.asList(row))){
                scores.setO_wins(scores.getO_wins() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        System.out.println("X won " + xWon());
        System.out.println("Y won " + oWon());
        System.out.println("Board full " + is_board_full());
        return !xWon() && !oWon() && is_board_full();
    }

    public void Move(Integer gridPt, Player player) {
        if(is_grid_free(gridPt)){
            board.put(gridPt,player);
        }
    }

    public void switchTurn(){
        xTurn = !xTurn;
    }

    public int getTurn() {
        return (xTurn) ? 1 : -1;
    }

    public HashMap<Integer, Player> getBoard()
    {
        return board;
    }


    public int getXWins() {
        return scores.getX_wins();
    }

    public int getOWins() {
        return scores.getO_wins();
    }

    public int getDraws() {
        return scores.getDraws();
    }

    public void printBoard() {
        for(int i = 0; i < BOARD_DIM*BOARD_DIM; i++){
            if(i%3 == 0){
                System.out.println();
            }
            if(board.get(i) != null) System.out.print(board.get(i).getXO() + " ");
            else System.out.print("- ");
        }
    }

    public int getBoardSize() {
        return BOARD_DIM;
    }

}
