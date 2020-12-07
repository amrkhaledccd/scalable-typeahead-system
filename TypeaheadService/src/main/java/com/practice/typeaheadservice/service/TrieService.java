package com.practice.typeaheadservice.service;

import com.practice.typeaheadservice.domain.Phrase;
import com.practice.typeaheadservice.domain.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Service
public class TrieService {
    @Autowired
    private TopSuggestionsUpdater topSuggestionsUpdater;
    public TrieNode root = new TrieNode('#');

    public void addPhrase(String text) {
        var nodesToUpdate = new Stack<TrieNode>();
        TrieNode cursor = root;

        for(int i = 0; i < text.length(); i++) {
            var ch = Character.toLowerCase(text.charAt(i));
            if(!cursor.getChildren().containsKey(ch)) {
                cursor.getChildren().put(ch, new TrieNode(ch));
            }

            if(i == text.length() - 1) {
                if(cursor.getChildren().get(ch).getPhrase() == null) {
                    cursor.getChildren().get(ch).setPhrase(new Phrase(text, 0));
                }
                var freq = cursor.getChildren().get(ch).getPhrase().getFrequency();
                cursor.getChildren().get(ch).getPhrase().setFrequency(freq + 1);
            }
            cursor = cursor.getChildren().get(ch);
            nodesToUpdate.push(cursor);
        }
        topSuggestionsUpdater.update(nodesToUpdate);
    }

    public List<Phrase> autocomplete(String prefix) {
        TrieNode cursor = root;
        for(int i = 0; i < prefix.length(); i++) {
            char ch = Character.toLowerCase(prefix.charAt(i));
            if(!cursor.getChildren().containsKey(ch)) {
                return Collections.EMPTY_LIST;
            }
            cursor = cursor.getChildren().get(ch);
        }
        return cursor.getTopPhrases();
    }
}
