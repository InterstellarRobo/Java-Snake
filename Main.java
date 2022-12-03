public class Main {
    public static void main(String[] args) {
        World testWorld = new World(15, 15);
        testWorld.printWorld();
        System.out.println();
        Snake snake = new Snake (9, 3, 5, testWorld);
        testWorld.setGameCoord(9, 4, -2);
        testWorld.printWorld();
        System.out.println();
        snake.moveSnakeHead(Direction.DOWN);
        testWorld.updateWorld();
        snake.placeSnakeHead();
        testWorld.printWorld();
    }
}