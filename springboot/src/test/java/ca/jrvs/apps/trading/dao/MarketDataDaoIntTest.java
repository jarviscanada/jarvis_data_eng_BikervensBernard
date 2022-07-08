package ca.jrvs.apps.trading.dao;

public class MarketDataDaoIntTest {
/*
    private MarketDataDao dao;
    private static final String API = "https://cloud.iexapis.com/";
    private static final String END_POINT = "v1/";

    @Before
    public void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost(this.API+this.END_POINT);
        marketDataConfig.setToken(System.getenv("token"));
        this.dao = new MarketDataDao(cm,marketDataConfig);
    }

    @Test
    public void findById_valid() {
        Optional optional = dao.findById("aapl");
        assertTrue(optional.isPresent());
        assertTrue(optional.get()!=null);
        assertTrue(optional.get() instanceof QuoteContainer);
        assertEquals("Apple Inc",((QuoteContainer) optional.get()).getQuote().getCompanyName());
    }

    @Test
    public void findById_invalid() {
        Optional optional = null;
        try {
            optional = dao.findById("very edgy nft compagny");
        } catch (RuntimeException e) {
            assertTrue(true);
            assertTrue(e.getMessage().contains("404"));
        }
        assertTrue(optional==null);
    }

    @Test
    public void findAllById_valid() {
        Iterable<QuoteContainer> all = dao.findAllById(Arrays.asList("aapl,fb"));
        assertTrue(all != null);
        all.forEach((quote) -> assertTrue(
                quote.getQuote().getCompanyName().equals("Apple Inc") || quote.getQuote().getCompanyName().equals("Meta Platforms Inc - Class A"))
        );
    }

    @Test
    public void findAllById_invalid() {
        Iterable<QuoteContainer> all = dao.findAllById(Arrays.asList("aapl,f1awd214b"));
        assertTrue(all != null);
        all.forEach((quote) -> assertTrue(
                quote.getQuote().getCompanyName().equals("Apple Inc"))
        );
    }*/
}