package Huffman;

public class TreeNode {
    private int freq;
    private char symbol;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(int freq, char symbol) {
        this.freq = freq;
        this.symbol = symbol;
        this.leftChild = null;
        this.rightChild = null;
    }

    public TreeNode(int freq, char symbol, TreeNode leftChild, TreeNode rightChild) {
        this.freq = freq;
        this.symbol = symbol;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getFreq() {
        return freq;
    }

    public char getSymbol() {
        return symbol;
    }

    public TreeNode getLeft() {
        return leftChild;
    }

    public TreeNode getRight() {
        return rightChild;
    }

    public void setLeft(TreeNode left) {
        this.leftChild = left;
    }

    public void setRight(TreeNode right) {
        this.rightChild = right;
    }

    public boolean isLeaf() {
        return (this.leftChild == null && this.rightChild == null);
    }
}
