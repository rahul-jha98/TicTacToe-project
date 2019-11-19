//package sample;
//
//
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class Board {
//
//    private final static int BOARD_SIZE = 3;
//    private final static Integer[][] WINNING_VALUES = {
//            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
//            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
//            {1, 5, 9}, {3, 5, 7}
//    };
//
//    private boolean xTurn;
//    private int xWins, oWins, draws;
//
//    private HashMap<Integer, Player> board;
//    private ArrayList<Integer> xPositions, oPositions;
//
//    public Board() {
//        board = new HashMap<Integer, Player>();
//        xPositions = new ArrayList<Integer>();
//        oPositions = new ArrayList<Integer>();
//
//        xTurn = true;
//        xWins = 0;
//        oWins = 0;
//        draws = 0;
//
//        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
//            board.put(new Integer(i + 1), null);
//        }
//    }
//
//    private boolean pointAvailable(Integer point, Player player) {
//        if (point.intValue() <= BOARD_SIZE * BOARD_SIZE &&
//                board.get(point) == null) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean boardFull() {
//        for (Integer item : board.keySet()) {
//            if (board.get(item) == null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void addXOPositions() {
//        for (Integer item : board.keySet()) {
//            if (board.get(item) != null) {
//                if (board.get(item).equals(new Player("X"))) {
//                    xPositions.add(item);
//                }
//                if (board.get(item).equals(new Player("O"))) {
//                    oPositions.add(item);
//                }
//            }
//        }
//    }
//
//    public boolean isXWin() {
//        for (Integer[] row : WINNING_VALUES) {
//            if (xPositions.size() < row.length) {
//                return false;
//            } else {
//                if (xPositions.containsAll(
//                        new ArrayList<Integer>(Arrays.asList(row))
//                )) {
//                    xWins++;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean isOWin() {
//        for (Integer[] row : WINNING_VALUES) {
//            if (oPositions.size() < row.length) {
//                return false;
//            } else {
//                if (oPositions.containsAll(
//                        new ArrayList<Integer>(Arrays.asList(row))
//                )) {
//                    oWins++;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean isDraw() {
//        if (boardFull() && (!isXWin() && !isOWin())) {
//            draws++;
//            return true;
//        }
//        return false;
//    }
//
//    public void addMove(Integer point, Player player) {
//        if (pointAvailable(point, player)) {
//            board.put(point, player);
//        }
//    }
//
//    public void switchTurn() {
//        xTurn = !xTurn;
//    }
//
//    public int getTurn() {
//        if (xTurn) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
//
//    public HashMap<Integer, Player> getBoard() {
//        return board;
//    }
//
//    public void resetBoard() {
//        board.clear();
//        xPositions.clear();
//        oPositions.clear();
//
//        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
//            board.put(new Integer(i + 1), null);
//        }
//    }
//
//    public int getXWins() {
//        return xWins;
//    }
//
//    public int getOWins() {
//        return oWins;
//    }
//
//    public int getDraws() {
//        return draws;
//    }
//
//    public void printBoard() {
//        String value = null;
//        for (Integer item : board.keySet()) {
//            try {
//                value = board.get(item).toString();
//            } catch (NullPointerException e) {
//                value = "-";
//            } finally {
//                if (item % 3 == 0) {
//                    System.out.println(value + " ");
//                } else {
//                    System.out.print(value + " ");
//                }
//            }
//        }
//        System.out.println();
//    }
//
//    public int getBoardSize() {
//        return BOARD_SIZE;
//    }
//
//}