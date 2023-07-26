package model;

import java.util.Date;

public class Data {
    private int price;
    private String expirationDate;
    private String verificationCode;

    public Data(int price, String expirationDate, String verificationCode) {
        this.price = price;
        this.expirationDate = expirationDate;
        this.verificationCode = verificationCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "Data{" +
                "price=" + price +
                ", expirationDate=" + expirationDate +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
