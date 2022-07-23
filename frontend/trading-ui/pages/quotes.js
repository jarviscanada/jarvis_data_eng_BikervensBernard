import Navbar from './components/NavBar';
import { useState } from 'react';
import { getQuotes } from '../util/constants'
import axios from 'axios';
import Head from 'next/head'
import QuoteList from './components/QuoteList';

function quotes({ quotes, error }) {
    const [getQuotes, setQuotes] = useState(quotes);
    const colForQuotePageTable = ["Tiker", "Last price", "Bid Price", "Bid Size", "Ask Price", "Ask Size"];

    return (
        <div className="flex">
            <Head>
                <title>Trading app</title>
                <meta name="React application which shows data obtained from apis. Users should be able to see: a list of all traders, a daily list of quotes, details of a specific trader, should be able to create a new trader/quote/withdrawal of funds" content="Generated by create next app" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="bg-white dark:bg-gray-800 flex flex-col w-18 h-screen px-4 py-8 overflow-y-auto border-r">
                <div className="flex flex-col justify-between mt-2">
                    <Navbar />
                </div>
            </div>
            <div className="w-full h-full sm:p-4 md:p-8 overflow-y-auto">
                <div className=" items-center justify-center border-4 border-dotted">
            
                    <h1 className="sm:text-3xl text-2xl font-medium title-font mb-2 text-gray-900">Quotes</h1>
                    <p className="w-full leading-relaxed text-gray-500">IEX Cloud is a platform that makes financial data and services accessible to everyone.</p>
                    <button className='button bg-gray-100 my-3 p-2 rounded-lg hover:bg-gray-200'><p onClick={() => { setShowAddTraderModal(!showAddTraderModal) }}> + Add quote</p></button>
                    <QuoteList initialAllQuotes={getQuotes} col={colForQuotePageTable}/>
                </div>
            </div>

        </div>
    );
};
export default quotes;

const fetchData = async () =>
    await axios.get(getQuotes).then(res => ({
        error: false,
        quotes: res.data,
    })).catch(() => ({
        error: true,
        quotes: [],
    }),
);

export const getServerSideProps = async () => {
    const data = await fetchData();
    console.log(data);
    return {
        props: data,
    };

}