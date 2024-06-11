import axios from "axios"
import { useContext, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import NotificationContext from "../../context/NotificationContext"



const Register = () => {
    const navigate = useNavigate()
    const {setNotification} = useContext(NotificationContext)

    const [info, setInfo] = useState({});

    const handleChange = (e) => {
        setInfo((prev)=>({...prev, [e.target.name]:e.target.value}))
    }


    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await axios.post("http://localhost:8000/auth/admin/register",info)
                .then((res)=>{
                    setNotification("Hostel Registration Successfull! Login now to access the portal.")
                    navigate("/admin/login")
                })
        }
        catch(e){
            console.log(e);
            setNotification("Some error occured. Please try again")
            navigate("/admin/register")
        }
    }

    return (
        <>

        <Navbar />
        <div className="w-[30%] mx-auto text-center mt-40">
            <h1 className="font-semibold text-2xl">Register as Admin</h1>
            <div>
                <form action="" className="text-left flex flex-col gap-4 my-12">
                    <div className=" flex flex-col">
                        <label htmlFor="">Hostel Name</label>
                        <input type="text" name="hostelName" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                    <div className=" flex flex-col">
                        <label htmlFor="">Owner Name</label>
                        <input type="text" name="name" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                    <div className=" flex flex-col">
                        <label htmlFor=""> Email</label>
                        <input type="email" name="email" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                    <div className=" flex flex-col">
                        <label htmlFor=""> Phone</label>
                        <input type="text" name="phone" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                    <div className=" flex flex-col">
                        <label htmlFor=""> Password</label>
                        <input type="text" name="password" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                  

                    <div className=" flex flex-col items-start">
                        <p className="my-2">Already have an account? <Link to="/admin/login" className="text-[blue]">Login now!</Link> </p>
                        <button className="bg-[red] text-[white] px-4 py-2 rounded" onClick={handleSubmit} >Register</button>

                    </div>


                </form>


                

                
            </div>
        </div>
        <Footer />
        </>
    )
}


export default Register