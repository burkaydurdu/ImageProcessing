package com.bdurdu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen extends JFrame implements ActionListener {
    private JMenuItem imageMenuItem;
    private JMenu open;
    private JLabel imageBox, newImageBox;
    private BufferedImage image;
    private Button grayFormatBtn;
    private JPanel controlPanel;
    private ImageControl imageControl;

    public Screen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 720));

        JMenuBar menuBar = onCreateMenu();
        this.setJMenuBar(menuBar);

        controlPanel = new JPanel();

        grayFormatBtn = new Button("Gray");
        grayFormatBtn.addActionListener(this);

        controlPanel.add(grayFormatBtn);

        imageBox = new JLabel();
        newImageBox = new JLabel();

        add(controlPanel, BorderLayout.EAST);
        add(imageBox, BorderLayout.WEST);
        add(newImageBox, BorderLayout.CENTER);

        imageControl = new ImageControl();

        setVisible(true);
    }

    private JMenuBar onCreateMenu() {
        JMenuBar menuBar = new JMenuBar();
        open = new JMenu("Open");

        imageMenuItem = new JMenuItem("Image");
        imageMenuItem.addActionListener(this);

        menuBar.add(open);
        open.add(imageMenuItem);

        return menuBar;
    }

    private void openFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                imageBox.setIcon(new ImageIcon(ImageIO.read(file)));
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void grayFormat() {
        imageControl.setImage(image);
        newImageBox.setIcon(new ImageIcon(imageControl.getGrayImage()));
    }

   /* private void getImageAnalysis(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                System.out.println("");
            }
        }
    }*/

   /* private BufferedImage createdImage(int width, int height, int imageArray[][]) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                image.setRGB(i, j, imageArray[i][j]);
        return image;
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(imageMenuItem)) {
            openFile();
        } else if(e.getSource().equals(grayFormatBtn)) {
            grayFormat();
        }
    }
}
