package PlaywrightPageObject.ScreenerFilterPage;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class FilterFormat {
    public String screenerName;
    public String tile;
    public String filterGroup;
    public String filterName;
    public String filterType;
    public String filterOperator;
    public String filterPreCondition;
    public int filterPosition;
    public LinkedList<String> filterInputValues;

    public FilterFormat(String screenerName, String tile,String filterPreCondition,int filterPosition,String filterGroup,String filterName, String filterType, String filterOperator, LinkedList<String> filterInputValues) {
        this.filterName = filterName;
        this.filterType = filterType;
        this.filterOperator = filterOperator;
        this.filterInputValues = filterInputValues;
        this.filterPreCondition = filterPreCondition;
        this.screenerName = screenerName;
        this.tile = tile;
        this.filterPosition = filterPosition;
        this.filterGroup = filterGroup;
        sortInputValues();
    }

    public FilterFormat(String screenerName, String tile,String filterPreCondition,int filterPosition,String filterGroup,String filterName, String filterType, String filterOperator) {
        this.filterName = filterName;
        this.filterType = filterType;
        this.filterOperator = filterOperator;
        this.filterPreCondition = filterPreCondition;
        this.screenerName = screenerName;
        this.tile = tile;
        this.filterPosition = filterPosition;
        this.filterGroup = filterGroup;
    }

    public void addFilterInputValue(String value){
        if(this.filterInputValues==null){
            this.filterInputValues = new LinkedList<String>();
        }
        this.filterInputValues.add(value);
    }

    public void addFilterGraphInputValue(LinkedList<String> value){
        if(this.filterInputValues==null){
            this.filterInputValues = new LinkedList<String>();
        }
        this.filterInputValues.addAll(value);
    }

    public void sortInputValues(){
        Collections.sort(this.filterInputValues);
    }
}
