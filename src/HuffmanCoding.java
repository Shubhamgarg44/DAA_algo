import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Node class for the Huffman tree
class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

// Comparator for min-heap priority queue
class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

public class HuffmanCoding {

    // Generate Huffman codes by traversing the tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If a leaf node is reached, save the character's code
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        // Traverse left and right with '0' and '1'
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    // Function to build the Huffman Tree and generate codes
    public static Map<Character, String> huffmanCoding(Map<Character, Integer> frequencies) {
        // Create a priority queue (min-heap)
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new HuffmanComparator());

        // Insert all characters and their frequencies into the min-heap
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            minHeap.add(node);
        }

        // Build the Huffman Tree
        while (minHeap.size() > 1) {
            // Extract two nodes with the lowest frequencies
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            // Create a new internal node with the sum of the frequencies
            HuffmanNode newNode = new HuffmanNode('-', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            // Add the new node back to the heap
            minHeap.add(newNode);
        }

        // Root of the Huffman tree
        HuffmanNode root = minHeap.poll();

        // Generate Huffman codes by traversing the tree
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    // Main function to demonstrate Huffman coding
    public static void main(String[] args) {
        // Example input string
        String input = "aabbbcccc";

        // Calculate character frequencies
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Perform Huffman coding
        Map<Character, String> huffmanCodes = huffmanCoding(frequencies);

        // Print the Huffman codes
        System.out.println("Huffman Codes: " + huffmanCodes);

        // Encode the input string
        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }

        System.out.println("Encoded String: " + encodedString.toString());
    }
}
