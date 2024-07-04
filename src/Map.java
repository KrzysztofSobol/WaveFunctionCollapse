import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Map {
    private int xMax, yMax;
    private LinkedList<Tile>[][] map;

    public Map(int x, int y) {
        this.xMax = x;
        this.yMax = y;
    }

    public void init(LinkedList<Tile> tiles) {
        tiles = deepCopyTiles(tiles);
        map = new LinkedList[xMax][yMax];
        
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                map[i][j] = deepCopyTiles(tiles);
            }
        }
    }

    private LinkedList<Tile> deepCopyTiles(LinkedList<Tile> source) {
        LinkedList<Tile> copy = new LinkedList<>();
        for (Tile tile : source) {
            copy.add(tile.clone());
        }
        return copy;
    }

    public void Generate(){
        Random rand = new Random();
        int x = rand.nextInt(xMax);
        int y = rand.nextInt(yMax);
        
        // Choosing first random tile on the map
        for(int h = 0; h < (xMax*yMax)-1; h++){
            Tile tile = RandomTile(map[x][y]);
            map[x][y].clear();
            map[x][y].add(tile);
            map[x][y].getFirst().collapse();
            UpdateNeighbours(x, y);

            // remake for a more efficient way to find the smallest list - priority queue maybe
            int smallest = Integer.MAX_VALUE;
            for(int i = 0; i < xMax; i++){
                for(int j = 0; j < yMax; j++){
                    if(map[i][j].size() < smallest && !(map[i][j].size() == 1 && map[i][j].getFirst().IsCollapsed())){
                        smallest = map[i][j].size();
                        x = i;
                        y = j;
                    }
                }
            }
        }
    }

    private void UpdateNeighbours(int x, int y) {
        Tile tile = map[x][y].getFirst();

        // Check left neighbor (x - 1)
        if (x > 0 && map[x - 1][y].size() > 1) {
            filterTiles(map[x - 1][y], tile.getNorth(), 'N');
        }

        // Check upper neighbor (y + 1)
        if (y < yMax - 1 && map[x][y + 1].size() > 1) {
            filterTiles(map[x][y + 1], tile.getEast(), 'E');
        }

        // Check right neighbor (x + 1)
        if (x < xMax - 1 && map[x + 1][y].size() > 1) {
            filterTiles(map[x + 1][y], tile.getSouth(), 'S');
        }

        // Check lower neighbor (y - 1)
        if (y > 0 && map[x][y - 1].size() > 1) {
            filterTiles(map[x][y - 1], tile.getWest(), 'W');
        }
    }

    private void filterTiles(LinkedList<Tile> neighbors, int direction, char side) {
        Iterator<Tile> iterator = neighbors.iterator();
        while (iterator.hasNext()) {
            Tile t = iterator.next();
            switch(side){
                case 'N':
                    if (t.getSouth() != direction) iterator.remove();
                    break;
                case 'S':
                    if(t.getNorth() != direction) iterator.remove();
                    break;
                case 'E':
                    if(t.getWest() != direction) iterator.remove();
                    break;
                case 'W':
                    if(t.getEast() != direction) iterator.remove();
                    break;
            }
        }
    }

    // Chooses a random tile based on its weight
    private Tile RandomTile(LinkedList<Tile> tiles){
        int totalWeight = tiles.stream().mapToInt(Tile::getWeight).sum();
        Random rand = new Random();
        int rValue = rand.nextInt(totalWeight);

        for(Tile tile : tiles){
            rValue -= tile.getWeight();
            if(rValue <= 0){
                return tile;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                char display = map[i][j].getFirst().getDisplay();
                result.append(display).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
