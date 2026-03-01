# 📚 CampusLife Planner – Project 2.0

## 👩‍🎓 Student Planner System (OOP with Inheritance & Polymorphism)

This project is an improved version of the **CampusLife Planner** from the previous assignment.  
It demonstrates core Object-Oriented Programming (OOP) concepts in Java such as inheritance, method overriding, and polymorphism.

---

## 🚀 Project Goal

The goal of this project is to redesign the planner using a **superclass and subclasses structure**, reduce code duplication, and demonstrate **polymorphic behavior** when different plan items are processed through a shared interface.

---

## 🏗️ Class Structure

### 1️⃣ Course  
Represents a university course.

**Attributes:**
- `name`
- `instructor`
- `credits`

---

### 2️⃣ PlanItem (Abstract Superclass)

Base class for all planning items in the system.

**Shared Attributes:**
- `title`
- `estimatedHours`
- `completed`

**Common Methods:**
- `markCompleted()`
- `isCompleted()`
- `getEstimatedHours()`
- `display()` *(abstract)*
- `urgencyLevel()` *(abstract)*

This class allows code reuse and supports polymorphism.

---

### 3️⃣ AssignmentTask (Subclass)

Represents homework or assignment tasks.

**Additional Attributes:**
- `Course course`
- `daysUntilDue`

**Overridden Methods:**
- `display()`
- `urgencyLevel()` (based on remaining days)

Assignments become more urgent as the deadline approaches.

---

### 4️⃣ StudySession (Subclass)

Represents study time for a course.

**Additional Attributes:**
- `Course course`
- `minutes`

**Overridden Methods:**
- `display()`
- `urgencyLevel()` (lower urgency by default)

---

## 💡 Polymorphism

Different plan item types are stored in a single collection of the base type:

```java
ArrayList<PlanItem> items;
