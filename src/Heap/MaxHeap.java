package Heap;

import java.util.*;

public class MaxHeap 
{
    static int[] b = new int[101];
    static int size = 0;

    public static void top() 
    {
        if (size >= 1) 
            System.out.println(b[1]);
        else
            System.out.println("Heap is empty");
    }

    public static void insert(int number) 
    {
        if (size >= b.length - 1) 
        {
            System.out.println("Heap is full, cannot insert more elements.");
            return;
        }

        size++;
        b[size] = number;

        int child = size;
        int parent = child / 2;

        // Max heap condition: parent must be greater than or equal to child
        while (parent >= 1 && b[parent] < b[child]) 
        {
            swap(parent, child);
            child = parent;
            parent = child / 2;
        }
    }

    public static void delete() 
    {
        if (size == 0) 
        {
            System.out.println("Heap is empty, no element to remove.");
            return;
        }

        b[1] = b[size]; // Move last element to the root
        size--;

        int parent = 1;
        while (true) 
        {
            int child1 = parent * 2;
            int child2 = child1 + 1;
            int maxChild = child1;

            if (child1 > size) 
                break;

            if (child2 <= size && b[child2] > b[child1]) 
                maxChild = child2;

            if (b[parent] < b[maxChild]) 
            {
                swap(parent, maxChild);
                parent = maxChild;
            } 
            else 
                break;
        }
    }

    public static void swap(int i, int j) 
    {
        int temp = b[i];
        b[i] = b[j];
        b[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            int option = sc.nextInt();
            if (option == 1)  // Insert Option - O(log(n))
                insert(sc.nextInt());
            else if (option == 2) // View Option - Top O(1)
                top();
            else if (option == 3) // Delete Option - O(log(n))
                delete();
            else
                break;
        }
        sc.close();
    }
}
