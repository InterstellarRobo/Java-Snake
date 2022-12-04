import java.io.*;
import java.lang.Thread;
import org.jline.terminal.*;
import org.jline.utils.*;
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

        World testWorld = new World(15, 15);
        Snake snake = new Snake (9, 3, 5, testWorld);

        Thread t = new Thread(new InputThread(terminal));
        t.start();

        while (input != 'q') {
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

            testWorld.updateWorld();
            snake.placeSnakeHead();
            //Whatever, fix tomorrow
            terminal.puts(Capability.clear_screen);
            testWorld.printWorld();
            
            Thread.sleep(1000);
        }

        t.interrupt();
        t.join();
        terminal.close();
    }
}