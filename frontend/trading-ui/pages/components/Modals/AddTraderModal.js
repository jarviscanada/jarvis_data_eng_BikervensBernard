import { useState } from 'react';
import { TRADER_ENDPOINT, getAllTraderUrl } from '../../../util/constants'
import axios from 'axios';
import Router from 'next/router';
import TraderInformationForm from '../Forms/TraderInformationForm';

const AddTraderModal = ({ closeModal, setTraders }) => {
  const [getGender, setGender] = useState("");
  const add = () => {
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
      axios.get(TRADER_ENDPOINT + 
      '/firstname/' + firstname + 
      '/lastname/' + lastname + 
      '/dob/' + dob + 
      '/country/' + country + 
      '/email/' + email + 
      '/gender/' + gender).then((res) => {
        axios.get(getAllTraderUrl).then((res) => {
          setTraders(res.data);
          closeModal(false);
          Router.reload(window.location.pathname);
        });
      });
    }
  }
  const resetUi = () => {

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
            <TraderInformationForm setGender={setGender}/>
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