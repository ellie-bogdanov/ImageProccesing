import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        this.setVisible(true);
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        MainContent mainContent = new MainContent();
        this.add(mainContent);
        this.repaint();
    }
}
