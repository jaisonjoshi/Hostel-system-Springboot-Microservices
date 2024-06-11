import { useContext, useEffect, useState } from "react"
import { Navigate, Outlet } from "react-router-dom";
import AuthContext from "../context/AuthContext"


const PrivateRoutesUser = () => {
    const {user, role, userLoading} = useContext(AuthContext);

    const [loading, setLoading] = useState(true)
    useEffect(()=>{
        if(user){
            setLoading(false)
        }
    }, [user])

    if (loading) {
        return <div><h1>Loading</h1></div>;
      }
    
      return user && role === "USER" ? <Outlet /> : <Navigate to="/login" />
}


export default PrivateRoutesUser