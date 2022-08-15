import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';
import axios from 'axios';
import { deleteTraderUrl, getAllTraderUrl } from '../../../util/constants';
import { useState } from 'react';
import Link from 'next/link';

const TraderList = ( {initialAllTraders, col, actions} ) => {
    const [traders, setTraders] = useState(initialAllTraders);
    const deleteTrader = async (id) => {
        await axios.delete(deleteTraderUrl + '/' + id).then(() => {
            axios.get(getAllTraderUrl).then(res => {
                setTraders(res.data);
            });
        });
    }
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
                        traders.map((trader, key) => (
                            <tr key={trader.id}>
                                <td> {trader.firstName} </td>
                                <td> {trader.lastName} </td>
                                <td> {trader.email} </td>
                                {
                                    trader.gender.toUpperCase().includes("f".toUpperCase()) ? 
                                    <td> 
                                        <FemaleIcon /> <p>Female</p>
                                    </td>
                                    : 
                                    <td> 
                                        <MaleIcon /> <p>Male</p>
                                    </td>
                                }
                                <td> {trader.country} </td>
                                <td> <small className="has-text-grey is-abbr-like" title="Oct 25, 2020">{trader.dob}</small></td>
                                <td> 
                                    {
                                        actions == "update" ? 
                                        <button className="button is-small is-warning jb-modal" data-target="sample-modal" type="button">
                                            <span className="icon"><Link href={`/accountUpdate/?id=${encodeURIComponent(trader.id)}`}><EditIcon /></Link> </span>
                                        </button> : 
                                        <button className="button is-small is-danger jb-modal" data-target="sample-modal" type="button" onClick={() => deleteTrader(trader.id)}>
                                            <span className="icon"><DeleteIcon /></span>
                                        </button>
                                    } 
                                </td>
                            </tr>
                        ))
                    }
                </tbody> 
            </table>
        </div>
    );
};
export default TraderList;