import axios from "axios"
import { useContext, useState } from "react"
import { useNavigate } from "react-router-dom"
import AdminNavbar from "../../components/AdminNavbar"
import AdminSideBar from "../../components/AdminSidebar"
import Footer from "../../components/Footer"
import NotificationContext from "../../context/NotificationContext"



const CreateRoom =() => {

    const navigate = useNavigate()

    const {setNotification, setPath} = useContext(NotificationContext)

    const [info, setInfo] = useState({})

    const handleChange = (e) => {
        setInfo((prev)=> ({...prev, [e.target.name]:e.target.value}))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const res = await axios.post("http://localhost:8000/api/hostel/rooms/", info, {
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setNotification("Room Added successfully")
            setPath("/admin/room-details")

        }catch(e){
            console.log(e)
            setNotification("Some error occured. Please try again!")
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

                     <div className="mt-32 mx-20  border rounded p-8 mb-12">
            <h1 className="text-2xl font-bold">Create a new Room</h1>

            <div>
            <form action="" className="w-full py-12 flex gap-[4%] flex-wrap">

<div className="flex flex-col w-[48%]">
    <label htmlFor="">Room No.</label>
    <input type="text" name="roomNo" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div>
<div className="flex flex-col w-[48%]">
    <label htmlFor="">Category</label>
    <select name="category" onChange={handleChange}  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2">
        <option value="" disabled selected>Select category</option>
        <option value="AC">AC</option>
        <option value="Non-AC">Non-AC</option>

    </select>
</div>
<div className="flex flex-col w-[48%]">
    <label htmlFor="">Capacity</label>
    <select name="capacity" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2">
        <option value="" disabled selected>Select capacity</option>
        <option value={1}>1</option>
        <option value={2}>2</option>
        <option value={3}>3</option>
        <option value={4}>4</option>
        <option value={5}>5</option>


    </select>
</div> <div className="flex flex-col w-[48%]">
    <label htmlFor="">Price</label>
    <input type="number" name="price" onChange={handleChange} id=""  className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
</div> 
<div className="mt-8">
    <button className="bg-[red] text-[white] px-4 py-2 rounded" onClick={handleSubmit}>Create Room</button>
</div>



</form>
            </div>
            </div>
      
                </div>



                 

            </div>


            <div className="absolute w-[85%] right-0 bottom-0">
            <Footer />

            </div>
          
        </div>
    )
}


export default CreateRoom