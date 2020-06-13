import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        List<Long> ahi = new ArrayList<Long>(a.subList(0, a.size() / 2));
        List<Long> alo = new ArrayList<Long>(a.subList(a.size() / 2, a.size()));
        List<Long> bhi = new ArrayList<Long>(b.subList(0, b.size() / 2));
        List<Long> blo = new ArrayList<Long>(b.subList(b.size() / 2, b.size()));
        List<Long> amid = new ArrayList<Long>();
        List<Long> bmid = new ArrayList<Long>();
        List<Long> h = new ArrayList<Long>();


        for(int i = 0; i<ahi.size(); i++){
            amid.add(alo.get(i)+ahi.get(i));
            bmid.add(bhi.get(i)+blo.get(i));
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