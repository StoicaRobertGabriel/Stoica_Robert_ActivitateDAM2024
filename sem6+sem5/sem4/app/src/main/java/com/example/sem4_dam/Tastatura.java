package com.example.sem4_dam;

import android.os.Parcel;
import android.os.Parcelable;

public class Tastatura implements Parcelable {
    private int keyboardMSN;
    private enum keyboardLength{
        FULL_SIZED,COMPACT,TKL
    };
    keyboardLength type;
    private String keyboardManufacturer;
    private Boolean keyboardWireless;

    public Tastatura(int keyboardMSN,String keyboardLength,String keyboardManufacturer,Boolean keyboardWireless){
        this.keyboardMSN=keyboardMSN;
        switch (keyboardLength) {
            case "COMPACT":
                this.type = Tastatura.keyboardLength.COMPACT;
                break;
            case "FULL-SIZED":
                this.type = Tastatura.keyboardLength.FULL_SIZED;
                break;
            case "TKL":
                this.type = Tastatura.keyboardLength.TKL;
                break;
        }
//        this.keyboardLenght=keyboardLenght;
        this.keyboardManufacturer=keyboardManufacturer;
        this.keyboardWireless=keyboardWireless;
    }

    protected Tastatura(Parcel in) {
        keyboardMSN = in.readInt();
        keyboardManufacturer = in.readString();
        byte tmpKeyboardWireless = in.readByte();
        keyboardWireless = tmpKeyboardWireless == 0 ? null : tmpKeyboardWireless == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(keyboardMSN);
        dest.writeString(keyboardManufacturer);
        dest.writeByte((byte) (keyboardWireless == null ? 0 : keyboardWireless ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tastatura> CREATOR = new Creator<Tastatura>() {
        @Override
        public Tastatura createFromParcel(Parcel in) {
            return new Tastatura(in);
        }

        @Override
        public Tastatura[] newArray(int size) {
            return new Tastatura[size];
        }
    };

    @Override
    public String toString() {
        return "Tastatura{" +
                "keyboardMSN=" + keyboardMSN +
                ", type=" + type +
                ", keyboardManufacturer='" + keyboardManufacturer + '\'' +
                ", keyboardWireless=" + keyboardWireless +
                '}';
    }
}
