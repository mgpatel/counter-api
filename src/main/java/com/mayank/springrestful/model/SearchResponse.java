package com.mayank.springrestful.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Mayank on 26/06/16.
 */
public class SearchResponse {

    List<Map<String, Long>> counts;

    public void setCounts(List<Map<String, Long>> counts) {
        this.counts = counts;
    }

    public List<Map<String, Long>> getCounts() {
        return counts;
    }
}
