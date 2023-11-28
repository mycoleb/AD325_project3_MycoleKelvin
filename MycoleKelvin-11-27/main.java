import java.io.File;
public class Main {
    public static void main(String[] args) {
        // First, I check if a file path is provided as an argument.
        if (args.length != 1) {
            System.out.println("Usage: java Main <path_to_java_file>");
            return;
        }
        // I create a file object for the Java program that I need to parse.
        File javaProgram = new File(args[0]);
        // I check if the file actually exists and if it's readable.
        if (!javaProgram.exists() || !javaProgram.canRead()) {
            System.out.println("File does not exist or cannot be read: " + args[0]);
            return;
        }
        // Here, I create an instance of my Parser class.
        Parser parser = new Parser();
        // Using my parser, I parse the Java program file.
        parser.getIdentifiers(javaProgram);
        // Now, I display the contents of the BSTs.
        System.out.println("Reserved Words BST:");
        // I'm assuming here that I have a method in my Parser class to print the BST in order.
        parser.printReservedWordsBST();
        System.out.println("User-defined Identifiers BST:");
        // Similarly, I assume there's a method to print the identifiers BST.
        parser.printIdentifiersBST();
    }
}




