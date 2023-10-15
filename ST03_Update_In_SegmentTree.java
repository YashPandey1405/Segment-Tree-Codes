//Time Complexity Of Update In Segment Tree Is O(Logn).....

import java.util.*;

public class ST03_Update_In_SegmentTree {
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

    public static void Update_Util(int i, int si, int sj, int idx, int Diff) {

        // Elemenet Doesn't Lie In The Current Range......
        if (idx > sj || idx < si) {
            return;
        }

        Tree[i] += Diff;
        if (si != sj) {
            int mid = (si + sj) / 2;
            Update_Util(2 * i + 1, si, mid, idx, Diff);
            Update_Util(2 * i + 2, mid + 1, sj, idx, Diff);
        }
    }

    public static void Update(int arr[], int idx, int NewVal) { // O(Logn)......
        int n = arr.length;
        int Diff = NewVal - arr[idx];
        arr[idx] = NewVal;
        Update_Util(0, 0, n - 1, idx, Diff);
    }

    public static void Print_Segment_Tree() { // Function To Print The Segment Tree......
        System.out.print("THE SEGMENT TREE IS ::: ");
        for (int i = 0; i < Tree.length; i++) {
            System.out.print(Tree[i] + " ");
        }
        System.out.println();
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
        Print_Array(arr);

        System.out.print("ENTER THE INDEX YOU WANT TO UPDATE ::: ");
        int idx = sc.nextInt();
        System.out.print("ENTER THE NEW VALUE AT INDEX-[" + idx + "] FOR UPDATE ::: ");
        int NewVal = sc.nextInt();

        int Start = 2, End = 5;
        int ans1 = GetSum(arr, Start, End);
        System.out
                .println("BEFORE UPDATION , THE SUM FROM INDEX-[" + Start + "] TO INDEX-[" + End + "] IS ::: " + ans1);

        Update(arr, idx, NewVal);

        int ans2 = GetSum(arr, Start, End);
        System.out
                .println("AFTER UPDATION , THE SUM FROM INDEX-[" + Start + "] TO INDEX-[" + End + "] IS ::: " + ans2);
    }
}