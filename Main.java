package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        GameField field = new GameField();
        System.out.print('\n');
        GameField.enterShips();
        System.out.print('\n');
        TargetField targetField = new TargetField();
        System.out.print('\n');
        GameField.takeShot();
    }
}
