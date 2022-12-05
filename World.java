import java.util.Arrays;

public class World {
    private int[][] worldArray;
    private boolean appleEaten = false;
    private boolean worldOver = false;

    public World(int playSizeX, int playSizeY) {
        worldArray = new int[playSizeY + 2][playSizeX + 2];

        initializeArray();
        generateWalls(playSizeX + 2, playSizeY + 2);
    }

    private void initializeArray() {
        for (int y = 0; y < worldArray.length; y++) {
            Arrays.fill(worldArray[y], 0);
        }
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

    public void renderWorld(Snake... snakes) {
        for (int y = 0; y < worldArray.length; y++) {
            System.out.print("\t");
            for (int x = 0; x < worldArray[y].length; x++) {
                s:
                switch (worldArray[y][x]) {
                    case -2:
                        System.out.print("🍎");
                        break;
                    case -1:
                        System.out.print("⬜");
                        break;
                    case 0:
                        System.out.print("⬛");
                        break;
                    default:
                        for (Snake snake:snakes) {
                            if (snake.getHeadX() + 1 == x && snake.getHeadY() + 1 == y){
                                System.out.print("👴");
                                break s;
                            }
                        }
                        System.out.print("🟩");
                        
                        break;
                }
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

    public void spawnApple() {
        int appleX = (int) (Math.random() * this.gameWidth());
        int appleY = (int) (Math.random() * this.gameHeight());

        while (this.getGameCoord(appleX, appleY) != 0) {
            appleX = (int) (Math.random() * this.gameWidth());
            appleY = (int) (Math.random() * this.gameHeight());
        }

        this.setGameCoord(appleX, appleY, -2);
    }

    public void setAppleEaten() {
        this.appleEaten = true;
    }

    public void resetAppleEaten() {
        this.appleEaten = false;
    }

    public boolean checkAppleEaten() {
        return this.appleEaten;
    }

    public void updateWorld() {
        if (this.checkAppleEaten() == false) {
            for (int x = 0; x < this.gameWidth(); x++) {
                for (int y = 0; y < this.gameHeight(); y++) {
                    int value = this.getGameCoord(x, y);
                    if (value > 0) {
                        this.setGameCoord(x, y, value - 1);
                    }
                }
            }
        }
    }

    public void endGame() {
        worldOver = true;
    }

    public boolean checkGameOver() {
        return worldOver;
    }
}
