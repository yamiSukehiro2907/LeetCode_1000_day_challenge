package January;

public class bus_ticket_change {
    static void main() {

    }

    static class Solution {
        public boolean canServe(int[] arr) {
            int count5 = 0, count10 = 0, count20 = 0;
            for (int num : arr) {
                if (num == 5) count5++;
                else if (num == 10) {
                    if (count5 == 0) return false;
                    count5--;
                    count10++;
                } else {
                    count20++;
                    if (count10 > 0 && count5 > 0) {
                        count5--;
                        count10--;
                    } else if (count5 > 2) count5 -= 3;
                    else return false;
                }
            }
            return true;
        }
    }
}
