package com.mayank.springrestful.service;

import com.mayank.springrestful.model.SearchResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mayank on 26/06/16.
 */
public interface SearchService {

    SearchResponse findText(List<String> searchTextList) throws Exception;

    String findTopText(int numberOfText) throws Exception;
}
