package bean;

import java.util.Date;

public class Staff {
    private int id;
    private String gender;
    private String name;
    private int age;
    private Date date;
    private int salary;
    private String pos;
    private String pwd;
    private int dep_id;

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getDate() {
        return date;
    }

    public int getSalary() {
        return salary;
    }

    public String getPos() {
        return pos;
    }

    public String getPwd() {
        return pwd;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", salary=" + salary +
                ", pos='" + pos + '\'' +
                ", pwd='" + pwd + '\'' +
                ", dep_id=" + dep_id +
                '}';
    }
}
