import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (isValid(current)) {
                result.add(current);
                found = true; // Mark that a valid level has been reached
            }

            // If we found a valid string at this BFS depth, stop expanding deeper levels
            if (found) continue;

            // Generate all possible states by removing one parenthesis at a time
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);
                if (ch != '(' && ch != ')') continue; // Skip non-parentheses characters

                String nextState = current.substring(0, i) + current.substring(i + 1);

                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }

        return result;
    }

    // Helper method to check if a string has valid parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                count++;
            } else if (ch == ')') {
                count--;
                if (count < 0) return false; // More closing than opening brackets
            }
        }
        return count == 0;
    }
}