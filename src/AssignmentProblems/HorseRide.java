package AssignmentProblems;

import java.util.*;

public class HorseRide {
    public int betResult(String[] horseGroups, String ticket) {
        Set<Character> allHorses = new HashSet<>();
        Set<Character> usedHorses = new HashSet<>();
        Set<Integer> usedGroups = new HashSet<>();

        // Map each horse to its group index
        Map<Character, Integer> horseToGroup = new HashMap<>();
        for (int i = 0; i < horseGroups.length; i++) {
            for (char ch : horseGroups[i].toCharArray()) {
                if (allHorses.contains(ch)) {
                    // Should not happen per problem constraints
                    return -1;
                }
                allHorses.add(ch);
                horseToGroup.put(ch, i);
            }
        }

        int correct = 0;

        for (char ch : ticket.toCharArray()) {
            if (!allHorses.contains(ch)) {
                return -1; // Invalid horse
            }
            if (usedHorses.contains(ch)) {
                return -1; // Duplicate horse
            }
            usedHorses.add(ch);

            int group = horseToGroup.get(ch);
            if (usedGroups.contains(group)) {
                return -1; // More than one pick from same group
            }
            usedGroups.add(group);

            if (horseGroups[group].charAt(0) == ch) {
                correct++; // Predicted the winner
            }
        }

        return correct;
    }

    public static void main(String[] args) {
        HorseRide hr = new HorseRide();
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "AC"));   // 2
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "CA"));   // 2
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "Cb"));   // 1
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "X"));    // 0
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "bed"));  // -1
        System.out.println(hr.betResult(new String[]{"AbX", "CdeF"}, "dAd"));  // -1
        System.out.println(hr.betResult(new String[]{"a", "b", "c", "d", "e", "f"}, "bead")); // 4
    }
}

