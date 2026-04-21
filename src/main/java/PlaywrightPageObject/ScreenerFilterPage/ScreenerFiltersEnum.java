package PlaywrightPageObject.ScreenerFilterPage;

import java.util.HashMap;
import java.util.Map;

public class ScreenerFiltersEnum {
    Map<String,String> filterType = new HashMap<String,String>();

    public Map<String,String> getCompanyFilerType(){
        filterType.put("Company Profile_Company Type","Multi-Select");
        return filterType;
    }


}
