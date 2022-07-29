import React from "react";
import axios from "axios";
import { depositFundUrl ,withdrawFundUrl, getAllAccountsUrl} from '../../../util/constants';
import { useEffect , useState} from 'react';
import Snackbar from '@mui/material/Snackbar';

export default function CardProfile({id}) {
  const [account, setAccount] = useState({});
  const [open, setOpen] = useState(false);
  const inputStyle = "appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-1 leading-tight focus:outline-none focus:ring w-full ease-linear transition-all duration-150";
  
  const addFund= () => {
    const amount = document.getElementById("fund-value").value;
    const text = document.querySelector("#add > p").textContent;
    axios.get(depositFundUrl+"/"+id+"/amount/"+amount).then(res => {
      const a = res.data;
      setAccount(a);
      document.getElementById("fund").value = a.amount;
      setOpen(true);
    });
  }
  const removeFund = () => {
    const amount = document.getElementById("fund-value").value;
    axios.get(withdrawFundUrl+"/"+id+"/amount/"+amount).then(res => {
      const a = res.data;
      setAccount(a);
      document.getElementById("fund").value = a.amount;
      setOpen(true);
    });
  }
  useEffect(() => {
    axios.get(getAllAccountsUrl).then(res => {
      const a = res.data.filter((e) =>{return e.traderId == id;})[0];
      setAccount(a);
      document.getElementById("fund").value = a.amount;
    });
  },[]);

  return (
    <div className="relative flex flex-col min-w-0 break-words bg-white w-full shadow-xl rounded-lg border">
      <div className="px-6">
        <div className="text-center mt-12">
          <h3 className="text-xl font-semibold leading-normal mb-2 text-blueGray-700 mb-2">
          {id}
          </h3>
          <div className="text-sm leading-normal mt-0 mb-2 text-blueGray-400 font-bold uppercase">
            <i id="fund" className="fas fa-map-marker-alt mr-2 text-lg text-blueGray-400"></i>
            <p>Available balance of : {account.amount}$</p>
          </div>
          <div className="">
            <input className={inputStyle} id='fund-value' type="number" min="0.01" step="0.01" max="2500" placeholder={"10,000"} />
          </div>
          <div className="">
            <button id="add" className='button is-success my-3 p-2 mx-3 uppercase'><p onClick={() => { addFund(); }}>deposite</p></button>
            <button id="remove" className='button is-success my-3 p-2 mx-3 uppercase'><p onClick={() => { removeFund(); }}>withdrawal</p></button>
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
        </div>
      </div>
    </div>
  );
}
