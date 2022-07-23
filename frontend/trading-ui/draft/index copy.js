import Navbar from '../pages/components/NavBar';
import TraderList from '../pages/components/TraderList';
import AddTraderModal from '../pages/components/AddTraderModal';
import { useState } from 'react';
import { getAllTraderUrl } from '../util/constants'
import axios from 'axios';
import Head from 'next/head'

function Dashboard({ users, error }) {
    const [showAddTraderModal, setShowAddTraderModal] = useState(false);
    const [getTraders, setTraders] = useState(users);
    return (
        <div className="flex">
            
            <div className="flex-none">
                <Navbar />
            </div>
            <div className="flex-1">
                <div className='p-7 w-30'>
                    <div className='section'>
                        <div className="flex flex-col md:pr-10 md:mb-0 pr-0 w-full md:w-auto md:text-left text-center">
                            <h1 className="sm:text-3xl text-2xl font-medium title-font mb-2 text-gray-900">Dashboard</h1>
                            <p className="lg:w-1/2 w-full leading-relaxed text-gray-500">Welcome to dashboard view. It is here that you will be able to manage all trader's account! press the "Add trader" button to add a new trader and start managing it's account</p>
                        </div>
                    </div>

                    <div>
                        {showAddTraderModal ? <AddTraderModal closeModal={setShowAddTraderModal} traders={setTraders} allTraders={getTraders}/> : null}
                        <div className='relative'>
                            <TraderList users={users} />
                            <button className="absolute mr-12 top-1 right-1 bg-gray-100 inline-flex py-2 px-4 rounded-lg items-center hover:bg-gray-200 focus:outline-none">

                                <p onClick={() => {
                                    setShowAddTraderModal(!showAddTraderModal)
                                }}> + Add trader</p>

                            </button>
                        </div>

                    </div>

                </div>
            </div>
            
        </div>
    );
};

export default Dashboard;

const fetchData = async () =>
    await axios.get(getAllTraderUrl).then(res => ({
        error: false,
        users: res.data,
    })).catch(() => ({
        error: true,
        users: null,
    }),
    );

export const getServerSideProps = async () => {
    const data = await fetchData();
    console.log(data);
    return {
        props: data,
    };

}