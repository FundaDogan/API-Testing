package reviews;

import java.util.HashMap;
import java.util.Map;

public class TestHerOku {

    public Map<String, String> bookingDatesSetUp(String checkin, String checkout){
        Map<String,String > bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);

        return bookingDatesMap;

    }


    public Map<String, Object> expectedDataSetUp(String firstname, String lastname,Integer totalprice,
                                                 Boolean depositpaid, Map<String,String> bookingdates,
                                                 String additionalneeds){

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);
        expectedDataMap.put("additionalneeds",additionalneeds);

        return expectedDataMap;

    }
}
