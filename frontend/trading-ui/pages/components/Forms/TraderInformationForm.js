import { useState } from 'react';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';
import CountrySelect from './CountrySelect';

const TraderInformationForm = ({ setGender }) => {
    return (
        <form className='w-full'>
            <div className='flex flex-wrap-mx-3'>
                <div className='w-full md:w-1/2 px-3 mb-6 md:mb-0'>
                    <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                        First Name
                    </label>

                    <input className='appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:ring w-full ease-linear transition-all duration-150' id='first-name' type='text' placeholder='Jane' />
                    <p id='first-name-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
                <div className='w-full md:w-1/2 px-3'>
                    <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                        Last Name
                    </label>
                    <input className='appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:ring w-full ease-linear transition-all duration-150' id='last-name' type='text' placeholder='Doe' />
                    <p id='last-name-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
            </div>
            <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                    <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                        Email
                    </label>
                    <input className='appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:ring w-full ease-linear transition-all duration-150' id='email' type='email' placeholder='Jane@gmail.com' />
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
                        <a className='button flex-1 mr-2' id='male' onClick={() => { document.getElementById('male').classList.add('is-success'); document.getElementById('female').classList.remove('is-success'); setGender("Male"); }}><MaleIcon />Male</a>
                        <a className='button flex-1' id='female' onClick={() => { document.getElementById('female').classList.add('is-success'); document.getElementById('male').classList.remove('is-success'); setGender("Female"); }}><FemaleIcon />Female</a>
                    </nav>
                </div>
            </div>
            <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-6 md:mb-0'>
                    <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                        Country
                    </label>
                    <CountrySelect />
                    <p id='country-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
            </div>
            <div className='flex flex-wrap-mx-3'>
                <div className='w-full xm:w-1/2 px-3 mb-3 md:mb-0'>
                    <label className='block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2'>
                        Date of birth
                    </label>
                    <input className='appearance-none block w-full bg-gray-200 text-gray-700 border  rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:ring w-full ease-linear transition-all duration-150' id='dob' type='date' placeholder='YYYY-MM-DD' />
                    <p id='dob-error' className='text-red-500 text-xs italic hidden'>Please fill out this field.</p>
                </div>
            </div>
        </form>
    );
};

export default TraderInformationForm;