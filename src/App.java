import view.FrameController;

import javax.swing.*;

/**
 * @author Eduard Spousta
 * @version 1.0
 * @since 26.07.2023
 */

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FrameController::new);
    }
}
