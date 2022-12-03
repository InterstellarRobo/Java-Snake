import java.util.Arrays;

public class World {
    private int[][] worldArray;

    public World(int playSizeX, int playSizeY) {
        worldArray = new int[playSizeY + 2][playSizeX + 2];

        for (int y = 0; y < worldArray.length; y++) {
            Arrays.fill(worldArray[y], 0);
        }
        generateWalls(playSizeX + 2, playSizeY + 2);
    }

    private void generateWalls(int gridSizeX, int gridSizey) {
        for (int x = 0; x < worldArray[0].length; x++) {
            worldArray[0][x] = -1;
            worldArray[worldArray.length - 1][x] = -1;
        }
        for (int y = 1; y < worldArray.length - 1; y++) {
            worldArray[y][0] = -1;
            worldArray[y][worldArray[y].length - 1] = -1;
        }
    }

    public void printWorld() {
        for (int y = 0; y < worldArray.length; y++) {
            for (int x = 0; x < worldArray[y].length; x++) {
                System.out.print(worldArray[y][x] + " ");
            }
            System.out.println();
        }
    }

    public int gameWidth() {
        return worldArray[0].length - 2;
    }

    public int gameHeight() {
        return worldArray.length - 2;
    }

    public int getGameCoord(int x, int y) {
        if (x < 0 || y < 0 || x >= this.gameWidth() || y >= this.gameHeight()) return -1;
        return worldArray[y + 1][x + 1];
    }

    public void setGameCoord(int x, int y, int val){
        worldArray[y + 1][x + 1] = val;
    }
}
