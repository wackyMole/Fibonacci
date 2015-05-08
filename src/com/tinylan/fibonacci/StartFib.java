package com.tinylan.fibonacci;

// Import necessary bits and pieces.
import static com.tinylan.fibonacci.FibMath.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * handles the GUI and calls the classes needed to do the hard work
 *
 * @author Justin Reherman
 */
public class StartFib extends javax.swing.JFrame {

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox checkBox;
    public javax.swing.JTextField textField;
    private javax.swing.JButton go;

    private static final long serialVersionUID = 1L;

    /** Creates new form StartFib */
    public StartFib() {
        initComponents();
        getRootPane().setDefaultButton(go);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textField = new javax.swing.JTextField();
        checkBox = new javax.swing.JCheckBox();
        go = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fibonacci");
        setResizable(false);

        // I don't know what color to use. Let me konw if you can thing of a better one
        jPanel1.setBackground(new java.awt.Color(255, 102, 0));
        jPanel1.setToolTipText("");

        // Same thing with the fonts
        textField.setFont(new java.awt.Font("Tahoma", 0, 24));
        textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        checkBox.setFont(new java.awt.Font("Dialog", 1, 12));
        checkBox.setText("Format for Reddit");
        checkBox.setBackground(new java.awt.Color(255, 102, 0));

        // Again with the fonts
        go.setFont(new java.awt.Font("Tahoma", 1, 18));
        go.setText("Go!");
        go.setHorizontalAlignment(javax.swing.JButton.CENTER);
        go.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                .addGap(133))
            .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(149)
               .addComponent(checkBox)
               .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174)
                .addComponent(go, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
            );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32)
                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(checkBox)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(go, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * handles the logic that occurs when the button is pressed.
     * 
     * @param evt the event that triggered the action
     */
    private void goActionPerformed(java.awt.event.ActionEvent evt) {
        // Make sure that something was entered when go button is pressed.
        if ("".equals(textField.getText())) {
            //If it is blank, then we whine to the user.
            JOptionPane.showMessageDialog(
                null,
                "I can't do anything until you enter a number.",
                "Nothing was entered!",
                JOptionPane.ERROR_MESSAGE);
        } else {
            // Otherwise we can kick of processing.
            // First we'll declare this here so that we can use it later.
            DecimalFormat df = new DecimalFormat("#.##");
            //Then we'll start the timer
            long startTime = System.nanoTime();
            // First declare the "output" field.
            String output = "";
            // Then convert the input to a String.
            String text = textField.getText();
            // and finally convert the String to a long Integer.
            long number = Integer.parseInt(text);
            // We need to diverge paths here if the query is for the 94th term
            // or higher. This is because a "long" primitive can only handle the
            // terms up to the 93rd term. Beyond that we need to use a method
            // that employs the BigInteger field type.
            if (number > 92) {
                number = number - 92;
                output = (fibonacciBig(number) + "");
                // Now that things are done, we can stop the timer.
                long endTime = System.nanoTime();
                long time = endTime - startTime;
                double seconds = (double)time / 1000000000.0;
                // Lets also get the length of the output.
                long length = output.length();
                JOptionPane.showMessageDialog(null,
                    "Completed in " 
                    + df.format(seconds)
                    + " seconds with a result that was "
                    + length
                    + " digits long.",
                    "Completed!",
                    JOptionPane.INFORMATION_MESSAGE);
                // Send the output over to "OutProcessor" to handle
                // the output and return the value to place in the
                // text box.
                String out = OutProcessor.out(output, checkBox.isSelected());
                // Here we set the returned value into the text box.
                textField.setText(out);
            } else {
                // generate fibonacci number for querries less than 94.
                // Call the fibonacciLoop method from FibMath class.
                output = (fibonacciLoop(number) + "");
                // Now that things are done, we can stop the timer.
                long endTime = System.nanoTime();
                long time = endTime - startTime;
                double seconds = (double)time / 1000000000.0;
                // Lets also get the length of the output.
                long length = output.length();
                // and show the user this data.
                JOptionPane.showMessageDialog(null,
                    "Completed in " 
                    + df.format(seconds)
                    + " seconds with a result that was "
                    + length
                    + " digits long.",
                    "Completed!",
                    JOptionPane.INFORMATION_MESSAGE);
                // Again, we are sending the output to OutProcessor to do
                // it's work and return the value for the text box.
                String out = OutProcessor.out(output, checkBox.isSelected());
                // and then set the returned value into the text box.
                textField.setText(out);
            }
        }
    }

    /**
     * set the look and feel and display the form
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /**
         * Set the Nimbus look and feel
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartFib().setVisible(true);
            }
        });
    }
}