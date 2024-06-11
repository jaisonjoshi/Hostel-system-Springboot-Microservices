import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import { CiEdit } from "react-icons/ci";
import { Link } from "react-router-dom";
import { IoMdAddCircleOutline } from "react-icons/io";
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";
import { SlPeople } from "react-icons/sl";
import { FaArrowRight } from "react-icons/fa";
import { MdOutlineBedroomChild } from "react-icons/md";
import { BiMehBlank } from "react-icons/bi";
import { MdPayment } from "react-icons/md";







const Dashboard = () => {

    const {user} = useContext(AuthContext)






    return(
        <div>
            <AdminNavbar />
            <div className="flex ">
                    

                    <div className="w-[15%]">
                    <AdminSideBar />
                    </div>


                <div className="w-[85%]">


                <div className="mt-40 mb-8 mx-20  py-4 ">


                    <div className="flex flex-wrap gap-[2%]">

                        <div className="bg-[#253955] mb-12 rounded w-[23%]  text-[white] cursor-pointer">
                           <div className="flex justify-between items-start p-2">
                            <div> 
                                <h1 className="font-bold text-5xl">20</h1>
                                <p className="text-lg mt-2">Residents</p>
                            </div>
                            <div>
                                <SlPeople className="text-4xl"/>
                            </div>
                           </div>
                           <div className="flex mt-4 text-sm items-center gap-1 px-2 bg-[red] rounded py-1 ">
                            <span>Resident details </span>
                            <FaArrowRight className="mt-1 text-sm"/>
                           </div>
                        </div>



                        <div className="bg-[#313e32] mb-12 rounded w-[23%]  text-[white] cursor-pointer">
                           <div className="flex justify-between items-start p-2">
                            <div> 
                                <h1 className="font-bold text-5xl">13</h1>
                                <p className="text-lg mt-2">Rooms</p>
                            </div>
                            <div>
                                <MdOutlineBedroomChild className="text-4xl"/>
                            </div>
                           </div>
                           <div className="flex mt-4 text-sm items-center gap-1 px-2 bg-[red] rounded py-1 ">
                            <span>Room details </span>
                            <FaArrowRight className="mt-1 text-sm"/>
                           </div>
                        </div>





                        <div className="bg-[#7c4b77] mb-12 rounded w-[23%]  text-[white] cursor-pointer">
                           <div className="flex justify-between items-start p-2">
                            <div> 
                                <h1 className="font-bold text-5xl">8</h1>
                                <p className="text-lg mt-2">Vacancies</p>
                            </div>
                            <div>
                                <BiMehBlank className="text-4xl"/>
                            </div>
                           </div>
                           <div className="flex mt-4 text-sm items-center gap-1 px-2 bg-[red] rounded py-1 ">
                            <span>Vacancy details </span>
                            <FaArrowRight className="mt-1 text-sm"/>
                           </div>
                        </div>








                        <div className="bg-[#7a1010] mb-12 rounded w-[23%]  text-[white] cursor-pointer">
                           <div className="flex justify-between items-start p-2">
                            <div> 
                                <h1 className="font-bold text-5xl">15</h1>
                                <p className="text-lg mt-2">Pending payments</p>
                            </div>
                            <div>
                                <MdPayment className="text-4xl"/>
                            </div>
                           </div>
                           <div className="flex mt-4 text-sm items-center gap-1 px-2 bg-[red] rounded py-1 ">
                            <span>Payment details </span>
                            <FaArrowRight className="mt-1 text-sm"/>
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



 export default Dashboard