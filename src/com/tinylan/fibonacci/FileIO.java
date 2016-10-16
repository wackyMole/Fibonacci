package com.tinylan.fibonacci;

// Import necessary bits and pieces.
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Handles file IO operations
 *
 * @author Justin Reherman
 */
public class FileIO extends StartFib {

    private static final long serialVersionUID = 1L;

    /**
     * Writes output to "output.txt"
     *
     * @param output type string
     */
    public static void writeFile(String output) {
        // Try to dump the result into a text file called output.txt.
        try (BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))) {
            out.write(output);
        } catch (IOException e) {
            // If it fails we inform the user that we were unable to write
            // to a file.
            JOptionPane.showMessageDialog(
                null,
                "Unable to write to file! Check permissions and try again.",
                "File write error!",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
