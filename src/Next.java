import java.util.TreeMap;

public class Next {
    String nextWord;
    int count;
    TreeMap<String, Integer> next;

    public Next(String nextWord, int count) {
        next = new TreeMap<>();
        this.nextWord = nextWord;
        this.count = count;
        next.put(nextWord, count);
    }

    @Override
    public String toString() {
        return " NextWords " + next + "\n";
    }
}
