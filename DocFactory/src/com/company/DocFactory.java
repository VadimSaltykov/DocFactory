package com.company;

class DocFactory extends AbstractFactory {

    DocFactory() {
    }

    public Doc getDoc(String var1) {
        if (var1 == null) {
            return null;
        } else if (var1.equalsIgnoreCase("Passport")) {
            return new Passport();
        } else {
            return var1.equalsIgnoreCase("Contract") ? new Contract() : null;
        }
    }
}