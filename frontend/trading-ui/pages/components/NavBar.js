import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faAddressBook as userAccount,
    faListAlt as dashboardI,
    faMoneyBill as quoteIcon // new icon for quote menu item
} from '@fortawesome/free-solid-svg-icons';
import { faChartArea } from '@fortawesome/free-solid-svg-icons';
const Navbar = () => {
    return (
        <aside>
            <div>
                <ul>
                    <li className="text-center">
                        <a href="/traderAccount">
                            <span className="relative h-6 w-6 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <svg width="20" height="20" fill="currentColor" className="m-auto" viewBox="0 0 2048 1792" xmlns="http://www.w3.org/2000/svg">
                                    <FontAwesomeIcon icon={userAccount}/>
                                </svg>
                                <p style={{fontSize:"8px", color: "white"}}>Account</p>
                            </span>
                        </a>
                    </li>
                    <li className="my-12 text-center">
                        <a href="/">
                            <span className="h-6 w-6 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <FontAwesomeIcon icon={dashboardI}/>
                            </span>
                            <p style={{fontSize:"8px", color: "white"}} >Board</p>
                        </a>
                    </li>
                    <li className="my-12 text-center">
                        <a href="/quotes">
                            <span className="h-6 w-6 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                            <FontAwesomeIcon icon={ quoteIcon } />
                            </span>
                            <p style={{fontSize:"8px", color: "white"}} >Quotes</p>
                        </a>
                    </li>
                    <li className="my-12 text-center">
                        <a href="#">
                            <span className="h-6 w-6 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <FontAwesomeIcon icon={faChartArea}/>
                            </span>
                            <p style={{fontSize:"8px", color: "white"}} >Chart</p>
                        </a>
                    </li>
                    <li className="my-12 text-center">
                        <a href="#">
                            <span className="h-6 w-6 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <svg width="20" height="20" className="m-auto" fill="currentColor" viewBox="0 0 2048 1792" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M1070 1178l306-564h-654l-306 564h654zm722-282q0 182-71 348t-191 286-286 191-348 71-348-71-286-191-191-286-71-348 71-348 191-286 286-191 348-71 348 71 286 191 191 286 71 348z">
                                    </path>
                                </svg>
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </aside>
    );
};

export default Navbar