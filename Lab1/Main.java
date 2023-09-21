import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tap 1 for Caesar and 2 for Caesar&Brute: ");
        int i = scanner.nextInt();
        if (i == 1) {
            System.out.print("Tap 1 for encode and 2 to decode: ");
            int i1 = scanner.nextInt();
            System.out.println();
            if (i1 == 1) {
                System.out.print("Introduce word to encode: ");
                String next = scanner.next();
                System.out.print("Introduce key to encode: ");
                int i2 = scanner.nextInt();
                cesarEncryption(next, i2);
            } else {
                System.out.print("Introduce word to decode: ");
                String next2 = scanner.next();
                System.out.println("Introduce key to decode: ");
                int i3 = scanner.nextInt();
                cesarDecryption(next2, i3);
            }
        } else {
            System.out.print("Tap 1 for encode and 2 to decode: ");
            int i1 = scanner.nextInt();
            if (i1 == 1) {
                System.out.print("Introduce word to encode: ");
                String next = scanner.next();
                System.out.print("Introduce key to encode: ");
                int i2 = scanner.nextInt();
                System.out.print("Introduce kew-word to encode: ");
                String next1 = scanner.next();
                cesarBrutEncryption(next, i2, next1);
            } else {
                System.out.print("Introduce word to decode: ");
                String next2 = scanner.next();
                System.out.print("Introduce key to decode: ");
                int i3 = scanner.nextInt();
                System.out.print("Introduce key-word to decode: ");
                String next3 = scanner.next();
                cesarBrutDecryption(next2, i3, next3);
            }
        }
        System.out.println();
        scanner.close();
    }

    private static List<Character> getAlphabetList() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Character> alphabet = new ArrayList<>();
        for (char c : str.toCharArray()) {
            alphabet.add(c);
        }
        return alphabet;
    }

    public static void cesarEncryption(String word, int key) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        alphabetList.forEach(System.out::print);
        System.out.println();
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i + key) >= alphabetList.size()) {
                return alphabetList.get(indexOfElement(alphabetList, character) - 26 + key);
            }
            return alphabetList.get(indexOfElement(alphabetList, character) + key);
        }).forEach(System.out::print);
    }

    public static void cesarDecryption(String word, int key) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        alphabetList.forEach(System.out::print);
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i - key) < 0) {
                return alphabetList.get(i + 26 - key);
            }
            return alphabetList.get(i - key);
        }).forEach(System.out::print);
    }

    public static void cesarBrutEncryption(String word, int key, String keyWord) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        List<Character> keyWordList = new ArrayList<>();
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordList.add(ch);
        }
        List<Character> keyWordRemove = new ArrayList<>();
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordRemove.add(ch);
        }
        keyWordRemove.forEach(alphabetList::remove);
        alphabetList.addAll(0, keyWordRemove.stream().distinct().toList());
        alphabetList.forEach(System.out::print);
        System.out.println();
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i + key) >= alphabetList.size()) {
                return alphabetList.get(indexOfElement(alphabetList, character) - 26 + key);
            }
            return alphabetList.get(indexOfElement(alphabetList, character) + key);
        }).forEach(System.out::print);
    }

    public static void cesarBrutDecryption(String word, int key, String keyWord) {
        keyVerification(key);
        List<Character> chars = new ArrayList<>();
        for (char ch : word.toUpperCase().replaceAll(" ", "").toCharArray()) {
            chars.add(ch);
        }
        List<Character> alphabetList = getAlphabetList();
        List<Character> keyWordList = new ArrayList<>();
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordList.add(ch);
        }
        List<Character> keyWordRemove = new ArrayList<>();
        for (char ch : keyWord.toUpperCase().replaceAll(" ", "").toCharArray()) {
            keyWordRemove.add(ch);
        }
        keyWordRemove.forEach(alphabetList::remove);
        alphabetList.addAll(0, keyWordRemove.stream().distinct().toList());
        alphabetList.forEach(System.out::print);
        System.out.println();
        chars.stream().map(character -> {
            int i = indexOfElement(alphabetList, character);
            if ((i - key) < 0) {
                return alphabetList.get(i + 26 - key);
            }
            return alphabetList.get(i - key);
        }).forEach(System.out::print);
    }

    private static void keyVerification(int key) {
        if ((key > 25) || (key <= 0)) {
            throw new NoSuchElementException("Introduce 0 < K < 26");
        }
    }

    public static int indexOfElement(List<Character> characterList, Character elementToFind) {
        int index = 0;
        for (Character element : characterList) {
            if (element.equals(elementToFind)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
