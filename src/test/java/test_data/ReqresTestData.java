package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String,String> dataMapSetUp(String email, String first_name, String last_name){

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("email",email);
        dataMap.put("first_name",first_name);
        dataMap.put("last_name",last_name);

        return dataMap;

    }

    public Map<String, String> supportMapSetUp(String text){

        Map<String, String> supportMap = new HashMap<>();
        supportMap.put("text",text);

        return supportMap;
    }

    public Map<Object, Object> expectedDataSetUp(Map datamap,Map supportmap){

        Map<Object,Object> expectedData = new HashMap<>();
        expectedData.put("data",datamap);
        expectedData.put("support",supportmap);

        return expectedData;
    }
}
