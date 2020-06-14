package summer2020;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class karatsubatest {

    private static long mod(long x){
        long mod = (long)1000000009;
        if(x >= mod){
            return x%mod;
        }
        return (x+mod)%mod;
    }

    public static long[] arrVal(List<Long> a){
        long[] aR = new long[a.size()];
        int i = 0;
        for(long c: a){
            aR[i] = mod(c);
            i++;
        }
        return aR;
    }

    private static long[] gradeSchool(long[] a, long[] b){
        long[] cR = new long[a.length*2-1];
        Arrays.fill(cR, (long)0);
        for(int i = 0; i<a.length; i++){
            for(int j = 0; j<b.length; j++){
                cR[i+j] += mod(mod(a[i])*mod(b[j]));
            }
        }
        return cR;
    }

    public static long[] smellCosmos(long[] a, long[] b){
        int n = a.length;
        if(n< 32){
            return gradeSchool(a, b);
        }
        long[] aH = Arrays.copyOfRange(a, a.length/2, a.length);
        long[] aL = Arrays.copyOfRange(a, 0, a.length/2);
        long[] bH = Arrays.copyOfRange(b, b.length/2, b.length);
        long[] bL = Arrays.copyOfRange(b, 0, b.length/2);
        long[] hH = smellCosmos(aH, bH); //recur on highs
        long[] hL= smellCosmos(aL, bL);  //recur on lows

        long[] bM = new long[b.length/2];
        long[] aM = new long[a.length/2];
        //populate mid arrs
        for(int i= 0; i< aH.length; i++){            
            aM[i] = mod(mod(aH[i])+mod(aL[i]));
            bM[i] = mod(mod(bH[i])+mod(bL[i]));
        }
        long[] hM = smellCosmos(aM, bM); //recur on middle arrs

        for(int i = 0; i < hM.length; i++){
            long temp = mod(hH[i]) + mod(hL[i]);
            hM[i] -= mod(temp);
        }

        int cLen = n*2-1;
        long[] hC = new long[cLen];
        Arrays.fill(hC, (long)0);
        
        for(int i = 0; i < hH.length; i++){
            hC[i+n] = mod(mod(hH[i])+ mod(hC[i+n]));
            hC[i] = mod(mod(hL[i]) + mod(hC[i]));
            hC[i+n/2] = mod(mod(hM[i]) + mod(hC[i+n/2]));
        }
        return hC;
    }

    public static void main(String[] args){
        //List<Long> a = new ArrayList<Long>();
        //List<Long> b = new ArrayList<Long>();
    }


}

/**
 * 
 *      
 * 
 *      import org.apache.commons.lang3.ArrayUtils;
 * 
        long[] aR = Result.arrVal(a);
        long[] bR = Result.arrVal(b);
        long[] res = Result.smellCosmos(aR, bR);
        
        Long[] resL = ArrayUtils.toObject(res);
        List<Long> result = Arrays.asList(resL);

**/