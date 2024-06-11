import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import { CiEdit } from "react-icons/ci";
import { Link } from "react-router-dom";
import { IoMdAddCircleOutline } from "react-icons/io";
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";
import Student from "./models/Student";
import axios from "axios";







const ResidentDetails = () => {
    const [resident, setResident] = useState(null)
    const [reFetch, setRefetch] = useState(false)


    const [residents, setResidents] = useState(null)
    
    useEffect(()=>{
        const getResidents = async () => {
            const res = await axios.get("http://localhost:8000/api/student",{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            });
            setResidents(res.data)
        }
        getResidents()

    },[reFetch])






    return(
        <div>
            <Student resident={resident} setResident={setResident} reFetch={reFetch}  setRefetch={setRefetch}/>
            <AdminNavbar />
            <div className="flex ">
                    

                    <div className="w-[15%]">
                    <AdminSideBar />
                    </div>


                <div className="w-[85%]">


              


               

             



                


              


                <div className="mt-40 mb-12 mx-20 px-8 py-4 border rounded">
                <div className="flex justify-between items-center">
                <h1 className="text-xl font-medium mb-2">Resident Details</h1>
                <div>
                <Link to="/admin/resident-registration">
                <div className="flex gap-2 items-center text-[blue]">
                    <IoMdAddCircleOutline /> <span>New resident registration</span>
                </div></Link>
                </div>
                </div>
               
                <hr />
                <div className="my-4 flex gap-20">
                   <div className="flex w-full flex-wrap gap-[2.6%]">


                        {residents && residents?.map((item,index)=>
                        <div key={index} onClick={()=>setResident(item)} className="border w-[23%] rounded hover:border-[red] text-center px-4 py-4 cursor-pointer">
                            <h2 className="font-medium">
                                {item.name}
                            </h2>
                            <p className="text-xs">Room {item.roomNo}</p>
                        </div>)}
                       
                   </div>
                </div>
            </div>
            

                </div>



            </div>
          




           


           

           <Footer />                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        </div>
    )
}



 export default ResidentDetails