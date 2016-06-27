package com.mayank.springrestful.service;

import com.mayank.springrestful.Constants;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;

/**
 * Created by Mayank on 26/06/16.
 */
@Service
public class IndexService{

    static{
        try{
            createIndex();
        }catch (IOException ie){
            throw new ExceptionInInitializerError();
        }
    }

    @ExceptionHandler
    public static void createIndex() throws IOException{

        Directory directory = new SimpleFSDirectory(Paths.get(Constants.INDEX_DIR));

        if(!DirectoryReader.indexExists(directory)){
            IndexWriter indexWriter = new IndexWriter(FSDirectory.open(Paths.get(Constants.INDEX_DIR)), new IndexWriterConfig(new StandardAnalyzer()));

            File file = new File(Constants.CONTENT_FILE);
            Reader reader = new FileReader(file);

            // deleting the item, if already exists
            indexWriter.deleteDocuments(new Term(Constants.CONTENT, reader.toString()));

            Document doc = new Document();
            doc.add(new TextField(Constants.CONTENT, reader));

            // add the document to the index
            indexWriter.addDocument(doc);

            indexWriter.close();
        }

    }
}
