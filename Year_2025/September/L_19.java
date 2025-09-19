public class L_19 {
    private static class Spreadsheet {
        private int[][] cells;

        public Spreadsheet(int rows) {
            this.cells = new int[26][rows];
        }

        public void setCell(String str, int value) {
            int[] cell = getCell(str);
            this.cells[cell[0]][cell[1]] = value;
        }

        public void resetCell(String str) {
            int[] cell = getCell(str);
            this.cells[cell[0]][cell[1]] = 0;
        }

        public int getValue(String formula) {
            char[] arr = formula.toCharArray();
            int plusIndex = getPlusIndex(arr);
            int num1 = (!Character.isDigit(arr[1])) ? Integer.parseInt((new String(arr, 1, plusIndex)))
                    : getNum(arr, 1, plusIndex);
            int num2 = (!Character.isDigit(arr[plusIndex + 1]))
                    ? Integer.parseInt((new String(arr, plusIndex + 1, arr.length)))
                    : getNum(arr, plusIndex + 1, arr.length);
            return num1 + num2;
        }

        private int getNum(char[] arr, int start, int end) {
            int[] cell = getCell((new String(arr, start, end)));
            return this.cells[cell[0]][cell[1]];
        }

        private int[] getCell(String cell) {
            return new int[] { cell.charAt(0) - 'A', Integer.parseInt(cell.substring(1)) };
        }

        private int getPlusIndex(char[] arr) {
            for (int i = 2; i < arr.length; i++) {
                if (arr[i] == '+') {
                    return i;
                }
            }
            return -1;
        }
    }
}
