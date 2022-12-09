package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePojo {

    /*
    1) Create private variables for every key
    2) Create constructors  with all parameters and without any parameter
    3) Create public getters and setters for all variables.
    4) Create toString() method
     */

    //1.
    private Integer bookingid;
    private BookingPojo booking;

    //2.

    public BookingResponsePojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponsePojo() {
    }

    //3.

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    //4.

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
