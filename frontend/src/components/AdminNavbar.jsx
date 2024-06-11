import { useContext } from "react";
import { MdAccountCircle } from "react-icons/md";
import { useNavigate } from "react-router-dom";
import AuthContext from "../context/AuthContext";



const AdminNavbar = () => {
    const {user} = useContext(AuthContext)
    const navigate = useNavigate()
    console.log("hiii")
    console.log(user)
    const handleLogout = () => {
        localStorage.clear()
        navigate("/admin/login")
    }

    return (
        <div>
            <div className="w-full px-4 py-4 bg-[red]  fixed top-0 z-[100] flex justify-between">
               <div>
               <h1 className="font-semibold text-xl text-[white]">Hostel Management System</h1>
               <p className="text-sm text-[white]">Admin portal</p>
               </div>

                <div className="flex items-center gap-12">
                <div className="text-[white]  flex items-center gap-4">
                    <div className="flex flex-col">
                        <span className="text-sm">{user ? user.hostelName: ""}</span>
                        <span className="text-xs">{user?.name}</span>
                    </div>
                    <div className="text-4xl">
                    <MdAccountCircle />

                    </div>
                </div>
                <button className="text-[red] px-4 py-2 bg-[white] rounded shadow-xl" onClick={handleLogout}>Logout</button>

                </div>
              
                
                
                </div>

        </div>
    )
}


export default AdminNavbar