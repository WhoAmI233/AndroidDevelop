package suprise.qiuguochao.com.charm.SuspendedWindow;

/**
 * Created by qiuguochao on 2017/2/21.
 */

public class HistoryRecord {
    public int id;
    public String record;
    public String time;

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getRecord() {
        return record;
    }
    public void setRecord(String record) {
        this.record = record;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public HistoryRecord() {//java.lang.InstantiationException
    }
    public HistoryRecord(String record, String time) {
        this.record = record;
        this.time = time;
    }
}
