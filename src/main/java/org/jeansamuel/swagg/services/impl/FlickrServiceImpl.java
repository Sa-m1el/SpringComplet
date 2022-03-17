package org.jeansamuel.swagg.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.services.FlickrService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

    @Value("${flickr.apiKey}")
    private String apikey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.apiKey}")
    private String appKey;

    @Value("${flickr.apiSecret}")
    private String appSecret;

    private Flickr flickr;

    private void Connect(){
        flickr = new Flickr(apikey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setToken(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId = flickr.getUploader().upload(photo, uploadMetaData);

        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }
}
