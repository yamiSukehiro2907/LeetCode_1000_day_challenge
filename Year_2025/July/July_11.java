import java.util.*;

public class July_11 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int mostBooked(int n, int[][] meetings) {
            if (meetings.length == 0)
                return 0;
            Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
            PriorityQueue<int[]> waitingMeetings = new PriorityQueue<>((a, b) -> a[0] - b[0]); //delayed meetings are sorted on the their startTime
            Room[] rooms = new Room[n];
            for (int i = 0; i < n; i++) {
                rooms[i] = new Room();
            }
            int index = 0;
            long currentTime = 0;
            while (index < meetings.length || !waitingMeetings.isEmpty() || anyRoomBusy(rooms)) {
                while (index < meetings.length && meetings[index][0] <= currentTime) {
                    waitingMeetings.offer(meetings[index]);
                    index++;
                }
                for (int i = 0; i < n; i++) { // Free up rooms that have ended by now
                    if (rooms[i].inUse && rooms[i].endTime <= currentTime) {
                        rooms[i].inUse = false;
                        rooms[i].meetingCount++;
                    }
                }
                for (int i = 0; i < n && !waitingMeetings.isEmpty(); i++) { // assign rooms to earliest empty rooms
                    if (!rooms[i].inUse) {
                        int[] meeting = waitingMeetings.poll();
                        long duration = meeting[1] - meeting[0];
                        rooms[i].inUse = true;
                        rooms[i].endTime = currentTime + duration;
                    }
                }
                long nextEventTime = Long.MAX_VALUE;
                if (index < meetings.length) {
                    nextEventTime = Math.min(nextEventTime, meetings[index][0]); // endTime of next meeting
                }
                for (int i = 0; i < n; i++) {
                    if (rooms[i].inUse) {
                        nextEventTime = Math.min(nextEventTime, rooms[i].endTime);// endTime of rooms being used
                    }
                }
                if (nextEventTime == Long.MAX_VALUE) { // if no meetings left
                    break;
                }
                currentTime = nextEventTime;
            }
            int maxRoom = 0;
            int maxMeetings = rooms[0].meetingCount;
            for (int i = 1; i < n; i++) {
                if (rooms[i].meetingCount > maxMeetings) {
                    maxMeetings = rooms[i].meetingCount;
                    maxRoom = i;
                }
            }
            return maxRoom;
        }

        private boolean anyRoomBusy(Room[] rooms) {
            for (Room room : rooms) {
                if (room.inUse)
                    return true;
            }
            return false;
        }

        private class Room {
            boolean inUse;
            long endTime;
            int meetingCount;

            Room() {
                this.inUse = false;
                this.endTime = 0;
                this.meetingCount = 0;
            }
        }
    }
}
