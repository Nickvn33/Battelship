/**
*
* File is now redundant as of Update #5
*
*/

package battleship;

public class TargetField {

    public static char[][] targetField;

    public TargetField() {
        targetField = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                targetField[i][j] = '~';
            }
        }
        printTargetField();
    }

    public static void printTargetField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for(int j = 0; j < 10; j++) {
                System.out.print(targetField[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}
