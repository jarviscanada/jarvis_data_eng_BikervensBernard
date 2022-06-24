package ca.jrvs.practice.search;

import java.util.Optional;

public class BinarySearch {
    /**
     * find the the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @param l index of where to start search
     * @param r index of where to end search
     * @return target index or Optional.empty() if not ound
     */
    public <E> Optional<Integer> binarySearchRecursion(E arr[], E l, E r, E target) {
        if (arr.length == 0) {return Optional.empty();}
        if ((int)r >= (int)l) {
            int mid = (int)l + ((int)r - (int)l) / 2;

            if (mid < arr.length) {
                // If the element is present at the
                // middle itself
                if (arr[mid] == target)
                    return Optional.of(mid);

                // If element is smaller than mid, then
                // it can only be present in left subarray
                if ((int)arr[mid] > (int)target)
                    return binarySearchRecursion(arr, l, mid - 1, target);

                // Else the element can only be present
                // in right subarray
                return binarySearchRecursion(arr, mid + 1, r, target);
            } else {return Optional.empty();}
        }

        // We reach here when element is not present
        // in array
        return Optional.empty();
    }

    /**
     * find the target index in a sorted array
     *
     * @param arr input arr (is sorted)
     * @param target value to be searched
     * @return target index or Optional.empty() if not found
     */
    public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {
        if (arr.length == 0) {return Optional.empty();}
        int l = 0, r = arr.length - 1;

        // Checking element in whole array
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == target)
                return Optional.of(m);

            // If x greater, ignore left half
            if ((int)arr[m] < (int)target)
                l = m + 1;

                // If x is smaller,
                // element is on left side
                // so ignore right half
            else
                r = m - 1;
        }
        return Optional.empty();
    }
}
