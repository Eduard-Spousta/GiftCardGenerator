package view;

import model.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class MainFrame extends JFrame implements ActionListener {
    private JButton btnReset;
    private JButton btnConfirm;
    private JFormattedTextField txtDate;
    private JTextField txtPrice;
    private Data data;
    private Random random = new Random();

    public MainFrame() {
        //Default field parameters
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusYears(1);
        String defaultDate = dateTimeFormat().format(localDate);
        String defaultPrice = "1000";

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
        txtPrice.setText(defaultPrice);
        txtDate = new JFormattedTextField(dateFormat());
        txtDate.setText(defaultDate);

        //Save default value (instead a click to the Date field is required)
        try {
            txtDate.commitEdit();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "DefaultDate is not currently supported. Please contact admin." + e.getMessage());
        }

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
        btnReset.addActionListener(this);
        btnConfirm.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            try {
                if (txtDate.getText() == null) {
                    JOptionPane.showMessageDialog(this, "Please write the day as e.g. 31.04.2023");
                    return;
                }
                data = new Data(
                        Integer.parseInt(txtPrice.getText()),
                        (Date) txtDate.getValue(),
                        generateRandomSequence()
                );

                System.out.println(data);

                //TODO: CONFIRMATION OF SUCCESS

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "Please enter number as e.g. 1500");
                txtPrice.setText("");
                txtDate.setValue(null);
            }
        }
        if (e.getSource() == btnReset) {
            txtPrice.setText("");
            txtDate.setValue(null);
        }
    }

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }
    private DateTimeFormatter dateTimeFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private String generateRandomSequence() {
        StringBuilder sequenceBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                sequenceBuilder.append("-");
            } else {
                if (random.nextInt(2) == 0) {
                    sequenceBuilder.append(generateRDMNumber());
                } else {
                    sequenceBuilder.append(generateRDMSymbol());
                }
            }
        }
        return sequenceBuilder.toString();
    }

    private char generateRDMSymbol() {
        int letter = random.nextInt(26) + 'a';
        return Character.toUpperCase((char) letter);
    }
    private int generateRDMNumber() {
        int number = random.nextInt(10);
        return number;
    }
}
