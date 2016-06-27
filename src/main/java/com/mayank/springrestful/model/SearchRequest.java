package com.mayank.springrestful.model;

import java.util.List;

/**
 * Created by Mayank on 26/06/16.
 */
public class SearchRequest {

    List<String> searchText;

    public List<String> getSearchText() {
        return searchText;
    }

    public void setSearchText(List<String> searchText) {
        this.searchText = searchText;
    }
}
