package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frame extends JFrame{
    private JButton btnReset;
    private JButton btnConfirm;
    private JFormattedTextField txtDate;
    private JTextField txtPrice;
    private int price;
    private Date expirationDate;

    public Frame(){
        //TODO: SAVE DATA
        //TODO: SEND/RESET DATA ON BTN CLICK

        //Main setup
        setTitle("Code Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Main panel
        int spacer = 100;
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        pnlMain.setBorder(new EmptyBorder(spacer, spacer, spacer, spacer));

        //North
        JPanel pnlNorth = new JPanel();
        JLabel lblHeadline = new JLabel("CODE GENERATOR");
        lblHeadline.setFont(new Font("Tw Cen MT", Font.BOLD, 60));
        pnlNorth.setBorder(new EmptyBorder(0, 0, (int) (spacer / 2), 0));
        pnlNorth.add(lblHeadline);
        pnlMain.add(pnlNorth, BorderLayout.NORTH);

        //Middle
        JPanel pnlMiddle = new JPanel();
        pnlMiddle.setLayout(new GridLayout(2, 2, 5, 5));
        JLabel lblPrice = new JLabel("Price");
        JLabel lblDate = new JLabel("Date");
        txtPrice = new JTextField();
        txtDate = new JFormattedTextField(dateFormat());
        Font font = new Font("SansSerif", Font.PLAIN, 24);
        lblPrice.setFont(font);
        lblDate.setFont(font);
        pnlMiddle.add(lblPrice);
        pnlMiddle.add(txtPrice);
        pnlMiddle.add(lblDate);
        pnlMiddle.add(txtDate);
        pnlMain.add(pnlMiddle, BorderLayout.CENTER);

        //South
        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new BorderLayout());
        btnReset = new JButton("RESET");
        btnConfirm = new JButton("CONFIRM");
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout());
        Dimension buttonSize = new Dimension(200, 50);
        btnReset.setPreferredSize(buttonSize);
        btnConfirm.setPreferredSize(buttonSize);
        pnlSouth.setBorder(new EmptyBorder(spacer / 4, 0, 0, 0));
        pnlButtons.add(btnReset);
        pnlButtons.add(btnConfirm);
        pnlSouth.add(pnlButtons, BorderLayout.CENTER);
        JPanel pnlCentered = new JPanel();
        pnlCentered.setLayout(new FlowLayout());
        JLabel lblCopyright = new JLabel("\u00A9 Eduard Spousta for www.vonavyobchod.cz");
        pnlCentered.add(lblCopyright);
        pnlCentered.setBorder(new EmptyBorder((int) (spacer / 4), 0, 0, 0));
        pnlSouth.add(pnlCentered, BorderLayout.SOUTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);

        //Specs
        add(pnlMain);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private SimpleDateFormat dateFormat() {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        return date;
    }

}
