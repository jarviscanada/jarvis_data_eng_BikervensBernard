package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountEntityDao;
import ca.jrvs.apps.trading.dao.PositionEntityDao;
import ca.jrvs.apps.trading.dao.SecurityOrderEntityDao;
import ca.jrvs.apps.trading.dao.TraderEntityDao;
import ca.jrvs.apps.trading.model.databaseEntity.AccountEntity;
import ca.jrvs.apps.trading.model.databaseEntity.SecurityOrderEntity;
import ca.jrvs.apps.trading.model.databaseEntity.TraderEntity;
import ca.jrvs.apps.trading.view.TraderAccountView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.ArrayList;
import java.util.List;

@org.springframework.transaction.annotation.Transactional()
@org.springframework.stereotype.Service
public class TraderAccountService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private final TraderEntityDao traderEntityDao;
    private final AccountEntityDao accountEntityDao;
    private final PositionEntityDao positionEntityDao;
    private final SecurityOrderEntityDao securityOrderEntityDao;

    @Autowired
    public TraderAccountService(TraderEntityDao traderEntityDao,
                                AccountEntityDao accountEntityDao,
                                PositionEntityDao positionEntityDao,
                                SecurityOrderEntityDao securityOrderEntityDao) {
        this.traderEntityDao = traderEntityDao;
        this.accountEntityDao = accountEntityDao;
        this.positionEntityDao = positionEntityDao;
        this.securityOrderEntityDao = securityOrderEntityDao;
    }

    public TraderAccountView createTraderAndAccount(TraderEntity trader) {
        trader = traderEntityDao.save(trader);

        // account
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setTraderId(trader.getId());
        accountEntity.setAmount(0d);
        accountEntity = accountEntityDao.findByTraderId(trader.getId()).orElse(accountEntity);

        accountEntityDao.save(accountEntity);

        // no position open at creation

        // no orders open at creation
        return new TraderAccountView(trader, accountEntity);
    }

    public void deleteTraderById(Integer id) {
        TraderEntity toRemove = this.traderEntityDao.findById(id).orElse(null);
        if (toRemove == null) {
            throw new DataRetrievalFailureException("unable to find trader with id");
        }

        AccountEntity account = this.accountEntityDao.findByTraderId(toRemove.getId()).orElse(null);
        if (account != null) {
            if (account.getAmount() == 0) {
                //delete filled order
                List<SecurityOrderEntity> orders = (ArrayList<SecurityOrderEntity>) this.securityOrderEntityDao.findAllForTraderId(toRemove.getId());
                if (orders != null) {
                    if (orders.size() >= 1) {
                        List<SecurityOrderEntity> notFilled = (List<SecurityOrderEntity>) orders.stream().filter(i->!i.getStatus().equals(SecurityOrderEntity.FILLED));
                        if (notFilled == null) {
                            //every order is filled. nothing further to do
                        }
                        else if (!notFilled.isEmpty()) {
                            throw new IllegalArgumentException("Trader has orders that need to be closed before deletion");
                        }
                    }
                }

                this.accountEntityDao.deleteById(toRemove.getId());
            }
            else {
                throw new IllegalArgumentException("Balance should be 0$ before deletion");
            }
        }
        // no account = direct delete
        // or account exist but no order to delete
        this.traderEntityDao.deleteById(toRemove.getId());

    }

    public AccountEntity deposit(Integer traderId, Double amount) {
        TraderEntity trader = this.traderEntityDao.findById(traderId).orElse(null);
        if (trader == null) {
            throw new DataRetrievalFailureException("unable to find trader with id");
        }
        AccountEntity account = this.accountEntityDao.findById(trader.getId()).orElse(null);
        if (account == null) {
            throw new DataRetrievalFailureException("unable to find account with id");
        }
        account.setAmount(account.getAmount()+amount);
        return this.accountEntityDao.save(account);
    }

    public AccountEntity withdraw(Integer traderId, Double amount) {
        TraderEntity trader = this.traderEntityDao.findById(traderId).orElse(null);
        if (trader == null) {
            throw new DataRetrievalFailureException("unable to find trader with id");
        }
        AccountEntity account = this.accountEntityDao.findById(trader.getId()).orElse(null);
        if (account == null) {
            throw new DataRetrievalFailureException("unable to find account with id");
        }
        if (account.getAmount() - amount < 0) {
            throw new DataRetrievalFailureException("unable to remove this much account with id");
        }
        account.setAmount(account.getAmount() - amount);
        return this.accountEntityDao.save(account);
    }
}
