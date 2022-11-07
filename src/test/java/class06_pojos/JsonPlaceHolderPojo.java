package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
// I am telling POJO, when you convert from json to pojo if some data (variable) is unknown then ignore them.
//
/*
interview Q:
    You are sending firstname and lastname by using POST() but in the response you see firstname, lastname
     and success message how can you use pojo for response?
     Firstname and lastname are OK for POJO because I have container for them but in my POJO there is no success container
     It will cause problem, so I use ==> @JsonIgnoreProperties (ignoreUnknown = true) to solve this problem
 */
public class JsonPlaceHolderPojo {
    //POJO ==> plain old java object
    /*
    POJO icinde private variable, getter ve setter, butun parametrelere sahip constructor
        ve parametresiz constructor ile en sonra toString method OLMALI
     */

//Create private variables
    private Integer userId;
    private String title;
    private Boolean completed;

//Create Constructors with all parameters

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

//Create Constructors without parameter

    public JsonPlaceHolderPojo() {
    }

//Generate getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

//Generate toString
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                "title='" + title + '\'' +
                "completed=" + completed +
                '}';
    }
}

