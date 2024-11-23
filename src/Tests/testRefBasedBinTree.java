package Tests;

import Huffman.RefBasedBinTreeHuffman; 
import java.util.Scanner;  
import java.io.IOException;  

public class testRefBasedBinTree {
    public static void main(String[] args) {
        try {
            
            RefBasedBinTreeHuffman huffman = new RefBasedBinTreeHuffman();
            
            // Read the frequency table from a file to initialize the Huffman tree
            huffman.readFrequencyTable("resources/LetterCountAscending.txt");
            
            // Generate the Huffman Tree based on the frequency table
            huffman.generateHuffmanTree();

            
            Scanner scanner = new Scanner(System.in);

            // Prompt user for a string to encode
            System.out.println("Enter a string to encode:");
            String input = scanner.nextLine();  // Read the input string

            // Encode the input string using the generated Huffman Tree
            String encoded = huffman.encode(input);
            System.out.println("Encoded: " + encoded);  

            // Prompt user for a Huffman code to decode
            System.out.println("Enter a Huffman code to decode:");
            String code = scanner.nextLine();  // Read the Huffman code

            // Decode the provided Huffman code back to the original string
            String decoded = huffman.decode(code);
            System.out.println("Decoded: " + decoded);  

            scanner.close();  

        } catch (IOException e) {
            // Handle any errors that occur while reading the frequency table
            System.out.println("Error reading frequency table: " + e.getMessage());
        }
    }
}
