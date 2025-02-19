import java.util.Scanner;

public class Game {

    private static final String GAME_MODE_MESSAGE = """
            Choose your game mode: \n1. Against Computer \n2. Against somebody else
            \n(Press 1 or 2 respectively or 0 to exit)
            """;

    private static final String MOVE_MESSAGE = """
            \nNow moves %s \n(Enter the tile index you would like to select (separate them with space))
    """;

    private  static final String WINNING_MESSAGE = """
            %s WON !!!
            """;

    private  static final String TIE_MESSAGE = """
            game ends with a tie.
            """;


    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameFinished = false;

        System.out.println("Welcome to TicTacToe.");

        while(!isGameFinished) {
            Board gameBoard = new Board();

            System.out.println(GAME_MODE_MESSAGE);
            String gameMode = scanner.nextLine();

            switch(gameMode) {
                case "1":
                    doRound(gameBoard, true);
                    break;

                case "2":
                    doRound(gameBoard, false);
                    break;

                case "0":

                    isGameFinished = true;
                    break;

                default:
                    System.out.println("Unknown mode");
                    break;
            }
        }

    }

    public static void doRound(Board board, Boolean againstComputer) {
        boolean somebodyWon = false;
        boolean playerTurn = true;
        int numberOfMoves = 0;

        User player = new Player("X");
        User opponent = againstComputer ? new Computer("O") : new Player("O");
        User currentPlayer = player;

        Scanner scanner = new Scanner(System.in);

        while(!somebodyWon && numberOfMoves < 9) {
            currentPlayer = playerTurn ? player : opponent;
            numberOfMoves++;

            if (currentPlayer instanceof Player) {
                board.showBoard();
                System.out.printf((MOVE_MESSAGE) + "%n", currentPlayer.getName());
                int tile = scanner.nextInt();
                currentPlayer.makeMove(board, tile - 1);

                somebodyWon = checkIfSomebodyWon(board, currentPlayer.getSign());

                playerTurn = !playerTurn;

            } else if (currentPlayer instanceof Computer) {
                Computer computer = (Computer) currentPlayer;
                int tile = computer.chooseTile(board.getGameBoard());
                computer.makeMove(board, tile);
                System.out.println("Computer choose: " + (tile + 1));

                somebodyWon = checkIfSomebodyWon(board, currentPlayer.getSign());
                playerTurn = !playerTurn;
            }
        }

        if (somebodyWon) displayEndGameMessage(currentPlayer.getName(), WINNING_MESSAGE);
        else if (numberOfMoves == 9) displayEndGameMessage(null, TIE_MESSAGE);
    }

    private static boolean checkIfSomebodyWon(Board board, String sign) {
        String[] gameBoard = board.getGameBoard();
        for (int i = 0; i < 9; i += 3) {
            if (gameBoard[i].equals(sign) &&
                gameBoard[i + 1].equals(sign) &&
                gameBoard[i + 2].equals(sign)) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i].equals(sign) &&
                gameBoard[i + 3].equals(sign) &&
                gameBoard[i + 6].equals(sign)) {
                return true;
            }
        }
        return (gameBoard[0].equals(sign) && gameBoard[4].equals(sign) && gameBoard[8].equals(sign)) ||
               (gameBoard[2].equals(sign) && gameBoard[4].equals(sign) && gameBoard[6].equals(sign));
    }

    private static void displayEndGameMessage(String currentPlayer, String message) {
        System.out.println();
        System.out.println();
        System.out.println(String.format(message, currentPlayer));
        System.out.println();
        System.out.println();
    }


    public static void main(String[] args) {
        startGame();
    }
}
