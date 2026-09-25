import java.util.*;

class Solution {
    private final TreeSet<String> resultSet = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String exp) {
        int right = exp.indexOf('}');
        if (right == -1) {
            resultSet.add(exp);
            return;
        }

        int left = exp.lastIndexOf('{', right);
        String prefix = exp.substring(0, left);
        String suffix = exp.substring(right + 1);

        String[] options = exp.substring(left + 1, right).split(",");
        for (String option : options) {
            dfs(prefix + option + suffix);
        }
    }
}