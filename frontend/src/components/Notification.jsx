import { useContext } from "react"
import { useNavigate } from "react-router-dom"
import NotificationContext from "../context/NotificationContext"



const Notification = () => {
    const navigate = useNavigate()

    const {notification, setNotification, path, setPath} = useContext(NotificationContext)

    const handleClose = () => {
        setNotification(null);
        if(path !== null){
            navigate(path)
            setPath(null)
        }
    }


    return(
        <>
        {notification !== null &&
         <div className="absolute w-[50%] bg-[white] top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%] shadow-xl border rounded-[5px] text-center flex flex-col items-center gap-12 py-12 ">
         <h1 className="text-2xl font-medium">{notification}</h1>
         <button onClick={handleClose} className="bg-[red] text-[white] px-4 py-2 rounded">Close</button>
     </div>
        }
        </>
       
    )
}


export default Notification