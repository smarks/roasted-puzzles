package com.orgamisoftware.puzzles.sorters;

import static com.orgamisoftware.puzzles.sorters.SorterType.MERGE_SORT;
import static com.orgamisoftware.puzzles.sorters.SorterType.QUICK_SORT;

public class SortFactory {

    private SortFactory(){}

    public static Sorter getInstance() {
        // read from configuration file type Sort
        return new MergeSort();
    }

    public static Sorter getInstance(SorterType type) {

        Sorter returnValue;

        if (type == MERGE_SORT) {
            returnValue = new MergeSort();
        } else if (type == QUICK_SORT) {
            returnValue = new QuickSort();
        } else {
            throw new IllegalArgumentException("Unknown SorterType specified");
        }

        return returnValue;
    }

}
