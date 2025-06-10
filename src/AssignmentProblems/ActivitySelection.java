package AssignmentProblems;

import java.util.*;

public class ActivitySelection {
    static class Activity {
        int start, end;
        Activity(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static int maxActivities(Activity[] activities) {
        // Sort by end time
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        int count = 1; // Always select the first activity
        int lastEnd = activities[0].end;

        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastEnd) {
                count++;
                lastEnd = activities[i].end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 4),
            new Activity(3, 5),
            new Activity(0, 6),
            new Activity(5, 7),
            new Activity(3, 8),
            new Activity(5, 9),
            new Activity(6, 10),
            new Activity(8, 11),
            new Activity(8, 12),
            new Activity(2, 13),
            new Activity(12, 14)
        };

        System.out.println("Maximum number of non-overlapping activities: " + maxActivities(activities)); // Output: 4
    }
}

