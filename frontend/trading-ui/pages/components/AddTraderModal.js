import { useState } from 'react';
import axios from 'axios';
import { createTraderUrl } from '../../util/constants'
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';

const AddTraderModal = ({ closeModal, traders, allTraders }) => {
  const [getFirstName, setFirstName] = useState('Jon');
  const [getLastName, setLastName] = useState('Doe');
  const [getEmail, setEmail] = useState('Jon@gmail.com');
  const [getGender, setGender] = useState('Male');
  const [getCountry, setCountry] = useState('Montreal, Canada');
  const [getDob, setDob] = useState('1999-10-11');
  const add = async () => {
    var letters = /^[A-Za-z]+$/;
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    setFirstName(document.getElementById('first-name').value);
    setLastName(document.getElementById('last-name').value);
    setEmail(document.getElementById('email').value);
    setGender(document.getElementById('gender').value);
    setCountry(document.getElementById('country').value);
    setDob(document.getElementById('dob').value);

    resetUi();
    if (!document.getElementById('first-name').value.match(letters)) {
      document.getElementById('first-name').classList.remove('appearance-none');
      document.getElementById('first-name').classList.add('border-red-600');
      document.getElementById('first-name').classList.add('red');
      document.getElementById('first-name-error').classList.remove('hidden');
      document.getElementById('first-name-error').innerText = "name can't contains non-alphebetic characters";
    }
    else if (!document.getElementById('last-name').value.match(letters)) {
      document.getElementById('first-name').classList.remove('appearance-none');
      document.getElementById('first-name').classList.add('border-red-600');
      document.getElementById('first-name').classList.add('red');
      document.getElementById('first-name-error').classList.remove('hidden');
      document.getElementById('first-name-error').innerText = "name can't contains non-alphebetic characters";
    }
    else if (!document.getElementById('email').value.match(mailformat)) {
      document.getElementById('email').classList.remove('appearance-none');
      document.getElementById('email').classList.add('border-red-600');
      document.getElementById('email').classList.add('red');
      document.getElementById('email-error').classList.remove('hidden');
      document.getElementById('email-error').innerText = 'please give a valid email';
    }
    else {
      addRequest();
      closeModal(false);
    }
  }

  const resetUi = async () => {

    document.getElementById('first-name').classList.remove('appearance-none');
    document.getElementById('first-name').classList.remove('border-red-600');
    document.getElementById('first-name-error').classList.add('hidden');

    document.getElementById('last-name').classList.remove('appearance-none');
    document.getElementById('last-name').classList.remove('border-red-600');
    document.getElementById('last-name-error').classList.add('hidden');

    document.getElementById('email').classList.remove('appearance-none');
    document.getElementById('email').classList.remove('border-red-600');
    document.getElementById('email-error').classList.add('hidden');
  }

  const addRequest = async () => {
    await axios.get(createTraderUrl + '/firstname/' + getFirstName + '/lastname/' + getLastName + '/dob/' + getDob + '/country/' + getCountry + '/email/' + getEmail + '/gender/' + getGender).then(res => {
      console.log(res)
      allTraders.push(res.data.trader);
      traders(allTraders);
    });
  }

  return (
    <div className='modal is-active'>
      <div className='modal-background' onClick={() => closeModal(false)} />
      <div className='modal-card'>
        <header className='modal-card-head'>
          <p className='modal-card-title'>Add Account</p>
          <button className='delete' onClick={() => closeModal(false)} />
        </header>
        <section className='modal-card-body'>
          <div className='content'>
            <form className='w-full'>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full md:w-1/2 px-3 mb-6 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    First Name
                  </label>
                  <input className='appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white' id='first-name' type='text' placeholder='Jane' />
                  <p id='first-name-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
                <div className='w-full md:w-1/2 px-3'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Last Name
                  </label>
                  <input className='appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white focus:border-gray-500' id='last-name' type='text' placeholder='Doe' />
                  <p id='last-name-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
              </div>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Email
                  </label>
                  <input className='appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white' id='email' type='email' placeholder='Jane@gmail.com' />
                  <p id='email-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
              </div>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Sexe
                  </label>
                  <input className='appearance-none block w-full hidden bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white' id='gender' type='text' placeholder='Male' />
                  <nav class='pagination' role='navigation' aria-label='pagination'>
                    <a class='pagination-previous' id='male'><MaleIcon/>Male</a>
                    <a class='pagination-next' id='female'><FemaleIcon/>Female</a>
                  </nav>
                </div>
              </div>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Country
                  </label>
                  <input className='appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white' id='country' type='text' placeholder='Canada' />
                  <p id='country-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
              </div>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-3 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Date of birth
                  </label>
                  <input className='appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:bg-white' id='dob' type='date' placeholder='YYYY-MM-DD' />
                  <p id='dob-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
              </div>
            </form>
          </div>
        </section>
        <footer className='modal-card-foot'>
          <a className='button is-success' onClick={() => add()}>Save</a>
          <a className='button is-danger' onClick={() => closeModal(false)}>Cancel</a>
        </footer>
      </div>
    </div>
  );
};



export default AddTraderModal;