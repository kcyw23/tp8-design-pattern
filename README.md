# TP8 - Design Patterns

## Objectif

Implémenter les patterns **Repository**, **Command** et **Strategy** dans une mini-application de gestion de notes.

---
### Étape 1 - Repository

Le modèle `Student` est déjà fourni dans `model/Student.java`.

**1.1** Créez l'interface `StudentRepository` dans un nouveau package `repository` :

```java
public interface StudentRepository {
    void save(Student student);
    List<Student> findAll();
    Optional<Student> findById(String id);
    void delete(String id);
}
```

**1.2** Créez la classe `InMemoryStudentRepository` qui implémente cette interface en utilisant une `ArrayList`.

> Pour `save` : vérifiez si un étudiant avec le même id existe déjà. Si oui, remplacez-le. Sinon, ajoutez-le.

**1.3** Créez la classe `StudentService` dans un nouveau package `service` :

```java
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String id, String name, double grade) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (grade < 0 || grade > 20) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        repository.save(new Student(id, name, grade));
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void deleteStudent(String id) {
        repository.delete(id);
    }
}
```
**1.4** Testez dans `Main.java` :
```java
StudentRepository repo = new InMemoryStudentRepository();
StudentService service = new StudentService(repo);
service.addStudent("1", "Alice", 15.5);
service.addStudent("2", "Bob", 12.0);
System.out.println(service.getAllStudents());
```
---

### Étape 2 - Command

**2.1** Créez l'interface `Command` dans un nouveau package `command` :

```java
public interface Command {
    void execute();
    String getName();
    String getDescription();
}
```

**2.2** Créez la classe `AddStudentCommand` :

```java
public class AddStudentCommand implements Command {
    private StudentService service;
    private Scanner scanner;

    public AddStudentCommand(StudentService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Grade (0-20): ");
        double grade = Double.parseDouble(scanner.nextLine());
        try {
            service.addStudent(id, name, grade);
            System.out.println("Student added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getName() { return "add"; }

    @Override
    public String getDescription() { return "Add a new student"; }
}
```

**2.3** Créez de la même manière :
- `ListStudentsCommand` - affiche tous les étudiants
- `DeleteStudentCommand` - supprime par ID
- `ExitCommand` - quitte l'application (`System.exit(0)`)

**2.4** Créez la classe `CLI` dans le package `command` :

```java
public class CLI {
    private Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command cmd) {
        commands.put(cmd.getName(), cmd);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student Manager ===");
            commands.values().forEach(c ->
                System.out.println("  " + c.getName() + " - " + c.getDescription()));
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            Command cmd = commands.get(input);
            if (cmd != null) {
                cmd.execute();
            } else {
                System.out.println("Unknown command. Try again.");
            }
        }
    }
}
```

**2.5** Assemblez le tout dans `Main.java` :

```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentRepository repo = new InMemoryStudentRepository();
    StudentService service = new StudentService(repo);

    CLI cli = new CLI();
    cli.register(new AddStudentCommand(service, scanner));
    cli.register(new ListStudentsCommand(service));
    cli.register(new DeleteStudentCommand(service, scanner));
    cli.register(new ExitCommand());
    cli.run();
}
```
---

### Étape 3 — Strategy

On veut trier les étudiants selon différents critères.

**3.1** Créez l'interface `SortStrategy` dans le package `strategy` :

```java
public interface SortStrategy {
    List<Student> sort(List<Student> students);
}
```

**3.2** Créez deux implémentations :
- `SortByName` - tri alphabétique par nom
- `SortByGrade` - tri par note décroissante

**3.3** Ajoutez une méthode dans `StudentService` :

```java
public List<Student> getSortedStudents(SortStrategy strategy) {
    return strategy.sort(repository.findAll());
}
```

**3.4** Créez un `ListSortedStudentsCommand` qui demande le critère de tri à l'utilisateur :

```java
@Override
public void execute() {
    System.out.println("Sort by: 1) Name  2) Grade");
    String choice = scanner.nextLine();

    SortStrategy strategy;
    if (choice.equals("1")) {
        strategy = new SortByName();
    } else {
        strategy = new SortByGrade();
    }

    List<Student> sorted = service.getSortedStudents(strategy);
    sorted.forEach(System.out::println);
}
```
**3.5** Enregistrez cette nouvelle commande dans la CLI.

---
### Application au projet

Vous devriez avoir dans votre projet :

- Au moins un **Repository** avec une interface et une implémentation InMemory
- Un **Service** qui utilise le repository par injection
- Une **CLI** structurée avec le pattern **Command**
- Un troisième pattern adapté à votre sujet

> Ne forcez pas un pattern. Si quelque chose ne se justifie pas dans votre code, ne le mettez pas.
---
### Ressources
- [Refactoring Guru](https://refactoring.guru) - Explications visuelles de chaque pattern
- Slides du CM 8
