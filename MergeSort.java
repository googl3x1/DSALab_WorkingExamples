class MergeSort {
    int[] a = {22, 58, 47, 96, 45, 32, 12};
    int[] b = new int[a.length];

    void merging(int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                b[k] = a[i];
                i++;
            } else {
                b[k] = a[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            b[k] = a[i];
            i++;
            k++;
        }

        while (j <= high) {
            b[k] = a[j];
            j++;
            k++;
        }

        for (i = low; i <= high; i++) {
            a[i] = b[i];
        }
    }

    void sort(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(low, mid);
            sort(mid + 1, high);
            merging(low, mid, high);
        }
    }

    void printArray() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        System.out.print("Before sorting: ");
        ms.printArray();
        ms.sort(0, ms.a.length - 1);
        System.out.print("After sorting: ");
        ms.printArray();
    }
}
