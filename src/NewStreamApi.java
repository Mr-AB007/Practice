import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NewStreamApi {

    public static void main(String[] args) {
        //Q1. From a list of integers, count how many numbers are greater than 5.

        List<Integer> nums = List.of(7,9,0,64,3,9,0,1,1);
        long count = nums.stream().filter(n -> n > 5).count(); // 4

        //Q2 Convert a list of strings to their uppercase equivalents and collect into a new list.

        List<String> strings = Arrays.asList("Anubhav", "is","great");
        List<String> upperStrings = strings.stream().map(String::toUpperCase).collect(Collectors.toList());

        //Q3. From a list of names, return only those that start with 'A', converted to lowercase.
        List<String> startWithA = strings.stream().map(String::toLowerCase)
                .filter(lowerCase -> lowerCase.startsWith("a")).toList();

        //Q4. Given a list of integers with duplicates, return a sorted list of unique values.
        List<Integer> uniqueSorted = nums.stream().distinct().sorted().toList();

        List<Employee> employees = Arrays.asList(
                new Employee("Amit", 120000, 25, "IT",
                        Arrays.asList("Java", "Spring", "AWS")),

                new Employee("Riya", 90000, 22, "HR",
                        Arrays.asList("Communication", "Recruitment", "Excel")),

                new Employee("John", 105000, 28, "IT",
                        Arrays.asList("Java", "Microservices", "Docker")),

                new Employee("Sara", 70000, 30, "Finance",
                        Arrays.asList("Excel", "Accounting", "Taxation")),

                new Employee("Raj", 100000, 18, "IT",
                        Arrays.asList("Java", "Spring", "Docker")),

                new Employee("Neha", 130000, 28, "HR",
                        Arrays.asList("Recruitment", "Management", "Excel"))

        );


        //Q5. Check if any employee earns more than 100k, and if all employees are older than 18.

        boolean istrue = employees.stream().allMatch(e ->  e.getAge() > 18 );
        istrue = employees.stream().anyMatch(e -> e.getSalary() > 100000);

        //Q6. Find the first employee from the IT department, return as Optional.

        Optional<Employee> employee = employees.stream().filter(e -> e.getDepartment().equals("IT")).findFirst();

        String name = employee.map(Employee::getName).orElseThrow();

        //Q7. Calculate the total salary of all employees using reduce.

        long totalSal = employees.stream().map(Employee::getSalary).reduce(0, Integer::sum);
        totalSal = employees.stream().mapToInt(Employee::getSalary).sum(); //without mapToInt sum() will not work

        //Q8. Find the top 3 highest-paid employees and return only their names.

        List<String> highestPaid = employees.stream().sorted((e ,e1) -> e1.getSalary() - e.getSalary())
                                                        .limit(3).map(Employee::getName).toList();
        //coding best practice approach
        highestPaid = employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                        .limit(3).map(Employee::getName).toList();

        // Find the 4th highest-paid employees and return only name

        String highest = employees.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .skip(3).findFirst().map(Employee::getName).orElse("No result");

        //Q9. Group employees by department and count the number of employees in each department.
        Map<String, Long> byDepartment = employees.stream()
                                                    .collect(
                                                            Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        // Also: group and get names list per dept

        Map<String,List<String> > namesBydep = employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));


        //Q10. Find the highest-paid employee in each department.
        Map<String, Optional<Employee>> highPaidEmp = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))
        ));
        // if you dont want return Optional and Directly get name
        Map<String,String> hihgEmpoyeByDep = employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment
                                ,Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))
                                        , opt -> opt.map(Employee::getName).orElse("NO Result Error")
                                        )
                                ));

        //Q11. Extract all unique skills from a list of employees where each employee has a List of skills.

        List<String> uniqueSkill = employees.stream()
                                            .flatMap(e-> e.getSkills().stream())
                                                    .distinct().toList();

        // Count employees per skill mean how many employees not java
        Map<String,Long> empCountBySkills = employees.stream().flatMap(
                e -> e.getSkills().stream().map(skill -> Map.entry(skill,employee))
        ).collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.counting()) );

        //Q12. Convert a list of employees into a Map of name to salary. Handle duplicate names.
        Map<String, Integer> empSalary = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName
                        ,Employee::getSalary));

        // With merge function for duplicate keys
        //added duplicate employee with differ salary

        List<Employee> employees2 = Arrays.asList(
                new Employee("Amit", 120000, 25, "IT",
                        Arrays.asList("Java", "Spring", "AWS")),
                new Employee("Neha", 130000, 28, "HR",
                        Arrays.asList("Recruitment", "Management", "Excel")),
                new Employee("Amit", 190000, 25, "IT",
                        Arrays.asList("Java", "Spring", "AWS"))

        );
        Map<String, Integer> empSalary2 = employees2.stream().collect(Collectors.toMap(
                Employee::getName
                ,Employee::getSalary
                ,(OldSalary,NewSalary) -> NewSalary //if duplicate key then keep new salry
        ));

        //Max Salary per department
        Map<String,Integer> maxSalByDep = employees2.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))
                        ,op -> op.map(Employee::getSalary).orElse(0)
        )));

        System.out.println(maxSalByDep);
    }
}
