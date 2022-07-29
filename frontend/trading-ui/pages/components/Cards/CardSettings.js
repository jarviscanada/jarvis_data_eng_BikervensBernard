import React from "react";
import TraderInformationForm from "../Forms/TraderInformationForm";
import Router from 'next/router';
import axios from "axios";
import Snackbar from '@mui/material/Snackbar';
import { useState, useEffect } from 'react';
import { updateTraderUrl, getAllTraderUrl } from '../../../util/constants';

export default function CardSettings({ id }) {
  const [gender, setGender] = useState("");
  const [trader, setTrader] = useState({});
  const [open, setOpen] = useState(false);

  const modify = () => {
    const letters = /^[A-Za-z]+$/;
    const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    const firstname = document.getElementById('first-name').value;
    const lastname = document.getElementById('last-name').value;
    const email = document.getElementById('email').value;
    const country = document.getElementById('country').value;

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

    else {
      const string = "/" + id + "/firstname/" + firstname + "/lastname/" + lastname + "/country/" + country + "/email/" + email + "/gender/" + gender;
      axios.put(updateTraderUrl + string).then((res) => {setOpen(true);});
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
  }
  useEffect(() => {
    axios.get(getAllTraderUrl).then(res => {
      const t = res.data.filter((e) => { return e.id == id; })[0];
      setTrader(t);
      setGender(t.gender);
      if (t.gender == "male" || t.gender == "Male") {
        document.getElementById('male').classList.add('is-success');
        document.getElementById('female').classList.remove('is-success');
      } else {
        document.getElementById('female').classList.add('is-success');
        document.getElementById('male').classList.remove('is-success');
      }
      document.getElementById("first-name").value = t.firstName;
      document.getElementById("last-name").value = t.lastName;
      document.getElementById("email").value = t.email;
      document.getElementById("dob").value = t.dob;
      document.getElementById("country").value = t.country;
    });
  }, []);
  return (
    <div className="relative flex flex-col min-w-0 break-words w-full mb-3 shadow-lg rounded-lg bg-blueGray-100 border">
      <div className="flex-auto px-4 lg:px-10 py-10 pt-10">
        <TraderInformationForm setGender={setGender} />
      </div>
      <button className='button is-success my-3 p-2 mx-3 uppercase'><p onClick={() => { modify(); }}> save </p></button>
      <Snackbar
        color="secondary"
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        key={'bottom' + 'right'}
        open={open}
        autoHideDuration={5000}
        onClose={() => setOpen(false)}
        message="saved"
      />
    </div>
  );
}