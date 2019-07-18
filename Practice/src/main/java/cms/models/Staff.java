package cms.models;

public class Staff {
    private int id;
    private String firstname;
    private String lastname;
    private String job_role;

    public Staff(int id, String firstname, String lastname, String job_role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.job_role = job_role;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getJob_role() {
        return job_role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", job_role='" + job_role + '\'' +
                '}';
    }
}
