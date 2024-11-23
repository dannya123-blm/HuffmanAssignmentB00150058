package Huffman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HuffmanGUI extends JFrame {
    private RefBasedBinTreeHuffman huffman;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton encodeButton;
    private JButton decodeButton;

    public HuffmanGUI() {
        super("Huffman Coding GUI");
        try {
            huffman = new RefBasedBinTreeHuffman();
            huffman.readFrequencyTable("resources/LetterCountAscending.txt"); 
            huffman.generateHuffmanTree();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load frequency table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputTextArea = new JTextArea(5, 20);
        inputTextArea.setBorder(BorderFactory.createTitledBorder("Input Message/Bitcode"));
        JScrollPane inputScroll = new JScrollPane(inputTextArea);
        add(inputScroll, BorderLayout.NORTH);

        outputTextArea = new JTextArea(5, 20);
        outputTextArea.setEditable(false);
        outputTextArea.setBorder(BorderFactory.createTitledBorder("Output Message/Bitcode"));
        JScrollPane outputScroll = new JScrollPane(outputTextArea);
        add(outputScroll, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        encodeButton = new JButton("Encode");
        encodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String encoded = huffman.encode(input);
                outputTextArea.setText(encoded);
            }
        });

        decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String decoded = huffman.decode(input);
                outputTextArea.setText(decoded);
            }
        });

        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HuffmanGUI();
    }
}
