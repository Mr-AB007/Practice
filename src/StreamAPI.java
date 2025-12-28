import java.util.*;
import java.util.stream.Collectors;

public class StreamAPI {
    public static void main(String[] args){
        List<Integer> numbers = List.of(1,4,5,6,78,9,4,3);

        // Given a list of integers, filter the even numbers.

        List<Integer> evens = numbers.stream().filter(x-> x%2==0).toList();

        //Find the maximum number from a list of integers.

        int max = numbers.stream().max(Integer::compare).orElseThrow()  ;

        /**
         * 3. Sort a list of integers in descending order.
         **/
        List<Integer> sorted = numbers.stream().sorted(Comparator.reverseOrder()).toList();

        /**
         * 4. Count strings starting with a specific prefix, e.g., “A”
         */
        List<String> names= List.of("Anubhav", "AB", "Ram","John");

        List<String> a_names= names.stream().filter(x -> x.startsWith("A")).toList(); //Output: [Anubhav, AB]

        /**
         * 5. Find First Non-Repeated Character in a String
         */
        String input = "swiss";
        char c1 = input.chars().mapToObj(x->(char) x)
                       .filter(c-> input.indexOf(c) == input.lastIndexOf(c)).findFirst().orElseThrow(); // Output: w

        /**
         * 6. Convert all strings in a list to uppercase.
         */

        List<String> upper_names = names.stream().map(String::toUpperCase).toList(); //Output [ANUBHAV, AB, RAM, JOHN]

        /**
         * 7. Sum of Numbers in a List
         */
        int sum = numbers.stream().reduce(Integer::sum).get();  //Output: 110
        int sum2 = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        /**
         *  8. Check if Any String Matches a Condition e.g "AB"
         */
        List<String> strings = List.of("Java", "Stream API", "Lambda");
        boolean match = strings.stream().anyMatch(x-> x.contains("API")); //Output: true


        /**
         *  9. Identify duplicate elements in a list
         */

        List<Integer> numbers2 = List.of(1, 2, 3, 4, 2, 5, 1);

        //using SET
        Set<Integer> unique = new HashSet<>();
        Set<Integer> duplicates = numbers2.stream().filter(n -> !unique.add(n) ).collect(Collectors.toSet());

        //using groupingBy
        List<Integer> duplca = numbers2.stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        //using indexOf
        Set<Integer> duplicates2 = numbers2.stream()
                .filter(n -> numbers2.indexOf(n) != numbers2.lastIndexOf(n)) // appears more than once
                .distinct()
                .collect(Collectors.toSet());
        System.out.println(duplicates2);


    }
}
