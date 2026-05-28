import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChallengeStream {
    public static void main(String[] args) {

        List<Integer> salaries = Arrays.asList(100, 200, 500, 400, 500, 300);

        //Find the second-highest unique salary using Streams.

        int secondHighest = salaries.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
        //System.out.println(secondHighest);

        //Find Frequency of Each Number
        List<Integer> nums = Arrays.asList(1,2,3,2,4,1,5,2);
        Map<Integer,Long> pairs = nums.stream().collect(Collectors.groupingBy(n->n ,Collectors.counting()));
        //System.out.println(pairs);

        //Partition Even and Odd Numbers
        Map<Boolean,List<Integer>> evenOdd = nums.stream().distinct().collect(Collectors.groupingBy(
                n-> n%2==0,Collectors.toList()
        ));


        //Find Duplicate Elements
        List<Integer> nums2 = Arrays.asList(1,2,3,2,4,5,1,6);
        List<Integer> dups = nums2.stream().collect(Collectors.groupingBy(
                n -> n,Collectors.counting()
        )).entrySet().stream().filter(e-> e.getValue()>1)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        //using set
        Set<Integer> unique = new LinkedHashSet<>();
        dups = nums2.stream().filter(n-> ! unique.add(n)).toList();


        //Find Longest Consecutive Sequence Length


        //Count Occurrence of Each Character
        Map<String,Long> map = Arrays.stream("banana".split(""))
                .collect(Collectors.groupingBy(n->n ,Collectors.counting()));

        //Find First Non-Repeated Character
        String first = Arrays.stream("stress".split(""))
                .collect(Collectors.groupingBy(
                Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
        ))
                .entrySet()
                .stream()
                .filter(e->e.getValue()==1)
                .map(Map.Entry::getKey).limit(1).findFirst().orElse("non");



        //Group Words by Length
        List<String> words = Arrays.asList("java","spring","api","microservice");

        Map<Integer, List<String>> map1 = words.stream().distinct().collect(Collectors.groupingBy(
                String::length
        ));
        System.out.println(map1);


        //Find Top 3 Most Frequent Words
        List<String> words2 = Arrays.asList(
                "java","spring","java","kafka","spring","java"
        );
        LinkedHashMap<String, Long> map2 = words2.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println(map2);
    }

}
