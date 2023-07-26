package file;

import model.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageEditor {
    private BufferedImage imageIn;
    private BufferedImage imageOut;

    public ImageEditor(Data data) {
        try {
            //Help variable for specific operation with Strings
            String temp;

            //Load image
            imageIn = ImageIO.read(new File("./src/resources/img/giftCard.png"));
            System.out.println("image load success");

            //New image setup
            imageOut = new BufferedImage(imageIn.getWidth(), imageIn.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imageOut.createGraphics();
            System.out.println(imageIn.getHeight());
            g.drawImage(imageIn, 0,0, null);

            //Load Specific font
            //https://fonts.google.com/noto/specimen/Noto+Sans+Grantha?preview.text=2000&preview.text_type=custom&query=grant&category=Sans+Serif
            InputStream inputStream = ImageEditor.class.getResourceAsStream("/resources/font/NotoSansGrantha-Regular.ttf");
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            myFont = myFont.deriveFont(265f);

            //draw price - depends on int value (number of digits)
            g.setFont(myFont);
            g.setColor(new Color(48,48,48));
            if(data.getPrice()<1000){
                g.drawString(data.getPrice() + " Kč", 920,  640);
                System.out.println("100-999");
            } else if (data.getPrice()<10000){
                g.drawString(data.getPrice() + " Kč", 820,  640);
                System.out.println("1000-9999");
            } else if(data.getPrice()<100000){
                temp = String.valueOf(data.getPrice());
                temp = temp.substring(0,2) + " " + temp.substring(2);
                g.drawString(temp+ " Kč", 720,  640);
                System.out.println("10000-99999");
            }

            //draw date
            g.setFont(new Font("Arial", Font.TRUETYPE_FONT, 26));
            g.drawString(String.valueOf(data.getExpirationDate()), 2390,  766);

            //draw code
            g.setFont(new Font("Arial", Font.BOLD, 46));
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(data.getVerificationCode()), 2260,  715);

            g.dispose();

            //Export file
            temp = data.getExpirationDate();
            temp = temp.replace(".", "_");
            temp = "kupon_" + temp + "_" +data.getVerificationCode() + ".png";
            File file = new File(temp);
            if(file.exists()){
                file.delete();
            }
            ImageIO.write(imageOut, "png", new File(temp));
            System.out.println("image out success");


        } catch (IOException e) {
            System.out.println("Can not load image giftCard.png. Please contact admin" + e.getMessage());
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            System.out.println("Can not load font NotoSansGrantha-Regular.ttf. Please contact admin" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
