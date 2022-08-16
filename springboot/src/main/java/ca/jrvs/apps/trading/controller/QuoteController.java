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

import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
@io.swagger.annotations.Api(value = "quote" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/quote")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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

    @ApiOperation(value = "Update quote table against iex data", notes = "Update all quotes in the quote table. Use IEX trading API as market data source")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @GetMapping(path = "/iex/MarketData") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public List<QuoteEntity> updateMarketData() {
        try {
            quoteService.updateMarketData();
            return quoteService.findAllQuotes();
        } catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }

    @ApiOperation(value = "Add in backend the specified daily list quotes", notes = "Fetch iex data fo specified quotes, save data to table and return list")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @GetMapping(path = "/dailylist/{tickers}") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public List<QuoteEntity> dailyList(@PathVariable String tickers) {
        try {
            String[] initDailyList = tickers.split(",");
            quoteService.saveQuotes(Arrays.asList(initDailyList));
            quoteService.updateMarketData();
            return quoteService.findAllQuotes();
        } catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }

    @ApiOperation(value = "Remove in backend the specified daily list quotes")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "404 :/ Ticker not found")})
    @DeleteMapping(path = "/dailylist/delete/{tickers}") @ResponseStatus(HttpStatus.OK) @ResponseBody
    public List<QuoteEntity> deleteFromDailyList(@PathVariable String tickers) {
        try {
            String[] list = tickers.toUpperCase().split(",");
            return quoteService.deleteQuotes(list);
        } catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }
}
