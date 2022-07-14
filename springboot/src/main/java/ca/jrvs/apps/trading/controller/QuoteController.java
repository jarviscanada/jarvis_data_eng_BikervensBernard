package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.controller.util.ResponseExeptionUtil;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.databaseEntity.QuoteEntity;
import ca.jrvs.apps.trading.service.QuoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@io.swagger.annotations.Api(value = "quote" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/quote")
public class QuoteController {

    private QuoteService quoteService;

    @org.springframework.beans.factory.annotation.Autowired
    public QuoteController(QuoteService service) {this.quoteService =service;}

    @ApiOperation(value = "Show iexQuote", notes = "Show iexQuote for a given ticker")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @GetMapping(path = "/iex/ticker/{ticker}") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public IexQuote getQuote(@PathVariable String ticker) {
        try {
            return quoteService.findIexQuoteByTicker(ticker);
        }catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }

    }

    @ApiOperation(
            value = "Update quote table entity against iex data",
            notes = "Quote table entity must exist " +
                    "Using IEX trading API as market data source")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @PutMapping(path = "/iex/MarketData") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public List<QuoteEntity> updateMarketData() {
        try {
            quoteService.updateMarketData();
            return quoteService.findAllQuotes();
        } catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }

    @ApiOperation(
            value = "Insert quote into database",
            notes = "Using IEX trading API as market data source "+
                    "if quote already exist in database return updated/recent values for this quote entity")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @GetMapping(path = "/iex/addQuoteToDb/{ticker}") @ResponseStatus(HttpStatus.CREATED) @ResponseBody
    public List<QuoteEntity> addQuoteToDb(@PathVariable String ticker) {
        try {
            IexQuote quote = quoteService.findIexQuoteByTicker(ticker);
            if (quoteService.findAllQuotes().stream().filter(i->i.getTicker().equals(quote.getSymbol())).count() ==0) {
                quoteService.buildAndSaveQuoteDbEntityFromIexQuote(quote);
            } else {
                quoteService.updateMarketData();
            }
            return quoteService.findAllQuotes().stream().filter(i->i.getTicker().equals(quote.getSymbol())).collect(Collectors.toList());
        } catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }
}
