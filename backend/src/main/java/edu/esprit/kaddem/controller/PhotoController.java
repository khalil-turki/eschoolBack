package edu.esprit.kaddem.controller;

import com.flickr4java.flickr.FlickrException;
import edu.esprit.kaddem.services.srategy.StrategyPhotoContext;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin(origins = "*")
@RestController
public class PhotoController {

    private StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @PostMapping("/photo/save/{id}/{title}/{context}")
    Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Integer id, @RequestPart("file") MultipartFile photo, @PathVariable(
            "title") String title) throws IOException,
            FlickrException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);

    }

    }






