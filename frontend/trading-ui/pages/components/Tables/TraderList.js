import DeleteIcon from '@mui/icons-material/Delete';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';
import axios from 'axios';
import { deleteTraderUrl } from '../../../util/constants';
import { getAllTraderUrl } from '../../../util/constants';
import { useState } from 'react';

const TraderList = ( {initialAllTraders, col} ) => {
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
                                <td> {trader.id}-{trader.firstName} </td>
                                <td> {trader.lastName} </td>
                                <td> {trader.email} </td>
                                <td> {trader.gender.toUpperCase().includes("f".toUpperCase()) ? <FemaleIcon /> : <MaleIcon />} {trader.id % 2 == 0 ? <p>Female</p> : <p>Male</p>} </td>
                                <td> {trader.country} </td>
                                <td> <small className="has-text-grey is-abbr-like" title="Oct 25, 2020">{trader.dob}</small></td>
                                <td>
                                    <button className="button is-small is-danger jb-modal" data-target="sample-modal" type="button" onClick={() => deleteTrader(trader.id)}>
                                        <span className="icon"><DeleteIcon /></span>
                                    </button>  
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