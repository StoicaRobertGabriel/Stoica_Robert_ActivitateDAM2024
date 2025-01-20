package com.example.test_stoica_robert;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HusaTelefon implements Parcelable {
    private String material;
    private int lungime;
    private Boolean smartphone;

    @Override
    public String toString() {
        return "HusaTelefon{" +
                "material='" + material + '\'' +
                ", lungime=" + lungime +
                ", smartphone=" + smartphone +
                '}';
    }

    public HusaTelefon(String material, int lungime, Boolean smartphone) {
        this.material = material;
        this.lungime = lungime;
        this.smartphone = smartphone;
    }

    protected HusaTelefon(Parcel in) {
        material = in.readString();
        lungime = in.readInt();
        byte tmpSmartphone = in.readByte();
        smartphone = tmpSmartphone == 0 ? null : tmpSmartphone == 1;
    }

    public static final Creator<HusaTelefon> CREATOR = new Creator<HusaTelefon>() {
        @Override
        public HusaTelefon createFromParcel(Parcel in) {
            return new HusaTelefon(in);
        }

        @Override
        public HusaTelefon[] newArray(int size) {
            return new HusaTelefon[size];
        }
    };

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getLungime() {
        return lungime;
    }

    public void setLungime(int lungime) {
        this.lungime = lungime;
    }

    public Boolean getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(Boolean smartphone) {
        this.smartphone = smartphone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(material);
        parcel.writeInt(lungime);
        parcel.writeByte((byte) (smartphone == null ? 0 : smartphone ? 1 : 2));
    }
}
