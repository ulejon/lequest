package se.lequest.lequest.maps;

/**
 * This Class represents a position on the Map..
 * x and y value..
 */
public class Position implements java.io.Serializable {
    private int x;
    private int y;

    /**
     * Constructs a Position with x = 0, And Y = 0
     */
    public Position() {
        x = 0;
        y = 0;
    }

    /**
     * Constructs a Position with x = x, And y = y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the value of the x-position
     *
     * @return x-value
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the value of the y-position
     *
     * @return y-value
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y value..
     *
     * @param y new Y-value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set the x-value
     *
     * @param x new x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns true if this and pos contains the same corrdinates.
     *
     * @param pos
     * @return
     */
    public boolean equals(Position pos) {
        if ((pos.x == x) && (pos.y == y)) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string reprecenting the position
     */
    public String toString() {
        return this.x + "," + this.y;
    }

}
