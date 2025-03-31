package ms;

import java.util.Scanner;

//merge sort

public class Main {
    public static void main(String[] args) {
        String[] A = getData();
        System.out.println("Length of A: " + A.length);
        ms(A);

        for(String s : A) {
            System.out.println(s);
        }
    }

    public static void ms(String[] a) {
        if (a==null || a.length <=1) return;
        split(a, 0, a.length-1);
    }

    public static void split(String[] a, int l, int r) {
        if (l<r) {
            int mid = l+(r-l)/2;
            split(a, l, mid);
            split(a, mid+1, r);
            merge(a, l, mid, r);
        }
    }

    public static void merge(String[] a, int l, int mid, int r) {
     
        int size1 = mid-l+1;
        int size2 = r-mid;

        String[] temp1 = new String[size1];
        String[] temp2 = new String[size2];

        for (int i = 0; i < size1; i++) {
            temp1[i] = a[l+i];
        }
        for (int j = 0; j < size2; j++) {
            temp2[j] = a[mid+1+j];
        }

        // merge the two smaller temp arrays together into a[]
        int i = 0;
        int j = 0;
        int k = l;

        while(i<size1 && j<size2) {
            if (temp1[i].compareTo(temp2[j]) <= 0) {
                a[k] = temp1[i];
                i++;
            } else {
                a[k] = temp2[j];
                j++;
            }
            k++;
        }

        // merge any remaining elements from either
        while(i < size1) {
            a[k] = temp1[i];
            i++;
            k++;
        }

        while(j<size2) {
            a[k] = temp2[j];
            j++;
            k++;
        }
    }

    public static String[] getData() {
        int i = 0;
        int maxsize = 1000;

        Scanner input = new Scanner(System.in);
        String[] data = new String[maxsize];

        // Determine proper size of the array based on file length
        int newSize = 0;

        while(input.hasNextLine() && i < maxsize) {
            String line = input.nextLine();

            if(line.isEmpty()) {
                break;
            }

            data[i] = line;
            i++;
            newSize++;
        }

        input.close();

        // Create a new array with the correct length
        String[] result = new String[newSize];
        for(int j = 0; j < newSize; j++) {
            result[j] = data[j];
        }

        return result;
    }
}
