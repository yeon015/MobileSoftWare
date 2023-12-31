package ddwu.moblie.week10.customadaptertest;
//DTO 형태. 원본 항목 데이터 저장 클래스. 값을 모아놓은 클래스
public class MyData {
    private long _id;
    private String name;
    private String phone;

    public MyData(long _id, String name, String phone) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
