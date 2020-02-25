package assignment_4;

import java.util.ArrayList;

/**
 * A class that has a static method of sorting an array of Template.SuggestWord Objects
 */
public class SortAlgorithm {

    /**
     * Main sorting method
     * @param array is an array of Template.SuggestWord Objects
     * @param low is the lowest index
     * @param high is the highest index
     */
    public static void mergeSort(ArrayList<Template.SuggestWord> array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    /**
     * Merge method, a part of the main sorting method
     * @param array is an array of Template.SuggestWord Objects
     * @param low is the lowest index
     * @param mid is the middle index
     * @param high is the highest index
     */
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
