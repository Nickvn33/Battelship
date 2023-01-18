package battleship;

import java.util.Scanner;

public class GameField {

    public char[][] field;
    static Scanner scanner = new Scanner(System.in);
    public int shipsRemaining;
    Ship[] ships;

    public GameField() {
        field = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = '~';
            }
        }
        printField();
    }

    public void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for(int j = 0; j < 10; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print('\n');
        }
    }

    public void printFogOfWar() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for(int j = 0; j < 10; j++) {
                if (field[i][j] == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.print('\n');
        }
    }

    public void enterShips() {
        ships = new Ship[5];
        ships[0] = new Ship("Aircraft Carrier", 5);
        ships[1] = new Ship("Battleship", 4);
        ships[2] = new Ship("Submarine", 3);
        ships[3] = new Ship("Cruiser", 3);
        ships[4] = new Ship("Destroyer", 2);
        for (Ship ship : ships) {
            enterShip(ship);
            shipsRemaining++;
            printField();
            System.out.print('\n');
        }
    }

    public void enterShip(Ship ship) {
        boolean validInput = false;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getLength());
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
            } else if (!(Math.abs(startY - endY) == ship.getLength() - 1 || Math.abs(startX - endX) == ship.getLength()
                    - 1)) {
                System.out.printf("Error! Wrong length of %s! Try Again:\n", ship.getName());
                System.out.print('\n');
            } else {
                for (int i = startY; i <= endY; i++) {
                    for (int j = startX; j <= endX; j++) {
                        validInput = validCoordinate(i, j);
                        if (!validInput) {
                            break;
                        }
                    }
                    if (!validInput) {
                        System.out.println("Error! Ship overlapping or too close to another ship!");
                        System.out.print('\n');
                        break;
                    }
                }
            }
            if (validInput) {
                int index = 0;
                for (int i = startY; i <= endY; i++) {
                    for (int j = startX; j <= endX; j++) {
                        field[i][j] = 'O';
                        ship.setCoords(index, (i * 10 + j));
                        index++;
                    }
                }
            }
        }
    }

    public boolean validCoordinate(int y, int x) {
        if (field[y][x] == 'O') {
            return false;
        }
        if (y > 0) {
            if (field[y - 1][x] == 'O') {
                return false;
            }
            if (x > 0) {
                if (field[y][x - 1] == 'O') {
                    return false;
                } else if (field[y - 1][x - 1] == 'O') {
                    return false;
                }
            }
            if (x < 9) {
                if (field[y][x + 1] == 'O') {
                    return false;
                } else if (field[y - 1][x + 1] == 'O') {
                    return false;
                }
            }
        }
        if (y < 9) {
            if (field[y + 1][x] == 'O') {
                return false;
            }
            if (x > 0) {
                if (field[y][x - 1] == 'O') {
                    return false;
                } else if (field[y + 1][x - 1] == 'O') {
                    return false;
                }
            }
            if (x < 9) {
                if (field[y][x + 1] == 'O') {
                    return false;
                } else if (field[y + 1][x + 1] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean takeShot() {
        System.out.print('\n');
        System.out.println("Take a shot!");

        boolean validInput = false;

        while (!validInput) {
            String shot = scanner.next();
            System.out.print('\n');
            String[] shotCoords = shot.split("(?<=.)", 2);
            int shotY = shotCoords[0].charAt(0) - 65;
            int shotX = Integer.parseInt(shotCoords[1]) - 1;
            boolean shipSunk = false;
            int shotCoordsNum = shotY * 10 + shotX;

            if (Math.max(0, shotY) != Math.min(shotY, 9) || Math.max(0, shotX) != Math.min(shotX, 9)) {
                System.out.println("Error! Shot out of range of playing area!");
            } else {
                if (field[shotY][shotX] == '~' || field[shotY][shotX] == 'M') {
                    field[shotY][shotX] = 'M';
                    System.out.println("You missed!");
                    System.out.print('\n');
                    return false;
                } else if (field[shotY][shotX] == 'X') {
                    System.out.println("You hit a ship!");
                    System.out.print('\n');
                    return false;
                } else {
                    field[shotY][shotX] = 'X';

                    for (Ship ship : ships) {
                        for (int i = 0; i < ship.coords.length; i++) {
                            if (shotCoordsNum == ship.coords[i]) {
                                shipSunk = ship.shipHit();
                            }
                        }
                    }

                    if (shipSunk) {
                        shipsRemaining--;
                        if (shipsRemaining == 0) {
                            System.out.println("You sank the last ship. You Won. Congratulations!");
                            System.out.print('\n');
                            return true;
                        } else {
                            System.out.println("You sank a ship!");
                            System.out.print('\n');
                            return false;
                        }
                    } else {
                        System.out.println("You hit a ship!");
                        System.out.print('\n');
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
