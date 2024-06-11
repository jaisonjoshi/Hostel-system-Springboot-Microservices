import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import { CiEdit } from "react-icons/ci";
import { Link } from "react-router-dom";
import { IoMdAddCircleOutline } from "react-icons/io";
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";







const HostelDetails = () => {

    const {user} = useContext(AuthContext)






    return(
        <div>
            <AdminNavbar />
            <div className="flex h-[100vh]">
            <div className="w-[15%]">
                    <AdminSideBar />
                    </div>


                <div className="w-[85%]">


                <div className="mt-40 mx-20 px-8 py-4 border rounded">
                <div className="flex justify-between">
                <h1 className="text-xl font-medium mb-2">Hostel Details</h1>
                <Link to="/admin/edit-admin-details">
                <div className="flex gap-2 items-center text-[blue]">
                    <CiEdit /> <span>Edit details</span>
                </div></Link>
                </div>
                <hr />
                <div className="my-4 flex gap-20">
                    <div className=" flex flex-col gap-1">
                        <p>Hostel Name</p>
                        <p>Owner Name</p>
                        <p>Owner Email</p>
                        <p>Owner Phone No. </p>
                    </div>
                    <div className=" flex flex-col gap-1">
                        <p><span className=" mr-4">:</span>{user.hostelName}</p>
                        <p><span className=" mr-4">:</span> {user.name}</p>
                        <p><span className=" mr-4">:</span>  {user.email}</p>
                        <p><span className=" mr-4">:</span>  {user.phone}</p>
                    </div>
                </div>
            </div>


          


                </div>



            </div>
          




           


           
           <div className="fixed bottom-0 w-[85%] right-0">
           <Footer />                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           


           </div>

        </div>
    )
}



 export default HostelDetails