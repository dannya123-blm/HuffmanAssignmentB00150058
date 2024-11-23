package Huffman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner;

//using the greedy algorithm
public class RefBasedBinTreeHuffman {
    private TreeNode root;
    private List<TreeNode> freqTable = new ArrayList<>();

    // Read the frequency table from a file
    public void readFrequencyTable(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            char symbol = tokens[0].charAt(0);
            int freq = Integer.parseInt(tokens[1]);
            freqTable.add(new TreeNode(freq, symbol));
        }
        scanner.close();
        // Sort the frequency table by frequency
        freqTable.sort((a, b) -> a.getFreq() - b.getFreq());
    }

    // Generate the Huffman Tree
    public void generateHuffmanTree() {
        List<TreeNode> trees = new ArrayList<>(freqTable);
        while (trees.size() > 1) {
            // Remove the two nodes with the smallest frequencies
            TreeNode left = trees.remove(0);
            TreeNode right = trees.remove(0);

            // Create a new parent node with a combined frequency
            TreeNode parent = new TreeNode(left.getFreq() + right.getFreq(), '*', left, right);

            // Insert the parent node back into the sorted list
            int i = 0;
            while (i < trees.size() && trees.get(i).getFreq() < parent.getFreq()) {
                i++;
            }
            trees.add(i, parent);
        }
        root = trees.get(0);
    }

    // Encode the input string using Huffman coding
    public String encode(String input) {
        // Build the Huffman codes for each symbol
        List<CodePair> huffmanCodes = new ArrayList<>();
        buildCodes(root, "", huffmanCodes);

        // Encode the input string
        StringBuilder encoded = new StringBuilder();
        for (char ch : input.toCharArray()) {
            boolean found = false;
            for (CodePair pair : huffmanCodes) {
                if (pair.symbol == ch) {
                    encoded.append(pair.code);
                    found = true;
                    break;
                }
            }
            if (!found) {
                encoded.append("[" + ch + " not in table]");
            }
        }
        return encoded.toString();
    }

    // Helper function to build the Huffman codes recursively
    private void buildCodes(TreeNode node, String code, List<CodePair> huffmanCodes) {
        if (node.isLeaf()) {
            huffmanCodes.add(new CodePair(node.getSymbol(), code));
        } else {
            if (node.getLeft() != null) {
                buildCodes(node.getLeft(), code + "0", huffmanCodes);
            }
            if (node.getRight() != null) {
                buildCodes(node.getRight(), code + "1", huffmanCodes);
            }
        }
    }

    // Decode the encoded string using the Huffman tree
    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        TreeNode current = root;
        for (int i = 0; i < encoded.length(); i++) {
            current = encoded.charAt(i) == '0' ? current.getLeft() : current.getRight();
            if (current.isLeaf()) {
                decoded.append(current.getSymbol());
                current = root;
            }
        }
        return decoded.toString();
    }

    // Helper class for storing Huffman codes
    private static class CodePair {
        char symbol;
        String code;

        public CodePair(char symbol, String code) {
            this.symbol = symbol;
            this.code = code;
        }
    }
}
