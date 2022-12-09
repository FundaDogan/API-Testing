package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestPojo {

    /*
    1) Create private variables for every key
    2) Create constructors  with all parameters and without any parameter
    3) Create public getters and setters for all variables.
    4) Create toString() method
     */

    //1.
    private Object meta;
    private GoRestDataPojo data;

    //2
    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }

    //3

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    //4

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
