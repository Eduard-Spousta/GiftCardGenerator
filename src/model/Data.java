package model;

import java.util.Date;

public class Data {
    private int price;
    private Date expirationDate;
    private String verificationCode;

    public Data(int price, Date expirationDate, String verificationCode) {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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
