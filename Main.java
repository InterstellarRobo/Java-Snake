import java.io.*;
import java.lang.Thread;
import org.jline.terminal.*;
import org.jline.utils.InfoCmp.Capability;

public class Main {
    private static char input = ' ';

    private static class InputThread implements Runnable{
        private Terminal t;

        public InputThread(Terminal t) {
            this.t = t;
        }

        public void run() {
            try {
                Reader r = t.reader();
                while (!Thread.interrupted()) {
                    input = (char) r.read();
                }
                r.close();
            }
            catch (IOException e) {
                //It's all good baby (-Brian Ehlenbach)
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException{
        Terminal terminal = TerminalBuilder.builder().jna(true).system(true).build();
        terminal.enterRawMode();

        World world = new World(15, 15);
        Snake snake = new Snake(5, 3, 5, world);

        Thread t = new Thread(new InputThread(terminal));
        t.start();

        while (input != 'q' && world.checkGameOver() == false) {
            switch (input) {
                case 'w':
                    snake.moveSnakeHead(Direction.UP);
                    break;
                case 'a':
                    snake.moveSnakeHead(Direction.LEFT);
                    break;
                case 's':
                    snake.moveSnakeHead(Direction.DOWN);
                    break;
                case 'd':
                    snake.moveSnakeHead(Direction.RIGHT);
                    break;
                default:
                snake.moveSnakeHead();
                    break;
            }

            world.updateWorld();
            snake.placeSnakeHead();
            terminal.puts(Capability.clear_screen);
            terminal.flush();
            System.out.println("Score: " + snake.getLength());
            System.out.println(world.renderWorld(snake));
            
            Thread.sleep(750);
        }

        System.out.println();
        System.out.println("Game over! Score is " + snake.getLength());

        t.interrupt();
        t.join();
        terminal.close();
    }
}