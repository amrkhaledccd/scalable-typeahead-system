package com.practice.gatewayservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TypeaheadClient {
    private String baseUri = "http://localhost:9000";
    @Autowired RestTemplate restTemplate;

    public void addPhrase( String phrase) {
        var uri =
                UriComponentsBuilder
                        .fromHttpUrl(baseUri)
                        .path("/trie/phrase")
                        .build()
                        .toUri();

        restTemplate.postForEntity(uri, phrase, String.class);
    }

    public List<String> topPhrases(String prefix) {
        var uri =
                UriComponentsBuilder
                        .fromHttpUrl(baseUri)
                        .path("/trie/phrase/{prefix}")
                        .buildAndExpand(prefix)
                        .toUri();

        return restTemplate.getForObject(uri, List.class);
    }
}
