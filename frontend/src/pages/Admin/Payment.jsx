import Footer from "../../components/Footer"
import AdminNavbar from "../../components/AdminNavbar";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../../context/AuthContext";
import AdminSideBar from "../../components/AdminSidebar";
import NotificationContext from "../../context/NotificationContext";
import axios from "axios";







const Payment = () => {
    const [pending, setPending] = useState(false)

    const date = new Date();
    const [paymentschedulestatus, setPaymentScheduleStatus] = useState(null)
    const [scheduled, setScheduled] = useState(false)
    const {setNotification} = useContext(NotificationContext)
    const [month, setMonth] = useState(date.getMonth() + 1)
    const [year, setYear] = useState(date.getFullYear());
    useEffect(()=>{
        setMonth(date.getMonth() + 1)
        setYear(date.getFullYear())
    }, [])
    useEffect(()=>{
        const getPaymentScheduleStatus = async () => {
            try{

                const res = await axios.get("http://localhost:8000/api/payment/paymentschedulestatus",{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
                setPaymentScheduleStatus(true);
            
            }
            catch(e){
                setPaymentScheduleStatus(false)
            }
            
        }
        getPaymentScheduleStatus()
    },[scheduled])

    const [payments, setPayments] = useState(null)
    const getAllPayments = async () => {
        try{

            const res = await axios.get(`http://localhost:8000/api/payment/${month}/${year}`,{
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
    },[month, year])



    const months = ["January","February","March","April","May","June","July","August","September","October","November","December"];



    const schedulePayment = async (e) => {
        e.preventDefault();
        try{

            const res = await axios.get("http://localhost:8000/api/payment/schedule",{
                headers:{
                    Authorization:`Bearer ${localStorage.getItem('token')}`
                }
            })
            setNotification("Successfully scheduled payment for current month")
            setScheduled(!scheduled)
            getAllPayments()

        }catch(e){
            setNotification("Some error occured. Please try again later!")
        }
    }

    const handleMonthChange =(e) => {
        setMonth(e.target.value)
    }
    const handleYearChange = (e) => {
        setYear(e.target.value)
    }


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

    return(
        <div>
            <AdminNavbar />
            <div className="flex ">
                    

                    <div className="w-[15%]">
                    <AdminSideBar />
                    </div>


                <div className="w-[85%]">


                <div className="mt-40 mb-12 mx-20 px-8 py-4 border rounded">
                

                <h1 className="text-2xl font-bold">Payment Details</h1>


               {paymentschedulestatus && <div>
                    <p className="mt-8" >You have <span className="text-[green] font-bold text-lg"> successfully </span> scheduled payment for this month. Review the payment status below.</p>
                </div>}
                {paymentschedulestatus ==false && <div>
                    <p className="mt-8" >You haven't scheduled payments for this month. Please schedule the payment request.</p>
                    <button className="text-[white] bg-[red] rounded px-4 py-2 mt-4" onClick={schedulePayment}>Schedule Payment Requests</button>
                </div>}


                
               
            </div>



          





            <div className="my-8 mx-20 px-8 py-4 border rounded">
                

                <h1 className="text-2xl font-bold mb-4">All Payment Requests Initiated</h1>

                <hr />
                <div className="flex gap-8 py-4">
                    <div>
                    
                        <select name="" id="" className="bg-[#e4e4e4] rounded px-4 py-1" onChange={handleMonthChange}>
                            {months?.map((item,index)=>(
                                <option selected={date.getMonth() === index ? true : false} value={index+1} key={index}>{item}</option>
                            ))}
                        </select>



                    </div>

                    <div>
                    
                    <select name="" onChange={handleYearChange} id="" className="bg-[#e4e4e4] rounded px-4 py-1">
                       <option>2023</option>
                       <option selected>2024</option>
                    </select>

                    

                </div>


                
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



 export default Payment