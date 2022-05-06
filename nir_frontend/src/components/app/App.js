import React from "react";
import axios from "axios";
import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom"

import "./App.css";

import Table from "../table/Table";
import Clusters from "../clusters/Clusters";
import Nav from "../nav/Nav";
import Loading from "../loading/Loading";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: "",
            clusters: ""
        }

        this.loadData = this.loadData.bind(this);
        this.loadClusters = this.loadClusters.bind(this);
    }

    componentDidMount() {
        this.loadData();
        this.loadClusters();
    }

    loadData() {
        axios.get("http://localhost:8080/api/customers/data")
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    this.setState({
                        data: response.data
                    })
                }
            });
    }

    loadClusters() {
        axios.get("http://localhost:8080/api/customers/clusters?level=1.1")
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    this.setState({
                        clusters: response.data
                    });
                }
            });
    }

    render() {
        return (
            <div className="app">
                <BrowserRouter>
                    <Nav/>
                    <Routes>

                        <Route path="/" element={<Navigate to={"/data"}/>} />

                        <Route path="/data" element={
                            this.state.data ?
                                <Table data={this.state.data}/> :
                                <Loading/>
                        }/>

                        <Route path="/clusters" element={
                            this.state.clusters ?
                                <Clusters clusters={this.state.clusters}/> :
                                <Loading/>
                        }/>

                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
