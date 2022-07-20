import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash } from '@fortawesome/free-solid-svg-icons';

const TraderList = () => {
    return (
        <table class="table is-striped is-narrow is-hoverable">
            <thead>
                <tr>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        First Name
                    </th>
                    <th scope="col" className="text-sm font-medium  px-6 py-4 text-left">
                        First Name
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
                        Mark
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Otto
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        rick@mdo
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Male
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Montreal, Canada
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 12, 2000 08:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <FontAwesomeIcon icon={faTrash} />
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
                        rick@mdo
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Male
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Montreal, Canada
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 12, 2000 08:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <FontAwesomeIcon icon={faTrash} />
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
                        rick@mdo
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Male
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Montreal, Canada
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        Mar 12, 2000 08:05 AM
                    </td>
                    <td className="text-sm font-light px-6 py-4 whitespace-nowrap">
                        <FontAwesomeIcon icon={faTrash} />
                    </td>
                </tr>
            </tbody>
        </table>


    );
};

export default TraderList