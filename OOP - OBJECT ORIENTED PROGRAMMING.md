# 📦 Chapter 1: Basic Structure (Attributes and Behaviors)

A class always consists of two main components:

- **Attributes (Variables)**: Data and characteristics of the object.
- **Behaviors (Methods/Functions)**: Functions that define actions and operations for the object.

```cpp
#include <iostream>
#include <string>

class Robot {
public: // Temporarily kept public so external code can access members
    // 1. Attributes
    std::string name;
    int battery;

    // 2. Behaviors
    void introduce() {
        std::cout << "I am " << name << ", battery remaining: " << battery << "%\n";
    }
};

int main() {
    Robot r1; // Create object r1 from the Robot class blueprint
    r1.name = "T-800"; // Assign data
    r1.battery = 100;
    r1.introduce(); // Output: "I am T-800, battery remaining: 100%"
}

```

---

## 🛠️ Chapter 2: Constructor and Destructor

When you write `Robot r1;`, how does it automatically load a name and battery level upon creation? That is the job of a **Constructor**. When the object is removed from memory, the **Destructor** runs automatically to clean up resources.

```cpp
#include <iostream>
#include <string>

class Robot {
public:
    std::string name;

    // CONSTRUCTOR: Shares the same name as the Class, no return type, runs automatically upon object creation
    Robot(std::string newName) {
        name = newName;
        std::cout << name << " has been powered on!\n";
    }

    // DESTRUCTOR: Prefixed with ~, runs automatically when the object is destroyed (scope ends)
    ~Robot() {
        std::cout << name << " has been powered off and memory freed!\n";
    }
};

```

---

## 🔒 Chapter 3: Encapsulation & The `this` Pointer

In production code, external code should not directly modify attributes (e.g., setting `r1.battery = -999` is invalid). You encapsulate attributes under `private` access and provide Getters (to read) and Setters (to update and validate data).

* **The `this` Pointer**: Points directly to the current object instance, commonly used to distinguish member variables from parameters with identical names.

```cpp
class Robot {
private: // Hide internal data
    int battery;

public:
    Robot(int battery) {
        this->battery = battery; // this->battery refers to the private member variable
    }

    // SETTER: Allows modifying battery with input validation
    void setBattery(int newBattery) {
        if (newBattery >= 0 && newBattery <= 100) {
            this->battery = newBattery;
        }
    }

    // GETTER: Provides read-only access to private data
    int getBattery() const {
        return this->battery;
    }
};

```

---

## 👥 Chapter 4: The `static` Keyword (Shared Class Members)

Each robot instance holds its own name and battery level. However, to track the **total number of robots** globally across all instances, you need a shared class-level variable defined with `static`.

```cpp
#include <iostream>

class Robot {
public:
    static int totalRobots; // static variable: belongs to the class itself, shared by all instances

    Robot() {
        totalRobots++; // Increment global counter on instance creation
    }
};

// Definition and initialization outside the class declaration (Required for standard C++)
int Robot::totalRobots = 0;

int main() {
    Robot a;
    Robot b;
    std::cout << Robot::totalRobots; // Outputs 2 (Accessed directly via ClassName::)
}

```

---

## 🤝 Chapter 5: Friend Functions and Operator Overloading

* **`friend`**: Grants an external function or class full access to `private` and `protected` members of the host class.
* **Operator Overloading**: By default, C++ cannot add two custom objects directly (`r1 + r2`). Operator overloading defines custom behavior for built-in operators.

```cpp
#include <iostream>

class Robot {
private:
    int powerLevel = 50;

public:
    // Declare doctorCheck as a friend function
    friend void doctorCheck(Robot r);

    // Overload binary '+' operator to combine power levels of two robots
    int operator+(const Robot& other) {
        return this->powerLevel + other.powerLevel;
    }
};

void doctorCheck(Robot r) {
    // Accesses private member 'powerLevel' without compilation errors due to friend declaration
    std::cout << "Robot power level: " << r.powerLevel;
}

```

---

## 🧬 Chapter 6: Inheritance Access Specifiers (public, protected, private)

In C++, selecting `public`, `protected`, or `private` inheritance determines member accessibility for derived classes and external scopes.

Access levels in the base class:

* **`public`**: Accessible from anywhere outside the class.
* **`protected`**: Accessible inside the base class and derived classes.
* **`private`**: Accessible only inside the base class.

---

### 1. Public Inheritance - "IS-A" Relationship

The most common inheritance mode. It preserves base class member access levels for derived classes.

**Access Rules:**

* Base `public` → becomes `public` in Derived.
* Base `protected` → becomes `protected` in Derived.
* Base `private` → **Inaccessible** directly by Derived.

**Practical Impact:**

* External callers (`main()`) can call public base methods via derived objects.
* Expresses a true **"IS-A"** relationship (e.g., `Motorcycle` IS-A `Vehicle`).

---

### 2. Private Inheritance - "IMPLEMENTED-IN-TERMS-OF" Relationship

Converts all `public` and `protected` base members into `private` members inside the derived class.

**Access Rules:**

* Base `public` → becomes `private` in Derived.
* Base `protected` → becomes `private` in Derived.
* Base `private` → **Inaccessible** directly by Derived.

**Practical Impact:**

* **External callers**: Blocked entirely from invoking base class methods via derived objects.
* **Subsequent Derived Classes (Grandchildren)**: Cannot access base members, as the direct child encapsulated them as `private`.
* Expresses an **"IMPLEMENTED-IN-TERMS-OF"** relationship (e.g., `StudentList` inherits privately from `Array` to reuse storage logic without exposing generic array methods).

#### Code Comparison Example

```cpp
#include <iostream>

class Base {
public:
    void printPublic() { std::cout << "Base Public\n"; }
protected:
    void printProtected() { std::cout << "Base Protected\n"; }
};

// ==================== PUBLIC INHERITANCE ====================
class PublicChild : public Base {
public:
    void test() {
        printPublic();    // VALID (remains public)
        printProtected(); // VALID (remains protected)
    }
};

// ==================== PRIVATE INHERITANCE ====================
class PrivateChild : private Base {
public:
    void test() {
        printPublic();    // VALID internally (now private inside PrivateChild)
        printProtected(); // VALID internally (now private inside PrivateChild)
    }
};

class Grandchild : public PrivateChild {
    void testGrandchild() {
        // printPublic(); // COMPILER ERROR! Hidden as private in PrivateChild.
    }
};

int main() {
    PublicChild pubObj;
    pubObj.printPublic(); // VALID! Accessible from main.

    PrivateChild privObj;
    // privObj.printPublic(); // COMPILER ERROR! printPublic is now private in PrivateChild.

    return 0;
}

```

---

### 3. Protected Inheritance - Intermediate Access Control

Restricts base `public` members down to `protected` in the derived class, acting as a gateway reserved exclusively for further inheritance.

**Access Rules:**

* Base `public` → downgraded to `protected` in Derived.
* Base `protected` → remains `protected` in Derived.
* Base `private` → **Inaccessible** directly by Derived.

**Practical Impact:**

* **External scope (`main`)**: Behaves like `private` inheritance; external invocation is blocked.
* **Derived Subclasses**: Unlike `private` inheritance, child classes of the derived class maintain access to inherited `protected` members.

#### Visual Code Example

```cpp
#include <iostream>

class Grandparent {
public:
    void legacy() { std::cout << "Grandparent Legacy\n"; }
};

// ==================== PROTECTED INHERITANCE ====================
class ProtectedParent : protected Grandparent {
    // legacy() is downgraded to PROTECTED level here
public:
    void testParent() {
        legacy(); // VALID: Parent class accesses protected legacy
    }
};

// ==================== SUBSEQUENT DERIVED CLASS ====================
class Child : public ProtectedParent {
public:
    void testChild() {
        legacy(); // VALID! legacy is protected in ProtectedParent, so Child retains access.
                  // (If ProtectedParent used private inheritance, this would fail)
    }
};

int main() {
    ProtectedParent parentObj;
    // parentObj.legacy(); 
    // COMPILER ERROR! Cannot access protected member from main.

    return 0;
}

```

---

### Inheritance Access Matrix

| Base Member Access | Public Inheritance | Protected Inheritance | Private Inheritance |
| --- | --- | --- | --- |
| **public** | → `public` | → **`protected`** | → `private` |
| **protected** | → `protected` | → **`protected`** | → `private` |
| **private** | Inaccessible | Inaccessible | Inaccessible |
| *Accessible in main()?* | **Yes** | **No** | **No** |
| *Accessible in Grandchild?* | **Yes** | **Yes** | **No** |

---

## 🎯 Chapter 7. Const Member Functions

Appending `const` to a member function declaration guarantees that the function will not modify any data members of the object.

```cpp
class Account {
private:
    int balance = 5000;

public:
    // const function guarantees read-only access to object state
    int getBalance() const {
        // balance = 0; // COMPILER ERROR! Cannot modify state in a const function.
        return balance;
    }
};

```

---

## ⚙️ Chapter 8. Constructor Initialization List

Instead of assigning values inside the constructor body with `=`, modern C++ uses **Member Initialization Lists** (syntax using `:` after constructor parameters).

* **Why use it?** Initializes members directly upon memory allocation, avoiding overhead from default construction followed by assignment. It is also required for initializing `const` attributes and reference (`&`) members.

```cpp
#include <string>

class Example {
private:
    const int id; // Constant class attribute
    std::string name;

public:
    // Idiomatic C++: Member Initialization List
    Example(int newId, std::string newName) : id(newId), name(newName) {
        // Empty body - initializations completed prior to body execution
    }
};

```

---

## 💾 Chapter 9. Resource Management: The Rule of Three

Classes managing raw dynamic memory on the Heap (`new`/`delete`) must implement the **Rule of Three** to prevent memory leaks, shallow copy bugs, and crashes.

1. **Destructor**: Deallocates heap memory via `delete` / `delete[]`.
2. **Copy Constructor**: Performs a Deep Copy when initializing a new object from an existing instance.
3. **Copy Assignment Operator**: Handles clean reallocation and deep copy during assignments (`objA = objB`).

```cpp
class RowArray {
private:
    int* ptr;

public:
    RowArray() { 
        ptr = new int[100]; // Allocation 
    }

    // 1. Destructor: Frees dynamically allocated memory
    ~RowArray() { 
        delete[] ptr; 
    }

    // 2. Copy Constructor: Allocates new memory and copies contents (Deep Copy)
    RowArray(const RowArray& source) {
        ptr = new int[100];
        for (int i = 0; i < 100; i++) {
            ptr[i] = source.ptr[i];
        }
    }
};

```

---

## 🪄 Chapter 10. Virtual Functions and Polymorphism

Without `virtual` functions, C++ resolves function calls at compile-time based on the **type of the pointer/reference**, rather than the **actual underlying object type** at runtime.

```cpp
#include <iostream>

class Animal {
public:
    virtual void makeSound() { 
        std::cout << "Generic animal sound...\n"; 
    }
};

class Cat : public Animal {
public:
    void makeSound() override { 
        std::cout << "Meow Meow!\n"; 
    } // Overrides base class virtual method
};

int main() {
    Animal* a = new Cat(); // Base pointer referencing a Derived instance
    a->makeSound(); // Output: "Meow Meow!" via dynamic dispatch (virtual mechanism)
                    // (Without 'virtual' in Base, it would print "Generic animal sound...")
    delete a;
}

```

```

```
