package com.example.sem4_dam;

public class Tastatura {
    private int keyboardMSN;
    private enum keyboardLength{
        FULL,TKL,SIXTY,HALF
    };
    private String keyboardManufacturer;
    private Boolean keyboardWireless;

    public Tastatura(int keyboardMSN,String keyboardLenght,String keyboardManufacturer,Boolean keyboardWireless){
        this.keyboardMSN=keyboardMSN;
//        this.keyboardLenght=keyboardLenght;
        this.keyboardManufacturer=keyboardManufacturer;
        this.keyboardWireless=keyboardWireless;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tastatura{");
        sb.append("keyboardMSN=").append(keyboardMSN);
        sb.append(", keyboardManufacturer='").append(keyboardManufacturer).append('\'');
        sb.append(", keyboardWireless=").append(keyboardWireless);
        sb.append('}');
        return sb.toString();
    }
}
