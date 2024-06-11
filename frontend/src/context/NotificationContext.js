import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";



const NotificationContext = createContext();

const NotificationContextProvider = ({children}) => {
    
    const [notification, setNotification] = useState(null);
    const [path, setPath] = useState(null);
    

     


    return (
        <NotificationContext.Provider value={{notification, setNotification, path, setPath}} >
            {children}
        </NotificationContext.Provider>
    )

    
   


    


}


export default NotificationContext
export { NotificationContextProvider };