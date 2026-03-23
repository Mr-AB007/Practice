import java.util.List;

class Employee {
    private String name;
    private int salary;
    private int age;
    private String department;
    private List<String> skills;

    public Employee(String name, int salary, int age, String department, List<String> skills) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.department = department;
        this.skills = skills;
    }

    public String getName() { return name; }
    public int getSalary() { return salary; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public List<String> getSkills() { return skills; }
}