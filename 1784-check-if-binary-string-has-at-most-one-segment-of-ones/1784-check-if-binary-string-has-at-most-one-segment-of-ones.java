class Solution {
    public boolean checkOnesSegment(String s) {
        // A valid string cannot contain "01" after the initial '1' segment
        return !s.contains("01");
    }
}