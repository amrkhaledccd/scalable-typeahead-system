package com.practice.typeaheadservice.contoller;

import com.practice.typeaheadservice.domain.Phrase;
import com.practice.typeaheadservice.payload.AddPhraseRequest;
import com.practice.typeaheadservice.service.TrieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TypeaheadController {

    @Autowired private TrieService trieService;

    @PostMapping("/trie/phrase")
    public ResponseEntity<?> addPhrase(@RequestBody AddPhraseRequest request) {
        log.info("Adding phrase {} to the trie", request.getText());
        trieService.addPhrase(request.getText());
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/trie/phrase/{prefix}")
    public ResponseEntity<List<String>> topPhrases(@PathVariable String prefix) {
        log.info("Retrieving top phrases for prefix {}", prefix);
        var phrases =
                trieService
                        .autocomplete(prefix).stream()
                        .map(Phrase::getText)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(phrases);
    }
}
