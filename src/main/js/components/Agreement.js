import React, { Suspense, lazy, useState, useEffect } from 'react';
import axios from 'axios';
import '../css/Styles.css'

function Home(props) {

    const [tools, setTools] = useState([]);
    const [agreement, setAgreement] = useState([]);
    const [request, setRequest] = useState({toolCode:"",rentalDayCount:"", discountPercent:"",checkoutDate:""});

    useEffect(() => { initialData(); }, [])
    useEffect(() => { }, [tools])
    useEffect(() => { }, [agreement])


    function initialData() {
        async function fetchData() {
            const tools = await axios.get('http://localhost:8080/api/tools');
            setTools(tools.data);
        }
        fetchData();
    }

    function checkoutTool(){
        async function fetchData() {
            const agreement = await axios.get('http://localhost:8080/api/checkout');
            setTools(agreement.data);
        }
        fetchData();

    }

    function updateForm(event){
        setRequest({...request, [event.target.name]: event.target.value})
    }

    function updateToolCode(){
        setRequest({...request, [event.target.name]: event.target.value})
    }

    return (
        <div className="home-container">
            <div className="title"><h2>Demo Tool Rental Store</h2></div>
            <div className="tools">
                {tools.map((tool, index)=>
                <React.Fragment key={index}>
                    <div className="tool-brand" onClick={() => { updateToolCode(tool.toolCode)}}>{tool.brand}</div>
                </React.Fragment>
                )}
            </div>
            <div className="form">
                <div className="input-container">
                    <input name="rentalDayCount" onChange={e => updateForm(e)} value={request === undefined ? "" :request.rentalDayCount} className="input-field" required />
                    <label className="input-label">Rental Days</label>
                </div>
                <div className="input-container">
                    <input name="discountPercent" onChange={e => updateForm(e)} value={request === undefined ? "" :request.discountPercent} className="input-field" required />
                    <label className="input-label">Applied Discount</label>
                </div>
                <div className="input-container">
                    <input name="checkoutDate" onChange={e => updateForm(e)} value={request === undefined ? "" :request.checkoutDate} className="input-field" required />
                    <label className="input-label">Checkout Date</label>
                </div>
            </div>
            <input onClick={() => actionEntry()} className="submitBtn btn" type="button" value="Submit" />
        </div>
    )
}

export default Home;