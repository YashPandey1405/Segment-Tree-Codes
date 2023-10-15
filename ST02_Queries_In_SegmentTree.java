//Time Complexity Of Queries In Segment Tree Is O(Logn).....

import java.util.*;

public class ST02_Queries_In_SegmentTree {
    static int Tree[];

    public static void Initialize(int n) {
        Tree = new int[4 * n];
    }

    public static int Build_Segment_Tree(int arr[], int i, int Start, int End) { // O(n)....
        if (Start == End) { // Base Case....
            Tree[i] = arr[Start];
            return arr[Start];
        }

        int mid = (Start + End) / 2;
        Build_Segment_Tree(arr, 2 * i + 1, Start, mid); // Recursive Call For Left SubTree......
        Build_Segment_Tree(arr, 2 * i + 2, mid + 1, End); // Recursive Call For Right SubTree......
        Tree[i] = Tree[2 * i + 1] + Tree[2 * i + 2]; // Sum Of Left-SubTree & Right-SubTree.....
        return Tree[i];
    }

    public static int GetSum(int arr[], int qi, int qj) { // O(Logn).....
        int n = arr.length;
        return GetSum_Util(0, 0, n - 1, qi, qj);
    }

    public static int GetSum_Util(int i, int si, int sj, int qi, int qj) {

        // Case 1 --> When Given Range Is Non-Inclusive For Segment Tree.....
        if (qj <= si || qi >= sj) { // Exclude Case.....
            return 0;
        }

        // Case 2 --> When Given Range Is Completely-Inclusive For Segment Tree.....
        else if (si >= qi && sj <= qj) { // Complete Overlap Case......
            return Tree[i];
        }

        // Case 2 --> When Given Range Is Partially-Inclusive For Segment Tree.....
        else { // Partial Overlap Case......
            int mid = (si + sj) / 2;
            int LeftSum = GetSum_Util(2 * i + 1, si, mid, qi, qj);
            int RightSum = GetSum_Util(2 * i + 2, mid + 1, sj, qi, qj);
            return (LeftSum + RightSum);
        }
    }

    public static void Print_Segment_Tree() { // Function To Print The Segment Tree......
        System.out.print("THE SEGMENT TREE IS ::: ");
        for (int i = 0; i < Tree.length; i++) {
            System.out.print(Tree[i] + " ");
        }
    }

    public static void Print_Array(int arr[]) { // Function To Print An Array......
        int n = arr.length;
        System.out.print("THE ARRAY TREE IS ::: { ");
        for (int i = 0; i < n; i++) {
            if (i == (n - 1)) {
                System.out.println(arr[i] + " }");
            } else {
                System.out.print(arr[i] + " , ");
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int n = arr.length;

        Initialize(n);
        Build_Segment_Tree(arr, 0, 0, n - 1);

        Print_Segment_Tree();
        System.out.println();

        Print_Array(arr);
        System.out.println();

        System.out.print("ENTER THE START INDEX FOR SUM CALCULATION ::: ");
        int Start = sc.nextInt();
        System.out.print("ENTER THE END INDEX FOR SUM CALCULATION ::: ");
        int End = sc.nextInt();
        System.out.println();

        int ans = GetSum(arr, Start, End);
        System.out.println("THE SUM FROM INDEX-[" + Start + "] TO INDEX-[" + End + "] IS ::: " + ans);
    }
}