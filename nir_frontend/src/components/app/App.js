import React from "react";
import axios from "axios";

// noinspection ES6CheckImport
import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom"

import "./App.css";

import Table from "../table/Table";
import Nav from "../nav/Nav";
import Loading from "../loading/Loading";
import Layers from "../layers/Layers";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: "",
            layers: [],
            layersLoaded: false
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
                if (response.status === 200) {
                    this.setState({
                        data: response.data
                    });
                }
            });
    }

    loadClusters() {
        const levels = [0, 0.5, 1, 1.25, 1.5, 2];

        const start = new Date();

        levels.forEach((level, index) => {
            axios.get("http://localhost:8080/api/customers/clusters?level=" + level)
                .then(response => {
                    console.log(response);
                    if (response.status === 200) {
                        let newLayers = this.state.layers;
                        newLayers[index] = response.data;
                        this.setState({
                            layers: newLayers
                        });
                        if (index === levels.length - 1) {
                            this.setState({
                                layersLoaded: true
                            });

                            const end = new Date();
                            console.log(end - start + " ms");
                        }
                    }
                })
                .catch(error => console.log(error));
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
                            this.state.layersLoaded ?
                                <Layers layers={this.state.layers}/> :
                                <Loading/>
                        }/>

                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
