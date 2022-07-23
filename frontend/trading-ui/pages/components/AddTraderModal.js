import { useState } from 'react';
import axios from 'axios';
import { TRADER_ENDPOINT } from '../../util/constants'
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';
import CountrySelect from './CountrySelect';
import Router from 'next/router';

const AddTraderModal = ({ closeModal, traders, allTraders }) => {
  const [getFirstName, setFirstName] = useState("");
  const [getLastName, setLastName] = useState("");
  const [getEmail, setEmail] = useState("");
  const [getGender, setGender] = useState("");
  const [getCountry, setCountry] = useState("");
  const [getDob, setDob] = useState("");
  const add = async () => {
    var letters = /^[A-Za-z]+$/;
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    const firstname = document.getElementById('first-name').value;
    const lastname = document.getElementById('last-name').value;
    const email = document.getElementById('email').value;
    const gender = getGender;
    const country = document.getElementById('country').value;
    const dob = document.getElementById('dob').value;

    resetUi();
    if (!firstname.match(letters)) {
      document.getElementById('first-name').classList.remove('appearance-none');
      document.getElementById('first-name').classList.add('border-red-600');
      document.getElementById('first-name').classList.add('red');
      document.getElementById('first-name-error').classList.remove('hidden');
      document.getElementById('first-name-error').innerText = "name can't contains non-alphebetic characters";
    }
    else if (!lastname.match(letters)) {
      document.getElementById('first-name').classList.remove('appearance-none');
      document.getElementById('first-name').classList.add('border-red-600');
      document.getElementById('first-name').classList.add('red');
      document.getElementById('first-name-error').classList.remove('hidden');
      document.getElementById('first-name-error').innerText = "name can't contains non-alphebetic characters";
    }
    else if (!email.match(mailformat)) {
      document.getElementById('email').classList.remove('appearance-none');
      document.getElementById('email').classList.add('border-red-600');
      document.getElementById('email').classList.add('red');
      document.getElementById('email-error').classList.remove('hidden');
      document.getElementById('email-error').innerText = 'please give a valid email';
    }
    else if (gender === "") {
      document.getElementById('gender-error').classList.remove('hidden');
      document.getElementById('gender-error').innerText = 'Please select a sexe';
    }
    else if (country === "") {
      document.getElementById('country-error').classList.remove('hidden');
      document.getElementById('country-error').innerText = 'Please select a country';
    }
    else if (dob === "") {
      document.getElementById('dob-error').classList.remove('hidden');
      document.getElementById('dob-error').innerText = 'Please select your date of birth';
    }
    else {
      await axios.get(TRADER_ENDPOINT + 
      '/firstname/' + firstname + 
      '/lastname/' + lastname + 
      '/dob/' + dob + 
      '/country/' + country + 
      '/email/' + email + 
      '/gender/' + gender).then(res => {
        console.log(res)
        allTraders.push(res.data.trader);
        traders(allTraders);
        closeModal(false);
        Router.reload(window.location.pathname);
      });
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

    document.getElementById('gender-error').classList.add('hidden');
    document.getElementById('country-error').classList.add('hidden');
    document.getElementById('dob-error').classList.add('hidden');
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
                  <p id='gender-error' className='text-red-500 text-xs italic hidden'>Please select a sexe</p>
                  <nav className='flex'>
                    <a className='button flex-1 mr-2' id='male' onClick={()=>{document.getElementById('male').classList.add('is-success');document.getElementById('female').classList.remove('is-success');setGender("Male")}}><MaleIcon/>Male</a>
                    <a className='button flex-1' id='female' onClick={()=>{document.getElementById('female').classList.add('is-success');document.getElementById('male').classList.remove('is-success');setGender("Female")}}><FemaleIcon/>Female</a>
                  </nav>
                </div>
              </div>
              <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                  <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                    Country
                  </label>
                  <CountrySelect/>
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