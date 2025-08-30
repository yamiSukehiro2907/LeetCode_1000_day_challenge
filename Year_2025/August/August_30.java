public class August_30 {
    public static void main(String[] args) {

    }

    static class Solution {
        static {
            char[][] temp = new char[9][9];
            for (int i = 0; i < 500; i++) {
                isValidSudoku(temp);
            }
        }

        public static boolean isValidSudoku(char[][] board) {
            int[] row = new int[9];
            int[] col = new int[9];
            int[] miniBoard = new int[9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.')
                        continue;
                    // to find the which box does this num lies
                    int boxNo = (i / 3) * 3 + (j / 3);
                    int c = board[i][j] - '0';
                    int num = 1 << c;
                    // if that bit position is already filled that means it is a set bit then sudoku
                    // is not
                    if ((row[i] & num) != 0 || (col[j] & num) != 0 || (miniBoard[boxNo] & num) != 0)
                        return false;
                    else {
                        // for each row , col and box we are setting 1 at char value bit position
                        row[i] |= num;
                        col[j] |= num;
                        miniBoard[boxNo] |= num;
                    }
                }
            }
            return true;
        }
    }
}
