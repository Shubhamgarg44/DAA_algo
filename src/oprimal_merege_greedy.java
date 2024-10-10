import java.util.PriorityQueue;
public class oprimal_merege_greedy {
    public static int minCost(int[] fileSizes) {
        // Create a min-heap (priority queue) to store the file sizes
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Insert all file sizes into the min-heap
        for (int size : fileSizes) {
            minHeap.add(size);
        }

        int totalCost = 0;

        // Keep merging the two smallest files until we have one file left
        while (minHeap.size() > 1) {
            // Extract the two smallest file sizes
            int smallest = minHeap.poll();
            int secondSmallest = minHeap.poll();

            // Calculate the cost of merging these two files
            int mergeCost = smallest + secondSmallest;
            totalCost += mergeCost;

            // Insert the merged file size back into the min-heap
            minHeap.add(mergeCost);
        }

        // The total cost is the sum of all merge costs
        return totalCost;
    }

    // Main function to test the optimal merge pattern
    public static void main(String[] args) {
        // Example file sizes
        int[] fileSizes = {4, 3, 2, 6};

        // Calculate the minimum merge cost
        int result = minCost(fileSizes);

        // Print the result
        System.out.println("The minimum cost to merge all files is: " + result);
    }
}
