
public class L_24 {
    public static void main(String[] args) {
        int num = 4;
        int den = 333;
        Solution solution = new Solution();
        System.out.println(solution.fractionToDecimal(num, den));
    }

    private static class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long lnum = numerator, lden = denominator;
        if (lnum == 0) return "0";
        if (lnum % lden == 0) return Long.toString(lnum / lden);
        StringBuilder number = new StringBuilder();
        if ((lden < 0) ^ (lnum < 0)) number.append('-');
        lnum = Math.abs(lnum);
        lden = Math.abs(lden);
        number.append(lnum / lden);
        number.append('.');
        long GCD = gcd(lnum, lden);
        lnum /= GCD;
        lden /= GCD;
        lnum %= lden; 
        lnum *= 10;
        int twos = 0, fives = 0;
        long temp = lden;
        while (temp % 2 == 0) {
            twos++;
            temp /= 2;
        }
        while (temp % 5 == 0) {
            fives++;
            temp /= 5;
        }
        if (temp == 1) {
            while (lnum != 0) {
                number.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            }
        } else {
            int nonRepLen = Math.max(twos, fives);
            for (int i = 0; i < nonRepLen; i++) {
                number.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            }
            long initRem = lnum;
            StringBuilder repeating = new StringBuilder();
            do {
                repeating.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            } while (lnum != initRem); 
            number.append('(').append(repeating.toString()).append(')');
        }
        return number.toString();
    }
    private long gcd(long a, long b) {return (b == 0) ? a : gcd(b, a % b);}
}
/*
class Solution {
    static{
        System.gc();
        for(int i = 0 ; i < 100 ; i++){
            fractionToDecimal(0 , 1);
        }
    }
    public static String fractionToDecimal(int num, int den) {
        if(num == 0) return "0";
        int sign = 1;
        StringBuilder sb = new StringBuilder();
        long numerator = num , denominator = den;
        if(numerator < 0){
            sign *= -1;
            numerator *= -1;
        }
        if(denominator < 0){
            sign *= -1;
            denominator *= -1;
        }
        if (sign == -1) sb.append('-');
        sb.append(numerator / denominator);
        numerator = numerator % denominator;
        if (numerator == 0) return sb.toString();
        sb.append('.');
        long GCD = gcd(numerator , denominator);
        numerator /= GCD;
        denominator /= GCD;
        Map<Long, Integer> map = new HashMap<>();
        while (numerator != 0) {
            if (map.containsKey(numerator)) {
                int index = map.get(numerator);
                sb.insert(index, '(');
                sb.append(')');
                return sb.toString();
            }
            map.put(numerator, sb.length());
            numerator *= 10;
            sb.append(numerator / denominator);
            numerator %= denominator;
        }
        return sb.toString();
    }
    private static long gcd(long a , long b){
        return a == 0 ? b : gcd(b % a , a);
    }
}
*/
}
