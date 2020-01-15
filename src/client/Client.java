package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;

    private BufferedReader mIn;
    private PrintWriter mOut;

    public Client(String ip, int port){
        try {
            socket = new Socket(ip, port);
            System.out.println(ip + " socket connect");

            // 통로 뚫기
            mIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            mOut = new PrintWriter(socket.getOutputStream());

            mOut.println("client -> server");
            mOut.flush();

            System.out.println(mIn.readLine());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String ip = "192.168.0.6";
        int port = 59090;

        System.out.println("client init");
        new Client(ip, port);

    }

}
