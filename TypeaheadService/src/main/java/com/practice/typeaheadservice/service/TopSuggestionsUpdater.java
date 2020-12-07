package com.practice.typeaheadservice.service;

import com.practice.typeaheadservice.domain.Phrase;
import com.practice.typeaheadservice.domain.TrieNode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopSuggestionsUpdater {

    public void update(Stack<TrieNode> stack) {
        while(!stack.isEmpty()) {
            var phrases = new ArrayList<List<Phrase>>();
            var current = stack.pop();
            for(var node: current.getChildren().values()) {
                if(node.getTopPhrases().size() > 0)
                    phrases.add(node.getTopPhrases());
            }
            if(current.getPhrase() != null)
                phrases.add(Arrays.asList(current.getPhrase()));

            var topPhrases = merge(phrases);
            current.setTopPhrases(topPhrases);
        }
    }

    private List<Phrase> merge(List<List<Phrase>> phrases) {
        Comparator<HeapItem> comparator = Comparator
                .<HeapItem>comparingInt(item -> phrases.get(item.arrayIdx).get(item.itemIdx).getFrequency())
                .reversed();

        var maxHeap = new PriorityQueue<>(comparator);
        var result = new ArrayList<Phrase>();

        for(int i = 0; i < phrases.size(); i++) {
            maxHeap.add(new HeapItem(i, 0));
        }

        while(maxHeap.size() > 0 &&  result.size() < 10) {
            var current = maxHeap.poll();
            result.add(phrases.get(current.arrayIdx).get(current.itemIdx));
            if(current.itemIdx + 1 < phrases.get(current.arrayIdx).size()) {
                maxHeap.add(new HeapItem(current.arrayIdx, current.itemIdx + 1));
            }
        }

        return result;
    }

    record HeapItem(int arrayIdx, int itemIdx) {}
}
