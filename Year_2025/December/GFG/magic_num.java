package December.GFG;

import java.util.Arrays;

public class magic_num {

    static void main() {
        find(new StringBuilder());
    }

    static void find(StringBuilder num) {
        if (num.length() > 9) return;
        if (!num.isEmpty() && num.length() == 9 && valid(Integer.parseInt(num.toString()))) {
            System.out.println(num);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (num.isEmpty() || !present(i, Integer.parseInt(num.toString()))) {
                num.append(i);
                find(num);
                num.deleteCharAt(num.length() - 1);
            }
        }
    }

    static boolean valid(int num) {
        if (!isValid(num)) return false;
        int[][] matrix = createMatrix(num);
        return checkSum(matrix);
    }

    static int[][] createMatrix(int num) {
        int[][] matrix = new int[3][3];
        int row = 0, col = 0;
        while (num > 0) {
            int rem = num % 10;
            matrix[row][col++] = rem;
            if (col == 3) {
                row++;
                col = 0;
            }
            num /= 10;
        }
        return matrix;
    }

    static boolean isValid(int num) {
        int visited = 0;
        int original = num;
        while (original > 0) {
            int rem = original % 10;
            visited ^= (1 << rem);
            if (((visited >> rem) & 1) == 0) return false;
            original /= 10;
        }
        return true;
    }

    static boolean present(int target, int num) {
        int temp = num;
        while (temp > 0) {
            int rem = temp % 10;
            if (rem == target) return true;
            temp = temp / 10;
        }
        return false;
    }

    static boolean checkSum(int[][] matrix) {
        int sum = matrix[0][0] + matrix[0][1] + matrix[0][2]; // top row
        if (sum != (matrix[1][0] + matrix[1][1] + matrix[1][2])) return false; // middle row
        if (sum != (matrix[2][0] + matrix[2][1] + matrix[2][2])) return false; // last row
        for (int j = 0; j < 3; j++) {
            int temp = 0;
            for (int i = 0; i < 3; i++) temp += matrix[i][j];
            if (temp != sum) return false;
        }
        if (sum != (matrix[0][0] + matrix[1][1] + matrix[2][2])) return false; // primary diagonal
        return sum == (matrix[0][2] + matrix[1][1] + matrix[2][0]); // secondary diagonal
    }
}
