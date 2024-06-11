import axios from "axios";
import { useContext, useState } from "react"
import { useNavigate } from "react-router-dom";
import AdminNavbar from "../../components/AdminNavbar";
import AdminSideBar from "../../components/AdminSidebar";
import Footer from "../../components/Footer"
import AuthContext from "../../context/AuthContext";
import NotificationContext from "../../context/NotificationContext";


const AdminDetails = () => {

    const {user, setUser} = useContext(AuthContext)
    const {setNotification, setPath} = useContext(NotificationContext)
    const [changePassword, setChangePassword] = useState(false);
    const navigate = useNavigate()

    const [info, setInfo] = useState(user);

    const handleChange = (e) => {
        setInfo((prev) => ({...prev, [e.target.name]: e.target.value}))
    }


    const handleSubmit =async (e) => {
        e.preventDefault()
        try{
            const res = await axios.put("http://localhost:8000/api/admin/details/update", info, {
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setUser(res.data)
            setNotification("Details Updated successfully!")
            setPath("/admin/hostel-details")
             
        }
        catch(e){
            setNotification("Some error occured. Please try again later!")
        }
    }

    const handleCheckBoxChange = (e) => {
        if(document.getElementById("changePassword").checked){
            setChangePassword(true);
        }
        else{
            setChangePassword(false);
        }
    }


    return(
        <div>
            <AdminNavbar />
            <div className="flex">
            <div className="w-[15%]">
                    <AdminSideBar />
                    </div>

                    <div className="w-[85%]">
                    <div className="mt-32 mx-20 border rounded p-8">
                <h1 className="text-2xl font-bold">
                    Edit Admin Details
                </h1>

                <div className=" flex flex-wrap">
                    <form action="" className="w-[50%] py-12">

                        <div className="flex flex-col">
                            <label htmlFor="">Hostel Name</label>
                            <input type="text" name="hostelName" id="" defaultValue={user.hostelName}  onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div>
                        <div className="flex flex-col">
                            <label htmlFor="">Owner Name</label>
                            <input type="text" name="name" id="" defaultValue={user.name}  onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div> <div className="flex flex-col">
                            <label htmlFor="">Email</label>
                            <input type="text" name="email" id=""  defaultValue={user.email} onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div> <div className="flex flex-col">
                            <label htmlFor="">Phone No.</label>
                            <input type="text" name="phone" id="" defaultValue={user.phone} onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div>
                        <div className="mt-8">
                            <button className="bg-[red] text-[white] px-4 py-2 rounded" onClick={handleSubmit}>Update Details</button>
                        </div>
                        

                        
                    </form>

                    <div className=" w-[50%] ">
                       <div className="mx-12 my-12 border rounded p-4">
                        <div className="flex justify-between items-center">
                        <h1 className="text-lg font-medium ">Change Password</h1>

                        <div>
                            <input type="checkbox" name="changePassword" id="changePassword" onChange={handleCheckBoxChange} />
                        </div>

                        </div>

                        {changePassword &&
                            <form action="" className="mt-8">
                        <div className="flex flex-col">
                            <label htmlFor="">Current Password</label>
                            <input type="text" name="" id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div>  <div className="flex flex-col">
                            <label htmlFor="">New Password</label>
                            <input type="text" name="" id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div>  <div className="flex flex-col">
                            <label htmlFor="">Confirm new Password</label>
                            <input type="text" name="" id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                        </div> 
                        <div className="mt-4">
                            <button className="bg-[red] text-sm text-[white] px-3 py-1 rounded">Change password</button>
                        </div>


                        </form>}
                       </div>
                    </div>

                    
                </div>

            </div>
                    </div>

            </div>
            
            <Footer />
        </div>
    )
}

export default AdminDetails