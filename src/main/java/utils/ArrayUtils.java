package utils;

import java.util.Random;

public class ArrayUtils {

    private static final Random RANDOM = new Random();

    public static int[] generateRandomArray() {
        return RANDOM.ints(10, 0, 1000).toArray();
    }

    public static int binarySearch(int[] arr, int key, int lEdge, int rEdge) {
        while (lEdge <= rEdge) {
            int pivot = (lEdge + rEdge) / 2;

            if (arr[pivot] == key) {
                return pivot;
            } else if (arr[pivot] < key) {
                lEdge = pivot + 1;
            } else {
                rEdge = pivot - 1;
            }
        }
        return -1;
    }

    public static void quickSort(int[] arr, int lEdge, int rEdge) {
        int lMarker = lEdge;
        int rMarker = rEdge;
        int pivot = arr[lEdge + (rEdge - lEdge) / 2];

        while (lMarker <= rMarker) {
            while (arr[lMarker] < pivot) lMarker++;
            while (arr[rMarker] > pivot) rMarker--;

            if (lMarker <= rMarker) {
                int temp = arr[lMarker];
                arr[lMarker] = arr[rMarker];
                arr[rMarker] = temp;
                lMarker++;
                rMarker--;
            }
        }
        if (lMarker < rEdge) quickSort(arr, lMarker, rEdge);
        if (rMarker > lEdge) quickSort(arr, lEdge, rMarker);
    }
}
