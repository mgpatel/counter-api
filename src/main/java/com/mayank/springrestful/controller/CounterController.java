package com.mayank.springrestful.controller;

import com.mayank.springrestful.exception.SearchResponseNotFoundException;
import com.mayank.springrestful.model.SearchRequest;
import com.mayank.springrestful.model.SearchResponse;
import com.mayank.springrestful.service.IndexService;
import com.mayank.springrestful.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mayank on 26/06/16.
 */

@RestController
@RequestMapping("/counter-api")
public class CounterController {

    @Autowired
    IndexService indexService;

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/top/{count}", method = RequestMethod.GET, produces = "text/csv")
    public ResponseEntity<String> getTopCount(@PathVariable int count) throws Exception{

        String topSearchText = searchService.findTopText(5);

        return new ResponseEntity<String>(topSearchText, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<SearchResponse> seachText(@RequestBody SearchRequest searchRequest) throws Exception{

        SearchResponse searchResponse = searchService.findText(searchRequest.getSearchText());

        if(searchResponse == null) throw new SearchResponseNotFoundException();

        return new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.OK);
    }
}