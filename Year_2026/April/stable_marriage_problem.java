package April;

public class stable_marriage_problem {
    static void main() {

    }

    static class Solution {
        public int[] stableMarriage(int[][] menPreferences, int[][] womenPreferences) {
            int totalPair = menPreferences.length;
            Male[] males = new Male[totalPair];
            for (int i = 0; i < totalPair; i++) males[i] = new Male(i, -1);
            Female[] females = new Female[totalPair];
            for (int i = 0; i < totalPair; i++) females[i] = new Female(i, -1);
            int[] manChoiceIndex = new int[totalPair];
            int freeMan = totalPair;
            while (freeMan > 0) {
                int maleIndex;
                for (maleIndex = 0; maleIndex < totalPair; maleIndex++) if (males[maleIndex].wife == -1) break;
                int femaleIndex = menPreferences[maleIndex][manChoiceIndex[maleIndex]++];
                if (females[femaleIndex].husband == -1) {
                    females[femaleIndex].husband = maleIndex;
                    males[maleIndex].wife = femaleIndex;
                    freeMan--;
                } else {
                    int currentHusbandIndex = females[femaleIndex].husband;
                    if (isCurrentMalePreferred(womenPreferences[femaleIndex], maleIndex, currentHusbandIndex)) {
                        males[currentHusbandIndex].wife = -1;
                        females[femaleIndex].husband = maleIndex;
                        males[maleIndex].wife = femaleIndex;
                    }
                }
            }
            int[] wives = new int[totalPair];
            for (int index = 0; index < totalPair; index++) wives[index] = males[index].wife;
            return wives;
        }

        private boolean isCurrentMalePreferred(int[] womenPreference, int manIndex, int currentHusbandIndex) {
            for (int index : womenPreference) {
                if (manIndex == index) return true;
                if (currentHusbandIndex == index) return false;
            }
            return false;
        }

        static class Male {
            int index;
            int wife;

            Male(int index, int wife) {
                this.index = index;
                this.wife = wife;
            }
        }

        static class Female {
            int index;
            int husband;

            Female(int index, int husband) {
                this.index = index;
                this.husband = husband;
            }
        }
    }
}
