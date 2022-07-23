export const BACKEND_URL = process.env.REACT_APP_BACKEND_URL || "http://localhost:8080";
export const TRADER_ENDPOINT = BACKEND_URL + "/trader";
export const getAllTraderUrl = TRADER_ENDPOINT + "/traders";
export const deleteTraderUrl = TRADER_ENDPOINT + "/delete/traderid";
export const QUOTE_ENDPOINT = BACKEND_URL + "/quote";
export const dailyListQuotesUrl = QUOTE_ENDPOINT + "/dailylist/";
export const getQuotes = QUOTE_ENDPOINT + "/iex/MarketData";