export const BACKEND_URL = process.env.REACT_APP_BACKEND_URL || "http://localhost:8080";
export const tradersUrl = BACKEND_URL + "/dashboard/traders";
export const createTraderUrl = BACKEND_URL + "/trader";
export const deleteTraderUrl = BACKEND_URL + "/trader/traderId";
export const dailyListQuotesUrl = BACKEND_URL + "/quote/dailyList";
export const traderAccountUrl = BACKEND_URL + "/dashboard/profile/traderId/";
export const depositFundsUrl = BACKEND_URL + "/trader/deposit/traderId/";
export const withdrawFundsUrl = BACKEND_URL + "/trader/withdraw/traderId/";