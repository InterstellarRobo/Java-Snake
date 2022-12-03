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
    }

    //currently hardcoded to spawn facing right, change later?
    private void genSnake() {
        for (int i = length; i > 0; i--) {
            w.setGameCoord(headX - (length - i), headY, i);
        }
    }
}
