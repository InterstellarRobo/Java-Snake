public enum Collider {
    WALL, APPLE, SNAKE, EMPTY;

    public static Collider checkGameCoord(int x, int y, World w) {
        switch(w.getGameCoord(x, y)) {
            case -2:
                return APPLE;
            case -1:
                return WALL;
            case 0:
                return EMPTY;
        }
        
        return SNAKE;
    }
}
