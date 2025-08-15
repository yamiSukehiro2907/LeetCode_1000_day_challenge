public class August_15 {
    public static void main(String[] args) {
        System.out.println(Math.log(79879));
        for (int i = 0; i < 18; i++) {
            long power = (long) (Math.pow(4, i));
            if (power > (1 << 31) - 1) {
                break;
            } else {
                System.out.println(power);
            }
        }
        System.out.println((1 << 31) - 1);
    }

}
