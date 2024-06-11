import { IoHomeOutline } from "react-icons/io5";
import { Link } from "react-router-dom";
import { MdOutlineDashboard } from "react-icons/md";
import { MdOutlineBedroomChild } from "react-icons/md";
import { MdOutlineAddHome } from "react-icons/md";
import { SlPeople } from "react-icons/sl";
import { BsBuildingAdd } from "react-icons/bs";
import { MdPayment } from "react-icons/md";



const UserSidebar = () => {
    return(
        <div className="pt-28 fixed top-0 w-[15%] z-[99] h-[100vh] bg-[#463f3f]">
            <div>

            <Link className="text-[white] " to="/dashboard">
                <div className="flex items-start hover:bg-[black] py-3  gap-2 pl-4">
                    <MdOutlineDashboard className="text-xl"/> <span>Dashboard</span>
                </div>
                </Link>

                <Link className="text-[white] " to="/hostel-details">
                <div className="flex items-start hover:bg-[black] py-3  gap-2 pl-4">
                    <IoHomeOutline className="text-xl"/> <span>Hostel Details</span>
                </div>
                </Link>
               


               

                <Link className="text-[white]" to="/payment">
                <div className="flex items-start hover:bg-[black] py-3  gap-2 pl-4">
                    <MdPayment className="text-xl"/> <span>Payment</span>
                </div>
                </Link>
              


               


            </div>
        </div>
    )
}



export default UserSidebar