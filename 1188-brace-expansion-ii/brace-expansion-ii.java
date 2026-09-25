import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        // Stack to store state before entering a inner `{...}` scope
        Stack<Set<String>> groupStack = new Stack<>();
        Stack<Set<String>> unionStack = new Stack<>();
        
        // Current expression state at the active level
        Set<String> currentGroup = new HashSet<>(Arrays.asList(""));
        Set<String> currentUnion = new HashSet<>();

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (c == '{') {
                // Save current state and reset for the inside of braces
                groupStack.push(currentGroup);
                unionStack.push(currentUnion);
                
                currentGroup = new HashSet<>(Arrays.asList(""));
                currentUnion = new HashSet<>();
                i++;
            } else if (c == '}') {
                // Finish active level by uniting last group into union
                currentUnion.addAll(currentGroup);
                
                // Pop saved states from outer level
                Set<String> prevUnion = unionStack.pop();
                Set<String> prevGroup = groupStack.pop();
                
                // Multiply outer group with the result of current brace level
                currentGroup = combine(prevGroup, currentUnion);
                currentUnion = prevUnion;
                i++;
            } else if (c == ',') {
                // Union current concatenated group into active level union
                currentUnion.addAll(currentGroup);
                currentGroup = new HashSet<>(Arrays.asList(""));
                i++;
            } else {
                // Lowercase letter: parse consecutive letters as a single block
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLowerCase(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                
                Set<String> wordSet = new HashSet<>(Arrays.asList(sb.toString()));
                currentGroup = combine(currentGroup, wordSet);
            }
        }

        // Merge remaining group into the total result set
        currentUnion.addAll(currentGroup);

        // Sort the unique words lexicographically
        List<String> result = new ArrayList<>(currentUnion);
        Collections.sort(result);
        return result;
    }

    // Cartesian product concatenation of two sets of strings
    private Set<String> combine(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}