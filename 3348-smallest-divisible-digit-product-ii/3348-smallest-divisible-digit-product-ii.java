import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t
        int[] target = new int[8];
        for (int p : new int[]{2, 3, 5, 7}) {
            while (t % p == 0) {
                target[p]++;
                t /= p;
            }
        }
        if (t > 1) return "-1"; // Prime factor > 7 impossible

        int n = num.length();
        int firstZero = num.indexOf('0');
        int validLen = (firstZero == -1) ? n : firstZero;

        // Calculate factors of the valid prefix
        int[] prefFactors = new int[8];
        for (int i = 0; i < validLen; i++) {
            addDigitFactors(prefFactors, num.charAt(i) - '0', 1);
        }

        // Check if num itself (without zeros) is already valid
        if (validLen == n && satisfies(prefFactors, target)) {
            return num;
        }

        // Try divergence index i from firstZero (or n - 1) down to 0
        int startPos = (firstZero != -1) ? firstZero : n - 1;
        for (int i = startPos; i >= 0; i--) {
            if (i < validLen) {
                addDigitFactors(prefFactors, num.charAt(i) - '0', -1);
            }

            // At the zero position, start digit can be 1 (since 1 > '0')
            // Otherwise, start digit must be num.charAt(i) - '0' + 1
            int startDigit = (i == firstZero) ? 1 : (num.charAt(i) - '0' + 1);

            for (int d = startDigit; d <= 9; d++) {
                int req2 = Math.max(0, target[2] - prefFactors[2] - getFactorCount(d, 2));
                int req3 = Math.max(0, target[3] - prefFactors[3] - getFactorCount(d, 3));
                int req5 = Math.max(0, target[5] - prefFactors[5] - getFactorCount(d, 5));
                int req7 = Math.max(0, target[7] - prefFactors[7] - getFactorCount(d, 7));

                int remLen = n - 1 - i;
                if (minDigitsNeeded(req2, req3, req5, req7) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append(d);
                    sb.append(buildSmallestSuffix(req2, req3, req5, req7, remLen));
                    return sb.toString();
                }
            }
        }

        // If no number of length n exists, expand to the true minimum required length
        int targetLen = Math.max(n + 1, minDigitsNeeded(target[2], target[3], target[5], target[7]));
        return buildSmallestSuffix(target[2], target[3], target[5], target[7], targetLen);
    }

    private boolean satisfies(int[] current, int[] target) {
        return current[2] >= target[2] && current[3] >= target[3] &&
               current[5] >= target[5] && current[7] >= target[7];
    }

    private int getFactorCount(int digit, int p) {
        if (digit <= 1) return 0;
        int count = 0;
        while (digit % p == 0) {
            count++;
            digit /= p;
        }
        return count;
    }

    private void addDigitFactors(int[] factors, int digit, int delta) {
        for (int p : new int[]{2, 3, 5, 7}) {
            factors[p] += getFactorCount(digit, p) * delta;
        }
    }

    private int minDigitsNeeded(int req2, int req3, int req5, int req7) {
        int count = req5 + req7;
        if (req2 == 0 && req3 == 0) return count;

        int min23Digits = 10000;
        for (int c9 = 0; c9 <= (req3 + 1) / 2; c9++) {
            for (int c8 = 0; c8 <= (req2 + 2) / 3; c8++) {
                for (int c6 = 0; c6 <= 1; c6++) {
                    int rem2 = Math.max(0, req2 - c8 * 3 - c6);
                    int rem3 = Math.max(0, req3 - c9 * 2 - c6);

                    int c4 = rem2 / 2;
                    int c2 = rem2 % 2;
                    int c3 = rem3;

                    min23Digits = Math.min(min23Digits, c9 + c8 + c6 + c4 + c2 + c3);
                }
            }
        }
        return count + min23Digits;
    }

    private String buildSmallestSuffix(int req2, int req3, int req5, int req7, int remLen) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < remLen; pos++) {
            int spacesLeft = remLen - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int next2 = Math.max(0, req2 - getFactorCount(d, 2));
                int next3 = Math.max(0, req3 - getFactorCount(d, 3));
                int next5 = Math.max(0, req5 - getFactorCount(d, 5));
                int next7 = Math.max(0, req7 - getFactorCount(d, 7));

                if (minDigitsNeeded(next2, next3, next5, next7) <= spacesLeft) {
                    sb.append(d);
                    req2 = next2;
                    req3 = next3;
                    req5 = next5;
                    req7 = next7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}