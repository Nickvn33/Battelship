package battleship;

public class Player {

    GameField gameField;
    final String name;

    Player(String name) {
        this.gameField = new GameField();
        this.name = name;
    }
}
