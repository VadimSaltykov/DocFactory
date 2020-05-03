package com.company;

import java.io.*;
import java.util.Date;

public class LogThread implements Runnable {

    private boolean isActive = true;

    public void disable() {
        isActive = false;
    }

    public void run() {

        File file = new File("log.0.0.txt");

        while (isActive) {
            int n = 5000;
            if (file.length() == 0) {
                Date date = new Date();
                System.out.println(date + " Файл с логами пуст за последние " + (n / 1000) + " секунд");
                sleepNSeconds(n);
            } else {
                try (BufferedReader in = new BufferedReader(new FileReader("log.0.0.txt"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    PrintWriter writer = new PrintWriter("log.0.0.txt");
                    writer.print("");
                    writer.close();
                    sleepNSeconds(5000);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sleepNSeconds(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
