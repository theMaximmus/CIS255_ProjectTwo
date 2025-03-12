package csm.cis255;

// STILL WORKING BUT ALMOST DONE - Elizabeth

import java.util.ArrayList;

public class CourseAL {
    private String name;
    private final ArrayList<Student> roster;
    private final ArrayList<Student> waitlist;
    private int maximumStudentsOnRoster;
    private int maximumStudentsOnWaitlist;

    public CourseAL(String name, int maximumStudentsOnRoster, int maximumStudentsOnWaitlist) {
        this.name = name;
        this.maximumStudentsOnRoster = maximumStudentsOnRoster;
        this.maximumStudentsOnWaitlist = maximumStudentsOnWaitlist;
        this.roster = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getRoster() {
        return new ArrayList<>(roster);
    }

    public ArrayList<Student> getWaitlist() {
        return new ArrayList<>(waitlist);
    }

    public int getMaximumStudentsOnRoster() {
        return maximumStudentsOnRoster;
    }

    public void setMaximumStudentsOnRoster(int maximumStudentsOnRoster) {
        this.maximumStudentsOnRoster = maximumStudentsOnRoster;
    }

    public int getMaximumStudentsOnWaitlist() {
        return maximumStudentsOnWaitlist;
    }

    public void setMaximumStudentsOnWaitlist(int maximumStudentsOnWaitlist) {
        this.maximumStudentsOnWaitlist = maximumStudentsOnWaitlist;
    }

    public int getCurrentStudentsEnrolled() {
        return roster.size();
    }

    public int getCurrentStudentsOnWaitlist() {
        return waitlist.size();
    }

    public boolean addStudent(Student student) {
        if (isStudentEligible(student)) {
            if (roster.size() < maximumStudentsOnRoster) {
                return roster.add(student);
            } else if (waitlist.size() < maximumStudentsOnWaitlist) {
                return waitlist.add(student);
            }
        }
        return false;
    }

    public boolean dropStudent(Student student) {
        if (waitlist.remove(student)) {
            return true;
        }
        if (roster.remove(student)) {
            if (!waitlist.isEmpty()) {
                roster.add(waitlist.removeFirst());
            }
            return true;
        }
        return false;
    }

    private boolean isStudentEligible(Student student) {
        return student.isTuitionPaid() && !roster.contains(student) && !waitlist.contains(student);
    }

    @Override
    public String toString() {
        return "Course:\n" +
                "\tName: " + name + "\n" +
                "\tCurrent Students on Roster: " + roster.size() + "\n" +
                "\tMaximum Students on Roster: " + maximumStudentsOnRoster + "\n" +
                "\tRoster: " + roster + "\n" +
                "\tCurrent Students on Waitlist: " + waitlist.size() + "\n" +
                "\tMaximum Students on Waitlist: " + maximumStudentsOnWaitlist + "\n" +
                "\tWaitlist: " + waitlist;
    }
}
