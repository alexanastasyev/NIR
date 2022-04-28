import React from "react";
import axios from "axios";
import Table from "../table/Table";

import "./App.css";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: ""
        }

    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/data")
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    this.setState({
                        data: response.data
                    })
                }
            });
    }

    render() {
        return (
            <div className="app">
                {
                    this.state.data ?
                        <div>
                            <Table data={this.state.data}/>
                        </div>
                        :
                        <div>Loading...</div>
                }
            </div>

        );
    }

}

export default App;
