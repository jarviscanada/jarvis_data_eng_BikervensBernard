package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.QuoteContainer;
import ca.jrvs.apps.trading.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/quote") /*Root path the controller (optional) e.g., http://localhost:8080/quote*/
public class QuoteController {

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService service) {this.quoteService =service;}

    @GetMapping(path = "/iex/ticker/{ticker}") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public QuoteContainer getQuote(@PathVariable String ticker) {
        try {
            return null;
            //return quoteService.findIexQuoteByTicker(ticker);
        }catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }
}
