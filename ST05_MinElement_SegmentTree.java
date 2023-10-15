public class ST05_MinElement_SegmentTree {
    static int Tree[];

    public static void Initialize(int n) {
        Tree = new int[4 * n];
    }

    public static void Build_MinElement_Segment_Tree(int arr[], int i, int Start, int End) { // O(n)....
        if (Start == End) { // Base Case....
            Tree[i] = arr[Start];
            return;
        }

        int mid = (Start + End) / 2;
        Build_MinElement_Segment_Tree(arr, 2 * i + 1, Start, mid); // Recursive Call For Left SubTree......
        Build_MinElement_Segment_Tree(arr, 2 * i + 2, mid + 1, End); // Recursive Call For Right SubTree......
        Tree[i] = Math.min(Tree[2 * i + 1], Tree[2 * i + 2]); // Min Of Left-SubTree & Right-SubTree.....
        return;
    }

    public static int GetMin(int arr[], int qi, int qj) { // O(Logn).....
        int n = arr.length;
        return GetMin_Util(0, 0, n - 1, qi, qj);
    }

    public static int GetMin_Util(int i, int si, int sj, int qi, int qj) {

        // Case 1 --> When Given Range Is Non-Inclusive For Segment Tree.....
        if (qj < si || qi > sj) { // Exclude Case.....
            return Integer.MAX_VALUE;
        }

        // Case 2 --> When Given Range Is Completely-Inclusive For Segment Tree.....
        else if (si >= qi && sj <= qj) { // Complete Overlap Case......
            return Tree[i];
        }

        // Case 2 --> When Given Range Is Partially-Inclusive For Segment Tree.....
        else { // Partial Overlap Case......
            int mid = (si + sj) / 2;
            int LeftMin = GetMin_Util(2 * i + 1, si, mid, qi, qj);
            int RightMin = GetMin_Util(2 * i + 2, mid + 1, sj, qi, qj);
            return Math.min(LeftMin, RightMin);
        }
    }

    public static void Update_Util(int i, int si, int sj, int idx, int NewVal) {

        // Elemenet Doesn't Lie In The Current Range......
        if (idx > sj || idx < si) {
            return;
        }
        if (si == sj) {
            Tree[i] = NewVal;
        }
        if (si != sj) {
            Tree[i] = Math.min(Tree[i], NewVal);
            int mid = (si + sj) / 2;
            Update_Util(2 * i + 1, si, mid, idx, NewVal);
            Update_Util(2 * i + 2, mid + 1, sj, idx, NewVal);
        }
    }

    public static void Update(int arr[], int idx, int NewVal) { // O(Logn)......
        int n = arr.length;
        arr[idx] = NewVal;
        Update_Util(0, 0, n - 1, idx, NewVal);
    }

    public static void Print_Segment_Tree() { // Function To Print The Segment Tree......
        System.out.print("THE SEGMENT TREE IS ::: ");
        for (int i = 0; i < Tree.length; i++) {
            System.out.print(Tree[i] + " , ");
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
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };
        int n = arr.length;
        Initialize(n);
        Build_MinElement_Segment_Tree(arr, 0, 0, n - 1);

        Print_Segment_Tree();
        Print_Array(arr);

        int Start = 2, End = 5;
        int ans1 = GetMin(arr, Start, End);
        System.out.println("THE MIN VALUE FROM INDEX-[" + Start + "] TO INDEX-[" + End + "] IS ::: " + ans1);

        int idx = 2, NewVal = 20;
        Update(arr, idx, NewVal);

        Print_Segment_Tree();
        Print_Array(arr);

        int ans3 = GetMin(arr, Start, End);
        System.out.println("AFTER UPDATION , THE MIN VALUE FROM INDEX-[" + Start + "] TO INDEX-[" + End + "] IS ::: "
                + ans3);
    }
}
