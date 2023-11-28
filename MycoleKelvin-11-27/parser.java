// I need Set and HashSet to manage a collection of unique elements, particularly for reserved words.
import java.util.Set;
//import java.lang.*; 
import java.util.HashSet;
// I use File to handle file operations, like reading the reserved words and Java program files.
import java.io.File;
// FileNotFoundException is necessary for handling cases where a file might not be found.
import java.io.FileNotFoundException;
// Scanner is used for reading text from the input files.
import java.util.Scanner;
// Pattern is used for regex matching to validate if a token is a valid identifier.
import java.util.regex.Pattern;
import BinarySearchTree;
import TreeIteratorInterface;
import TreePackage;

import EmptyTreeException;
import TreeIteratorInterface.java;
import EmptyTreeException;

import BinaryTree;
import BinaryNode;
import TreeInterface;
import BinaryTreeInterface;
import SearchTreeInterface;





public class Parser {

    // I declare two binary search trees: one for identifiers and another for reserved words.
    private BinarySearchTree<String> identifiersBST;
    private BinarySearchTree<String> reservedBST;
    // I use a set to temporarily store reserved words before adding them to the BST.
    private Set<String> reservedWords;

    // In my constructor, I initialize both BSTs and load reserved words from a file.
    public Parser() throws FileNotFoundException {
        identifiersBST = new BinarySearchTree<>();
        reservedBST = new BinarySearchTree<>();
        initializeReservedWords(new File("reservedWords.txt"));
    }

    // This method reads reserved words from a file and adds them to my set and BST.
    private void initializeReservedWords(File file) throws FileNotFoundException {
        reservedWords = new HashSet<>();
        // I use a try-with-resources statement to ensure the scanner is closed after use.
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                // I read each word and add it to my set of reserved words.
                String word = scanner.next();
                reservedWords.add(word);
            }
        }
        // After loading the words into the set, I add them to the reserved BST.
        setBalancedBST(reservedWords);
    }

    // In this method, I add each word from the set to the reserved BST.
    private void setBalancedBST(Set<String> words) {
        for (String word : words) {
            // I insert each word into the reserved BST.
            reservedBST.insert(word);
        }
    }

    // This method processes a Java file to find and add identifiers to my identifiers BST.
    public void getIdentifiers(File classFile) throws FileNotFoundException {
        // I use a scanner to read through the file.
        try (Scanner scanner = new Scanner(classFile)) {
            while (scanner.hasNext()) {
                // I read each token from the file.
                String token = scanner.next();
                // If the token is not a reserved word and not already in the identifiers BST, I add it.
                if (!isReservedWord(token) && !identifiersBST.contains(token) && isIdentifier(token)) {
                    identifiersBST.insert(token);
                }
            }
        }
    }

    // I use this method to check if a token is a reserved word.
    private boolean isReservedWord(String token) {
        // I check if the token is in the reserved BST.
        return reservedBST.contains(token);
    }

    // This method checks if a string matches the Java identifier regex pattern.
    private boolean isIdentifier(String token) {
        // I use a regex pattern to validate the token.
        return Pattern.matches("[a-zA-Z\\d_$][a-zA-Z\\d_$]*", token);// My regex code makes sure the word starts with a letter, number, or uderscore. Before it made sure the code did not start with a number but now it can start with the aforementioned types and then be followed by zero or more letters, numbers, or underscores. 
    }

    // Other methods and logic for my Parser class...
    // ...
}
