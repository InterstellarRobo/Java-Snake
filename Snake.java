public class Snake {
    private int headX;
    private int headY;
    private int length;
    private Direction d;
    private World w;

    public Snake(int headX, int headY, int length, World w) {
        this.headX = headX;
        this.headY = headY;
        this.length = length;
        this.d = Direction.RIGHT;
        this.w = w;

        genSnake();
        w.spawnApple();
    }

    //currently hardcoded to spawn facing right, change later?
    private void genSnake() {
        for (int i = this.length; i > 0; i--) {
            w.setGameCoord(this.headX - (this.length - i), this.headY, i);
        }
    }

    public void moveSnakeHead(Direction d) {
        
        //don't move the opposite direction check
        if (!(d == Direction.UP && this.d == Direction.DOWN) && !(d == Direction.DOWN && this.d == Direction.UP) && !(d == Direction.LEFT && this.d == Direction.RIGHT) && !(d == Direction.RIGHT && this.d == Direction.LEFT)) {
            this.d = d;
            //honestly don't know if this is required yet
            int tempHeadX = this.headX;
            int tempHeadY = this.headY;

            switch (this.d) {
                case UP:
                    tempHeadY--;
                    break;
                case DOWN:
                    tempHeadY++;
                    break;
                case LEFT:
                    tempHeadX--;
                    break;
                case RIGHT:
                    tempHeadX++;
                    break;
            }

            switch (Collider.checkGameCoord(tempHeadX, tempHeadY, w)) {
                case EMPTY:
                    this.headX = tempHeadX;
                    this.headY = tempHeadY;
                    break;
                case APPLE:
                    this.headX = tempHeadX;
                    this.headY = tempHeadY;
                    w.setAppleEaten();
                    break;
                default:
                    System.out.println("Deal with this later");
                    break;
            }
        }
        //if the input is bad keep moving it the same way
        else moveSnakeHead();
    }

    public void moveSnakeHead() {
        moveSnakeHead(this.d);
    }

    public void placeSnakeHead() {
        if (w.checkAppleEaten() == true) {
            this.length++;
            w.setGameCoord(this.headX, this.headY, this.length);
            w.spawnApple();
            w.resetAppleEaten();
        }
        else w.setGameCoord(this.headX, this.headY, this.length);
    }
}
