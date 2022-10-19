package com.khalil.eschool.services;

import java.io.InputStream;

public interface FlickrPhotoService {
    String savePhoto(InputStream photo, String title);
}
