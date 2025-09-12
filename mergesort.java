import java.util.Scanner;

public class mergesort {
    
    // Merge function
    void merge(int a[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = a[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = a[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    // Sort function
    void sort(int a[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            sort(a, l, m);
            sort(a, m + 1, r);

            merge(a, l, m, r);
        }
    }

    // Main method with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mergesort ob = new mergesort();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int a[] = new int[n];

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        ob.sort(a, 0, n - 1);

        System.out.println("Sorted array:");
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");

        sc.close();
    }
}
