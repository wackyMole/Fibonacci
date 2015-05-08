package com.tinylan.fibonacci;

//Importing necessary parts
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * handles processing of the result
 *
 * @author Justin Reherman
 */
public class OutProcessor extends StartFib {

    private static final long serialVersionUID = 1L;

    /**
     * Determines if the output will fit in the GUI or needs to written to file
     * 
     * @param output type string
     * @return       type string
     */
    public static String out(String output, Boolean reddit) {
        // First we need to get the length of the output so that we can determine
        // if the result will fit in the box
        long length = output.length();
        if (reddit) {
            length = length + 2;
        }
        // If the length is short enough to fit nicely in the box we return the
        // output as is
        if(length <= 15) {
            // If the checkbox was checked, we'll format it for reddit
            if (reddit) {
                return "`" + output + "`";
            }
            return output;
        // Otherwise, we write the output to a file and return an empty string
        } else {
            // Add a space after every tenth character
            output = output.replaceAll("(.{10})", "$1 ");
            // If the checkbox was checked, we'll format it for reddit
            if (reddit) {
                output = "`" + output + "`";
            }
            // Here we call fileIO to write the output to file
            FileIO.writeFile(output);
            // Next, we determine if a desktop is supported.
            if(Desktop.isDesktopSupported()) {
                // If it is, we determine if "open" action is supported.
                if(Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    // If it is, we inform the user that we wrote the output
                    // to output.txt and ask them if they want to open it or
                    // move on.
                    Object[] options = {"Open output.txt", "Continue"};
                    int n = JOptionPane.showOptionDialog(
                        null,
                        "The output was too long to display in the box. See output.txt for the results.",
                        "Unable to display output",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                    // If they elected to open the file, then we attempt to open
                    // it in their default text editor.
                    if(n == 0) {
                        try {
                            Desktop.getDesktop().open(new File("output.txt"));
                        } catch (IOException ioe) {
                            // If there is a problem opening it, we inform the user.
                            JOptionPane.showMessageDialog(
                                null,
                                "I wasn't able to open output.txt for you.",
                                "Unable to open file",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // If however, desktop isn't supported we just let the user
                    // know that we wrote the result to output.txt.
                    JOptionPane.showMessageDialog(null,
                        "The result wouldn't fit in the box. Please check output.txt",
                        "Result was written to file.",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            return "";
        }
    } 
}