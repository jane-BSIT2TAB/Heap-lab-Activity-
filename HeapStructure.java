import java.util.Arrays;
import java.util.Scanner;

public class MinHeapPriorityQueue {

    private int[] heap;
    private int size;
    private int capacity;

    public MinHeapPriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity + 1]; // 1-based indexing
        this.size = 0;
    }

    private void heapifyUp(int index) {
        int parent = index / 2;
        while (parent > 0 && heap[parent] > heap[index]) {
            swap(parent, index);
            index = parent;
            parent = index / 2;
        }
    }

    private void heapifyDown(int index) {
        int left = 2 * index;
        int right = 2 * index + 1;
        int smallest = index;

        if (left <= size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right <= size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full!");
            return;
        }
        size++;
        heap[size] = value;
        heapifyUp(size);
    }

    public int extractMin() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return -1; // Or throw an exception
        }
        int min = heap[1];
        heap[1] = heap[size];
        size--;
        heapifyDown(1);
        return min;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity of the priority queue: ");
        int capacity = scanner.nextInt();
        MinHeapPriorityQueue pq = new MinHeapPriorityQueue(capacity);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Extract Min");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    int value = scanner.nextInt();
                    pq.insert(value);
                    System.out.println("Heap: " + Arrays.toString(Arrays.copyOfRange(pq.heap, 1, pq.size + 1)));
                    break;
                case 2:
                    int min = pq.extractMin();
                    if (min != -1) {
                        System.out.println("Extracted Min: " + min);
                        System.out.println("Heap: " + Arrays.toString(Arrays.copyOfRange(pq.heap, 1, pq.size + 1)));
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
