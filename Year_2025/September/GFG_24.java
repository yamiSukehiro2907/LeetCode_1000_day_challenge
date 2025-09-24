import java.util.*;

public class GFG_24 {
    public static void main(String[] args) {
        SpecialQueue specialQueue = new SpecialQueue();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Choose one of the operation:");
            System.out.println("1: Add element");
            System.out.println("2: Remove element");
            System.out.println("3: Get Front Element");
            System.out.println("4: Get Min in Queue");
            System.out.println("5: Get Max in Queue");
            System.out.println("-1 : To exit");
            OUTER: while (true) {
                int op = sc.nextInt();
                switch (op) {
                    case 1 -> {
                        System.out.print("Enter the num to add: ");
                        int num = sc.nextInt();
                        specialQueue.enqueue(num);
                    }
                    case 2 -> specialQueue.dequeue();
                    case 3 -> System.out.println("Front Element: " + specialQueue.getFront());
                    case 4 -> System.out.println("Minimum Element in Queue: " + specialQueue.getMin());
                    case 5 -> System.out.println("Maximum Element in Queue: " + specialQueue.getMax());
                    case 6 -> specialQueue.print();
                    default -> {
                        System.out.println("Exiting....");
                        break OUTER;
                    }
                }
            }
        }
    }

    private static class SpecialQueue {

        private final Queue<Integer> queue;
        private final Stack<Integer> minStack;
        private final Stack<Integer> maxStack;

        public SpecialQueue() {
            this.queue = new LinkedList<>();
            this.minStack = new Stack<>();
            this.maxStack = new Stack<>();
        }

        public void enqueue(int x) {
            queue.offer(x);
            if (minStack.isEmpty() || minStack.peek() >= x)
                minStack.push(x);
            if (maxStack.isEmpty() || maxStack.peek() <= x)
                maxStack.push(x);
        }

        public void dequeue() {
            if(queue.isEmpty()) return;
            int num = queue.poll();
            while (!minStack.isEmpty() && minStack.peek() >= num) {
                minStack.pop();
            }
            while (!maxStack.isEmpty() && maxStack.peek() <= num) {
                maxStack.pop();
            }
            if (minStack.isEmpty()) {
                fill();
            }
            if (maxStack.isEmpty()) {
                fill();
            }
        }

        public int getFront() {
            return queue.peek();
        }

        public int getMin() {
            return minStack.peek();
        }

        public int getMax() {
            return maxStack.peek();
        }

        private void fill() {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                if (minStack.isEmpty() || minStack.peek() >= num)
                    minStack.push(num);
                if (maxStack.isEmpty() || maxStack.peek() <= num) {
                    maxStack.push(num);
                }
                queue.offer(num);
            }
        }

        public void print() {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                System.out.print(num);
                System.out.print((i == size - 1) ? "" : " -> ");
                queue.offer(num);
            }
            System.out.println();
        }
    }
}
