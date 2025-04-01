package ms;

import java.util.*;

//mergesort

public class Main {
    public static void main(String[] args) {
        ArrayList<String> A = getData();
        A = ms(A);
        for(String s : A) System.out.println(s);
    }

    public static ArrayList<String> ms(ArrayList<String> a) {

        //as per spec, split input, run sort on itself and on the split (recursion), 
        //finally merge and return
        if(a.size()>1){
            ArrayList<String> l = split(a);
            a = ms(a);
            l = ms(l);
            a = merge(a,l);
        }
        
        return a;
    }

    public static ArrayList<String> split(ArrayList<String> a) {
        ArrayList<String> l = new ArrayList<String>();

        int mid = a.size()/2;
        int i=mid;
        while(i<a.size()) {
            l.add(l.size(), a.get(i));
            i++;
        }

        while(a.size()>mid) a.remove(a.size()-1);
        
        return l;
    }

    public static ArrayList<String> merge(ArrayList<String> L, ArrayList<String> R) {
        ArrayList<String> merged = new ArrayList<>();
        int i=0, j=0;

        //merges L and R
        while(i<L.size() && j<R.size()) {
            if(L.get(i).compareTo(R.get(i)) <=0) {
                merged.add(L.get(i));
                i++;
            } else {
                merged.add(R.get(j));
                j++;
            }
        }

        //add remaining if needed
        while(i<L.size()) {
            merged.add(L.get(i));
            i++;
        }

        while(j<R.size()) {
            merged.add(R.get(j));
            j++;
        }

        return merged;
    }

    //(from qs.java)
    public static ArrayList<String> getData() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();
        while(input.hasNextLine()) {
            String line = input.nextLine();
            if(line.isEmpty())break;
            data.add(line);
        }

        input.close();
        return data;
    }
}
