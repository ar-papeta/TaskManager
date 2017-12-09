package ua.sumdu.j2se.artem;

/**
 * description of basically task, its status, time of alerts
 *
 * @author Papeta
 */
public class Task implements Cloneable {

    private String title;
    private int time;
    private int start;
    private int end;
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
    public void setTime(int time) {
        if (time < 1)
            throw new IllegalArgumentException("Invalid input time");
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
    public void setTime(int start, int end, int interval) {
        if (start >= end || start < 1)
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
    public int getTime() {
        return this.time;
    }

    /**
     * @return starting time of alerts
     */
    public int getStartTime() {
        return (interval > 0) ? start : time;
    }

    /**
     * @return end time of alerts
     */
    public int getEndTime() {
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

    /**
     * @return time of the next alerts
     */
    public int nextTimeAfter(int time) {
        if (!active) {
            return -1;
        }
        if (this.interval == 0) {
            if (time < this.time)
                return this.time;
            else
                return -1;
        } else {
            if (time >= this.end)
                return -1;
            else {
                int alertTime = this.start;
                while (alertTime <= time) {
                    alertTime += this.interval;
                }
                if (alertTime <= this.end)
                    return alertTime;
                else
                    return -1;
            }
        }
    }

    /**
     * Constructor for non-repeated tasks
     */
    public Task(String title, int time) {
        if (title == null)
            throw new NullPointerException("Task title can not be null");
        else if (time < 1)
            throw new IllegalArgumentException("Invalid input time");
        else {
            this.title = title;
            this.time = time;
        }
    }

    /**
     * Constructor for repeated tasks
     */
    public Task(String title, int start, int end, int interval) {
        if (title == null) {
            throw new NullPointerException("Task title can not be null");
        } else if (start >= end || start < 1) {
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

    public String toString() {
        if (isRepeated()) {
            return
                    "\" " + getTitle() + "\" start time = " + getStartTime() + " end time = " + getEndTime() + " interval = " + getRepeatInterval() + " active = " + isActive();
        }
        return "\" " + getTitle() + "\" " + getTime() + " active = " + isActive() + "\n";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (time != task.time) return false;
        if (start != task.start) return false;
        if (end != task.end) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        return title.equals(task.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + time;
        result = 31 * result + start;
        result = 31 * result + end;
        result = 31 * result + interval;
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
