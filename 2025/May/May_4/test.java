public class test {
    public static void main(String[] args) {
        double arr[] = { 10.71, 25.71 , 0.71 , -19.29 , -9.29 , 15.71 , -24.29 };
        double sum = (double) 0;
        double total = (double)0;
        for (double num : arr) {
            sum += ((double) num * num);
            total += num;
        }
        System.out.println(sum);
        double ans = Math.sqrt(sum / ((double) 7));
        System.out.println(ans);
        System.out.println(sum / ((double) 7));
        System.out.println(total);
    }
}