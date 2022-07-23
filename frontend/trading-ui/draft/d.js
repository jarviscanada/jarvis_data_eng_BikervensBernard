import Navbar from '../pages/components/NavBar';
import TraderList from '../pages/components/TraderList';
import AddTraderModal from '../pages/components/AddTraderModal';
import { useEffect, useState } from 'react';
import axios from 'axios';
const fetchData = async () => await axios.get('https://jsonplaceholder.typicode.com/users')
  .then(res => ({
    users: res.data,
  }))
  .catch(() => ({
    }),
  );

function d({users}) {
    const [showAddTraderModal, setShowAddTraderModal] = useState(false);    
    return (
        <div className="flex">
            <div className="flex-none">
                <Navbar />
            </div>
            <div className="flex-1 w-64">
                <div className='p-7'>
                    <section class="text-gray-600 body-font">
                        <div class="container px-5 py-24 mx-auto">
                            <div class="flex flex-wrap w-full">
                                <div class="lg:w-1/2 w-full mb-6 lg:mb-0">
                                    <h1 class="sm:text-3xl text-2xl font-medium title-font mb-2 text-gray-900">Dashboard</h1>
                                    <div class="h-1 w-2/4 bg-indigo-500 rounded"></div>
                                    <button type="button" className="mt-4 button is-success">
                                        <p onClick={() => {
                                            setShowAddTraderModal(!showAddTraderModal)
                                        }}> + Add trader</p>
                                    </button>
                                </div>
                                <p class="lg:w-1/2 w-full leading-relaxed text-gray-500">Welcome to dashboard view. It is here that you will be able to manage all trader's account! press the "Add trader" button to add a new trader and start managing it's account</p>
                            </div>
                        </div>
                    </section>

                    <div>
                        {showAddTraderModal ? <AddTraderModal closeModal={setShowAddTraderModal} /> : null}
                        <TraderList/>
                    </div>

                </div>
            </div>
        </div>
    );
};

export const getServerSideProps = async () => {
    const data = await fetchData();
    return {
      props: data,
    };
  }

export default d;