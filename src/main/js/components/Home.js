import React, { Suspense, lazy, useState, useEffect } from 'react';
import axios from 'axios';
import '../css/Styles.css'
import moment from 'moment'
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css";

function Home(props) {

    const [tools, setTools] = useState([]);
    const [agreement, setAgreement] = useState();
    const [showAgreement, setShowAgreement] = useState([]);
    const [request, setRequest] = useState({ toolCode: "", rentalDayCount: "", discountPercent: "", checkoutDate: Date.now() });

    useEffect(() => { initialData(); }, [])
    useEffect(() => { }, [tools])
    useEffect(() => { }, [agreement])
    useEffect(() => { }, [showAgreement])


    function initialData() {
        async function fetchData() {
            const tools = await axios.get('http://localhost:8080/api/tools');
            setTools(tools.data);
        }
        fetchData();
    }

    function checkoutTool() {
        console.log("test")
        async function fetchData() {
            const agreement = await axios.post("http://localhost:8080/api/checkout", JSON.stringify(request),
            {
                headers: {
                    'Content-type': 'application/json'
                },
            });
            setAgreement(agreement.data);
            setShowAgreement(true);
        }
        fetchData();
        

    }

    function updateForm(event) {
        setRequest({ ...request, [event.target.name]: event.target.value })
    }

    function updateCheckoutDate(event) {
        setRequest({ ...request, checkoutDate: moment(event).format('YYYY-MM-DDTHH:mm:ss') })
    }


    function updateToolCode(toolCode) {
        setRequest({ ...request, toolCode: toolCode })
    }

    return (
        <div className="home-container">
            <div className="title"><h2>Demo Tool Rental Store</h2></div>
            <div className="tools">
                {tools.map((tool, index) =>
                    <React.Fragment key={index}>
                        <div className={"tool-brand "+tool.toolCode} onClick={() => { updateToolCode(tool.toolCode) }}>
                            <div className="brand">{tool.brand}</div>
                        </div>
                    </React.Fragment>
                )}
            </div>
            <div className="form">
                <div className="input-container">
                    <input name="rentalDayCount" onChange={e => updateForm(e)} value={request === undefined ? "" : request.rentalDayCount} className="input-field" required />
                    <label className="input-label">Rental Days</label>
                </div>
                <div className="input-container">
                    <input name="discountPercent" onChange={e => updateForm(e)} value={request === undefined ? "" : request.discountPercent} className="input-field" required />
                    <label className="input-label">Applied Discount</label>
                </div>
                <div className="input-container">
                    <DatePicker filterDate={(date) => { return moment() < date; }} onChange={e => updateCheckoutDate(e)} className="input-field" name="checkoutDate" selected={moment(request.checkoutDate).toDate()}/>
                    <label className="input-label">Checkout Date</label>
                </div>
            </div>
            <input onClick={() => checkoutTool()} className="submitBtn btn" type="button" value="Submit" />
            {agreement === undefined ? null :
                <div className="agreement-module">
                    <h3>Rental Agreement Receipt</h3>
                    <span className="agreement-data">Tool Code:{agreement.toolCode}</span>
                    <span className="agreement-data">Tool Type:{agreement.toolType}</span>
                    <span className="agreement-data">Tool Brand:{agreement.tooBrand}</span>
                    <span className="agreement-data">Rental days:{agreement.rentalDays}</span>
                    <span className="agreement-data">Check out date:{agreement.checkoutDate}</span>
                    <span className="agreement-data">Due date:{agreement.dueDate}</span>
                    <span className="agreement-data">Daily rental charge: ${agreement.dailyRentalCharge}</span>
                    <span className="agreement-data">Charge days:{agreement.chargeDays}</span>
                    <span className="agreement-data">Pre-discount charge: ${agreement.preDiscountCharge}</span> 
                    <span className="agreement-data">Discount percent: {agreement.discountPercent}%</span>
                    <span className="agreement-data">Discount amount: ${agreement.discountAmount}</span>
                    <span className="agreement-data">Final Charge: ${agreement.finalCharge}</span>
                </div>
            }
        </div>
    )
}

export default Home;