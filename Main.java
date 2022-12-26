package battleship;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char[][] field = new char[10][10];

    public static void initialise() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
    }

    public static void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for(int j = 0; j < 10; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print('\n');
        }
    }

    public static void enterShips() {
        // Aircraft Carrier
        enterShip(5, "Aircraft Carrier");
        printField();
        System.out.print('\n');
        // Battleship
        enterShip(4, "Battleship");
        printField();
        System.out.print('\n');
        // Submarine
        enterShip(3, "Submarine");
        printField();
        System.out.print('\n');
        // Cruiser
        enterShip(3, "Cruiser");
        printField();
        System.out.print('\n');
        // Destroyer
        enterShip(2, "Destroyer");
        printField();
        System.out.print('\n');
    }

    public static void enterShip(int shipLength, String name) {
        boolean validInput = false;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", name, shipLength);
        System.out.print('\n');

        while (!validInput) {
            String start = scanner.next();
            String end = scanner.next();
            System.out.print('\n');
            String[] startCoords = start.split("(?<=.)", 2);
            String[] endCoords = end.split("(?<=.)", 2);
            int startY = Math.min(startCoords[0].charAt(0), endCoords[0].charAt(0)) - 65;
            int endY = Math.max(startCoords[0].charAt(0), endCoords[0].charAt(0)) - 65;
            int startX = Math.min(Integer.parseInt(startCoords[1]), Integer.parseInt(endCoords[1])) - 1;
            int endX = Math.max(Integer.parseInt(startCoords[1]), Integer.parseInt(endCoords[1])) - 1;

            if (startY < 0 || startY > 9 || startX < 0 || startX > 9) {
                System.out.println("Error! Start coordinates out of range! Try Again:");
                System.out.print('\n');
            } else if (endY < 0 || endY > 9 || endX < 0 || endX > 9) {
                System.out.println("Error! End coordinates out of range! Try Again:");
                System.out.print('\n');
            } else if (!(startY == endY || startX == endX)) {
                System.out.println("Error! Wrong ship shape! Must be horizontal or vertical! Try Again:");
                System.out.print('\n');
            } else if (!(Math.abs(startY - endY) == shipLength - 1 || Math.abs(startX - endX) == shipLength - 1)) {
                System.out.printf("Error! Wrong length of %s! Try Again:\n", name);
                System.out.print('\n');
            } else {
                boolean invalidCoord = false;
                if (startY == endY) {
                    for (int i = 0; i < shipLength; i++) {
                        if (field[startY][startX + i] == 'O') {
                            System.out.println("Error! Overlapping placement! Try Again!");
                            System.out.print('\n');
                            invalidCoord = true;
                            break;
                        } else {
                            if (startY > 0 && startY < 9 && startX + i > 0 && startX + i < 9) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY][startX + i - 1] == 'O'  ||
                                        field[startY + 1][startX + i + 1] == 'O' || field[startY - 1][startX + i + 1] == 'O'
                                        || field[startY + 1][startX + i - 1] == 'O' || field[startY - 1][startX + i - 1] == 'O'
                                        || field[startY + 1][startX + i] == 'O' || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 0 && startX + i == 0) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY + 1][startX + i + 1] == 'O'
                                        || field[startY + 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 0 && startX + i == 9) {
                                if (field[startY][startX + i - 1] == 'O'  || field[startY + 1][startX + i - 1] == 'O'
                                        || field[startY + 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 9 && startX + i == 0) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY - 1][startX + i + 1] == 'O'
                                        || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 9 && startX + i == 9) {
                                if (field[startY][startX + i - 1] == 'O'  || field[startY - 1][startX + i - 1] == 'O'
                                        || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 0) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY][startX + i - 1] == 'O'  ||
                                        field[startY + 1][startX + i + 1] == 'O' || field[startY + 1][startX + i - 1] == 'O'
                                        || field[startY + 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY == 9) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY][startX + i - 1] == 'O'  ||
                                        field[startY - 1][startX + i + 1] == 'O'|| field[startY - 1][startX + i - 1] == 'O'
                                        || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startX + i == 0) {
                                if (field[startY][startX + i + 1] == 'O' || field[startY + 1][startX + i + 1] == 'O' ||
                                        field[startY - 1][startX + i + 1] == 'O' || field[startY + 1][startX + i] == 'O'
                                        || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startX + i == 9) {
                                if (field[startY][startX + i - 1] == 'O'  || field[startY + 1][startX + i - 1] == 'O' ||
                                        field[startY - 1][startX + i - 1] == 'O' || field[startY + 1][startX + i] == 'O'
                                        || field[startY - 1][startX + i] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!invalidCoord) {
                        for (int j = 0; j < shipLength; j++) {
                            field[startY][startX + j] = 'O';
                        }
                        validInput = true;
                    }
                } else {
                    for (int i = 0; i < shipLength; i++) {
                        if (field[startY + i][startX] == 'O') {
                            System.out.println("Error! Overlapping placement! Try Again!");
                            System.out.print('\n');
                            invalidCoord = true;
                            break;
                        } else {
                            if (startY + i > 0 && startY + i < 9 && startX > 0 && startX < 9) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i - 1][startX] == 'O' ||
                                        field[startY + i + 1][startX + 1] == 'O' || field[startY + i - 1][startX + 1] == 'O'
                                        || field[startY + i + 1][startX - 1] == 'O' || field[startY + i - 1][startX - 1] == 'O'
                                        || field[startY + i][startX + 1] == 'O' || field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 0 && startX == 0) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i + 1][startX + 1] == 'O' ||
                                        field[startY + i][startX + 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 0 && startX == 9) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i + 1][startX - 1] == 'O' ||
                                        field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 9 && startX == 9) {
                                if (field[startY + i - 1][startX] == 'O' || field[startY + i - 1][startX - 1] == 'O'
                                        || field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 9 && startX == 0) {
                                if (field[startY + i - 1][startX] == 'O' || field[startY + i - 1][startX + 1] == 'O'||
                                        field[startY + i][startX + 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 0) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i + 1][startX + 1] == 'O' ||
                                        field[startY + i + 1][startX - 1] == 'O' || field[startY + i][startX + 1] == 'O'
                                        || field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startY + i == 9) {
                                if (field[startY + i - 1][startX] == 'O' || field[startY + i - 1][startX + 1] == 'O'
                                        || field[startY + i - 1][startX - 1] == 'O' || field[startY + i][startX + 1] == 'O'
                                        || field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startX == 0) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i - 1][startX] == 'O' ||
                                        field[startY + i + 1][startX + 1] == 'O' || field[startY + i - 1][startX + 1] == 'O'
                                        || field[startY + i][startX + 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            } else if (startX == 9) {
                                if (field[startY + i + 1][startX] == 'O' || field[startY + i - 1][startX] == 'O' ||
                                        field[startY + i + 1][startX - 1] == 'O' || field[startY + i - 1][startX - 1] == 'O'
                                        || field[startY + i][startX - 1] == 'O') {
                                    System.out.println("Error! Ship placed too close to another ship!");
                                    System.out.print('\n');
                                    invalidCoord = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!invalidCoord) {
                        for (int j = 0; j < shipLength; j++) {
                            field[startY + j][startX] = 'O';
                        }
                        validInput = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Write your code here
        initialise();
        printField();
        System.out.print('\n');
        enterShips();
    }
}
