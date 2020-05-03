package com.company;

import java.util.Scanner;

class AbstractFactoryPatternExample {

    public static void main(String args[]) {

        LogThread logThread = new LogThread();
        Thread thread = new Thread(logThread);
        thread.start();
        boolean state = true;

        UIProcessing ui = new UIProcessing();

        while (state) {
            ui.showMenu();
            try {
                switch (new Scanner(System.in).nextInt()) {
                    case 1: {
                        ui.addDocument();
                    }
                    break;
                    case 2: {
                        ui.changeDocument();
                        break;
                    }
                    case 3: {
                        ui.deleteDocument();
                        break;
                    }
                    case 4: {
                        ui.showDocuments();
                        break;
                    }
                    case 5: {
                        state = false;
                        logThread.disable();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка");
            }
        }
    }
}