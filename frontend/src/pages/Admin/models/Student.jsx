import axios from "axios";
import { useContext, useState } from "react"
import NotificationContext from "../../../context/NotificationContext";



const Student = ({resident, setResident, setRefetch, reFetch}) => {

    const {setNotification} = useContext(NotificationContext)


    const deleteStudent = async (e) => {
        e.preventDefault();
        try{
            const res = await axios.delete(`http://localhost:8000/api/student/delete/${resident.id}`,{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setResident(null)
            setNotification('Student Deleted successfully and room details updated')
            setRefetch(!reFetch)

        }
        catch(e){
            setNotification("Some error occured. Please try again later!")
        }
    }

    return(
        <>
        {resident &&
            <div className="w-[100vw] h-[100vh] absolute top-0 left-0 z-[100000] bg-[#00000087] ">
            <div className="shadow-xl rounded   w-[60%] bg-[white] top-[50%] relative left-[50%] translate-x-[-50%] translate-y-[-50%]">
                <div>
                    <div className="flex justify-between px-6 py-2">
                        <span className="font-bold text-xl">Resident Details</span>
                        <span className="text-[red] text-2xl font-bold cursor-pointer" onClick={()=> setResident(null)}>x</span>
                    </div>
                    <hr />
                    <div className="px-6 py-4">
                    <div className="my-4 flex gap-20">
                    <div className=" flex flex-col gap-1">
                        <p> Name</p>
                        <p>Email</p>
                        <p>Phone No. </p>
                        <p>Room No. </p>

                    </div>
                    <div className=" flex flex-col gap-1">
                        <p><span className=" mr-4">:</span>{resident.name}</p>
                        <p><span className=" mr-4">:</span>  {resident.email}</p>
                        <p><span className=" mr-4">:</span>  {resident.phone}</p>
                        <p><span className=" mr-4">:</span>  {resident.roomNo}</p>


                    </div>
                </div>
                    </div>
                    <hr />
                    <div className="px-6 py-4">
                        <button className="text-[white] bg-[red] rounded shadow-xl px-4 py-2" onClick={deleteStudent}>Remove student</button>
                    </div>
                </div>
            </div>
        </div>
        }
        </>
        
    )
}


export default Student