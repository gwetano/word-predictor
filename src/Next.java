import java.util.TreeMap;

public class Next {
    private TreeMap<String, Integer> next;

    public Next(String nextWord, int count) {
        next = new TreeMap<>();
        next.put(nextWord, count);
    }

    public TreeMap<String, Integer> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return " NextWords " + next + "\n";
    }
}
