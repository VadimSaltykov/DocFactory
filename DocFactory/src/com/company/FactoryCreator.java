package com.company;

class FactoryCreator {
    public static AbstractFactory getFactory(String choice) {

        if (choice.equalsIgnoreCase("Doc"))
            return new DocFactory();
        return null;

    }
}