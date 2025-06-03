package AssignmentProblems;

public class SquareOfDigits {
    public int getMax(String[] data) {
        int rows = data.length;
        int cols = data[0].length();
        int maxSize = 1; // Minimum square size is 1x1

        // Iterate over all possible top-left corners
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Try to expand the square
                for (int s = 1; i + s < rows && j + s < cols; s++) {
                    // Check if all four corners have the same digit
                    if (data[i].charAt(j) == data[i].charAt(j + s) &&
                        data[i].charAt(j) == data[i + s].charAt(j) &&
                        data[i].charAt(j) == data[i + s].charAt(j + s)) {
                        maxSize = Math.max(maxSize, s + 1);
                    }
                }
            }
        }

        return maxSize * maxSize; // Return the area of the largest square
    }
}

