import java.util.*;

public class ST01_Creation {
    static int Tree[];

    public static void Initialize(int n) {
        Tree = new int[4 * n];
    }

    public static int Build_Segment_Tree(int arr[], int i, int Start, int End) { // O(n)....
        if (Start == End) {
            Tree[i] = arr[Start];
            return arr[Start];
        }

        int mid = (Start + End) / 2;
        Build_Segment_Tree(arr, 2 * i + 1, Start, mid); // Recursive Call For Left SubTree......
        Build_Segment_Tree(arr, 2 * i + 2, mid + 1, End); // Recursive Call For Right SubTree......
        Tree[i] = Tree[2 * i + 1] + Tree[2 * i + 2]; // Sum Of Left-SubTree & Right-SubTree.....
        return Tree[i];
    }

    public static void Print_Segment_Tree() {
        System.out.print("THE SEGMENT TREE IS ::: ");
        for (int i = 0; i < Tree.length; i++) {
            System.out.print(Tree[i] + " ");
        }
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int n = arr.length;
        Initialize(n);
        Build_Segment_Tree(arr, 0, 0, n - 1);
        Print_Segment_Tree();
    }
}