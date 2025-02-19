
import java.util.Random;

public class Computer implements User{

    private final String sign;


    @Override
    public void makeMove(Board board, int tile) {
        board.claimTile(tile, this.sign);
    }

    public int chooseTile(String[] board) {
        int choosenTile;
        Random random = new Random();

        do {
            choosenTile = random.nextInt(9);
        }
        while(!isTileValid(board, choosenTile));

        return choosenTile;
    }

    private boolean isTileValid(String[] board, int tile) {
        int value = tile + 1;
        return board[tile].equals(String.valueOf(value));

    }


    @Override
    public String getSign() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    public Computer(String sign) {
        this.sign = sign;
    }
}
