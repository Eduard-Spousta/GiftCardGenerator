package file;

import model.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class ImageEditor {
    private BufferedImage imageIn;
    private BufferedImage imageOut;

    public ImageEditor(Data data) {
        try {
            imageIn = ImageIO.read(new File("giftCard.png"));
            System.out.println("image load success");

            imageOut = new BufferedImage(imageIn.getWidth(), imageIn.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imageOut.createGraphics();
            System.out.println(imageIn.getHeight());

            //TODO: CHECK PRICE SIZE - 3/4/5 digit price -> different layout location

            //draw price
            g.drawImage(imageIn, 0,0, null);
            g.setFont(new Font("Tw Cen MT", Font.PLAIN, 286));
            g.setColor(new Color(48,48,48));
            g.drawString(data.getPrice() + " Kč", 825,  640);

            //draw code
            g.setFont(new Font("Arial", Font.BOLD, 46));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(data.getVerificationCode()), 2255,  715);

            //draw date
            g.setFont(new Font("Arial", Font.TRUETYPE_FONT, 26));
            g.setColor(new Color(48,48,48));
            g.drawString(String.valueOf(data.getExpirationDate()), 2390,  766);


            g.dispose();

            File file = new File("newImage.png");
            if(file.exists()){
                file.delete();
            }
            ImageIO.write(imageOut, "png", new File("newImage.png"));
            System.out.println("image out success");


        } catch (IOException e) {
            System.out.println("Can not load image giftCard.png. Please contact admin" + e.getMessage());
        }
    }
}
