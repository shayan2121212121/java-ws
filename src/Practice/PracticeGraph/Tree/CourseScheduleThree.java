package Practice.PracticeGraph.Tree;

import java.util.*;

public class CourseScheduleThree {

    public int scheduleCourse(int[][] courses) {
        // Sort courses by their end time (lastDay)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        // Max-heap to keep track of durations of selected courses
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;

        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];

            time += duration;
            maxHeap.offer(duration);

            // If time exceeds deadline, drop the longest duration course
            if (time > lastDay) {
                time -= maxHeap.poll();
            }
        }

        // The heap size is the number of courses we can take
        return maxHeap.size();
    }

    // Test
    public static void main(String[] args) {
        CourseScheduleThree cs = new CourseScheduleThree();

        int[][] courses1 = {{100,200},{200,1300},{1000,1250},{2000,3200}};
        System.out.println(cs.scheduleCourse(courses1)); // Output: 3

        int[][] courses2 = {{1,2}};
        System.out.println(cs.scheduleCourse(courses2)); // Output: 1

        int[][] courses3 = {{3,2},{4,3}};
        System.out.println(cs.scheduleCourse(courses3)); // Output: 0
    }
}

