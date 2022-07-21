import DeleteIcon from '@mui/icons-material/Delete';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';

const TraderList = () => {
    return (
        <table className="table is-striped is-narrow is-hoverable">
            <thead>
                <tr>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        First Name
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Last Name
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Email
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Gender
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Country
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Date of Birth
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        Action
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Beth
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Sanchez
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        rick@mdo
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <FemaleIcon />Female
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Paris, France
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 11, 2001 02:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <DeleteIcon />
                    </td>
                </tr>
                <tr>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mark
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Otto
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mark@Otto.com
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <MaleIcon />Male
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Montreal, Canada
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 12, 2000 08:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <DeleteIcon />
                    </td>
                </tr>
                <tr>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Rick
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Sachez
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Rick@Sachez.com
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <MaleIcon />Male
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Los angeles, United states
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 12, 2000 08:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <DeleteIcon />
                    </td>
                </tr>
            </tbody>
        </table>
    );
};

export default TraderList