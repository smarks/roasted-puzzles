package com.orgamisoftware.puzzles.client;

import com.orgamisoftware.puzzles.sorters.MergeSort;
import com.orgamisoftware.puzzles.sorters.SortFactory;
import com.orgamisoftware.puzzles.sorters.Sorter;
import com.orgamisoftware.puzzles.sorters.SorterType;

public class SortClient {


    public static void main(String[] args) {

        // let's play a game.
        Sorter sorter = SortFactory.getInstance();
        int[] results = sorter.sort(new int[10]);

        Sorter sorter1 = SortFactory.getInstance(SorterType.MERGE_SORT);

        sorter1 = new MergeSort();
    }

}
