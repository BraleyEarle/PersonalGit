/*
 * Created by MightyJoeYoung on 8/13/2017.
 */

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;  // can use to draw labels; function draw string
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;  // imports to make a graph GUI; may want a seperate class


public class graphing_class extends main_Class
{
    private JFrame my_frame = new JFrame("Pie Chart");
    my_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Error here. Not known why

    JComponent component_one = new JComponent() { // rewrite and select from options
    };


    // need function to print the pie chart with slices
    // each slice needs to be out of 100; so num in array / 100 slices
    // each slices must have a separate color; look into rand color functions if any
    // need to connect to the main class; id like the array to be passed into the specific
    // function and go from there; no user input
    // other option is using a third party API to work out the pie chart; look into that

}
