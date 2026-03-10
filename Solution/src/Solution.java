import java.util.*;

public class Solution{

    static HashMap<String, Set<Integer>> ngramTable = new HashMap<>();

    static void storeDocument(int id, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i < words.length - 2; i++) {

            String gram = words[i] + " " + words[i + 1] + " " + words[i + 2];

            ngramTable.putIfAbsent(gram, new HashSet<>());
            ngramTable.get(gram).add(id);
        }
    }

    static void findMatches(String text) {

        String[] words = text.split(" ");

        for (int i = 0; i < words.length - 2; i++) {

            String gram = words[i] + " " + words[i + 1] + " " + words[i + 2];

            if (ngramTable.containsKey(gram)) {

                for (int id : ngramTable.get(gram)) {
                    System.out.println("Match found with document " + id);
                }
            }
        }
    }

    public static void main(String[] args) {

        storeDocument(1, "data structures and algorithms are important");
        storeDocument(2, "machine learning and data structures");

        findMatches("data structures and algorithms");
    }
}
