import javax.imageio.ImageIO;
import javax.swing.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;


public class MainContent extends JPanel {
    private JTextField input;
    private JButton search;

    private BufferedImage image;
    private JLabel filteredPic;
    private ImageIcon imageIcon;

    private JButton[] buttons;


    public MainContent() {
        this.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
        this.setDoubleBuffered(true);
        this.setLayout(null);

        this.input = new JTextField();
        this.input.setBounds(Constants.INPUT_X, Constants.INPUT_Y, Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT);
        this.add(this.input);
        this.input.setVisible(true);

        this.buttons = new JButton[Constants.BUTTONS_SIZE];

        this.search = new JButton("Search");
        this.search.setBounds(Constants.INPUT_X + Constants.INPUT_WIDTH + 1, Constants.INPUT_Y
                , Constants.INPUT_WIDTH, Constants.INPUT_HEIGHT);
        this.add(this.search);

        this.search.addActionListener((e) -> {
            try {
                
                String fInput = this.input.getText();
                System.setProperty("webdriver.chrome.driver", "D:\\Chrome-Driver\\chromedriver.exe");
                ChromeDriver driver = new ChromeDriver();
                
                String FACEBOOK_URL = "https://www.facebook.com/";
                driver.get(FACEBOOK_URL + fInput);
                List<WebElement> profileElements = driver.findElements(By.cssSelector("image[preserveAspectRatio=\"xMidYMid slice\"]"));
                WebElement imageLink = profileElements.get(0);


                
                String link = imageLink.getAttribute("xlink:href");
                JLabel pic = new JLabel();
                pic.setBounds(Constants.PIC_X, Constants.PIC_Y, Constants.PIC_WIDTH, Constants.PIC_HEIGHT);
                URL url = new URL(link);
                this.image = ImageIO.read(url);
                this.imageIcon = new ImageIcon(new ImageIcon(this.image).getImage().
                        getScaledInstance(Constants.IMAGE_ICON_WIDTH, Constants.IMAGE_ICON_HEIGHT, Image.SCALE_DEFAULT));
                pic.setIcon(this.imageIcon);
                this.add(pic);

                this.filteredPic = new JLabel();
                this.filteredPic.setBounds(Constants.FILTERED_PIC_X, Constants.FILTERED_PIC_Y, Constants.PIC_WIDTH, Constants.PIC_HEIGHT);
                this.filteredPic.setIcon(this.imageIcon);
                this.add(this.filteredPic);

                buttons();

                for (JButton button : this.buttons) {
                    this.add(button);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


    }

    public void buttons() {
        String[] names = {"Grayscale", "Color Shift Right", "Color Shift Left", "Mirror", "Negative" ,"Brighter"};
        int y = 100;
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton(names[i]);
            this.buttons[i].setBounds(Constants.BUTTON_X, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
            y += Constants.BUTTON_Y_ADDITION;
        }

        Filters filters = new Filters(this.image);

        //this.filteredPic.setIcon(this.imageIcon);
        BufferedImage grayscaleImage = filters.grayScale();
        ImageIcon iconGrayscale = new ImageIcon(new ImageIcon(grayscaleImage).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));

        //this.filteredPic.setIcon(this.imageIcon);
        BufferedImage colorShiftRightImage = filters.colorShiftRight();
        ImageIcon iconShiftRight = new ImageIcon(new ImageIcon(colorShiftRightImage).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));

        //this.filteredPic.setIcon(this.imageIcon);
        BufferedImage colorShiftLeftImage = filters.colorShiftLeft();
        ImageIcon iconShiftLeft = new ImageIcon(new ImageIcon(colorShiftLeftImage).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));
        
        BufferedImage mirrored = filters.mirror();
        ImageIcon iconMirrored = new ImageIcon(new ImageIcon(mirrored).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));

        BufferedImage negative = filters.negative();
        ImageIcon iconNegative = new ImageIcon(new ImageIcon(negative).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));

        BufferedImage brighter = filters.brighter(Constants.BRIGHT_LEVEL, Constants.BRIGHT_CEILING);
        ImageIcon iconBorders = new ImageIcon(new ImageIcon(brighter).getImage().
        getScaledInstance(Constants.PIC_WIDTH, Constants.PIC_HEIGHT, Image.SCALE_DEFAULT));



        //ACTION LISTENERS
        this.buttons[Constants.FIRST_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconGrayscale);
        });

        this.buttons[Constants.SECOND_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconShiftRight);
        });

        this.buttons[Constants.THIRD_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconShiftLeft);
        });

        this.buttons[Constants.FOURTH_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconMirrored);
        });

        this.buttons[Constants.FIFTH_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconNegative);
        });

        this.buttons[Constants.SIXTH_BUTTON].addActionListener((e) -> {
            this.filteredPic.setIcon(iconBorders);
        });

        

    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        repaint();
    }


}









