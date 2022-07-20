import Navbar from '../components/NavBar'
import TraderList from '../components/TraderList'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAdd } from '@fortawesome/free-solid-svg-icons'
import AddTraderModal from '../components/AddTraderModal'
import { useState, createContext, useContext } from "react"

export default function Dashboard() {
    const [showAddTraderModal, setShowAddTraderModal] = useState(false);
    return (
        <div className="flex">
            <div className="flex-none">
                <Navbar />
            </div>
            <div className="flex-1 w-64">
                <div className='p-7'>
                    <h1 className='uppercase is-inline-block'>
                        Dashboard
                    </h1>
                    <button type="button" className="is-inline-block p-2 bg-orange-600 border rounded-xl">
                        <p onClick={() => {
                            setShowAddTraderModal(!showAddTraderModal)
                        }}> + Add trader</p>
                    </button>
                    <div>
                        {showAddTraderModal?  <AddTraderModal /> : null}
                        <TraderList />
                    </div>
                </div>
            </div>
        </div>
    );
};