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
public class karatsubatest {

    private static Long mod(Long x){
        Long mod = Long.valueOf(1000000009);
        if(x>= mod){
            return x%mod;
        }
        return (x+mod)%mod;
    }

    private static Long[] arrVal(List<Long> a){
        Long[] aR = new Long[a.size()];
        int i = 0;
        for(Long c: a){
            aR[i] = c;
            i++;
        }
        return aR;
    }

    private static List<Long> gradeSchool(List<Long> a, List<Long> b){
        List<Long> result = new ArrayList<Long>();
        Long[] aR = arrVal(a);
        Long[] bR = arrVal(b);
        Long[] cR = new Long[aR.length*2-1];
        for(int i = 0; i<cR.length; i++){
            cR[i] = Long.valueOf(0);
        }
        for(int i = 0; i<aR.length; i++){
            for(int j = 0; j<bR.length; j++){
                cR[i+j] += mod(aR[i]*bR[j]);
            }
        }
        result = Arrays.asList(cR);
        return result;
    }

    public static List<Long> smellCosmos(List<Long> a, List<Long> b){
        int n = a.size();
        Long[] aR = arrVal(a);
        Long[] bR = arrVal(b);
        Long[] aH = new Long[aR.length/2];
        Long[] aL = new Long[aR.length/2];
        Long[] aM = new Long[aR.length/2];
        Long[] bH = new Long[bR.length/2];
        Long[] bL = new Long[bR.length/2];
        Long[] bM = new Long[bR.length/2];

        //populate the six half arrs
        for(int i= 0; i< aH.length; i++){
            aH[i] = aR[i+aH.length];
            bH[i] = bR[i+bH.length];
            aL[i] = aR[i];
            bL[i] = bR[i];
            aM[i] = aH[i]+aL[i];
            bM[i] = bH[i]+bL[i];
        }

        Long[] hH = arrVal(smellCosmos(Arrays.asList(aH), Arrays.asList(bH)));
        Long[] hL= arrVal(smellCosmos(Arrays.asList(aL), Arrays.asList(bL)));
        Long[] hM = arrVal(smellCosmos(Arrays.asList(aM), Arrays.asList(bM)));

        for(int i = 0; i < hM.length; i++){
            hM[i] -= (hH[i]+ hL[i]);
        }

        int cLen = a.size()*2-1;
        Long[] hC = new Long[cLen];
        for(int i = 0; i < cLen; i++){
            hC[i] = Long.valueOf(0);
        }
        for(int i = 0; i < hH.length; i++){
            hC[i+n] += hH[i];
            hC[i] += hL[i];
            hC[i+n/2] += hH[i];
        }
        return Arrays.asList(hC);
    }

    public static void main(String[] args){
        System.out.println("input");
    }


}