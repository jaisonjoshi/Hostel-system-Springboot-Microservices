import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import { CiEdit } from "react-icons/ci";
import { Link } from "react-router-dom";
import { IoMdAddCircleOutline } from "react-icons/io";
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";
import axios from "axios";







const RoomDetails = () => {

    const [rooms, setRooms] = useState(null)
    const [totalRooms, setTotalRooms] = useState()
   const [filteredRooms, setFilteredRooms] = useState(null)
    const [vacant, setVacant] = useState(false)
    const [category, setCategory] = useState("")
    const [capacity, setCapacity] = useState(0)

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await axios.get("http://localhost:8000/api/hostel/rooms/",{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            });
            setRooms(response.data);
            setTotalRooms(response.data.length)
            setFilteredRooms(response.data)
          } catch (error) {
            console.error("Error fetching data:", error);
          }
        };
      
        fetchData();
      }, []);

      const applyFilters = () =>{
        let filtered = rooms ;

        if(vacant){
            filtered =filtered.filter(room => room.vacancy >0)
        }
        else{
            filtered = rooms
        }

        if(category === "AC"){
            filtered = filtered.filter(room => room.category === "AC")
        }
        else if(category === "Non-AC"){
            filtered =filtered.filter(room=>room.category === "Non-AC")
        }
        else{
            filtered = filtered
        }

        if(capacity != 0){
            filtered = filtered.filter(room => room.capacity == capacity)

        }
        else{
            filtered = filtered

        }

        




        setFilteredRooms(filtered)
      }


      useEffect(()=>{
        applyFilters()
        console.log(capacity)
      },[vacant, category, capacity])

    
      const handleCategoryChange = (e) => {
        setCategory(e.target.value)
        
      }
      
      const handleCapacityChange = (e) => {
        setCapacity(e.target.value)
        
        
      }

      const clearFilters = () => {
        setCapacity(0)
        setCategory("")
        setVacant(false)
      }
      
      

     


      

    return(
        <div>
            <AdminNavbar />
            <div className="flex">
                <div className="w-[15%]  bg-[#463f3f]">
                    <AdminSideBar />
                </div>


                <div className="w-[85%]">


            


            <div className="mt-40 mb-12 mx-20 px-8 py-4 border rounded">
                <div className="flex justify-between">
                    <div className="flex gap-6 items-end mb-2">
                        <h1 className="text-xl font-medium ">Room Details</h1>
                        <p className="text-base">Total No. of rooms : {totalRooms}</p>
                    </div>
                <Link to="/admin/create-room">
                <div className="flex gap-2 items-center text-[blue]">
                    <IoMdAddCircleOutline /> <span>Add new Room</span>
                </div></Link>
                </div>
                <hr />

               



                <div className="">
                <div className="my-4 flex  gap-4 ">

                <button className={`border rounded px-4 py-2 hover:border-[red] ${vacant ? "border-[red]" : ""}`} onClick={()=>setVacant(!vacant)} >Rooms having vacancies</button>

                <select name="" id="" onChange={handleCategoryChange} className="border rounded px-4 py-1 outline-none">
                        <option value="" selected disabled>Select Category</option>
                        <option value={null}>All</option>
                        <option value="AC">AC</option>
                        <option value="Non-AC">Non-AC</option>
        

                    </select>
                    <select name="" id="" onChange={handleCapacityChange} className="border rounded px-4 py-1 outline-none">
                        <option value="" selected disabled>Select Capacity</option>
                        <option value={0}>All</option>
                        <option value={1}>1</option>
                        <option value={2}>2</option>
                        <option value={3}>3</option>
                        <option value={4}>4</option>
                        <option value={5}>5</option>

                    </select>
                    <button className="border rounded px-4 py-1 bg-[red] hover:border-[red] text-[white]" onClick={clearFilters}>Clear Filters</button>

                    


                </div>
                </div>
                <hr />


                <div className="my-4 flex gap-20">
                   

                    <div className=" w-full flex gap-[2.6%] flex-wrap">
                        
                    {
                        filteredRooms?.map((item,index)=>(
                            <div key={index} className="border hover:border-[red] w-[23%] cursor-pointer rounded text-center px-8 py-4">
                        <p className="text-base font-medium">Room No</p>
                        <h2 className="text-2xl font-bold">{item.roomNo}</h2>
                        <p className="text-xs mt-2">{item.category}</p>
                        <p className="text-xs">{item.capacity} Sharing</p>
                        <p className="text-xs">Vacancy : {item.vacancy}</p>
                   </div>
                        ))
                    }
                    {filteredRooms &&
                        filteredRooms?.length == 0 &&
                        <div className="text-center py-12">
                            <p>No items to show!</p>
                        </div>
                    }

                  

                    </div>
                   
                   
                </div>



               
            </div>


                </div>



            </div>
          




           


           


            <div className="absolute right-0 w-[85%]">
            <Footer />                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

            </div>
        </div>
    )
}



 export default RoomDetails