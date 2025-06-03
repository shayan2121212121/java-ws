package AssignmentProblems;

import java.util.*;

public class PartySeats {

    public String[] seating(String[] attendees) {
        List<String> boys = new ArrayList<>();
        List<String> girls = new ArrayList<>();
        
        // Split attendees into boys and girls
        for (String attendee : attendees) {
            String[] parts = attendee.split(" ");
            String name = parts[0];
            String gender = parts[1];
            
            if (gender.equals("boy")) {
                boys.add(name);
            } else {
                girls.add(name);
            }
        }
        
        // If the number of boys and girls is not equal or less than 2 attendees, return empty array
        if (boys.size() != girls.size() || boys.size() % 2 != 0) {
            return new String[0];
        }
        
        // Sort names lexicographically
        Collections.sort(boys);
        Collections.sort(girls);
        
        int n = boys.size();
        String[] seatingArrangement = new String[2 * n + 2];
        
        // Assign HOST and HOSTESS
        seatingArrangement[0] = "HOST";
        seatingArrangement[n + 1] = "HOSTESS";
        
        // Assign seats alternately
        int boyIndex = 0, girlIndex = 0;
        for (int i = 1; i < n + 1; i += 2) {
            seatingArrangement[i] = girls.get(girlIndex++);
            seatingArrangement[i + 1] = boys.get(boyIndex++);
        }
        
        for (int i = n + 2; i < 2 * n + 2; i += 2) {
            seatingArrangement[i] = boys.get(boyIndex++);
            seatingArrangement[i + 1] = girls.get(girlIndex++);
        }
        
        return seatingArrangement;
    }
}
