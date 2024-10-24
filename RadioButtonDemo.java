
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;

public class RadioButtonDemo extends JFrame {
    private JLabel imageLabel;

    public RadioButtonDemo() {
        setTitle("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create radio buttons
        JRadioButton birdButton = new JRadioButton("Bird");
        JRadioButton catButton = new JRadioButton("Cat");
        JRadioButton dogButton = new JRadioButton("Dog");
        JRadioButton rabbitButton = new JRadioButton("Rabbit");
        JRadioButton pigButton = new JRadioButton("Pig");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Add buttons to panel
        buttonPanel.add(birdButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(catButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(dogButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(rabbitButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(pigButton);

        // Create and setup image label
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 300));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add action listener for radio buttons
        ActionListener listener = e -> {
            String petType = e.getActionCommand().toLowerCase();
            updateImage(petType);
            JOptionPane.showMessageDialog(this,
                    "You selected a " + petType + "!",
                    "Pet Selection",
                    JOptionPane.INFORMATION_MESSAGE);
        };

        // Assign listener to buttons
        birdButton.addActionListener(listener);
        catButton.addActionListener(listener);
        dogButton.addActionListener(listener);
        rabbitButton.addActionListener(listener);
        pigButton.addActionListener(listener);

        // Add components to main panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Center window on screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateImage(String petType) {
        try {
            // Load image
            String imagePath = "/images/" + petType + ".jpg";
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Image not found");
            }

            // Scale image
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));

        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found for " + petType);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RadioButtonDemo());
    }
}