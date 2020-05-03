package com.company;

import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UIProcessing {

    static Logger LOGGER;
    ArrayList<Doc> myArrayList = new ArrayList<Doc>();

    static {
        try (FileInputStream ins = new FileInputStream("C:\\Users\\log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Main.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public void addDocument() {
        System.out.print("Введите название документа (Passport, Contract): ");
        String docName = new Scanner(System.in).nextLine();

        AbstractFactory docFactory = FactoryCreator.getFactory("Doc");
        Doc b = docFactory.getDoc(docName);
        System.out.println();
        System.out.print("Введите ID документа: ");
        b.setId(new Scanner(System.in).nextInt());
        System.out.println();
        LOGGER.log(Level.INFO, "Создался документ с именем \"" + docName
                + "\"" + " и полем ID со значением: "
                + b.getId() + " Класс созданного объекта: " + b.getDocName());
        myArrayList.add(b);
    }

    public void changeDocument() {
        if (myArrayList.size() == 0) {
            System.out.println("Ошибка, документ ещё не создан, введите \"1\"");
            return;
        } else {
            System.out.print("Введите ячейку в списке которую хотите изменить: ");
            int slot = new Scanner(System.in).nextInt();
            System.out.print("Введите новое значение: ");
            int newId = new Scanner(System.in).nextInt();
            int test = myArrayList.get(slot - 1).getId();
            myArrayList.get(slot - 1).setId(newId);
            LOGGER.log(Level.INFO, "Была отредактирована запись под номером "
                    + slot + " Класс отредактированного объекта: "
                    + myArrayList.get(slot - 1).getDocName()
                    + " Предыдущее значение ID: " + test
                    + " Новое значение ID: "
                    + myArrayList.get(slot - 1).getId());
        }
    }

    public void deleteDocument() {
        if (myArrayList.size() == 0) {
            System.out.println("Ошибка, документ ещё не создан, введите \"1\"");
            return;
        }
        System.out.print("Введите номер ячейки которую хотите удалить: ");
        int slot = new Scanner(System.in).nextInt();

        int id = myArrayList.get(slot - 1).getId();
        String idName = myArrayList.get(slot - 1).getDocName();
        myArrayList.remove(slot - 1);
        LOGGER.log(Level.INFO, "Была удалена запись под номером "
                + slot + " Класс удаленного объекта: "
                + idName + " Значение ID: " + id);
    }

    public void showDocuments() {
        if (myArrayList.size() == 0) {
            System.out.println("Список пуст введите \"1\"");
            return;
        }
        System.out.println("Вывожу список документов");
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.print((i + 1) + ": ");
            System.out.print(myArrayList.get(i));
            System.out.println(" ID: " + myArrayList.get(i).getId());
        }
    }

    public void showMenu() {
        System.out.print("Доступные команды:\n" +
                "1) Создать новый документ\n" +
                "2) Редактировать документ\n" +
                "3) Удалить документ\n" +
                "4) Вывести список документов\n" +
                "5) Выйти из программы\n" +
                "Введите номер команды: ");
        System.out.println();
    }
}