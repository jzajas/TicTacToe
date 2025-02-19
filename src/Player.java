public class Player implements User{

    private final String sign;

    @Override
    public void makeMove(Board board, int tile) {
        String[] gameBoard= board.getGameBoard();

        if(gameBoard[tile].equals(String.valueOf(tile + 1))) {
            board.claimTile(tile, this.sign);
        }
    }

    @Override
    public String getSign() {
        return sign;
    }

    @Override
    public String getName() {
        return "player " + this.sign;
    }

    public Player(String sign) {
        this.sign = sign;
    }
}
