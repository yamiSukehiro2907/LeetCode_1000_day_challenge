package Geeks_For_Geeks;

import java.util.*;

public class GFG_4 {
    public static void main(String[] args) {
        String str = "00204";
        Solution solution = new Solution();
        ArrayList<String> possibleNumCombinations = solution.findExpr(str, 0);
        for (String list : possibleNumCombinations) {
            System.out.println(list);
        }
    }

    private static class Solution {
        private ArrayList<ArrayList<String>> possibleNumCombinations;

        public ArrayList<String> findExpr(String str, int target) {
            possibleNumCombinations = new ArrayList<>();
            ArrayList<String> temp1 = new ArrayList<>();
            generate(str, temp1);
            ArrayList<String> possibleOperationsCombinations = new ArrayList<>();
            for (ArrayList<String> numCombination : possibleNumCombinations) {
                if (valid(numCombination)) {
                    ArrayList<String> possibleOperationCombination = generatePossibleOperationCombinations(
                            numCombination);
                    for (String comb : possibleOperationCombination) {
                        possibleOperationsCombinations.add(comb);
                    }
                }
            }

            ArrayList<String> validCombination = new ArrayList<>();
            for (String expression : possibleOperationsCombinations) {
                if (canGetTarget(expression, target)) {
                    validCombination.add(expression);
                }
            }
            Collections.sort(validCombination);
            return validCombination;
        }

        private boolean valid(ArrayList<String> numCombination) {
            for (String num : numCombination) {
                if (num.length() > 1 && num.charAt(0) == '0') {
                    return false;
                }
            }
            return true;
        }

        private boolean canGetTarget(String expression, int target) {
            return getValue(expression) == target;
        }

        private int getValue(String expression) {
            int num = 0;
            int lastNum = 0;
            char lastOp = '+';
            int ans = 0;
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                if (Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                }
                if (!Character.isDigit(ch) && ch != ' ' || i == expression.length() - 1) {
                    switch (lastOp) {
                        case '+' -> {
                            ans += lastNum;
                            lastNum = num;
                        }
                        case '-' -> {
                            ans += lastNum;
                            lastNum = -num;
                        }
                        case '*' -> lastNum *= num;
                        default -> {
                        }
                    }
                    lastOp = ch;
                    num = 0;
                }
            }
            ans += lastNum;
            return ans;
        }

        private ArrayList<String> generatePossibleOperationCombinations(ArrayList<String> list) {
            ArrayList<String> cases = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            generateCases(sb, cases, list, 0);
            return cases;
        }

        private void generateCases(StringBuilder sb, ArrayList<String> cases, ArrayList<String> list, int index) {
            int initialLength = sb.length();
            sb.append(list.get(index));
            if (index == list.size() - 1) {
                cases.add(sb.toString());
                sb.setLength(initialLength);
                return;
            }
            sb.append("+");
            generateCases(sb, cases, list, index + 1);
            sb.setLength(sb.length() - 1);

            sb.append("-");
            generateCases(sb, cases, list, index + 1);
            sb.setLength(sb.length() - 1);

            sb.append("*");
            generateCases(sb, cases, list, index + 1);
            sb.setLength(sb.length() - 1);

            sb.setLength(initialLength);
        }

        private void generate(String str, ArrayList<String> temp) {
            if (str.length() == 0) {
                possibleNumCombinations.add(new ArrayList<>(temp));
                return;
            }

            for (int i = 1; i <= str.length(); i++) {
                String left = str.substring(0, i);
                String right = str.substring(i);
                temp.add(left);
                generate(right, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
