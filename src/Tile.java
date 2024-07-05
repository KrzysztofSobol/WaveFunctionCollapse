public class Tile implements Cloneable{
    private char display; // a character to display on the map
    private int weight; // a percentage chance of being chosen

    private int north;
    private int east;
    private int south;
    private int west;

    private boolean inQueue;
    private boolean collapsed;

    public Tile(char display, int weight, int north, int east, int south, int west) {
        this.setDisplay(display);
        this.setWeight(weight);
        this.setNorth(north);
        this.setEast(east);
        this.setSouth(south);
        this.setWest(west);
        this.collapsed = false;
    }

    public void Rotate(){
        int temp = this.north;
        this.north = this.west;
        this.west = this.south;
        this.south = this.east;
        this.east = temp;
    }

    // Getters and Setters

    public boolean isInQueue() {return inQueue; }

    public void setInQueue(boolean b) {this.inQueue = b; }

    public void collapse() {
        this.collapsed = true;
    }

    public boolean isCollapsed() {
        return this.collapsed;
    }

    public char getDisplay() {
        return display;
    }

    public void setDisplay(char display) {
        this.display = display;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNorth() {return north; }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public int getSouth() {return south; }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    @Override
    public Tile clone() {
        try {
            Tile cloned = (Tile) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
