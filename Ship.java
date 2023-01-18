package battleship;

public class Ship {

    private final String name;
    private final int length;
    public int health;
    public int[] coords;

    Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.health = length;
        this.coords = new int[length];
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getHealth() {
        return health;
    }

    public void setCoords(int index, int coord) {
        coords[index] = coord;
    }

    public int[] getCoords() {
        return coords.clone();
    }

    public boolean shipHit() {
        health--;
        if (health == 0) {
            return true;
        }
        return false;
    }
}
