import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import AdminNavbar from "../../components/AdminNavbar"
import AdminSideBar from "../../components/AdminSidebar"
import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import NotificationContext from "../../context/NotificationContext"



const ResidentRegistration =() => {
    const navigate = useNavigate()
    const {setNotification, setPath} = useContext(NotificationContext)
    const [info, setInfo] = useState()
    const handleChange = (e) => {
        setInfo((prev)=> ({...prev, [e.target.name]: e.target.value}))
    }



    const [rooms, setRooms] = useState(null)
    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get("http://localhost:8000/api/hostel/rooms/",{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            });
            setRooms(response.data.filter(room => room.vacancy > 0))
            
            
          } catch (error) {
            console.error("Error fetching data:", error);
          }
        };
      
        fetchData();
      }, []);

      useEffect(()=>{
        if(rooms && rooms.length == 0){
            setNotification("No rooms available")
            setPath("/admin/dashboard")
        }
      }, [rooms])

      const handleSubmit = async (e) => {
        e.preventDefault()
        try{
            const res = await axios.post("http://localhost:8000/api/registration/", info,{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setNotification("Resident registration successfull")
            navigate("/admin/resident-details")

        }catch(e){
            console.log(e)
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
                <div className="mt-32 mx-20 border rounded p-8 mb-12">
            <h1 className="text-2xl font-bold">Resident Registration</h1>

            <div>
            <form action="" className="w-full py-12 flex gap-[4%] flex-wrap">

<div className="flex flex-col w-[48%]">
    <label htmlFor="">Resident Name </label>
    <input type="text" name="name" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div>
<div className="flex flex-col w-[48%]">
    <label htmlFor="">Email </label>
    <input type="text" name="email" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div>
<div className="flex flex-col w-[48%]">
    <label htmlFor="">Phone </label>
    <input type="text" name="phone" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div>
<div className="flex flex-col w-[48%]">
    <label htmlFor="">Password </label>
    <input type="text" name="password" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div>

<div className="flex flex-col w-[48%]">
    <label htmlFor="">Room no</label>
    <select name="roomNo" className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2" onChange={handleChange}>
        <option value="" disabled selected>Select Room No.</option>
        {rooms && rooms?.map((item, index) => (
            <option value={item.roomNo} key={index}>{item.roomNo}</option>
        ))}


    </select>
</div> 
<div className="mt-8 w-full">
    <button className="bg-[red] text-[white] px-4 py-2 rounded" onClick={handleSubmit}>Register Resident</button>

</div>



</form>
            </div>
            </div>
                </div>
          

            </div>
           
       <div className="absolute bottom-0 w-[85%] right-0">
       <Footer />
       </div>
        </div>
    )
}


export default ResidentRegistration