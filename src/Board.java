public class Board {

    private static final String[] EMPTY_BOARD = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] gameBoard;

    public Board() {
        gameBoard = EMPTY_BOARD.clone();
    }

    public void claimTile(int tile, String sign) {
        gameBoard[tile] = sign;
    }

    public void showBoard() {
        int indexCount = 0;

        for (int i = 0; i < 3; i++) {
            System.out.println();

            for (int j = 0; j < 3; j++) {
                System.out.print(" [ " + this.gameBoard[indexCount] + " ] ");
                indexCount++;
            }
        }
    }

    public String[] getGameBoard() {
        return gameBoard;
    }
}
