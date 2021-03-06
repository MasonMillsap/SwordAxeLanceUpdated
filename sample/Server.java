package sample;

/**
 * Created by Mason Millsap on 3/2/2017.
 */
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.ServerSocket;
        import java.net.Socket;

public class Server {
    static Fighter receivedPlayer;

    public static void main(String[] args) throws IOException {
        Server s = new Server(Integer.parseInt(args[0]));
        s.listen();
    }

    private ServerSocket accepter;

    public Server(int port) throws IOException {
        accepter = new ServerSocket(port);
        System.out.println("Server: IP address: " + accepter.getInetAddress() + " (" + port + ")");
    }

    public void listen() throws IOException {
//        for (;;) {
            Socket s = accepter.accept();
            SocketEchoThread echoer = new SocketEchoThread(s);
            System.out.println("Server: Connection accepted from " + s.getInetAddress());
            echoer.start();
//        }
    }

    private class SocketEchoThread extends Thread {
        private Socket socket;

        public SocketEchoThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                sendGreeting(writer);
                String msg = getMessage();
                System.out.println("Server: Received [" + msg + "]");
                receivedPlayer = Parser.parseFighter(msg);
                echoAndClose(writer, msg);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        private void sendGreeting(PrintWriter writer) {
            writer.println("Connection open.");
            writer.println("I will echo a single message, then close.");
        }

        private void echoAndClose(PrintWriter writer, String msg) throws IOException {
            writer.print(msg);
            writer.flush();
            socket.close();
        }

        private String getMessage() throws IOException {
            BufferedReader responses =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (!responses.ready()){}
            while (responses.ready()) {
                sb.append(responses.readLine() + '\n');
            }
            return sb.toString();
        }
    }
}