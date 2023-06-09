package ru.kors.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            SimpleClient simpleClient = new SimpleClient(i);
            simpleClient.start();
        }
    }
}

class SimpleClient extends Thread {

    private final static String[] COMMAND = {"HELLO", "MORNING", "DAY", "EVENING"};

    private int cmdNumber;

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
        try {
//            System.out.println("Started: " + LocalDateTime.now());
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];

            String str = command + " Anton";

            bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String answer = bufferedReader.readLine();
            System.out.println("Client got string: " + answer);

            bufferedWriter.close();
            bufferedReader.close();
//            System.out.println("Finished: " + LocalDateTime.now());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
