package Year_2025.May.May_15;

import java.util.*;

public class Test {
    public static void main(String[] args) {

    }

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int start = newInterval[0], end = newInterval[1], n = intervals.length, i = 0;
            if (n == 0) {
                return new int[][] { { start, end } };
            }
            List<int[]> list = new ArrayList<>();
            boolean used = false;
            if (intervals[0][0] > end) { // if the interval will be added before first interval
                list.add(newInterval);
                used = true;
            } else if (end <= intervals[0][1] && start <= intervals[0][0]) { // if the newInterval's start is before start of intervals and end is in the first interval
                used = true;
                list.add(new int[] { Math.min(start, intervals[0][0]), Math.max(end, intervals[0][1]) });
                i++;
            } else if (end > intervals[0][0] && start <= intervals[0][0]) { // if the newInterval's start is before start of intervals and end is not in the first interval
                start = Math.min(start, intervals[0][0]);
                while (i < n) {
                    int temp2[] = intervals[i];
                    if (end < temp2[0]) { // if the end does not lie in any interval and needed to create a new interval
                        list.add(new int[] { start, end });
                        used = true;
                        break;
                    } else if (end <= temp2[1]) { // if the end lies in any interval
                        list.add(new int[] { start, temp2[1] });
                        used = true;
                        i++;
                        break;
                    }
                    i++;
                }
                if (i == n && !used) { // if reach the end and not even added interval then it means newInterval contains all intervals
                    list.add(new int[] { start, end });
                    used = true;
                }
            }
            for (; i < n; i++) {
                int[] row = intervals[i];
                if (!used) { // if interval is not used
                    if (end <= row[1] && start >= row[0]) { // if the newInterval lies completely in a interval
                        return intervals;
                    } else if (start <= row[1] && end > row[1]) { // if the interval contains the start
                        int[] temp = { Math.min(start, row[0]), end };
                        i++;
                        while (i < n) {
                            int temp2[] = intervals[i];
                            if (end >= temp2[0] && end <= temp2[1]) { // if interval contains the end
                                temp[1] = temp2[1];
                                used = true;
                                list.add(temp);
                                break;
                            } else if (end < temp2[1]) { // if end is not in any interval and needed to create a new interval
                                list.add(temp);
                                used = true;
                                i--;
                                break;
                            }
                            i++;
                        }
                        if (i == n && !used) { // reach the end and not even added interval
                            list.add(temp);
                            used = true;
                            break;
                        }
                    } else if (start < row[0]) { // if the newInterval's start is before start of intervals
                        while (i < n) {
                            int temp[] = intervals[i];
                            if (end < temp[0]) { // if the end is also before start of intervals
                                list.add(newInterval);
                                used = true;
                                i--;
                                break;
                            } else if (end <= temp[1]) { // if the end is in the interval
                                list.add(new int[] { start, Math.max(end, temp[1]) });
                                used = true;
                                break;
                            }
                            i++;
                        }
                        if (i == n && !used) { // reach the end and not even added interval
                            list.add(newInterval);
                            used = true;
                            break;
                        }
                    } else {// if not used and cannot be merged
                        list.add(row);
                    }
                } else { // if used
                    list.add(row);
                }
            }
            if (!used) { // if not used then it lies after the last interval
                list.add(newInterval);
                used = true;
            }
            int matrix[][] = new int[list.size()][2];
            int k = 0;
            for (int[] temp : list) {
                matrix[k][0] = temp[0];
                matrix[k++][1] = temp[1];
            }
            return matrix;
        }
    }
}
