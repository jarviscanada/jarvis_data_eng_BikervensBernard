import { useContext } from 'react';

const AddTraderModal = ({closeModal}) => {
  return (
    <div className="relative z-10" aria-labelledby="modal-title" role="dialog" aria-modal="true">

      <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" ></div>
      <div className="fixed z-10 inset-0 overflow-y-auto" >
        
        <div className="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0">
          <div className="relative bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:max-w-lg sm:w-full">
            <div className='w-5 h-5 bg-black text-white p-4' onClick={() => closeModal(false)}><p className='absolute left-3 top-1'>x</p></div>
            <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
              <div className="sm:flex sm:items-start">
                <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                  <form className="w-full">
                    <div className="flex flex-wrap-mx-3">
                      <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-first-name">
                          First Name
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="text" placeholder="Jane" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                      <div className="w-full md:w-1/2 px-3">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-last-name">
                          Last Name
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" placeholder="Doe" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                    </div>
                    <div className="flex flex-wrap-mx-3">
                      <div className="w-full xm:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-first-Email">
                          Email
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white" id="grid-first-Email" type="email" placeholder="Jane@gmail.com" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                    </div>
                    <div className="flex flex-wrap-mx-3">
                      <div className="w-full xm:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-first-name">
                          Password
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="password" placeholder="Secret!?Password" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                    </div>
                    <div className="flex flex-wrap-mx-3">
                      <div className="w-full xm:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-first-name">
                          Country
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="text" placeholder="Canada" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                    </div>
                    <div className="flex flex-wrap-mx-3">
                      <div className="w-full xm:w-1/2 px-3 mb-3 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-first-date">
                          Date of birth
                        </label>
                        <input className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white" id="grid-first-date" type="date" placeholder="1999-01-01" />
                        <p className="text-red-500 text-xs italic">Please fill out this field.</p>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
              <button type="button" className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"  onClick={() => closeModal(false)}>Cancel</button>
              <button type="button" className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">Add</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddTraderModal;