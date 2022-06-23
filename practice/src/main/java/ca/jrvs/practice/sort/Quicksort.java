package ca.jrvs.practice.sort;

public class Quicksort {

    /**
     * method to find the partition position.
     * It finds pivot element such that
     * elements < than pivot are on the left
     * elements > than pivot are on the right
     *
     * @param start where to start sort
     * @param end where to end sort
     * @return the partition position
     */
    private static int partition(int[] array, int start, int end) {
        // arbitrary choose the last element as pivot
        int pivot = array[end];
        int pivot_index = start;
        for (int i = start; i < end; i++) {
            // if i see a value that should be on the right of pivot_index
            if (array[i] <= pivot) {
                swap(array,i,pivot_index);
                pivot_index++;
            }
        }
        //swap pivot at pivot_index_result
        swap(array,pivot_index,end);
        return pivot_index;
    }

    private static void swap(int arr[], int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    /**
     * recursion call is made on pivot_index-1 and pivot_index+1
     * @param low where to start sort
     * @param high where to end sort
     */
    public static void sort(int array[], int low, int high) throws IndexOutOfBoundsException{
        if (low < 0 || high >= array.length) {throw new IndexOutOfBoundsException("incorrect parameters");}
        if (low < high) {
            // find pivot element such that elements < than pivot are on the left elements > than pivot are on the right
            int pi = partition(array, low, high);
            sort(array, low, pi-1);
            sort(array, pi + 1, high);
        }
    }

}
