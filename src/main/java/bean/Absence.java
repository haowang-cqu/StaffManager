package bean;

import java.util.Date;

public class Absence {
    private int abs_id;
    private Date date;
    private String reason;
    private int user_id;

    public int getAbs_id() {
        return abs_id;
    }

    public void setAbs_id(int abs_id) {
        this.abs_id = abs_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    private String agree;
}
