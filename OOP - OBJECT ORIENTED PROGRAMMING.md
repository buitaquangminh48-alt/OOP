## 📦 Chapter 1: Basic Structure (Attributes and Behaviors)

A class commonly contains two major categories of members: data members and member functions.

- **Attributes (Variables)**: Data or characteristics of the object.

- **Behaviors (Methods/Functions)**: Processing functions or actions performed by the object.

#include <iostream>

#include <string>

class Robot {

public: // Set to public for now so external code can access it

// 1. Attributes

std::string ten;

int pin;

// 2. Behaviors

void gioiThieu() {

std::cout << "I am " << ten << ", battery remaining: " << pin <<

"%\n";

}

};

int main() {

Robot r1; // Create object r1 from the Robot blueprint

r1.ten = "T-800"; // Assign data

r1.pin = 100;

r1.gioiThieu(); // Call function: "I am T-800, battery remaining: 100%"

}

## 🛠️ Chapter 2: Constructor and Destructor

When you write `Robot r1;`, how do you automatically assign a name and battery level the moment it is created? That is the job of the **Constructor**. Conversely, when the object is removed from memory, the **Destructor** runs to clean up.

class Robot {

public:

std::string ;

// CONSTRUCTOR: Same name as the Class, no return type, runs automatically when the object is created

Robot(std::string name) {

name = newName;

std::cout << name << " has been powered on!\n"; }

_// DESTRUCTOR: Prefixed with ~, runs automatically when the object is destroyed (end of function/program)_

~Robot() {

std::cout << name << " has been powered down and deallocated!\n";

}

};

## 🔒 Chapter 3: Encapsulation & The 'this' Keyword

In practice, you never want outsiders arbitrarily modifying attributes (e.g., `r1.pin = -999` makes no sense). You must hide attributes as `private` and provide Getter functions (for reading) and Setter functions (for writing/validating data).

- **The 'this' keyword**: A pointer pointing directly to "the current robot instance itself," used to distinguish between a function parameter name and an attribute name.

class Robot {

private: _// Hides data internally_

int pin;

public:

Robot(int pin) {

this->pin = pin; _// this->pin is the private attribute, while pin is the passed parameter_

}

_// SETTER: Allows modifying pin but includes condition checks_

void setPin(int newPin) {

if (newPin >= 0 && newPin <= 100) {

this->pin = newPin;

}

}

_// GETTER: Allows viewing the pin level but not direct modification_

int getPin() {

return this->pin;

}

};

## 👥 Chapter 4: The 'static' Keyword (Shared Members)

Normally, each robot has its own name and battery level. But if you want to count the **total number of robots** active in the world, you need a variable that all objects share. That is what `static` is for.

class Robot {

public:

static int totalRobots; _// Static variable: belongs to the class itself, not to individual objects_

Robot() {

totalRobots++; _// Increment the total count whenever a robot is created_

}

};

_// Initialize the static variable (must be done outside the class)_

int Robot::totalRobots = 0;

int main() {

Robot a;

Robot b;

std::cout \<\< Robot::totalRobots; _// Prints 2 (Use ClassName:: to access directly)_

}

## 🤝 Chapter 5: Friends and Operator Overloading

- **friend**: Allows an external function or another class to directly access the private members of this class.

- **Operator Overloading**: Normally, you cannot add two objects together (e.g., r1 + r2). C++ allows you to define exactly what this operation does.

class Robot {

private:

int sucManh = 50;

public:

_// Declare the "Doctor" function as a friend of Robot_

friend void bacSiKiemTra(Robot r);

_// Define the addition operator (+): When two robots are added, sum their power levels_

int operator+(const Robot& khac) {

return this-\>sucManh + khac.sucManh;

}

};

void bacSiKiemTra(Robot r) {

_// Because it is a friend, this function can access the private variable 'sucManh' without an error!_

std::cout \<\< "Robot power: " \<\< r.sucManh;

}

🧬 Chapter 6: Public, private, and protected inheritance types

In C++, choosing public or private inheritance determines the **"perspective"** the outside world (and future derived classes) has regarding the members inherited from the parent class. To make it easy to understand, visualize the access levels in the parent class (Base) as follows:

- public: Visible to everyone.

- protected: Visible only to the "family" (Parent and Child).

- private: A strictly guarded secret belonging solely to the Parent; the Child cannot touch it.

Below are the detailed effects of each inheritance type:

## 1. Public Inheritance - "IS-A" Relationship

This is the most common type of inheritance. It preserves (or restricts, in the case of private members) the access levels of members as they pass from the parent class to the child class.

## Access conversion rules:

- Parent's public $\rightarrow$ becomes Child's public.

- Parent's protected $\rightarrow$ becomes Child's protected.

- Parent's private $\rightarrow$ Child **cannot** access directly.

## Practical impact:

- **main() function and external code**: Public functions of the parent class can be called via an object of the child class.

- **Design significance**: Represents an **"Is-A"** relationship (e.g., a Motorcycle _is a_ Vehicle).

## 2. Private Inheritance - The "IMPLEMENTED-IN-TERMS-OF" Relationship

This type of inheritance **converts all** public and protected members of the parent class
into private members of the child class.

## Access Conversion Rules:

- Parent's public members $\rightarrow$ become Child's private members.

- Parent's protected members $\rightarrow$ become Child's private members.

- Parent's private members $\rightarrow$ Child **cannot** access them directly.

## Practical Impact:

- **`main()` function and external code**: Completely blocked. You **cannot**
  call any parent class functions via a child class object anymore.

- **Grandchild class (inheriting from the child class)**: The grandchild class
  cannot access any members of the grandparent class either, because the child
  class has already made them private.

- **Design Significance**: Represents an **"implemented-in-terms-of"** relationship
  (the child class is built using the parent class's features), but the child class
  does not want the outside world to know it uses the parent class for implementation
  (e.g., a `StudentList` class inheriting privately from an `Array` class; you want
  users to call `AddStudent()` but not `DeleteArrayElement[0]`).

## Direct Code Comparison Example

#include <iostream>

class Parent {

public:

void PrintPublic() { std::cout << "Original Public\n"; }

protected:

void PrintProtected() { std::cout << "Original Protected\n"; }

};

// ==================== PUBLIC INHERITANCE ====================

class ChildPublic : public Parent {

public:

void Test() {

PrintPublic(); // VALID (remains public)

PrintProtected(); // VALID (remains protected)

}

}; _// ==================== PRIVATE INHERITANCE ====================_

class childPrivate : private {

public:

void experiment() {

PrintPublic(); _// VALID (internal access allowed; it has now become private to
child)_

PrintProtected(); _// VALID (internal access allowed; it has now become private
to child)_

}

};

class Child : public childPrivate {

void ExperimentChild() {

_// PrintPublic(); // ERROR! Because childPrivate changed it to private, the
Child class can no longer access it._

}

};

int main() {

childPublic objPublic;

objPublic.PrintPublic(); _// VALID! Called normally from main._

childPrivate objPrivate;

_// objPrivate.XuatPublic(); // COMPILATION ERROR! PrintPublic is now
private relative to objPrivate._

return 0;

}

3\. Protected Inheritance – The “IS-IMPLEMENTED-IN-TERMS-OF” Relationship

Protected inheritance is an intermediate form of inheritance, combining
aspects of public and private inheritance. It acts as a **restrictive filter**,
forcing all public members of the parent class to become "internal matters"
reserved exclusively for future descendant classes.

To remember this easily, look at the access-level transformation table for
this type of inheritance:

## Access Conversion Rules for Protected Inheritance:

- Parent's public $\rightarrow$ downgraded to Child's protected.

- Parent's protected $\rightarrow$ remains Child's protected.

- Parent's private $\rightarrow$ Child **cannot** access.

## Practical Impact (Comparison with Public and Private)

- **For the outside world (main function)**: It behaves exactly like private inheritance. The `main` function is **completely blocked**; it cannot call any functions of the parent class
  through an object of the child class.

- **For the grandchild generation (a class inheriting from the child class)**: This is where it differs from `private`. Since the parent's assets have become `protected` in the child, the grandchild class
  **retains access and usage rights**. (In the case of `private` inheritance,
  the child class keeps them as a private secret, and the grandchild class is barred from access).

## Illustrative Code Example

\#include \<iostream\>

class grandFather {

public:

void grandChild() { std::cout \<\< "GrandFather's legacy\n"; }

};

_// ==================== PROTECTED INHERITANCE ====================_

class parentProtected : protected grandChild {

_// grandChild() (originally public) becomes PROTECTED here_

public:

void TestParent() {

grandChild(); _// VALID: Parent can still use GrandFather's assets_

}

};

_// ==================== GRANDCHILD INHERITANCE ====================_

class : public Protected {

public:

void TestChild() {

grandChild(); _// VALID! Since it is protected in the parent class, can still use it._

_// (If parentProtected used private inheritance, this line would cause an ERROR)_

}

};

int main() {

parentProtected objparent;

_// objParent.grandChild();_

_// COMPILATION ERROR! Accessing a protected function from outside (e.g., in main) is not allowed._

return 0;

}

## Summary comparison table of all 3 inheritance types

Here is an overview that allows you to distinguish between the three types at a glance:

| **Base Class Access Level** | **Public Inheritance**  | **Protected Inheritance**   | **Private Inheritance** |
| --------------------------- | ----------------------- | --------------------------- | ----------------------- |
| **public**                  | $\rightarrow$ public    | $\rightarrow$ **protected** | $\rightarrow$ private   |
| **protected**               | $\rightarrow$ protected | $\rightarrow$ **protected** | $\rightarrow$ private   |
| **private**                 | Inaccessible            | Inaccessible                | Inaccessible            |
| _Accessible by main()?_     | **Yes**                 | **No**                      | **No**                  |
| _Usable by Grandchild?_     | **Yes**                 | **Yes**                     | **No**                  |

## Chapter 7. Constant Members (const in Class)

Placing the `const` keyword at the end of a member function serves as a guarantee: _"This function only reads data and absolutely does not modify any of the Object's attributes."_

class Account {

private:

int balance = 5000;

public:

_// const function: protects data from accidental modification_

int balance() const {

_// balance = 0; // COMPILATION ERROR immediately! Because a const function does not allow modifying variables._

return balance;

}

};

## ⚙️ Chapter 8. Constructor Initialization List

Instead of assigning values ​​using the `=` operator inside the constructor body, professional C++ programmers always use an **Initialization List** (indicated by the `:` symbol after the constructor).

- **Why is it needed?** It helps initialize variables **directly** upon memory allocation, skipping an intermediate step to improve execution speed; furthermore, it is the only way to initialize `const` or Reference (`&`) type attributes.

class example {

private:

const int id; _// Constant variable within the class_

std::string name; public:

_// Standard C++ style: Use an initializer list_

ViDu(int newID, std::string newName) : id(newID), name(newName) {

_// Empty function body; nothing else needed_

}

};

## 💾 Chapter 9. Memory Management: The Rule of Three

This section answers the question raised earlier: **Why is the destructor
crucial when using pointers (new/delete)?**

If your class directly owns a resource such as dynamically allocated memory, you need to carefully define or disable the appropriate special member functions. This is the idea behind the Rule of Three.

1.  **Destructor**: To free memory using `delete`.

2.  **Copy Constructor**: To create a new "deep copy" instead of
    simply copying the memory address.

3.  **Copy Assignment Operator**: To handle the assignment of one
    object containing pointers to another (e.g., `a = b`).

class rowArr {

private:

int\* ptr;

public:

rowArr() { ptr = new int[100]; } _// Allocate memory_

_// 1. Destructor: Without this, a memory leak occurs immediately!_

~rowArr() { delete[] ptr; }

_// 2. Copy Constructor: Ensures a completely new, independent memory
block is created when copying to a new object_

rowArr(const rowArr& nguon) {

ptr = new int[100];

_// Copy each element from nguon.ptr to ptr..._

}

}; ## 🪄 Chapter 10. Virtual Functions and Polymorphism

Even though we previously decided to skip public inheritance, there is an incredibly powerful mechanism associated with classes that simply cannot be overlooked: the **Virtual Function**.

Without the `virtual` keyword, C++ calls a function based on the **pointer's data type** at compile-time, rather than looking at the **actual object** at runtime.

class ConVat {
public:
virtual void keu() {
std::cout << "Generic sound...\n";
}

    virtual ~ConVat() = default;

};

class ConMeo : public ConVat {

public:

void keu() override { std::cout << "Meow Meow!\n"; } // override to replace the parent function

};

int main() {

ConVat\* v = new ConMeo(); // Pointer of type ConVat holding a ConMeo object

v->keu(); // Result: "Meow Meow!" thanks to the virtual keyword!

// (Without 'virtual' in the parent class, it would print "Generic sound...")

delete v;

}
