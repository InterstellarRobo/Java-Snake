public class Main {
    public static void main(String[] args) {
        World testWorld = new World(5, 5);
        testWorld.printWorld();
        System.out.println();
        World testWorld2 = new World(10, 3);
        Snake world2Snake = new Snake(7, 1, 4, testWorld2);
        Snake world2Snake2 = new Snake(5, 0, 6, testWorld2);
        testWorld2.printWorld();
    }
}