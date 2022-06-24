package ca.jrvs.practice.sorting;

public class Mergesort {
    public static void sort(int[] inputArray) {
        int inputLength = inputArray.length;

        //if we have 1 element return
        if (inputLength < 2) {return;}

        //seperate the array into 2 halves
        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        //populate the 2 halves
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i - midIndex] = inputArray[i];
        }

        //redo all that until a return because i have 1 element in each array
        sort(leftHalf);
        sort(rightHalf);

        //merge the element in a sorted maner
        merge(inputArray, leftHalf, rightHalf);
    }

    private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            //if left element is the smaller number put it first
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            }
            else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        //left over left
        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;k++;
        }

        //left over right
        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;k++;
        }
    }
}