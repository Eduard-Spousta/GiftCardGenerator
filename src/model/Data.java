package model;

/**
 * Data model for user input or autoGenerated data
 * Record - Java 16 feature - final + private variables, "getters", constructor,...
 * <a href="https://docs.oracle.com/en/java/javase/20/language/records.html#GUID-6699E26F-4A9B-4393-A08B-1E47D4B2D263">DOCS</a>
 *
 * @param price            Int, given by user (e.g. 1000)
 * @param expirationDate   Preformatted String, given by user (e.g. 31.12.2024)
 * @param verificationCode String, autoGenerated (e.g. 1234-ABED)
 */
public record Data(int price, String expirationDate, String verificationCode) {
}
