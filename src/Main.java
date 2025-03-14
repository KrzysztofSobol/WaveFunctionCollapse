import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Tile> tiles = new LinkedList<>();

        Tile grass = new Tile('X', 300, 3, 3, 3, 3);
        Tile sea = new Tile('1', 150, 1, 1, 1, 1);

        Tile coast = new Tile('A', 10, 3, 5, 1, 5);
        Tile coast1 = new Tile('B', 10, 4, 3, 4, 1);
        Tile coast2 = new Tile('C', 10, 1, 4, 3, 4);
        Tile coast3 = new Tile('D', 10, 2, 1, 2, 3);

        Tile coast_corner = new Tile('3', 10,3, 5, 2, 3);
        Tile coast_corner1 = new Tile('4', 10,3, 3, 4, 5);
        Tile coast_corner2 = new Tile('5', 10, 4, 3, 3, 4);
        Tile coast_corner3 = new Tile('6', 10, 2, 4, 3, 3);

        Tile outer_corner = new Tile('O', 10, 1, 1, 2, 4);
        Tile outer_corner1 = new Tile('O', 10, 2, 1, 1, 5);
        Tile outer_corner2 = new Tile('O', 10, 1, 4, 4, 1);
        Tile outer_corner3 = new Tile('O', 10, 4, 5, 1, 1);

        tiles.add(grass);
        tiles.add(sea);
        tiles.add(coast);
        tiles.add(coast1);
        tiles.add(coast2);
        tiles.add(coast3);
        tiles.add(coast_corner);
        tiles.add(coast_corner1);
        tiles.add(coast_corner2);
        tiles.add(coast_corner3);
        tiles.add(outer_corner);
        tiles.add(outer_corner1);
        tiles.add(outer_corner2);
        tiles.add(outer_corner3);

        Map map = new Map(10, 10);
        map.init(tiles);
        map.Generate();

        System.out.println(map);
    }
}
