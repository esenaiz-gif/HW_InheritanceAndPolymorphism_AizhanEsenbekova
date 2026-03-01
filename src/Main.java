import java.util.ArrayList;

class Course {
    private String name;
    private String instructor;
    private int credits;

    public Course(String name, String instructor, int credits) {
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void displayCourse() {
        System.out.println("Course { name = '" + name +
                "', instructor = '" + instructor +
                "', credits = " + credits + " }");
    }
}

/*
   SUPERCLASS
    */
abstract class PlanItem {

    protected String title;
    protected int estimatedHours;
    protected boolean completed;

    public PlanItem(String title, int estimatedHours) {
        this.title = title;
        this.estimatedHours = estimatedHours;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getEstimatedHours() {
        return completed ? 0 : estimatedHours;
    }

    public abstract void display();
    public abstract int urgencyLevel();
}

    /*
   SUBCLASS 1
   */
class AssignmentTask extends PlanItem {

    private Course course;
    private int daysUntilDue;

    public AssignmentTask(String title, Course course,
                          int estimatedHours, int daysUntilDue) {
        super(title, estimatedHours);
        this.course = course;
        this.daysUntilDue = daysUntilDue;
    }

    @Override
    public void display() {
        System.out.println("Assignment: " + title +
                "\nCourse: " + course.getName() +
                "\nHours: " + estimatedHours +
                "\nDays left: " + daysUntilDue +
                "\nCompleted: " + completed + "\n");
    }

    @Override
    public int urgencyLevel() {
        if (completed) return 0;
        return daysUntilDue; // smaller = more urgent
    }
}

    /*
   SUBCLASS 2
    */
class StudySession extends PlanItem {

    private Course course;
    private int minutes;

    public StudySession(Course course, int minutes) {
        super("Study Session", minutes / 60);
        this.course = course;
        this.minutes = minutes;
    }

    @Override
    public void display() {
        System.out.println("Study Session for: " + course.getName() +
                "\nMinutes: " + minutes +
                "\nCompleted: " + completed + "\n");
    }

    @Override
    public int urgencyLevel() {
        return completed ? 0 : 10;
    }
}

/*
   MAIN CLASS
   */
public class Main {

    public static void main(String[] args) {

        Course oop = new Course("OOP", "Talant Arystanov", 6);
        Course dm = new Course("Discrete Math", "Ahmad Sarosh", 4);

        PlanItem t1 = new AssignmentTask(
                "Presentation about classes",
                oop, 3, 1);

        PlanItem t2 = new AssignmentTask(
                "Limits Homework 1",
                dm, 4, 5);

        PlanItem s1 = new StudySession(oop, 120);

        ArrayList<PlanItem> items = new ArrayList<>();
        items.add(t1);
        items.add(t2);
        items.add(s1);

        System.out.println("=== ALL PLAN ITEMS ===\n");

        for (PlanItem item : items) {
            item.display();  // POLYMORPHISM
        }

        // MARK ONE AS COMPLETED
        t1.markCompleted();

        System.out.println("=== AFTER COMPLETING FIRST ITEM ===\n");

        for (PlanItem item : items) {
            item.display();
        }

        // CALCULATE TOTAL HOURS
        int total = 0;
        for (PlanItem item : items) {
            total += item.getEstimatedHours();
        }

        System.out.println("Total remaining hours: " + total);

        //FIND THE MOST URGENT
        PlanItem mostUrgent = items.get(0);

        for (PlanItem item : items) {
            if (item.urgencyLevel() < mostUrgent.urgencyLevel()) {
                mostUrgent = item;
            }
        }

        System.out.println("\nMost urgent item:");
        mostUrgent.display();
    }
}