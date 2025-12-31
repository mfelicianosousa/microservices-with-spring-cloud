package br.com.mardevsystem.controller;

import br.com.mardevsystem.dto.Exchange;
import br.com.mardevsystem.environment.InstanceInformationService;
import br.com.mardevsystem.model.Book;
import br.com.mardevsystem.proxy.ExchangeProxy;
import br.com.mardevsystem.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book EndPoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    @Autowired
    private ExchangeProxy proxy;

    // http://localhost:8100/book-service/1/BRL


    //@GetMapping(value = "/books/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
   // @GetMapping(value = "/{id:\\d+}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    // public Book findBook(@PathVariable Long id, @PathVariable String currency) {
   //     ...
   // }
    @Operation(summary = "Find a specific Book by your ID")
    @GetMapping(value ="/{id:\\d+}/{currency}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){

        String port = informationService.retrieveServerPort();

        var book = repository.findById( id ).orElseThrow();

        Exchange exchange = proxy.getExchange(book.getPrice(),"USD", currency);

        book.setCurrency(currency);
        book.setPrice(exchange.getConvertedValue());
        // book.setEnvironment( port + " FEIGN");
        book.setEnvironment( "BOOK PORT: "+ port + " EXCHANGE PORT: "+exchange.getEnvironment());

        return book;
    }

    /*
    @GetMapping(value ="/{id}/{currency}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){

        String port = informationService.retrieveServerPort();

        var book = repository.findById( id ).orElseThrow();

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency );

        var response =new RestTemplate()
                .getForEntity("http://localhost:8000/exchange-service/"+
                                "{amount}/{from}/{to}", Exchange.class, params);
        Exchange exchange = response.getBody();

        book.setCurrency(currency);
        book.setPrice(exchange.getConvertedValue());
        book.setEnvironment( port );

        return book;
    }

    */

    /*@GetMapping(value ="/{id}/{currency}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){

        String port = informationService.retrieveServerPort();

        var book = repository.findById( id ).orElseThrow();
        book.setCurrency(currency);
        book.setEnvironment( port );

        return book;
    }*/


/*
   @GetMapping(value ="/{id}/{currency}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
            ){

        String port = informationService.retrieveServerPort();

        return new Book(
                1L, "Nigel Pouton","Docker Deep Dive",new Date(), 15.8, "BRL",port
        );
    }
    */


}
