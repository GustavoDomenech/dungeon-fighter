```java
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.io.File;
import java.io.IOException;
```

```java
public class CustomFontExample {
    public static void main(String[] args) {
        // Load the font
        Font customFont = null;
        try {
            File fontFile = new File("path/to/your/font.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(24f); // 24f is the font size
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        // Create a JFrame and a JLabel
        JFrame frame = new JFrame("Custom Font Example");
        JLabel label = new JLabel("Hello, Custom Font!");

        // Set the custom font to the JLabel
        if (customFont != null) {
            label.setFont(customFont.deriveFont(24f)); // Set the font size
        }

        // Configure JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
```
