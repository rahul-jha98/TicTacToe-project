package sample.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameHandlerThread implements Runnable{
    private GameListner gameListner;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    public GameHandlerThread(Socket socket, GameListner gameListner) throws IOException {
        this.gameListner = gameListner;
        InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(streamReader);
        writer = new PrintWriter(socket.getOutputStream());

        writer.println("-1");
        writer.flush();
        System.out.println("Strobe done");
        this.socket = socket;
    }


    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                System.out.print("Reading Messages");
               if(message.startsWith("M")) {
                   String[] data = message.split(" ");
                   System.out.println(data[1] + " " + data[2] + " " + data[3]);
                    gameListner.onMoved(data[1], Integer.parseInt(data[2]));
                    gameListner.onPlayerWon(Integer.parseInt(data[3]));
                }
                else if(message.startsWith("R")){
                    gameListner.onReset();
               }
                else {
                   gameListner.setChar(message);
                   System.out.println("Acknowledge");
               }
            }
        } catch(Exception ex) {
            System.out.print("Error in MessageThreadListner");
        }
    }

    public void sendMove(String message){
        System.out.println(message);
        writer.println(message);
        writer.flush();
    }
}
