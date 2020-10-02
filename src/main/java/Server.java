import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();

    public void start(int port) {
        try {
            ServerSocket servSocket = new ServerSocket(port);
            while (true) {
                try (Socket socket = servSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        if (line.equals("end")) {
                            break;
                        }
                        try {
                            int num = Integer.parseInt(line);
                            if (num < 0){
                                System.out.println("Number should be >= 0");
                            }
                            int res = fibonacciCalculator.calc(num);
                            out.println("Result: " + res);
                        } catch (NumberFormatException e) {
                            System.out.println("Smth's wrong with the entered data");
                        }
                    }
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }

                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
