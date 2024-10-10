import java.util.Arrays;

class Job {
    int id, deadline, profit;

    // Constructor
    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class job_sequencing_greedy {

    // Function to find the maximum profit sequence of jobs
    public static int[] jobSequencing(Job[] jobs, int n) {
        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline to know the size of the time slot array
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Create an array to keep track of which jobs are scheduled
        int[] result = new int[maxDeadline + 1]; // result[i] stores job ID at time slot i
        Arrays.fill(result, -1); // Initialize with -1 indicating empty slots

        int totalProfit = 0; // Variable to store total profit

        // Iterate over all jobs
        for (Job job : jobs) {
            // Find a free time slot for this job, starting from the job's deadline
            for (int j = job.deadline; j > 0; j--) {
                // If the slot is free, schedule the job
                if (result[j] == -1) {
                    result[j] = job.id; // Schedule the job at time slot j
                    totalProfit += job.profit; // Add profit
                    break; // Job is scheduled, no need to look further
                }
            }
        }

        // Print the job sequence and total profit
        System.out.println("Scheduled Jobs:");
        for (int i = 1; i <= maxDeadline; i++) {
            if (result[i] != -1) {
                System.out.print("Job " + result[i] + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);

        return result;
    }

    // Main function
    public static void main(String[] args) {
        // Array of jobs (id, deadline, profit)
        Job[] jobs = {
                new Job(1, 4, 20),
                new Job(2, 1, 10),
                new Job(3, 1, 40),
                new Job(4, 1, 30)
        };

        int n = jobs.length;
        jobSequencing(jobs, n); // Call job sequencing function
    }
}
