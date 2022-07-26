import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faAddressBook as userAccount,
    faListAlt as dashboardI,
    faMoneyBill as quoteIcon // new icon for quote menu item
} from '@fortawesome/free-solid-svg-icons';
import { faChartArea } from '@fortawesome/free-solid-svg-icons';
import { Popover } from '@mui/material';
import { useState } from 'react';
const Navbar = () => {
    const [isShown, setIsShown] = useState(false);
    return (
        <aside>
            <div>
                <ul>
                    <li className="text-center" data-tooltip="Account">
                        <a href="/account">
                            <span className="relative h-8 w-8 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <svg width="20" height="20" fill="currentColor" className="m-auto" viewBox="0 0 2048 1792" xmlns="http://www.w3.org/2000/svg">
                                    <FontAwesomeIcon icon={userAccount} />
                                </svg>
                                <p style={{ fontSize: "8px", color: "white" }}>Account</p>
                            </span>
                        </a>
                    </li>
                    <li className="my-12 text-center" data-tooltip="Board">
                        <a href="/">
                            <span className="h-8 w-8 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <FontAwesomeIcon icon={dashboardI} />
                            </span>
                            <p style={{ fontSize: "8px", color: "white" }} >Board</p>
                        </a>
                    </li>
                    <li className="my-12 text-center" data-tooltip="Quotes">
                        <a href="/quotes">
                            <span className="h-8 w-8 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <FontAwesomeIcon icon={quoteIcon} />
                            </span>
                            <p style={{ fontSize: "8px", color: "white" }} >Quotes</p>
                        </a>
                    </li>
                    <li className="my-12 text-center" data-tooltip="Chart">
                        <a href="#">
                            <span className="h-8 w-8 text-gray-500 dark:text-gray-300 mx-auto transition-colors duration-200">
                                <FontAwesomeIcon icon={faChartArea} />
                            </span>
                            <p style={{ fontSize: "8px", color: "white" }} >Chart</p>
                        </a>
                    </li>
                </ul>
            </div>
        </aside>
    );
};

export default Navbar