package AssignmentProblems;

public class MatchNumbersEasy {

    public String maxNumber(int[] matches, int n) {
        int minCost = Integer.MAX_VALUE;
        int minDigit = -1;

        //digit with minimum cost
        for (int i = 0; i < matches.length; i++) {
            if (matches[i] < minCost || (matches[i] == minCost && i > minDigit)) {
                minCost = matches[i];
                minDigit = i;
            }
        }

        String best = "";

        for (int d = matches.length - 1; d >= 0; d--) {
            int cost = matches[d];
            if (cost > n) continue;

            // Skip leading 0s 
            if (d == 0 && matches.length > 1 && n >= minCost * 2) continue;

            int remaining = n - cost;
            int count = remaining / minCost;

            StringBuilder candidate = new StringBuilder();
            candidate.append(d); // start

            remaining = remaining - count * minCost;

            for (int i = 0; i < count; i++) {
                for (int x = matches.length - 1; x >= 0; x--) {
                    if (matches[x] - minCost <= remaining) {
                        candidate.append(x);
                        remaining -= (matches[x] - minCost);
                        break;
                    }
                }
            }

            String num = candidate.toString();
            if (num.length() > best.length() || (num.length() == best.length() && num.compareTo(best) > 0)) {
                best = num;
            }
        }
        if(best == ""){
            return "0";
        } else {
            return best;

        }

    }
}
