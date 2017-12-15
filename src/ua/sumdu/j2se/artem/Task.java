package ua.sumdu.j2se.artem;
import java.io.Serializable;
import java.util.Date;

/**
 * description of basically task, its status, time of alerts
 *
 * @author Papeta
 */
public class Task implements Cloneable,Serializable {

    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;

    /**
     * @return checked activity of task
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * set an activity of task
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return a title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * set a title
     */
    public void setTitle(String title) {
        if (title == null)
            throw new NullPointerException("Task title can not be null!");
        else
            this.title = title;
    }

    /**
     * set time for non-repeated tasks
     */
    public void setTime(Date time) {
        if (time.before(new Date(0)))
            throw new IllegalArgumentException("Invalid input time(must be >0)");
        else {
            this.time = time;
            this.end = time;
            this.start = time;
            this.interval = 0;
        }
    }

    /**
     * set time for repeated tasks
     */
    public void setTime(Date start, Date end, int interval) {
        if (end.before(start)  || start.before(new Date(0)) )
            throw new IllegalArgumentException("Invalid input start/end");
        else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = start;

        }
    }

    /**
     * @return time for all tasks
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * @return starting time of alerts
     */
    public Date getStartTime() {
        return (interval > 0) ? start : time;
    }

    /**
     * @return end time of alerts
     */
    public Date getEndTime() {
        return (interval > 0) ? end : time;
    }

    /**
     * check repetition of tasks
     */
    public boolean isRepeated() {
        return (interval > 0);
    }

    /**
     * @return repeat interval
     */
    public int getRepeatInterval() {
        return this.interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (interval != task.interval) return false;
        if (active != task.active) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (time != null ? !time.equals(task.time) : task.time != null) return false;
        if (start != null ? !start.equals(task.start) : task.start != null) return false;
        return end != null ? end.equals(task.end) : task.end == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + interval;
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    /**
     * @return time of the next alerts
     */
    public Date nextTimeAfter(Date time) {
        if (!active) {
            return null;
        }
        if (!isRepeated()) {
            if (time.before(this.time))
                return this.time;
            else
                return null;
        } else {
            if (time.after(this.end))
                return null;
            else {
                Date alertTime = (Date)start.clone();
                while (alertTime.compareTo(time) <= 0) {
                    alertTime.setTime(alertTime.getTime() + getRepeatInterval() * 1000);
                }
                if (alertTime.getTime() <= end.getTime())
                    return alertTime;
                else
                    return null;
            }
        }
    }

    /**
     * Constructor for non-repeated tasks
     */
    public Task(String title, Date time) {
        if (title == null)
            throw new NullPointerException("Task title can not be null");
        else if (time.before(new Date(0)))
            throw new IllegalArgumentException("Invalid input time");
        else {
            this.title = title;
            this.time = time;
        }
    }

    /**
     * Constructor for repeated tasks
     */
    public Task(String title, Date start, Date end, int interval) {
        if (title == null) {
            throw new NullPointerException("Task title can not be null");
        } else if (start.after(end) || start.before(new Date(0))) {
            throw new IllegalArgumentException("Invalid input start/end");
        } else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = start;
        }
    }

    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    @Override
    public String toString() {
        if (isRepeated()) {
            return " " + getTitle() + " start in  " + getStartTime() + " end in " + getEndTime() + " with interval  "
                            + getRepeatInterval() + " (active is " + isActive() + ")\n";
        }
        return " " + getTitle() + " " + "in time " +  getTime() + " (active is " + isActive() + ")\n";
    }

}
