package view;

import file.FileEditor;
import file.ImageEditor;
import model.Data;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * GUI Controller for the application
 */
public class FrameController extends JFrame implements ActionListener {
    private final JButton btnReset;
    private final JButton btnConfirm;
    private final JFormattedTextField txtDate;
    private final JTextField txtPrice;
    private final Random random = new Random();
    private String defaultDate;
    private String defaultPrice;

    /**
     * Creates new GUI Controller
     */
    public FrameController() {
        //Default field parameters
        setDefaultParameters();

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
        pnlNorth.setBorder(new EmptyBorder(0, 0, spacer / 2, 0));
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
        setDefaultFieldParameters();

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
        JLabel lblCopyright = new JLabel("© Eduard Spousta for www.vonavyobchod.cz");
        pnlCentered.add(lblCopyright);
        pnlCentered.setBorder(new EmptyBorder(spacer / 4, 0, 0, 0));
        pnlSouth.add(pnlCentered, BorderLayout.SOUTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);

        //Specs
        add(pnlMain);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Button click handler
     * check if data are valid, passes the work on Editors (file/image)
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            try {
                //Checks for legit data
                if (txtDate.getText() == null) {
                    JOptionPane.showMessageDialog(this, "Please write the day as e.g. 31.04.2023");
                    return;
                }
                if (Integer.parseInt(txtPrice.getText()) < 100 || Integer.parseInt(txtPrice.getText()) > 10000) {
                    JOptionPane.showMessageDialog(this, "Out of price limit (100Kč - 10 000Kč)");
                    return;
                }

                //Creates a new Object
                Data data = new Data(
                        Integer.parseInt(txtPrice.getText()),
                        txtDate.getText(),
                        generateRandomSequence()
                );

                //Passes the object to the Editors
                new ImageEditor(data);
                new FileEditor(data);

                //Close panel
                dispose();

                //If the code gets here it is always success because there are in each method RuntimeExceptions.
                JOptionPane.showMessageDialog(this, "success");

                //When click OK the app will terminate
                System.exit(0);


            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(this, "Please enter number as e.g. 1500");
                setDefaultFieldParameters();
            }
        }
        if (e.getSource() == btnReset) {
            setDefaultFieldParameters();
        }

    }


    /**
     * Date format for JFormattedTextField
     * @return SimpleDateFormat dateFormat
     */
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    /**
     * Date format for operation with Date
     * @return DateTimeFormatter dateFormat
     */
    private DateTimeFormatter dateTimeFormat() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    /**
     * Generating unique code sequence
     * @return String sequence (e.g. 1234-ABED)
     */
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

    /**
     * Generate RDM symbol
     * @return char (a-z)
     */
    private char generateRDMSymbol() {
        int letter = random.nextInt(26) + 'a';
        return Character.toUpperCase((char) letter);
    }

    /**
     * Generate RDM number
     * @return int (0-9)
     */
    private int generateRDMNumber() {
        return random.nextInt(10);
    }

    /**
     * Generate default parameters
     * Date - today + 1 Year
     * Price - 1000
     */
    private void setDefaultParameters(){
        LocalDate localDate;
        localDate = LocalDate.now();
        localDate = localDate.plusYears(1);
        defaultDate = dateTimeFormat().format(localDate);
        defaultPrice = "1000";
    }

    /**
     * Insert generated parameters to txtFields
     * @see #setDefaultParameters()
     */
    private void setDefaultFieldParameters(){
        txtPrice.setText(defaultPrice);
        txtDate.setText(defaultDate);
    }
}
