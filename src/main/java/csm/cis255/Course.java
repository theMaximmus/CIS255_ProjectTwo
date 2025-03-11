package csm.cis255;

import java.util.Arrays;

public class Course {
    // TODO: any other variables you think are helpful
    private String name;
    private Student[] roster;
    private Student[] waitlist;
    private int maximumStudentsOnRoster;
    private int maximumStudentsOnWaitlist;

    public Course(String name, int maximumStudentsOnRoster, int maximumStudentsOnWaitlist) {
        this.name = name;
        this.maximumStudentsOnRoster = maximumStudentsOnRoster;
        this.maximumStudentsOnWaitlist = maximumStudentsOnWaitlist;
        this.roster = new Student[0];
        this.waitlist = new Student[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getRoster() {
        return roster;
    }

    // There is no need for setRoster() method as we won't directly change a list of students on the roaster
    // with a different one (that's already assembled)

    public Student[] getWaitlist() {
        return waitlist;
    }

    // There is no need for setWaitlist() method as we won't directly change a list of students on the waitlist
    // with a different one (that's already assembled)

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
        return this.roster.length;
    }

    public int getCurrentStudentsOnWaitlist() {
        return this.waitlist.length;
    }

    // TODO: Finish method
    /**
     * An addStudent method adds a Student to the Course.
     * A student is eligible to add:
     * if there is room on the roster, add the student to the roster;
     * if the roster is full but there is room on the waitlist, add the student to the waitlist;
     * if there is no room on the roster or waitlist, do not add the student.
     * @param student Student to be added to the course
     * @return A boolean value whether student is added or not
     */
    public boolean addStudent(Student student) {
        if (this.isStudentEligible(student) && this.isRoomInRoster()) {
            this.roster = addStudentToArray(this.roster, student);
            return true;
        } else if (this.isStudentEligible(student) && this.isRoomOnWaitlist()) {
            this.waitlist = addStudentToArray(this.waitlist, student);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A dropStudent method removes a Student from the Course.
     * This method drops a student from the course:
     * if the student is not on the roster or waitlist, the student cannot be dropped;
     * if the student is on the waitlist, remove the student from the waitlist;
     * if the student is on the roster, remove the student from the roster.
     * @param student Student to be dropped from the course
     * @return A boolean value whether student was dropped or not
     */
    public boolean dropStudent (Student student) {
        if(!this.isAlreadyOnRoster(student) && !this.isAlreadyOnWaitlist(student)){
            return false; //cannot be dropped
        }
        if(this.isAlreadyOnWaitlist(student)){
            this.waitlist = removeStudentFromArray(this.waitlist, student);
            return true;
        }
        if(this.isAlreadyOnRoster(student)){
            this.roster = removeStudentFromArray(this.roster, student);

            //if waitlist not empty, add waitlist student 
            if(this.waitlist.length > 0){
                Student firstWaitlisted = this.waitlist[0];
                this.waitlist = removeStudentFromArray(this.waitlist, firstWaitlisted);
                this.roster = addStudentToArray(this.roster, firstWaitlisted);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks whether student is eligible for this course. A student is eligible to add the course if:
     * they have paid tuition, and
     * they are not already enrolled on the roster or waitlist.
     * @param student Student to be checked
     * @return A boolean value whether student is eligible for the course or not
     */
    private boolean isStudentEligible(Student student) {
        if (student.isTuitionPaid() && !this.isAlreadyOnWaitlist(student) && !this.isAlreadyOnRoster(student)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether there is room in the course's seats.
     * @return A boolean value whether there is room or not
     */
    private boolean isRoomInRoster() {
        return this.getCurrentStudentsEnrolled() < this.getMaximumStudentsOnRoster();
    }

    /**
     * Checks whether there is room in the course's waitlist
     * @return A boolean value whether there is room or not
     */
    private boolean isRoomOnWaitlist() {
        return this.getCurrentStudentsOnWaitlist() < this.getMaximumStudentsOnWaitlist();
    }

    /**
     * Checks if student is already on the waitlist
     * @param prospectiveStudent Student to be checked for
     * @return A boolean value whether the student is on the list or not
     */
    private boolean isAlreadyOnWaitlist(Student prospectiveStudent) {
        for (Student student: this.waitlist) {
            if (student.equals(prospectiveStudent)) {
                return true;
            }
        }

        return false;
    }

    private boolean isAlreadyOnRoster(Student prospectiveStudent){
        for(Student student: this.roster){
            if(student.equals(prospectiveStudent)){
                return true;
            }
        }
        return false;
    }

    //helper to add student
    private Student[] addStudentToArray(Student[] array, Student student) {
        //array empty, make new array containing student
        if (array == null) {
            return new Student[]{student};
        }
        Student[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = student;
        return newArray;
    }

    //helper to drop student
    private Student[] removeStudentFromArray(Student[] array, Student student) {
        if (array == null || array.length == 0){
            return array;
        }
        Student[] newArray = new Student[array.length - 1];
        int index = 0;
        for (Student s : array) {
            if (!s.equals(student)) {
                newArray[index++] = s;
            }
        }
        return newArray;
    }

    @Override
    public String toString() {
        return "Course:\n" +
                "\tName: " + name + "\n" +
                "\tCurrent amount of Students on Roster: " + this.getCurrentStudentsEnrolled() + "\n" +
                "\tMaximum amount of Students on Roster: " + this.maximumStudentsOnRoster + "\n" +
                "\tRoster: " +(roster.length > 0 ? Arrays.toString(roster) : "[]") + "\n" + //only if roster is not empty
                "\tCurrent amount of Students on Waitlist: " + this.getCurrentStudentsOnWaitlist() + "\n" +
                "\tMaximum amount of Students on Waitlist: " + this.maximumStudentsOnWaitlist + "\n" +
                "\tWaitlist: " + (waitlist.length > 0 ? Arrays.toString(waitlist): "[]"); //only if waitlist is not empty
    }
}