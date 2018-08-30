package com.sh.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.stock.dbservice.model.Quote;
import com.sh.stock.dbservice.model.Quotes;
import com.sh.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	
	private QuotesRepository quotesRepository;

	public DbServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}

	/**
	 * @param username
	 * @return
	 */
	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username){
		
		
		return getQuotesByUsername(username);
		
		
	}
	
	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes){
		
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(),quote)).forEach(quote->quotesRepository.save(quote));
		return getQuotesByUsername(quotes.getUserName());
	}
	
	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") String username) {
		List<Quote> quotes=quotesRepository.findByUserName(username);
		quotesRepository.delete(quotes.get(0));
		
		return getQuotesByUsername(username);
	}
	
	private List<String> getQuotesByUsername(@PathVariable("username") String username){
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}
