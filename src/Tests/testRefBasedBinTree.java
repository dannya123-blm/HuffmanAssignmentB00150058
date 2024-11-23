package Tests;


import Huffman.RefBasedBinTreeHuffman;
import java.util.Scanner;
import java.io.IOException;


public class testRefBasedBinTree {
    public static void main(String[] args) {
        try {
            RefBasedBinTreeHuffman huffman = new RefBasedBinTreeHuffman();
            huffman.readFrequencyTable("resources/LetterCountAscending.txt");
            huffman.generateHuffmanTree();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a string to encode:");
            String input = scanner.nextLine();
            String encoded = huffman.encode(input);
            System.out.println("Encoded: " + encoded);

            System.out.println("Enter a Huffman code to decode:");
            String code = scanner.nextLine();
            String decoded = huffman.decode(code);
            System.out.println("Decoded: " + decoded);

            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading frequency table: " + e.getMessage());
        }
    }
}
