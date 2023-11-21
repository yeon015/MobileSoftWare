package ddwu.moblie.finalreport;

import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    int image;   //사진
    String title;   //제목
    String mainActor;  //주연(메인 캐릭터)
    String director;  //감독
    String grade;  //평점

    public Movie(int image, String title, String mainActor, String director, String grade) {
        this.image = image;
        this.title = title;
        this.mainActor = mainActor;
        this.director = director;
        this.grade = grade;
    }

    public Movie(long _id, int image, String title, String mainActor, String director, String grade) {
        this._id = _id;
        this.image = image;
        this.title = title;
        this.mainActor = mainActor;
        this.director = director;
        this.grade = grade;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
