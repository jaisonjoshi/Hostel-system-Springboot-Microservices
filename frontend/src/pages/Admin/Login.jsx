import axios from "axios"
import { jwtDecode } from "jwt-decode"
import { useContext, useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import Footer from "../../components/Footer"
import Navbar from "../../components/Navbar"
import AuthContext from "../../context/AuthContext"
import NotificationContext from "../../context/NotificationContext"



const Login = () => {
    const {user, setUser, setRole} = useContext(AuthContext)

   


    const {notification, setNotification} = useContext(NotificationContext)
    
    const navigate = useNavigate()

    const [info, setInfo] = useState(null)

    const handleChange = (e) => {
        setInfo((prev)=> ({...prev, [e.target.name]: e.target.value}))
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const res = await axios.post("http://localhost:8000/auth/admin/login",info)

            localStorage.setItem('token', res.data.token)
            const decoded = jwtDecode(res.data.token)

            localStorage.setItem("role",decoded.role)
            setNotification("Login Successfull")
            setUser(res.data.user)
            localStorage.setItem('user', JSON.stringify(res.data.user))
            setRole(decoded.role)
            navigate("/admin/dashboard")  

        }
        catch(e){
            console.log(e);
            setNotification("Some error occured. Please try again")
            navigate("/admin/login")
        }
    }





    return (
        <>

        <Navbar />
        <div className="w-[30%] mx-auto text-center mt-40">
            <h1 className="font-semibold text-2xl">Login as Admin</h1>
            <div>
                <form action="" className="text-left flex flex-col gap-4 my-12">
                    <div className=" flex flex-col">
                        <label htmlFor="">Email</label>
                        <input type="text" name="email" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                  


                    
                    <div className=" flex flex-col">
                        <label htmlFor=""> Password</label>
                        <input type="text" name="password" onChange={handleChange} className="outline-none bg-[#e4e4e4] rounded py-2 px-2 my-2"/>
                    </div>

                    

                    <div className=" flex flex-col items-start">
                        <p className="my-2">New customer? <Link className="text-[blue]" to="/admin/register">Register here!</Link> </p>
                        <button className="bg-[red] text-[white] px-4 py-2 rounded" onClick={handleSubmit}>Login</button>

                    </div>


                </form>


                

                
            </div>
        </div>
        <div className="absolute bottom-0 w-full">
        <Footer />

        </div>
        </>
    )
}


export default Login