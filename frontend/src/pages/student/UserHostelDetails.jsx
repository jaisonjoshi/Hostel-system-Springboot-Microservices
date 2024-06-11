import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import { CiEdit } from "react-icons/ci";
import { Link } from "react-router-dom";
import { IoMdAddCircleOutline } from "react-icons/io";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import { SlPeople } from "react-icons/sl";
import { FaArrowRight } from "react-icons/fa";
import { MdOutlineBedroomChild } from "react-icons/md";
import { BiMehBlank } from "react-icons/bi";
import { MdPayment } from "react-icons/md";
import UserNavbar from "../../components/UserNavbar";
import UserSidebar from "../../components/UserSidebar";







const UserHostelDetails = () => {

    const {user} = useContext(AuthContext)






    return(
        <div>
            <UserNavbar />
            <div className="flex ">
                    

                    <div className="w-[15%]">
                    <UserSidebar />
                    </div>


                <div className="w-[85%]">


                <div className="mt-40 mb-8 mx-20  py-4 ">
                  <div className="border rounded shadow-xl px-8 py-4">
                    <h1 className="font-bold mb-2  text-2xl">Hostel Details</h1>
                    <hr />
                    <div className="my-4 flex gap-20">
                    <div className=" flex flex-col gap-1">
                        <p>Hostel Details</p>
                        <p>Name</p>
                        <p>Email</p>
                        <p>Phone No. </p>
                    </div>
                    <div className=" flex flex-col gap-1">
                    <p><span className=" mr-4">:</span> hello</p>

                        <p><span className=" mr-4">:</span> hello</p>
                        <p><span className=" mr-4">:</span> hello</p>
                        <p><span className=" mr-4">:</span>  hello</p>
                    </div>
                </div>

                
                
                  </div>


                 
                
              
            </div>



        

           



                </div>



            </div>
          




           


           <div className="w-[85%] absolute bottom-0 right-0">
           <Footer />  
           </div>

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        </div>
    )
}



 export default UserHostelDetails