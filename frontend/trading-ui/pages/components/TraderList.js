import DeleteIcon from '@mui/icons-material/Delete';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';
//<div class="bg-white rounded-md w-30 overflow-x-auto overflow-y-auto relative h-screen p-5 shadow-md"></div>
const TraderList = ({ users }) => {
    const col = ["First Name", "Last Name", "Email", "Gender", "Country", "Date of Birth", "Action"];
    return (
        <div class="bg-white rounded-md overflow-x-auto relative h-screen pl-5 shadow-md">
            <div class="-mx-4 sm:-mx-8  ">
                <table class=" min-w-full leading-normal shadow rounded-lg">
                    <thead>
                        <tr>
                            {
                                col.map(col => (
                                    <th
                                        class="px-5 py-1 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        {col}
                                    </th>
                                ))
                            }
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map((user, key) => (
                                <tr>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 ">{user.username} </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> {user.name} </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> {user.username}@gmail.com </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> {user.id % 2 == 0 ? <FemaleIcon /> : <MaleIcon />} {user.id % 2 == 0 ? <p>Female</p> : <p>Male</p>} </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> Paris, France </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> Mar 11, 2001 02:05 AM </td>
                                    <td className="px-5 py-1 bg-white text-sm text-gray-900 "> <DeleteIcon /> </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );
};
//<table className="table is-striped is-narrow is-hoverable"> <thead> <tr> <th scope="col" className="py-3 px-6"> First Name </th> <th scope="col" className="py-3 px-6"> Last Name </th> <th scope="col" className="py-3 px-6"> Email </th> <th scope="col" className="py-3 px-6"> Gender </th> <th scope="col" className="py-3 px-6"> Country </th> <th scope="col" className="py-3 px-6"> Date of Birth </th> <th scope="col" className="py-3 px-6"> Action </th> </tr> </thead> <tbody> { users.map((user, key) => ( <tr> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> {user.username} </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> {user.name} </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> {user.username}@gmail.com </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> {user.id % 2 == 0 ? <FemaleIcon /> : <MaleIcon />} {user.id % 2 == 0 ? <p>Female</p> : <p>Male</p>} </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> Paris, France </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> Mar 11, 2001 02:05 AM </td> <td className="text-sm font-light px-6 py-4 whitespace-nowrap"> <DeleteIcon /> </td> </tr> )) } </tbody> </table>
export default TraderList;