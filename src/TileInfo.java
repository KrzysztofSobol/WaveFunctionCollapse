public class TileInfo implements Comparable<TileInfo> {
    int x, y, size;

    TileInfo(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public int compareTo(TileInfo other) {
        return Integer.compare(this.size, other.size);
    }
}