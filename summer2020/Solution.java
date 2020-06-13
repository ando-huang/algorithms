package summer2020;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Result {

    //grade school mult for small arrays and base case
    private static List<Long> gradeSchool(List<Long> a, List<Long> b){
        List<Long> result = new ArrayList<Long>();
        Long[] aArr = new Long[a.size()];
        Long[] bArr = new Long[b.size()];
        for(int i = 0; i< a.size(); i++){
            aArr[i] = a.get(i);
            bArr[i] = b.get(i);
        }
        Long[] prod = new Long[a.size()*2];
        for(int i = 0; i<(2*a.size()); i++){
            prod[i] = Long.valueOf(0);
        }
        for(int i = 0; i < a.size(); i++){
            for(int j = 0; j<b.size();j++){
                prod[i+j] += mod(aArr[i]*bArr[j]);
            }
        }
        result = Arrays.asList(prod);
        return result;
    }
    
    //mod function prevents overly large nums and negatives
    private static Long mod(Long x){
        if(x > 1000000009)
            return Long.valueOf(x%1000000009);
        return x;
    }

    /*
     * Complete the 'smellCosmos' function below.
     *
     * The function is expected to return a LONG_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER_ARRAY b
     */

    public static List<Long> smellCosmos(List<Long> a, List<Long> b) {
    // Write your code here
        //base case for length < 16
        if(a.size() < 16){
            return gradeSchool(a, b);
        }
        List<Long> h = new ArrayList<Long>();
        //convert a and b to arrays
        Long[] aR = (Long[])a.toArray();
        Long[] bR = (Long[])b.toArray();
        
        Long[] aHigh = new Long[aR.length/2];
        Long[] aLow = new Long[aR.length/2];
        Long[] bHigh = new Long[bR.length/2];
        Long[] bLow = new Long[bR.length/2];
        Long[] aMid = new Long[aR.length/2];
        Long[] bMid = new Long[bR.length/2];
        
        //populate the subarrs
        for(int i = 0; i< aR.length/2; i++){
            aHigh[i] = aR[i];
            bHigh[i] = bR[i];
            aLow[i] = aR[i+aR.length/2];
            bLow[i] = bR[i+bR.length/2];
            aMid[i] = aHigh[i]+ aLow[i];
            bMid[i] = bHigh[i]+ bLow[i];
        }
        //all subarrays have right items
        Long[] hHigh = (Long[])smellCosmos(Arrays.asList(aHigh), Arrays.asList(bHigh)).toArray();
        Long[] hLow = (Long[])smellCosmos(Arrays.asList(aLow), Arrays.asList(bLow)).toArray();
        Long[] hMid = (Long[])smellCosmos(Arrays.asList(aMid), Arrays.asList(bMid)).toArray();

        Long[] hComb = new Long[aR.length+bR.length-1];

        for(int i = 0; i< hComb.length; i++){
            
        }
        
        


        return h;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        List<Long> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        List<Long> result = Result.smellCosmos(a, b);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}