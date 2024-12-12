package view;

import controller.Controller;
import model.Topping;
import model.Cake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomCakeFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private Controller controller;

    private JTextField cakeNameField;
    private JTextField piecesField;
    private JComboBox<Topping> toppingComboBox;
    private DefaultComboBoxModel<Topping> toppingComboBoxModel;
    private JLabel selectedToppingsLabel;
    private JButton saveButton;
    private List<Topping> selectedToppings;
    private JScrollPane scrollableToppingsPanel;

    public CustomCakeFrame(Controller controller) {
        mainPanel = new JPanel(new GridLayout(1, 1));
        setSize(600, 400);
        this.setContentPane(mainPanel);
        setupPanel();
        this.setTitle("Create your own cake!");
        this.setVisible(true);
        this.setResizable(false);
        this.controller = controller;
        selectedToppings = new ArrayList<>();
    }

    public void setupPanel() {
        JLabel nameLabel = new JLabel("Cake name:");
        cakeNameField = new JTextField(15);

        JLabel piecesLabel = new JLabel("Number of pieces (4, 6 or 12):");
        piecesField = new JTextField(10);

        JLabel toppingLabel = new JLabel("Toppings(Select the item again to remove it)");
        selectedToppingsLabel = new JLabel("Selected toppings: None");

        scrollableToppingsPanel = new JScrollPane(selectedToppingsLabel);
        scrollableToppingsPanel.setPreferredSize(new Dimension(200, 100));

        toppingComboBoxModel = new DefaultComboBoxModel<>();
        for (Topping topping : Topping.values()) {
            toppingComboBoxModel.addElement(topping);
        }
        toppingComboBox = new JComboBox<>(toppingComboBoxModel);
        toppingComboBox.addActionListener(e -> toggleToppingSelection((Topping) toppingComboBox.getSelectedItem()));

        saveButton = new JButton("Save Cake");
        saveButton.addActionListener(this);

        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.add(nameLabel);
        mainPanel.add(cakeNameField);
        mainPanel.add(piecesLabel);
        mainPanel.add(piecesField);
        mainPanel.add(toppingLabel);
        mainPanel.add(toppingComboBox);
        mainPanel.add(scrollableToppingsPanel);
        mainPanel.add(saveButton);
    }

    public void toggleToppingSelection(Topping selectedTopping) {
        if (selectedToppings.contains(selectedTopping)) {
            selectedToppings.remove(selectedTopping);
        } else {
            selectedToppings.add(selectedTopping);
        }

        if (selectedToppings.isEmpty()) {
            selectedToppingsLabel.setText("<html>Selected toppings: None</html>");
        } else {
            String toppingsText = "<html>Selected toppings:<br>";
            for (Topping topping : selectedToppings) {
                toppingsText += topping + "<br>";
            }
            toppingsText += "</html>";
            selectedToppingsLabel.setText(toppingsText);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String cakeName = cakeNameField.getText();

            if (cakeName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid cake name.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String piecesText = piecesField.getText();
            int pieces = 0;
            Scanner scanner = new Scanner(piecesText);
            if (!scanner.hasNextInt() || (pieces = scanner.nextInt()) <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of pieces.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (pieces != 4 && pieces != 6 && pieces != 12) {
                JOptionPane.showMessageDialog(this, "Please choose a valid number of pieces: 4, 6, or 12.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedToppings.size() < 2) {
                JOptionPane.showMessageDialog(this, "Please select at least 2 toppings.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cake newCake = new Cake(cakeName, pieces, selectedToppings);
            controller.addNewCakeToMenu(newCake);

            this.dispose();
        }
    }
}