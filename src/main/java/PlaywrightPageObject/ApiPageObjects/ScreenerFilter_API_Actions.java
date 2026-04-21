package PlaywrightPageObject.ApiPageObjects;

public class ScreenerFilter_API_Actions extends BaseAPI_Actions {


    public void getFilterApiNameFromJsonFile(String ScreenerTypeDataJsonPath,String tile,String group,String filterName){

    }


    public void userIsAbleToRequestMultiFieldFilterAPI(String ScreenerType,String tile,String group,String filterName){
        String multiSelectBody = "{\"dataField\":\""+filterName+"\",\"request\":[]}";
    }


}
