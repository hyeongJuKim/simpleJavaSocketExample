package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

    private BufferedReader mIn;
    private PrintWriter mOut;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("start server");

            // 연결 요청이 들어오면 연결
            socket = serverSocket.accept();
            System.out.println("connect client");

            mIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            mOut = new PrintWriter(socket.getOutputStream());

            // 클라이언트에서 보낸 문자열 출력
            System.out.println(mIn.readLine());

            // 클라이언트에 문자열 전송
            mOut.println("success message");
            mOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 소켓 닫기 (연결 끊기)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        
        int port = 59090;
        Server server = new Server(port);
    }
}
