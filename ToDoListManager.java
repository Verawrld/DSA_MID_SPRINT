class Task {
    String description;
    Boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return description + " [" + (isCompleted ? "Completed" : "Pending") + "]";
    }
}

class TaskList {
    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;

    void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    void markTaskAsCompleted(String description) {
        Node current = head;
        while (current != null) {
            if (current.task.description.equals(description)) {
                current.task.markAsCompleted();
                break;
            }
            current = current.next;
        }
    }

    void printTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

}

class User {
    String name;
    TaskList taskList;

    User(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    void addTask(String description) {
        taskList.addTask(new Task(description));
    }

    void markTaskAsCompleted(String description) {
        taskList.markTaskAsCompleted(description);
    }

    void printTasks() {
        System.out.println("Tasks for " + name);
        taskList.printTasks();
    }
}

public class ToDoListManager {
    private static final int MAX_USERS = 10;
    private User[] users;
    private int userCount;

    public ToDoListManager() {
        users = new User[MAX_USERS];
        userCount = 0;
    }

    void addUser(String name) {
        if (userCount < MAX_USERS) {
            if (getUser(name) == null) {
                users[userCount++] = new User(name);
            } else {
                System.out.println("User with this name already exists");
            }
        } else {
            System.out.println("Limit for users reached");
        }
    }

    User getUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        ToDoListManager manager = new ToDoListManager();
        manager.addUser("Alice");
        manager.addUser("Bob");

        User alice = manager.getUser("Alice");
        if (alice != null) {
            alice.addTask("Buy groceries");
            alice.addTask("Pay bills");
            alice.markTaskAsCompleted("Buy groceries");
        }

        User bob = manager.getUser("Bob");
        if (bob != null) {
            bob.addTask("Finish homework");
            bob.addTask("Clean room");
            bob.markTaskAsCompleted("Finish homework");
        }

        manager.addUser("Charlie");
        User charlie = manager.getUser("Charlie");
        if (charlie != null) {
            charlie.addTask("Read a book");
            charlie.addTask("Go for a walk");
        }

        // Print tasks for all users in an organized manner
        System.out.println("===== To-Do List Manager =====");
        for (int i = 0; i < manager.userCount; i++) {
            System.out.println("------------------------------");
            manager.users[i].printTasks();
            System.out.println("------------------------------");
        }
        System.out.println("===== End of To-Do Lists =====");
    }
}