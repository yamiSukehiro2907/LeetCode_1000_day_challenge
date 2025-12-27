package December.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class meeting_room_three {
    static void main() {
        int n = 4;
        int[][] meetings = {{19, 20}, {14, 15}, {13, 14}, {11, 20}};
        Solution solution = new Solution();
        System.out.println(solution.mostBooked(n, meetings));
    }

    static class Solution {
        private PriorityQueue<long[]> rooms;
        // room[i] = endTime , index , totalMeeting
        private long currentTime;

        public int mostBooked(int n, int[][] m) {
            this.temp = new ArrayList<>();
            Arrays.sort(m, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
            this.rooms = new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                return Long.compare(a[1], b[1]);
            });
            System.out.println("Meetings: ");
            for (int[] meeting : m) System.out.println(Arrays.toString(meeting));
            for (int i = 0; i < n; i++) rooms.offer(new long[]{-1, i, 0});
            int meetingIndex = 0;
            this.currentTime = 0;
            while (meetingIndex < m.length) {
                int[] meeting = m[meetingIndex++];
                System.out.println("Before Meeting Arrived: ");
                printQueue();
                System.out.println("Current Time: " + this.currentTime);
                if (currentTime < meeting[0]) currentTime = meeting[0];
                System.out.println("After meeting Arrived: " + Arrays.toString(meeting));
                printQueue();
                System.out.println("Current Time: " + this.currentTime);
                removeEndedMeetings();
                System.out.println("After removing all ended meetings: ");
                printQueue();
                System.out.println("Current Time: " + this.currentTime);
                long[] room = rooms.peek();
                if (room[0] == -1) allotRoom(meeting);
                else {
                    currentTime = room[0];
                    removeEndedMeetings();
                    allotRoom(meeting);
                }
            }
            return findMostBookedRoom();
        }

        private List<long[]> temp;

        private void removeEndedMeetings() {
            while (!rooms.isEmpty()) {
                long[] room = rooms.peek();
                if (room[0] <= currentTime) temp.add(rooms.poll());
                else break;
            }
            for (long[] room : temp) {
                room[0] = -1;
                rooms.offer(room);
            }
            temp.clear();
        }

        private void allotRoom(int[] meeting) {
            long[] room = rooms.poll();
            room[2]++;
            room[0] = currentTime + meeting[1] - meeting[0];
            rooms.add(room);
        }

        private int findMostBookedRoom() {
            int index = -1;
            long booked = Long.MIN_VALUE;
            while (!rooms.isEmpty()) {
                long[] room = rooms.poll();
                if (index == -1 || room[2] > booked || (room[2] == booked && index > room[1])) {
                    index = (int) room[1];
                    booked = room[2];
                }
            }
            return index;
        }

        private void printQueue() {
            System.out.println("Queue: ");
            while (!rooms.isEmpty()) {
                long[] room = rooms.poll();
                System.out.println(Arrays.toString(room));
                temp.add(room);
            }
            while (!temp.isEmpty()) {
                rooms.offer(temp.removeLast());
            }
        }
    }
}
