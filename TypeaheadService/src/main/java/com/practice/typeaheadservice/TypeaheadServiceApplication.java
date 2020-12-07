package com.practice.typeaheadservice;

import com.practice.typeaheadservice.domain.Phrase;
import com.practice.typeaheadservice.service.TrieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TypeaheadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypeaheadServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(TrieService trieService) {
		return args -> {
			trieService.addPhrase("How are you doing?");
			trieService.addPhrase("How much does it cost?");
			trieService.addPhrase("What is your name?");
			trieService.addPhrase("Where are you from?");
			trieService.addPhrase("Where do you live?");
			trieService.addPhrase("How old are you?");
			trieService.addPhrase("How much do you need?");
			trieService.addPhrase("Where do you go?");
			trieService.addPhrase("When will we meet?");
			trieService.addPhrase("How are you doing?");

			trieService.addPhrase("How");
			trieService.addPhrase("How are");
			trieService.addPhrase("How are");
			trieService.addPhrase("How are");
			trieService.addPhrase("How");
			trieService.addPhrase("How much");

			System.out.println("======== how ========");
			trieService.autocomplete("how").stream().map(Phrase::getText).forEach(System.out::println);
			System.out.println("======== how mu ========");
			trieService.autocomplete("how mu").forEach(System.out::println);
			System.out.println("======== whe =========");
			trieService.autocomplete("whe").forEach(System.out::println);
			System.out.println("======== where a ==========");
			trieService.autocomplete("where a").forEach(System.out::println);
		};
	}
}
