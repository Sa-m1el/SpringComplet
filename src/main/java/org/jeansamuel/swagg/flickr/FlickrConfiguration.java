package org.jeansamuel.swagg.flickr;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

//@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apikey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apikey, apiSecret, new REST());
        OAuth10aService service = new ServiceBuilder(apikey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));

        final Scanner scanner = new Scanner(System.in);
        final OAuth1RequestToken request = service.getRequestToken();
        final String authUrl = service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");

        final String authVerifier = scanner.nextLine();
        OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("------------------------");
        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());




        return flickr;

    }


}