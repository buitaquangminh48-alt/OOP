# 📦 OOP in Python: From Basics to Advanced

This documentation provides a comprehensive guide to Object-Oriented Programming (OOP) in **Python**. It is formatted in clean Markdown with detailed syntax highlighting for easy reading and repository storage on GitHub.

---

## 📦 Chapter 1: Basic Structure (Attributes and Methods)

In Python, a class defines an object using two core components:

* **Attributes**: Store the state data of an object, primarily initialized inside the `__init__` method.
* **Methods**: Functions defining actions and behaviors. They **must** accept `self` as their first parameter, representing the instance calling the method.

```python
class Robot:
    # 1. Constructor and attribute initialization
    def __init__(self, name: str, battery: int):
        self.name = name
        self.battery = battery

    # 2. Instance method
    def introduce(self):
        print(f"I am {self.name}, remaining battery: {self.battery}%")


if __name__ == "__main__":
    # Instantiate object r1
    r1 = Robot("T-800", 100)
    r1.introduce()  # Output: I am T-800, remaining battery: 100%

```

---

## 🛠️ Chapter 2: The `__init__` Constructor and Memory Management via `__del__`

Python manages memory automatically using **Reference Counting** combined with a **Garbage Collector (GC)**.

* **`__init__`**: A magic method acting as the Constructor, invoked automatically when a new instance is created.
* **`__del__`**: A magic method acting as the Destructor, invoked when an object's reference count drops to zero.

```python
class Robot:
    def __init__(self, name: str):
        self.name = name
        print(f"{self.name} initialized!")

    def __del__(self):
        print(f"{self.name} has been garbage collected!")


# Life cycle of an object
r1 = Robot("T-1000")
del r1  # Output: T-1000 has been garbage collected!

```

---

## 🔒 Chapter 3: Encapsulation & The `@property` Decorator

Python follows the philosophy: *"We are all consenting adults here"*. The language does not strictly enforce private access modifiers at compile time; instead, it relies on **naming conventions**:

* `name`: Public (freely accessible).
* `_name`: Protected (intended for internal use within the class and its subclasses).
* `__name`: Private (triggers Name Mangling—Python renames it to `_ClassName__name` to restrict external access).

```python
class Robot:
    def __init__(self, battery: int):
        self.__battery = battery  # Private attribute

    # Getter using the @property decorator
    @property
    def battery(self) -> int:
        return self.__battery

    # Setter using the @<attribute>.setter decorator
    @battery.setter
    def battery(self, new_battery: int):
        if 0 <= new_battery <= 100:
            self.__battery = new_battery
        else:
            print("Invalid battery value!")


r = Robot(50)
r.battery = 80  # Triggers setter implicitly
print(r.battery)  # Output: 80 (Triggers getter implicitly)

```

---

## 👥 Chapter 4: Class Attributes, Class Methods & Static Methods

Python differentiates between three types of methods using built-in decorators:

1. **Instance Method**: Takes `self` as the first parameter; operates on instance-specific data.
2. **Class Method (`@classmethod`)**: Takes `cls` as the first parameter; operates on class-level data shared across all instances.
3. **Static Method (`@staticmethod`)**: Takes neither `self` nor `cls`; behaves like an isolated utility function scoped inside the class namespace.

```python
class Robot:
    total_robots = 0  # Class Attribute (Shared state)

    def __init__(self, name: str):
        self.name = name
        Robot.total_robots += 1

    @classmethod
    def get_total(cls):
        print(f"Total robots created: {cls.total_robots}")

    @staticmethod
    def is_valid_name(name: str) -> bool:
        return len(name) > 0


r1 = Robot("Alpha")
r2 = Robot("Beta")

Robot.get_total()  # Output: Total robots created: 2
print(Robot.is_valid_name("T-800"))  # Output: True

```

---

## 🧬 Chapter 5: Inheritance & The `super()` Function

Inheritance is declared using the syntax `class Derived(Base):`. The `super()` function is used to invoke methods from a parent class.

```python
class Vehicle:
    def __init__(self, brand: str):
        self.brand = brand

    def start(self):
        print("Vehicle engine starting...")


class Motorcycle(Vehicle):
    def __init__(self, brand: str, displacement: int):
        super().__init__(brand)  # Invoke parent class constructor
        self.displacement = displacement

    def rev_engine(self):
        print(f"{self.brand} {self.displacement}cc revs loudly!")


mc = Motorcycle("Yamaha", 155)
mc.start()       # Inherited parent method
mc.rev_engine()  # Subclass method

```

---

## 🔀 Chapter 6: Multiple Inheritance & The Method Resolution Order (MRO)

Unlike Java, Python natively supports **Multiple Inheritance**. Method lookup order and diamond inheritance resolution are determined by the **C3 Linearization algorithm (Method Resolution Order - MRO)**.

```python
class A:
    def identify(self):
        print("Class A")

class B(A):
    def identify(self):
        print("Class B")

class C(A):
    def identify(self):
        print("Class C")

# D inherits from both B and C (Diamond problem resolved by MRO)
class D(B, C):
    pass

d = D()
d.identify()  # Output: Class B (B precedes C in class definition D(B, C))
print(D.__mro__)  # Lookup order: D -> B -> C -> A -> object

```

---

## 🎭 Chapter 7: Duck Typing & Polymorphism

Python implements dynamic polymorphism via **Duck Typing**: *"If it walks like a duck and quacks like a duck, it's a duck"*. Polymorphism in Python does not require subclasses to inherit from a common base class, provided they implement the expected interface/methods.

```python
class Dog:
    def make_sound(self):
        print("Woof Woof!")

class Cat:
    def make_sound(self):
        print("Meow Meow!")

class AudioRobot:
    def make_sound(self):
        print("Beep Boop!")

# Function accepts any object implementing `make_sound`
def play_sound(speaker):
    speaker.make_sound()

entities = [Dog(), Cat(), AudioRobot()]
for entity in entities:
    play_sound(entity)  # Output: Woof Woof! -> Meow Meow! -> Beep Boop!

```

---

## 🎭 Chapter 8: Abstract Base Classes (ABC)

To enforce interface contracts requiring subclasses to implement specific methods, Python provides the standard library module `abc`.

```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def calculate_area(self) -> float:
        pass

class Square(Shape):
    def __init__(self, side: float):
        self.side = side

    # Mandatory concrete implementation of abstract method
    def calculate_area(self) -> float:
        return self.side ** 2

# s = Shape()  # ERROR! Cannot instantiate an abstract class directly.
sq = Square(4.0)
print(sq.calculate_area())  # Output: 16.0

```

---

## 🪄 Chapter 9: Magic Methods (Dunder Methods) & Operator Overloading

Magic methods (methods surrounded by double underscores `__`) allow customization of built-in object behaviors, such as string representation, length evaluation, and operator overloading (`+`, `-`, `==`, etc.).

```python
class Vector2D:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    # Overload addition operator (+)
    def __add__(self, other):
        return Vector2D(self.x + other.x, self.y + other.y)

    # Customize string representation for print()
    def __str__(self):
        return f"Vector2D({self.x}, {self.y})"

    # Overload equality operator (==)
    def __eq__(self, other):
        return self.x == other.x and self.y == other.y


v1 = Vector2D(2, 3)
v2 = Vector2D(4, 5)
v3 = v1 + v2  # Implicitly calls v1.__add__(v2)

print(v3)        # Output: Vector2D(6, 8)
print(v1 == v2)  # Output: False

```

---

## ⚙️ Chapter 10: Dataclasses & Type Hinting (Modern Python)

Introduced in Python 3.7+, the `dataclasses` module automatically generates boilerplate code such as `__init__`, `__repr__`, and `__eq__`.

```python
from dataclasses import dataclass
from typing import Optional

@dataclass
class Product:
    id: int
    name: str
    price: float
    description: Optional[str] = None  # Default argument

    def apply_discount(self, percentage: float) -> float:
        return self.price * (1 - percentage / 100)


p1 = Product(1, "Mechanical Keyboard", 150.0)
p2 = Product(1, "Mechanical Keyboard", 150.0)

print(p1)                     # Output: Product(id=1, name='Mechanical Keyboard', price=150.0, description=None)
print(p1 == p2)              # Output: True (dataclass generates value-based __eq__)
print(p1.apply_discount(10)) # Output: 135.0

```