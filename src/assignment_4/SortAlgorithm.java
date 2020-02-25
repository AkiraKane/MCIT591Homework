package assignment_4;

import java.util.ArrayList;

public class SortAlgorithm {

    public static void mergeSort(ArrayList<Template.SuggestWord> array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    public static void merge(ArrayList<Template.SuggestWord> array, int low, int mid, int high) {
        ArrayList<Template.SuggestWord> lPart = new ArrayList<>(), rPart = new ArrayList<>();
        for (int i = low; i <= mid; i++) {
            lPart.add(array.get(i));
        }
        for (int j = mid + 1; j <= high; j++) {
            rPart.add(array.get(j));
        }
        int i = 0, j = 0, lSize = lPart.size(), rSize = rPart.size();
        for (int k = low; k <= high; k++) {
            if ((i < lSize) && (j < rSize)) {
                if (lPart.get(i).stats >= rPart.get(j).stats) {
                    array.set(k, lPart.get(i));
                    i++;
                }
                else {
                    array.set(k, rPart.get(j));
                    j++;
                }
            }
            else if (i < lSize) {
                array.set(k, lPart.get(i));
                i++;
            }
            else {
                array.set(k, rPart.get(j));
                j++;
            }
        }
    }
}
