import axios from 'axios';
import { useState } from 'react';

const QuoteList = ( {initialAllQuotes, col} ) => {
    const [quotes, setQuotes] = useState(initialAllQuotes);

    return (
        <div className="table-wrapper overflow-x-auto">
            <table className="table overflow-x-auto is-striped is-hoverable is-fullwidth">
                <thead>
                    <tr>
                        {
                            col.map((col, key) => (
                                <th key={col + key}
                                    className="border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-600 uppercase">
                                    {col}
                                </th>
                            ))
                        }
                    </tr>
                </thead>
                <tbody>
                    {
                        quotes.length == 0 ? null:
                        quotes.map((quotes, key) => (
                            <tr key={quotes.id}>
                                <td> {quotes.id}</td>
                                <td> {quotes.lastPrice}</td>
                                <td> {quotes.bidPrice}</td>
                                <td> {quotes.bidSize}</td>
                                <td> {quotes.askPrice}</td>
                                <td> {quotes.askSize}</td>
                            </tr>
                        ))
                    }
                </tbody> 
            </table>
        </div>
    );
};
export default QuoteList;