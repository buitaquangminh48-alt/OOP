# 🚀 Ultimate OOP Guide (C++ | Java | Python)

A comprehensive compilation of **Object-Oriented Programming (OOP)** concepts ranging from core fundamentals to advanced patterns, formatted as a standard, high-efficiency **Cheat Sheet** for developers.

This repository helps you master core OOP design principles while understanding the fundamental philosophies, architectural differences, and syntax variations across three major programming languages: **C++**, **Java**, and **Python**.

---

## 🏛️ The 4 Pillars of OOP

| Pillar | Concept | C++ | Java | Python |
| :--- | :--- | :--- | :--- | :--- |
| **Encapsulation** | Hiding internal object state and exposing only safe, public interfaces. | Strict `private` / `public` modifiers | `private` / `public` + Getters/Setters | Naming conventions (`_protected`, `__private`) + `@property` |
| **Abstraction** | Hiding complex implementation details behind clean, unified contracts. | Pure Virtual Functions (`= 0`) | `interface` & `abstract class` | `abc` module (`@abstractmethod`) |
| **Inheritance** | Reusing and extending attributes/behaviors from parent classes. | Supports Multiple Inheritance | Single Class Inheritance (via `implements` for Interfaces) | Supports Multiple Inheritance (C3 / MRO Algorithm) |
| **Polymorphism** | Executing uniform actions across distinct types at runtime. | `virtual` keyword + Pointers/References | Dynamic dispatch by default (`@Override`) | Duck Typing ("Walks like a duck, quacks like a duck") |

---

## 📚 Detailed Language Documentation

Select your language of choice to explore the dedicated documentation:

| Language | File | Philosophy & Key Features |
| :---: | :--- | :--- |
| ⚡ **C++** | [`OOP_CPP.md`](./docs/en/OOP_CPP.md) | **High Performance & Manual Control:** Explicit Heap memory management (`new`/`delete`), Pointers/References, `Rule of Three/Five`, `const` correctness, `friend` functions, Private/Protected inheritance. |
| ☕ **Java** | [`OOP_Java.md`](./docs/en/OOP_Java.md) | **Strictness & Enterprise Discipline:** Automatic Garbage Collection (GC), Contract enforcement via `Interface` & `Abstract Class`, Checked Exception handling, `Generics`, `Records`. |
| 🐍 **Python** | [`OOP_Python.md`](./docs/en/OOP_Python.md) | **Flexibility & Expressiveness:** *"We are all consenting adults"* philosophy, Magic/Dunder Methods (`__add__`, `__str__`), `@classmethod`, `@staticmethod`, `dataclasses`. |

---

## 🗺️ 10-Chapter Curriculum Roadmap

All documentation files across the 3 languages adhere strictly to the same 10-chapter structural layout for seamless cross-referencing:

```text
Chapter 01: Core Structure (Attributes & Methods)
Chapter 02: Constructors & Memory Management (Destructors / Garbage Collector)
Chapter 03: Encapsulation & Access Controls (Access Modifiers / Properties)
Chapter 04: Class-Level & Shared Members (static / Class Methods)
Chapter 05: Single Inheritance & Parent Initialization (extends / super)
Chapter 06: Advanced Inheritance Mechanisms (Access inheritance / Final / MRO)
Chapter 07: Polymorphism & Virtual Functions (Virtual / Interfaces / Duck Typing)
Chapter 08: Abstraction & Contract Design (Abstract Classes / ABC)
Chapter 09: Language-Specific Power Features (Memory Ownership / Exceptions / Magic Methods)
Chapter 10: Modern OOP Paradigm (Templates / Generics / Dataclasses)
