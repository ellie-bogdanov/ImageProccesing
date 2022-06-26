import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;


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
                JLabel pic = new JLabel();
                pic.setBounds(30, 1, Constants.WIDTH, Constants.HEIGHT);
                URL url = new URL("https://img.fruugo.com/product/2/47/131338472_max.jpg");
                this.image = ImageIO.read(url);
                this.imageIcon = new ImageIcon(new ImageIcon(this.image).getImage().
                        getScaledInstance(500, 700, Image.SCALE_DEFAULT));
                pic.setIcon(this.imageIcon);
                this.add(pic);

                this.filteredPic = new JLabel();
                this.filteredPic.setBounds(840, 1, Constants.WIDTH, Constants.HEIGHT);
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
        String[] names = {"Grayscale", "Color Shift Right", "Color Shift Left", "Mirror", "Negative" ,"Add Borders"};
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
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));

        //this.filteredPic.setIcon(this.imageIcon);
        BufferedImage colorShiftRightImage = filters.colorShiftRight();
        ImageIcon iconShiftRight = new ImageIcon(new ImageIcon(colorShiftRightImage).getImage().
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));

        //this.filteredPic.setIcon(this.imageIcon);
        BufferedImage colorShiftLeftImage = filters.colorShiftLeft();
        ImageIcon iconShiftLeft = new ImageIcon(new ImageIcon(colorShiftLeftImage).getImage().
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));
        
        BufferedImage mirrored = filters.mirror();
        ImageIcon iconMirrored = new ImageIcon(new ImageIcon(mirrored).getImage().
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));

        BufferedImage negative = filters.negative();
        ImageIcon iconNegative = new ImageIcon(new ImageIcon(negative).getImage().
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));

        BufferedImage borders = filters.borders();
        ImageIcon iconBorders = new ImageIcon(new ImageIcon(borders).getImage().
        getScaledInstance(500, 700, Image.SCALE_DEFAULT));



        //ACTION LISTENERS
        this.buttons[0].addActionListener((e) -> {
            this.filteredPic.setIcon(iconGrayscale);
        });

        this.buttons[1].addActionListener((e) -> {
            this.filteredPic.setIcon(iconShiftRight);
        });

        this.buttons[2].addActionListener((e) -> {
            this.filteredPic.setIcon(iconShiftLeft);
        });

        this.buttons[3].addActionListener((e) -> {
            this.filteredPic.setIcon(iconMirrored);
        });

        this.buttons[4].addActionListener((e) -> {
            this.filteredPic.setIcon(iconNegative);
        });

        this.buttons[5].addActionListener((e) -> {
            this.filteredPic.setIcon(iconBorders);
        });

        

    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        repaint();
    }


}








//String fInput = this.input.getText();           TRY 1
//System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ IDEA Community Edition 2021.2.3\\chromedriver.exe");
//ChromeDriver driver = new ChromeDriver();
//String FACEBOOK_URL = "https://www.facebook.com/";
//driver.get(FACEBOOK_URL + fInput);
//WebElement outerPic = driver.findElement(By.tagName("image"));
//outerPic.click();
//WebElement innerPic = driver.findElement(By.tagName("img"));
//String picLink = innerPic.getAttribute("src");
//try {
//    URL picUrl = new URL(picLink);
//    BufferedImage bufferedImage = ImageIO.read(picUrl);
//    ImageIcon image = new ImageIcon(bufferedImage);
//    this.pic = new JLabel();
//    this.pic.setBounds(0, 100, Constants.WIDTH, Constants.HEIGHT);
//    this.pic.setIcon(image);
//    this.add(this.pic);
//    this.repaint();
//
//} catch (Exception ex) {
//    ex.printStackTrace();
//}

//new Thread(() -> {        TRY 2
//    try {
//        System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ IDEA Community Edition 2021.2.3\\chromedriver.exe");
//        ChromeDriver driver = new ChromeDriver();
//        String FACEBOOK_URL ="https://www.facebook.com/";
//        driver.get(FACEBOOK_URL + fInput);
//        Thread.sleep(50);
//        WebElement svg = driver.findElement(By.className("pzggbiyp"));
//        svg.click();
//        Thread.sleep(50);
//        WebElement img = driver.findElement(By.cssSelector("img[src=\"));
//        String src = img.getAttribute("src");
//        URL imgUrl = new URL(src);
//        BufferedImage saveImage = ImageIO.read(imgUrl);
//        ImageIO.write(saveImage, "png", new File("image.png"));
//    } catch (Exception ex) {
//        ex.printStackTrace();
//    }
//}).start();
