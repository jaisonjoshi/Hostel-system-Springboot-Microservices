import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";



const AuthContext = createContext();

const AuthContextProvider = ({children}) => {


    const [user, setUser] = useState(null);
    const [role, setRole] = useState(null)
    const [userLoading, setUserLoading] = useState(true)
   
        const getLoggedIn = async () => {
            const token = localStorage.getItem("token");

            

            if(token){
                const role = jwtDecode(token).role;
                const endpoint = role === 'ADMIN' ? '/auth/admin/loggedin' : '/auth/student/loggedin';

                try{
                   const res = await axios.get(`http://localhost:8000${endpoint}`,{
                    headers:{
                        Authorization:`Bearer ${token}`
                    }
                   })
                    setUser(res.data)
                    setRole(jwtDecode(token).role)

                    setUserLoading(false) 


                  
                    
                        
                }
                catch(e){
                        console.log(e)
                        setUserLoading(false) 
                        setUser(null)

                }

               
            }
            else{
                setUser(null)
                setUserLoading(false) 

            }


        }


        useEffect(()=>{
            getLoggedIn()

        }, [])
    

    
    
    


    return (
        <AuthContext.Provider value={{user, setUser, role, setRole, userLoading}} >
            {children}
        </AuthContext.Provider>
    )

    
   


    


}


export default AuthContext
export { AuthContextProvider };