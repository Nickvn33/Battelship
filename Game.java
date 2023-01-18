package battleship;

import java.util.Scanner;

public class Game {

    public void changeTurn() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    public void start() {

        boolean isGameOver = false;

        System.out.println("Player 1, place your ships on the game field");
        System.out.print('\n');
        Player playerOne = new Player("Player 1");
        System.out.print('\n');
        playerOne.gameField.enterShips();
        changeTurn();

        System.out.println("Player 2, place your ships on the game field");
        System.out.print('\n');
        Player playerTwo = new Player("Player 2");
        System.out.print('\n');
        playerTwo.gameField.enterShips();
        changeTurn();

        while(!isGameOver) {
            System.out.print('\n');
            playerTwo.gameField.printFogOfWar();
            System.out.println("----------------------");
            playerOne.gameField.printField();
            System.out.print('\n');
            System.out.println("Player 1, it's your turn:");
            isGameOver = playerTwo.gameField.takeShot();

            if(isGameOver) {
                break;
            }

            changeTurn();

            System.out.print('\n');
            playerOne.gameField.printFogOfWar();
            System.out.println("----------------------");
            playerTwo.gameField.printField();
            System.out.print('\n');
            System.out.println("Player 2, it's your turn:");
            isGameOver = playerOne.gameField.takeShot();

            if(isGameOver) {
                break;
            }

            changeTurn();
        }
    }
}
