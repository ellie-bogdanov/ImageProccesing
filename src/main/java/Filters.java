
import javax.imageio.ImageIO;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Filters {
    private BufferedImage image;
    private File out;

    public Filters(BufferedImage image) {
        this.image = image;
        this.out = new File("C:\\Users\\guybo\\vscode_projects\\ImageProcessing-master\\src\\main\\resources\\catBoy.png");
        try {
            ImageIO.write(this.image , "png" , out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage grayScale() {
        BufferedImage tempImg = null;
        try {
            tempImg = ImageIO.read(this.out);
            for (int x = 0; x < tempImg.getWidth(); x++) {
                for (int y = 0; y < tempImg.getHeight(); y++) {
                    int pixel1 = tempImg.getRGB(x, y);
                    Color color1 = new Color(pixel1);
                    int red = color1.getRed();
                    int green = color1.getGreen();
                    int blue = color1.getBlue();
                    int average = (red + green + blue) / 3;
                    Color color = new Color(average, average, average);
                    tempImg.setRGB(x, y, color.getRGB());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImg;
    }

    public BufferedImage colorShiftRight() {
        BufferedImage tempImg = null;
        try {
            tempImg = ImageIO.read(this.out);
            for (int x = 0; x < tempImg.getWidth(); x++) {
                for (int y = 0; y < tempImg.getHeight(); y++) {
                    int pixel1 = tempImg.getRGB(x, y);
                    Color color1 = new Color(pixel1);
                    int red = color1.getRed();
                    int green = color1.getGreen();
                    int blue = color1.getBlue();
                    int tempRed = red;
                    red = green;
                    green = blue;
                    blue = tempRed;
                    Color color = new Color(red, green, blue);
                    tempImg.setRGB(x, y, color.getRGB());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImg;
    }

    public BufferedImage colorShiftLeft() {
        BufferedImage tempImg = null;
        try {
            tempImg = ImageIO.read(this.out);
            for (int x = 0; x < tempImg.getWidth(); x++) {
                for (int y = 0; y < tempImg.getHeight(); y++) {
                    int pixel1 = tempImg.getRGB(x, y);
                    Color color1 = new Color(pixel1);
                    int red = color1.getRed();
                    int green = color1.getGreen();
                    int blue = color1.getBlue();
                    int tempRed = red;
                    red = blue;
                    blue = green;
                    green = tempRed;
                    Color color = new Color(red, green, blue);
                    tempImg.setRGB(x, y, color.getRGB());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImg;
    }
    
    public BufferedImage mirror() {
        BufferedImage tempMirrored = null;
        try {
            BufferedImage tempOriginal = ImageIO.read(this.out);
            tempMirrored = ImageIO.read(this.out);
            for(int x = tempOriginal.getWidth() - 1; x >= 0; x--) {
                for(int y = 0; y < tempOriginal.getHeight(); y++) {
                    tempMirrored.setRGB(tempOriginal.getWidth() - x - 1, y, tempOriginal.getRGB(x, y));
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return tempMirrored;

    }


 
    public BufferedImage brighter(int brightLevel, int brightCeiling) {
        BufferedImage tempImg = null;
        try {
            tempImg = ImageIO.read(this.out);
            for (int x = 0; x < tempImg.getWidth(); x++) {
                for (int y = 0; y < tempImg.getHeight(); y++) {
                    int pixel1 = tempImg.getRGB(x, y);
                    Color color1 = new Color(pixel1);
                    int red = color1.getRed();
                    int green = color1.getGreen();
                    int blue = color1.getBlue();
                    if(red + brightLevel > brightCeiling) {
                        red = brightCeiling;
                    }
                    else{
                        red += brightLevel;
                    }

                    if(green + brightLevel > brightCeiling) {
                        green = brightCeiling;
                    }
                    else{
                        green += brightLevel;
                    }

                    if(blue + brightLevel > brightCeiling) {
                        blue = brightCeiling;
                    }
                    else{
                        blue += brightLevel;
                    }


                    Color color = new Color(red, green, blue);
                    tempImg.setRGB(x, y, color.getRGB());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImg;
    }

    public BufferedImage negative() {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(this.out);
            for(int x = 0; x < temp.getWidth(); x++) {
                for(int y = 0; y < temp.getHeight(); y++) {
                    Color currentColor = new Color(temp.getRGB(x, y));
                    int red = currentColor.getRed();
                    int green = currentColor.getGreen();
                    int blue = currentColor.getBlue();
                    Color newColor = new Color(255 - red, 255 - green, 255 - blue);
                    temp.setRGB(x, y, newColor.getRGB());

                    
                }
            }
        } catch (Exception e) {
            
        }

        return temp;
    }


}

