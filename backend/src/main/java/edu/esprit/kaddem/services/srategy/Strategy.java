package edu.esprit.kaddem.services.srategy;

import com.flickr4java.flickr.FlickrException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.InputStream;

@CrossOrigin(origins = "http://localhost:4200")

public interface Strategy <T> {
    T savePhoto(Integer id , InputStream photo , String titre) throws FlickrException;

}
