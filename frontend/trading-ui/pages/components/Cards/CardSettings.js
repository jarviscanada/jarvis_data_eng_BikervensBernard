import React from "react";
import TraderInformationForm from "../Forms/TraderInformationForm";
import { useState } from 'react';

export default function CardSettings() {
  const [getGender, setGender] = useState("");
  const modify = () => {
    var letters = /^[A-Za-z]+$/;
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    const firstname = document.getElementById('first-name').value;
    const lastname = document.getElementById('last-name').value;
    const email = document.getElementById('email').value;
    const gender = getGender;
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
      axios.get("").then((res) => {
        axios.get(getAllTraderUrl).then((res) => {
          // feed back "account saved"
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
  }
  return (
    <div className="relative flex flex-col min-w-0 break-words w-full mb-3 shadow-lg rounded-lg bg-blueGray-100 border">
      <div className="flex-auto px-4 lg:px-10 py-10 pt-10">
        <TraderInformationForm setGender={setGender}/>
      </div>
    </div>
  );
}
