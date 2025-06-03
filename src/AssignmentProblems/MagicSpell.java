package AssignmentProblems;

public class MagicSpell {
    public String fixTheSpell(String spell) {
        char[] chars = spell.toCharArray();
        char[] az = new char[chars.length];
        int azCount = 0;

        for (char c : chars) {
            if (c == 'A' || c == 'Z') {
                az[azCount] = c;
                azCount++;
            }
        }

        int index = azCount - 1; // Start from end
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'A' || chars[i] == 'Z') {
                chars[i] = az[index];
                index--;
            }
        }

        return new String(chars);
    }
}

