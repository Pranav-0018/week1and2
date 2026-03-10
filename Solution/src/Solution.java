import java.util.*;

public class Solution{

    static HashMap<String, String> shortToLong = new HashMap<>();
    static HashMap<String, Integer> clickCount = new HashMap<>();

    static String shortenURL(String longURL) {

        String shortURL = "url" + shortToLong.size();

        shortToLong.put(shortURL, longURL);
        clickCount.put(shortURL, 0);

        return shortURL;
    }

    static String redirect(String shortURL) {

        if (shortToLong.containsKey(shortURL)) {

            clickCount.put(shortURL, clickCount.get(shortURL) + 1);

            return shortToLong.get(shortURL);
        }

        return "URL not found";
    }

    static void printStats() {

        for (String key : clickCount.keySet()) {
            System.out.println(key + " -> " + clickCount.get(key) + " clicks");
        }
    }

    public static void main(String[] args) {

        String shortURL = shortenURL("https://google.com");

        System.out.println("Short URL: " + shortURL);

        System.out.println(redirect(shortURL));
        System.out.println(redirect(shortURL));

        printStats();
    }
}