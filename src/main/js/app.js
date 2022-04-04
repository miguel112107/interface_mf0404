import React from 'react';
import ReactDom from 'react-dom'
import Home from './components/Home'

export default function App(){
    return(
        <Home />
    )
}

ReactDom.render(<App />, document.getElementById("react"));