import { useContext, useEffect, useState } from "react";
import { Navigate, Outlet } from "react-router-dom";
import AuthContext from "../context/AuthContext";

const PrivateRoutesAdmin = () => {
    const [loading, setLoading] = useState(true)
    const { user, role, userLoading } = useContext(AuthContext);
    useEffect(()=>{
        if(user){
            setLoading(false)
        }
        if(!userLoading){
            setLoading(false)
        }
    }, [user])
  
    if (loading) {
      return <div><h1>Loading</h1></div>;
    }
  
    return user && role === "ADMIN" ? <Outlet /> : <Navigate to="/admin/login" />
  };


  export default PrivateRoutesAdmin