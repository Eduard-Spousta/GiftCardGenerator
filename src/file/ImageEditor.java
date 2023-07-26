package file;

import model.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Works with .PNG file
 */
public class ImageEditor implements Editor {

    /**
     * Create new PNG file with given data
     *
     * @param data Data given by user
     */
    public ImageEditor(Data data) {
        try {
            //Help variable for specific operation with Strings
            String temp;

            //Load image
            BufferedImage imageIn = ImageIO.read(new File("./src/resources/img/giftCard.png"));

            //New image setup
            BufferedImage imageOut = new BufferedImage(imageIn.getWidth(), imageIn.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imageOut.createGraphics();
            g.drawImage(imageIn, 0, 0, null);

            //Load Specific font
            //https://fonts.google.com/noto/specimen/Noto+Sans+Grantha?preview.text=2000&preview.text_type=custom&query=grant&category=Sans+Serif
            InputStream inputStream = ImageEditor.class.getResourceAsStream("/resources/font/NotoSansGrantha-Regular.ttf");
            assert inputStream != null;
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            myFont = myFont.deriveFont(265f);

            //draw price - depends on int value (number of digits)
            g.setFont(myFont);
            g.setColor(new Color(48, 48, 48));
            if (data.price() < 1000) {
                g.drawString(data.price() + " Kč", 920, 640);
            } else if (data.price() < 10000) {
                g.drawString(data.price() + " Kč", 820, 640);
            } else if (data.price() < 100000) {
                temp = String.valueOf(data.price());
                temp = temp.substring(0, 2) + " " + temp.substring(2);
                g.drawString(temp + " Kč", 720, 640);
            }

            //draw date
            g.setFont(new Font("Arial", Font.PLAIN, 26));
            g.drawString(String.valueOf(data.expirationDate()), 2390, 766);

            //draw code
            g.setFont(new Font("Arial", Font.BOLD, 46));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(data.verificationCode()), 2260, 715);

            g.dispose();

            //Export file
            temp = data.expirationDate();
            temp = temp.replace(".", "_");
            temp = "kupon_" + temp + "_" + data.verificationCode() + ".png";

            ImageIO.write(imageOut, "png", new File(findParentDirectory() + File.separator + temp));

        } catch (IOException e) {
            throw new RuntimeException("Can not load image giftCard.png. Please contact admin" + e);
        } catch (FontFormatException e) {
            throw new RuntimeException("Can not load font NotoSansGrantha-Regular.ttf. Please contact admin" + e);
        }
    }
}
