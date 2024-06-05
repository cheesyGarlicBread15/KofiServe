package com.example.kofiserve4.SearchAlgorithms;

import com.example.kofiserve4.Interfaces.ComparatorAndValidator;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    /**
     * Sort objects according to the Primary comparator
     * Objects with same precedence are not yet further sorted
     * @param arr ArrayList to sort
     * @param comparator Primary comparator to base first sort
     * @return Sorted Arraylist
     * @param <T> Data type or object stored inside the ArrayList
     * @param <U> Innerclass Comparator, must match with the data type of the OuterClass (which is T)
     */
    public static <T, U extends ComparatorAndValidator<T>>  int sortObjects(ArrayList<T> arr, U comparator){
        int until = arr.size() - 1;
        while (comparator.validate(arr.get(0)) == null && until > 0) {
            T t = arr.remove(0);
            arr.add(t);
            until -= 1;
        }
        int i = 1;
        int j;
        while (i <= until) {
            while (comparator.validate(arr.get(i)) == null && i <= until) {
                T t = arr.remove(i);
                arr.add(t);
                until -= 1;
            }
            if (i > until) break;
            T current = arr.get(i);
            j = i - 1;
            while (j >= 0 && comparator.compare(arr.get(j), current) > 0) {
                arr.set(j + 1, arr.get(j));
                j -= 1;
            }
            arr.set(j + 1, current);
            i += 1;
        }
        return until;
    }

    /**
     * Nested ArrayList, each ArrayList represents a sub group (same precedence on sort via primary
     * comparator, but may be different on secondary comparator)
     * @param arr ArrayList that have gone through Primary sort, and must be further sorted because
     *            of sub groups
     * @param comparator Primary comparator
     * @param until until at this index, exists a subgroup,
     * @return Nested ArrayList wherein each consists of 2 integers (first and last index)
     * @param <T> Data type or object stored inside the ArrayList
     * @param <U> Innerclass Comparator (Primary), must match with the data type of the OuterClass (which is T)
     */
    public static <T, U extends ComparatorAndValidator<T>> ArrayList<ArrayList<Integer>> getSubGroup(ArrayList<T> arr, U comparator, int until){
        ArrayList<ArrayList<Integer>> subGroupIndexes = new ArrayList<>();
        for (int i = 0; i < until; i++) {
            if (comparator.compare(arr.get(i), arr.get(i + 1)) == 0) {
                int j = i + 1;
                int k = j + 1;
                int skip = 1;
                while (j != until) {
                    if (comparator.compare(arr.get(j), arr.get(k)) == 0) {
                        j += 1;
                        k += 1;
                        skip += 1;
                    } else break;
                }
                subGroupIndexes.add(new ArrayList<>(Arrays.asList(i, j)));
                i += skip;
            }
        }
        if (arr.size() - until >=2) {
            subGroupIndexes.add(new ArrayList<>(Arrays.asList(until + 1, arr.size() - 1)));
        }
        return subGroupIndexes;
    }

    /**
     * Sort the subgroup
     * @param arr ArrayList to be further sorted
     * @param indexes Nested ArrayList, each representing a subgroup and their range
     * @param subComparator Secondary comparator
     * @param <T> Data type or object stored inside the ArrayList
     * @param <U> Innerclass Comparator (Secondary), must match with the data type of the OuterClass (which is T)
     */
    public static <T, U extends ComparatorAndValidator<T>> void sortSubGroup(ArrayList<T> arr, ArrayList<ArrayList<Integer>> indexes, U subComparator){
        if (indexes.isEmpty()) {
            return;
        }
        for (int i = 0; i < indexes.size(); i++) {
            int start = indexes.get(i).get(0);
            int end = indexes.get(i).get(1);
            for (int j = start + 1; j <= end; j++) {
                T current = arr.get(j);
                int k = j - 1;
                while (k >= start && subComparator.compare(arr.get(k), current) > 0) {
                    arr.set(k + 1, arr.get(k));
                    k -= 1;
                }
                arr.set(k + 1, current);
            }
        }
    }

    /**
     * Method to call when sorting objects
     * @param arr ArrayList to sort
     * @param comparator Primary comparator
     * @param subComparator Secondary comparator
     * @param <T> Data type or object stored inside the ArrayList
     * @param <U> Innerclass Comparator (Primary), must match with the data type of the OuterClass (which is T)
     * @param <K> Innerclass Comparator (Secondary), must match with the data type of the OuterClass (which is T)
     */
    public static <T, U extends ComparatorAndValidator<T>, K extends ComparatorAndValidator<T>> void basicSort(ArrayList<T> arr, U comparator, K subComparator){
        sortSubGroup(arr, getSubGroup(arr, comparator, sortObjects(arr, comparator)), subComparator);
    }
}
