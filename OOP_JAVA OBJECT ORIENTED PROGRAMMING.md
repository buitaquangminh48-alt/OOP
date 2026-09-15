# ☕ OOP in Java: From Basics to Advanced

This documentation provides a comprehensive guide to Object-Oriented Programming (OOP) in **Java**. It is formatted in clean Markdown with detailed syntax highlighting for easy reading and repository storage on GitHub.

---

## 📦 Chapter 1: Basic Structure (Attributes and Methods)

In Java, everything is encapsulated within classes. A class serves as a blueprint defining:

* **Attributes (Fields)**: Store state data of an object.
* **Methods**: Functions defining actions and behaviors executed by instances.

```java
public class Robot {
    // 1. Attributes
    String name;
    int battery;

    // Constructor
    public Robot(String name, int battery) {
        this.name = name;
        this.battery = battery;
    }

    // 2. Instance Method
    public void introduce() {
        System.out.println("I am " + this.name + ", remaining battery: " + this.battery + "%");
    }

    public static void main(String[] args) {
        Robot r1 = new Robot("T-800", 100);
        r1.introduce(); // Output: I am T-800, remaining battery: 100%
    }
}

```

---

## 🛠️ Chapter 2: Constructors and Automatic Memory Management (Garbage Collector)

Java manages memory automatically via the **Garbage Collector (GC)** on the JVM heap.

* **Constructors**: Invoked using the `new` keyword to allocate memory and initialize objects. Java supports constructor overloading.
* **Memory Management**: Unlike C++, Java has no explicit destructors. The GC reclaims unreachable objects automatically when reference counters drop to zero.

```java
public class Robot {
    private String name;

    // Default Constructor
    public Robot() {
        this.name = "Default Bot";
    }

    // Overloaded Constructor
    public Robot(String name) {
        this.name = name;
    }

    public void showName() {
        System.out.println("Robot Name: " + this.name);
    }

    public static void main(String[] args) {
        Robot r1 = new Robot();
        Robot r2 = new Robot("T-1000");

        r1.showName(); // Output: Robot Name: Default Bot
        r2.showName(); // Output: Robot Name: T-1000

        // Explicitly dereferencing marks the object eligible for GC
        r1 = null;
        System.gc(); // Suggests running Garbage Collection
    }
}

```

---

## 🔒 Chapter 3: Encapsulation & Access Modifiers

Java strictly enforces data hiding through four access modifier levels:

| Modifier | Class | Package | Subclass | World |
| --- | --- | --- | --- | --- |
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(Default / Package-Private)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

```java
public class Robot {
    // Private attribute restricted to internal access
    private int battery;

    public Robot(int battery) {
        setBattery(battery);
    }

    // Getter
    public int getBattery() {
        return this.battery;
    }

    // Setter with input validation
    public void setBattery(int battery) {
        if (battery >= 0 && battery <= 100) {
            this.battery = battery;
        } else {
            System.out.println("Invalid battery level!");
        }
    }

    public static void main(String[] args) {
        Robot r = new Robot(50);
        r.setBattery(85);
        System.out.println("Battery: " + r.getBattery()); // Output: Battery: 85
    }
}

```

---

## 👥 Chapter 4: Static Members & Utility Methods

The `static` keyword binds fields and methods to the **Class level** rather than individual object instances.

1. **Static Fields**: Shared across all instances of a class.
2. **Static Methods**: Can be invoked directly using `ClassName.method()` without creating an instance. Cannot access instance members (`this`).

```java
public class Robot {
    private String name;
    public static int totalRobots = 0; // Shared counter

    public Robot(String name) {
        this.name = name;
        Robot.totalRobots++;
    }

    public static void showTotal() {
        System.out.println("Total robots created: " + Robot.totalRobots);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static void main(String[] args) {
        Robot r1 = new Robot("Alpha");
        Robot r2 = new Robot("Beta");

        Robot.showTotal(); // Output: Total robots created: 2
        System.out.println(Robot.isValidName("T-800")); // Output: true
    }
}

```

---

## 🧬 Chapter 5: Single Inheritance & The `super` Keyword

Java uses `extends` for single inheritance. Subclasses inherit non-private members of the superclass and invoke superclass constructors using `super()`.

```java
class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println("Vehicle engine started.");
    }
}

class Motorcycle extends Vehicle {
    private int displacement;

    public Motorcycle(String brand, int displacement) {
        super(brand); // Call superclass constructor
        this.displacement = displacement;
    }

    public void revEngine() {
        System.out.println(this.brand + " " + this.displacement + "cc revs loudly!");
    }

    public static void main(String[] args) {
        Motorcycle mc = new Motorcycle("Yamaha", 155);
        mc.start();     // Inherited method
        mc.revEngine(); // Subclass method
    }
}

```

---

## 🔀 Chapter 6: Multiple Interfaces & The `final` Modifier

Java **prohibits direct multiple inheritance of classes** to prevent diamond inheritance ambiguity. Instead, it achieves multiple inheritance through **Interfaces**.

* **`final` Keyword**:
* `final` variable: Constant value (cannot be reassigned).
* `final` method: Prevents overriding in subclasses.
* `final` class: Prevents inheritance completely.



```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// Class implementing multiple interfaces
class AmphibiousDrone implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Drone flying in the air.");
    }

    @Override
    public void swim() {
        System.out.println("Drone navigating underwater.");
    }

    public static void main(String[] args) {
        AmphibiousDrone drone = new AmphibiousDrone();
        drone.fly();
        drone.swim();
    }
}

```

---

## 🎭 Chapter 7: Polymorphism & Method Overriding (`@Override`)

Polymorphism allows interface reference types to hold concrete subclass instances. Runtime method invocation relies on dynamic dispatch via `@Override`.

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof Woof!");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        // Polymorphic references
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myDog.makeSound(); // Output: Woof Woof!
        myCat.makeSound(); // Output: Meow Meow!
    }
}

```

---

## 🎭 Chapter 8: Abstract Classes vs. Interfaces

Java provides two mechanisms for structural abstraction:

1. **Abstract Class (`abstract`)**: Can contain concrete methods, fields, and constructors. Supports single inheritance (`extends`).
2. **Interface (`interface`)**: Defines pure abstract API contracts (supports `default` methods since Java 8). Supports multiple implementation (`implements`).

```java
// Abstract Class
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method must be implemented by concrete subclasses
    public abstract double calculateArea();
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Shape circle = new Circle("Red", 5.0);
        System.out.println("Area: " + circle.calculateArea()); // Output: Area: 78.5398...
    }
}

```

---

## 🪄 Chapter 9: Exception Handling & Custom Exceptions

Java enforces explicit error handling through **Checked** and **Unchecked** exceptions using `try-catch-finally` blocks and `throws` declarations.

```java
class InvalidBatteryException extends Exception {
    public InvalidBatteryException(String message) {
        super(message);
    }
}

public class Robot {
    private int battery;

    public void setBattery(int battery) throws InvalidBatteryException {
        if (battery < 0 || battery > 100) {
            throw new InvalidBatteryException("Battery percentage must be between 0 and 100.");
        }
        this.battery = battery;
    }

    public static void main(String[] args) {
        Robot bot = new Robot();
        try {
            bot.setBattery(150);
        } catch (InvalidBatteryException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Execution completed.");
        }
    }
}

```

---

## ⚙️ Chapter 10: Generics & Records (Modern Java)

Modern Java includes **Generics** for type safety and **Records** (introduced in Java 14+) to eliminate boilerplate code for data-carrier classes.

```java
// Generic Wrapper Class
class Response<T> {
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}

// Java Record (Immutable Data Carrier)
record Product(int id, String name, double price) {}

public class Main {
    public static void main(String[] args) {
        // Using Generics
        Response<String> strResponse = new Response<>("Success");
        System.out.println("Response: " + strResponse.getData());

        // Using Records
        Product p1 = new Product(1, "Laptop", 1200.0);
        System.out.println(p1); // Automatic toString() generation
        System.out.println("Product Name: " + p1.name()); // Auto-generated accessor
    }
}

```