package com.practice.typeaheadservice.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TrieNode {
    @NonNull
    private char ch;
    private Phrase phrase;
    private List<Phrase> topPhrases = new ArrayList<>();
    private Map<Character, TrieNode> children = new HashMap<>();
}
