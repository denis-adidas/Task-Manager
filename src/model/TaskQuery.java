package model;

import jdk.jfr.Description;
import jdk.jfr.Name;

public class TaskQuery {
    @Description("It's task's filters")
    public static final String FILTER_FIELD_STATUS = "Status";
    public static final String FILTER_FIELD_TARGET_DATE = "Due date";
    public static final String FILTER_FIELD_ASSIGNEE = "Assigned To";

    @Description("It's also task's filters, but as lists")
    public static final String FILTER_BY_ALL = "All";
    public static final String FILTER_BY_ACTIVE = "Active";
    public static final String FILTER_BY_COMPLETED = "Completed";
    public static final String FILTER_BY_IMPORTANT = "Important";
    public static final String FILTER_BY_TODAY = "Today";
    public static final String FILTER_BY_THIS_WEEK = "This Week";
    public static final String FILTER_BY_THIS_MONTH = "This Month";
    public static final String FILTER_BY_NEXT_MONTH = "Next month";

    @Description("It's task's sort by parameters")
    public static final String SORT_BY_NAME = "Due name";
    public static final String SORT_BY_ASSIGNEE = "Assignee";
    public static final String SORT_BY_TARGET_DATE = "Due date";
    public static final String SORT_BY_TARGET_TIME = "Due time";

    @Description("It's task's sort by ascending or descending")
    public static final String SORT_ORDER_ASC = "Ascending";
    public static final String SORT_ORDER_DESC = "Descending";

    private String searchText = "";
    private String sortBy = "";
    private String sortOrder = "";
    private String filterName = "";
    private String filterLink = "";

    public TaskQuery() {
    }

    public TaskQuery(String searchText, String sortBy, String sortOrder, String filterName, String filterLink) {
        this.searchText = searchText;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.filterName = filterName;
        this.filterLink = filterLink;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterLink() {
        return filterLink;
    }

    public void setFilterLink(String filterLink) {
        this.filterLink = filterLink;
    }
}
