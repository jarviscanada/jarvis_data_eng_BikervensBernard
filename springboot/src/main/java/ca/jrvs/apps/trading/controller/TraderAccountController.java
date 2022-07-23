package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.controller.util.ResponseExeptionUtil;
import ca.jrvs.apps.trading.dao.TraderEntityDao;
import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import ca.jrvs.apps.trading.service.TraderAccountService;
import ca.jrvs.apps.trading.view.TraderAccountView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@org.springframework.stereotype.Controller
@io.swagger.annotations.Api(value = "Trader" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/trader")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TraderAccountController {

    private TraderAccountService service;

    @org.springframework.beans.factory.annotation.Autowired
    public TraderAccountController(TraderAccountService service) {this.service =service;}


    @ApiOperation(value = "Get all traders", notes = "fetch all traders in database an returns")
    @GetMapping(path = "/traders", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK) @ResponseBody
    public Iterable<TraderEntity> traders() {
        return this.service.getAllTraders();
    }

    @ApiOperation(value = "Create trader and an account", notes = "traderId and accountId are auto generated by database, they should be mostly the same")
    @GetMapping(path = "/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}/gender/{gender}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED) @ResponseBody
    public TraderAccountView createTrader(@PathVariable String firstname, @PathVariable String lastname,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") String dob,
                                          @PathVariable String country, @PathVariable String email, @PathVariable String gender) {
        try {
            TraderEntity savedTraderEntity = new TraderEntity();
            savedTraderEntity.setFirstName(firstname);
            savedTraderEntity.setLastName(lastname);
            savedTraderEntity.setDob(Date.valueOf(dob));
            savedTraderEntity.setCountry(country);
            savedTraderEntity.setEmail(email);
            savedTraderEntity.setGender(gender);
            return this.service.createTraderAndAccount(savedTraderEntity);
        }catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }

    @ApiOperation(value = "Delete a trader", notes = "Delete a trader if its account amount is 0 and no open positions.")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Unable to delete the trader")})
    @DeleteMapping(path = "/delete/traderid/{traderId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK) @ResponseBody
    public void deleteTrader(@PathVariable Integer traderId) {
        try {
            this.service.deleteTraderById(traderId);
        }catch (Exception e) {
            throw ResponseExeptionUtil.getResponseStatusExecption(e);
        }
    }


    @ApiOperation(value = "Deposit fund", notes = "Add fund to the trader account")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "trade id not found"),
            @ApiResponse(code = 400, message = "Unable to deposit fund to the trader")
    })
    @GetMapping(path = "/deposit/trader/{traderId}/amount/{amount}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK) @ResponseBody
    public AccountEntity deposit(@PathVariable Integer traderId, @PathVariable Double amount){
        return this.service.deposit(traderId,amount);
    }

    @ApiOperation(value = "Withdraw fund", notes = "Withdraw fund to the trader account")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "trade id not found"),
            @ApiResponse(code = 400, message = "Unable to Withdraw fund from trader's account")
    })
    @GetMapping(path = "/withdraw/trader/{traderId}/amount/{amount}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.OK) @ResponseBody
    public AccountEntity withdraw(@PathVariable Integer traderId, @PathVariable Double amount){
        return this.service.withdraw(traderId,amount);
    }
}
