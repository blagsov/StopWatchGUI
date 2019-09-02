import javax.swing.*;
/*
 * code written
 * by Zoya Klocheva
 */
public class Main {
    public static void main(String[] args) {
        //создание формы
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            Form form = new Form();
            frame.setContentPane(form.getRootPanel());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("StopWatch");
            frame.setSize(300, 170);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }
}