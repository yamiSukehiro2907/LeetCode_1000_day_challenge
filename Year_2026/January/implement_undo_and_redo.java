package January;

import java.util.Stack;

public class implement_undo_and_redo {
    static void main() {

    }

    static class Solution {
        private final Stack<Character> undoStack = new Stack<>();
        private final StringBuilder sb = new StringBuilder();

        public void append(char x) {
            sb.append(x);
        }

        public void undo() {
            if (sb.isEmpty()) return;
            undoStack.push(sb.charAt(sb.length() - 1));
            sb.setLength(sb.length() - 1);
        }

        public void redo() {
            if (undoStack.isEmpty()) return;
            sb.append(undoStack.pop());
        }

        public String read() {
            return sb.toString();
        }
    }

}
