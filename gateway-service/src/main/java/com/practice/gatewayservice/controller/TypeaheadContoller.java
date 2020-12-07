package com.practice.gatewayservice.controller;

import com.practice.gatewayservice.client.TypeaheadClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
public class TypeaheadContoller {
    @Autowired private TypeaheadClient typeaheadClient;

    @PostMapping("/trie/phrase")
    public ResponseEntity<?> addPhrase(@RequestBody String phrase) {
        log.info("Adding phrase {} to the trie", phrase);
        typeaheadClient.addPhrase(phrase);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/trie/phrase/{prefix}")
    public ResponseEntity<List<String>> topPhrases(@PathVariable String prefix) {
        log.info("retrieving top phrases for prefix {}", prefix);
        return ResponseEntity.ok(typeaheadClient.topPhrases(prefix));
    }
}
