/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoriacomp;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Extends JPanel class
public class Plot extends JPanel {
    static private List<Integer> coordinates = new ArrayList<>();
    private int marg = 60;

    public Plot() {
        // Read coordinates from the text file
        coordinates=extractBValues("Coordenadas.txt");
    }

    // Read coordinates from the text file
    public static List<Integer> extractBValues(String fileName) {
        List<Integer> result=new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line using "\t" as the delimiter
                String[] parts = line.split("\t");

                // Ensure there are at least two parts (a and b)
                if (parts.length >= 2) {
                    // Extract "b" (the second part) and add it to the list
                    String a=(parts[1].trim());
                    result.add(Integer.valueOf(a));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected void paintComponent(Graphics grf) {
        // Create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        // Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get width and height
        int width = getWidth();
        int height = getHeight();

        // Draw graph
        graph.draw(new Line2D.Double(marg, marg, marg, height - marg));
        graph.draw(new Line2D.Double(marg, height - marg, width - marg, height - marg));

        // Find value of x and scale to plot points
        double x = (double) (width - 2 * marg) / (coordinates.size() - 1);
        double scale = (double) (height - 2 * marg) / getMax();

        // Set color for points
        graph.setPaint(Color.RED);

        // Set points to the graph
        for (int i = 0; i < coordinates.size(); i++) {
            double x1 = marg + i * x;
            double y1 = height - marg - scale * coordinates.get(i);
            graph.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
        }
    }

    // Create getMax() method to find maximum value
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for (Integer coordinate : coordinates) {
            if (coordinate > max)
                max = coordinate;
        }
        return max;
    }

    // main() method start
    public static void main(String args[]) {
        // Create an instance of JFrame class
        JFrame frame = new JFrame();
        // Set size, layout, and location for frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Plot());
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}
