package com.mayank.springrestful.service;

import com.mayank.springrestful.Constants;
import com.mayank.springrestful.model.SearchResponse;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.misc.HighFreqTerms;
import org.apache.lucene.misc.TermStats;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mayank on 26/06/16.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public SearchResponse findText(List<String> searchTextList) throws Exception {

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(Constants.INDEX_DIR)));

        List<Map<String, Long>> searchCountList = new ArrayList<Map<String, Long>>();

        for(String searchText : searchTextList){
            long totalTextFeq = indexReader.totalTermFreq(new Term(Constants.CONTENT, searchText.toLowerCase()));
            Map<String, Long> object = new HashMap<String, Long>();
            object.put(searchText, totalTextFeq);
            searchCountList.add(object);
        }

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setCounts(searchCountList);

        return searchResponse;
    }

    @Override
    public String findTopText(int numberOfText) throws Exception{

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(Constants.INDEX_DIR)));

        StringBuilder topTextSearchSB = new StringBuilder();

        TermStats[] commonTerms = HighFreqTerms.getHighFreqTerms(indexReader, numberOfText, Constants.CONTENT, new HighFreqTerms.TotalTermFreqComparator());
        for (TermStats commonTerm : commonTerms) {
            if(topTextSearchSB.length() > 0){
                topTextSearchSB.append("\n");
            }
            topTextSearchSB.append(commonTerm.termtext.utf8ToString());
            topTextSearchSB.append("|");
            topTextSearchSB.append(commonTerm.totalTermFreq);
        }
        return topTextSearchSB.toString();
    }
}
