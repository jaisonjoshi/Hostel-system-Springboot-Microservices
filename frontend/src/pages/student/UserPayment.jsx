import Footer from "../../components/Footer"
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";
import NotificationContext from "../../context/NotificationContext";
import UserNavbar from "../../components/UserNavbar";
import UserSidebar from "../../components/UserSidebar";
import axios from "axios";







const UserPayment = () => {
    const {user} = useContext(AuthContext)
    const [pending, setPending] = useState(false)

    const {setNotification} = useContext(NotificationContext)
   
   

    const [payments, setPayments] = useState(null)
    const getAllPayments = async () => {
        try{

            const res = await axios.get(`http://localhost:8000/api/payment/student/${user.id}`,{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setPayments(res.data)
            
        }
        catch(e){
            console.log(e)
        }
        
    }
    useEffect(()=>{
        
        getAllPayments()
    },[])






  

   

    useEffect(()=>{
        if(pending){
            let filtered = payments.filter(payment => payment.status==false)
            setPayments(filtered)

        }
        else{
            getAllPayments()
        }
    },[pending])

    const handlePending = () => {
        setPending(!pending)
    }


    const payAmount = async (id) => {
        try{
            await axios.get(`http://localhost:8000/api/payment/${id}` ,{
                headers:{
                    Authorization: `Bearer ${localStorage.getItem('token')}`
                }
            })
            setNotification("Payment Done successfully")
            getAllPayments()
        }
        catch(e){
            console.log(e)
        }

    }

    return(
        <div>
            <UserNavbar />
            <div className="flex ">
                    

                    <div className="w-[15%]">
                    <UserSidebar />
                    </div>


                <div className="w-[85%]">




          





            <div className="my-8 mt-40 mx-20 px-8 py-4 border rounded">
                

                <h1 className="text-2xl font-bold mb-4">All Payment Requests Initiated</h1>

                <hr />
                <div className="flex gap-8 py-4">
                    



                
                <div>
                    <button onClick={handlePending} className={`border rounded px-4 py-2 hover:border-[red] ${pending ? "border-[red]" : ""}`}>Pending payments</button>
                </div>





                
                    
                </div>

                <hr />


                <div>

                    <table className="border my-8" cellPadding="12px" width="100%" >
                        <tr className="border text-base font-medium ">
                            <td>Request Id</td>
                            <td>Resident ID</td>
                            <td>Name</td>
                            <td>Room No</td>
                            <td>Room Type</td>
                            <td>Amount</td>
                            <td>Status</td>
                            <td>Action</td>
                        </tr>

                        {payments && payments?.map((item,index)=>(
                            <tr className="hover:bg-[#dba4a4]" key={index}>
                            <td>{item.id}</td>
                                <td>{item.studentid}</td>
                                <td className="font-medium">{item.name}</td>
                                <td>{item.roomNo}</td>
                                <td>{item.category}</td>
                                <td>{item.amount}</td>
                                <td>{item.status ? (<span className="text-[green] font-medium">Paid</span>): <span className="text-[red] font-medium">Not Paid</span>}</td>
                                <td>{!item.status && <button className="bg-[red] text-[white] rounded px-2 py-1" onClick={()=>payAmount(item.id)}>Pay now</button>}</td>
                            </tr>
                        ))}
                       



                    </table>


                </div>


                
               
            </div>


           


                </div>



            </div>
          




           


           

           <Footer />                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        </div>
    )
}



 export default UserPayment