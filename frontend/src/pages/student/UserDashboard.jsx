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
import axios from "axios";







const UserDashboard = () => {

    const {user} = useContext(AuthContext)


    const [room, setRoom ] =useState()

    useEffect(()=>{
        const getRoom = async () => {
           const res =  await axios.get(`http://localhost:8000/api/hostel/rooms/${user.roomNo}`, {
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })

            setRoom(res.data)
        }
        getRoom();
    }, [user])






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
                    <h1 className="font-bold mb-2  text-2xl">Resident Details</h1>
                    <hr />
                    <div className="my-4 flex gap-20">
                    <div className=" flex flex-col gap-1">
                        <p>Name</p>
                        <p>Email</p>
                        <p>Phone No. </p>
                    </div>
                    <div className=" flex flex-col gap-1">
                        <p><span className=" mr-4">:</span> {user.name}</p>
                        <p><span className=" mr-4">:</span> {user.email}</p>
                        <p><span className=" mr-4">:</span>  {user.phone}</p>
                    </div>
                </div>

                {room && <div>
                <h2 className="mt-12 font-bold text-lg mb-2">Room Details</h2>
                <hr />

                <div className=" border rounded mt-6 flex justify-between items-center px-8 py-4">
                    <span className="font-medium">Room No. <span className="font-bold text-2xl">{room.roomNo}</span></span>
                    <span >Category: <span className="font-medium">{room.category}</span></span>
                    <span><span className="font-bold">{room.capacity}</span> Sharing</span>
                    <span><span className="font-bold text-xl">{room.price}</span><span className="text-sm"> Per Month</span></span>
                </div>


                </div>}
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



 export default UserDashboard