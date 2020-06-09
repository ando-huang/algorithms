import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

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
        List<Long> ahi = new ArrayList<Long>(a.sublist(0, a.size()/2));
        List<Long> alo = new ArrayList<Long>(a.sublist(a.size()/2, a.size()));
        List<Long> bhi = new ArrayList<Long>(b.sublist(0, b.size()/2));
        List<Long> blo = new ArrayList<Long>(b.sublist(b.size()/2, b.size()));
        List<Long> amid = new List<Long>();
        List<Long> bmid = new List<Long>();


        for(int i = 0; i<ahi.size(); i++){
            amid.add(alo.get(i)+ahi.get(i));
            bmid.add(bhi.get(i)+blo.get(i));
        }

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