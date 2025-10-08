public class L_8 {

    private static class Solution {
        public int[] successfulPairs(int[] spells, int[] potions, long success) {
            int max = potions[0];
            for (int num : potions) if (max < num) max = num;
            int[] freq = new int[max + 1];
            for (int num : potions) freq[num]++;
            for (int i = max - 1; i > 0; i--) freq[i] += freq[i + 1];
            for (int i = 0; i < spells.length; i++) {
                long index = (spells[i] + success - 1) / spells[i];
                if (index <= max) spells[i] = freq[(int) index];
                else spells[i] = 0;
            }
            return spells;
        }
    }

    public static void main(String[] args) {
        
    }
}
