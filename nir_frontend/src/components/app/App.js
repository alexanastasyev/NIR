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
            layersLoaded: false,
            strategy: "average"
        }

        this.loadData = this.loadData.bind(this);
        this.loadClusters = this.loadClusters.bind(this);
        this.handleStrategyChanged = this.handleStrategyChanged.bind(this);
    }

    componentDidMount() {
        this.loadData();
        this.loadClusters();
    }

    loadData() {
        axios.get("http://localhost:8080/api/customers/data?strategy=" + this.state.strategy)
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        data: response.data
                    });
                }
            });
    }

    loadClusters() {
        const levels = [0, 0.2, 0.3, 0.5, 1, 2];

        levels.forEach((level, index) => {
            axios.get("http://localhost:8080/api/customers/clusters?level=" + level + "&strategy=" + this.state.strategy)
                .then(response => {
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
                        }
                    }
                })
                .catch(error => console.log(error));
        });

    }

    handleStrategyChanged(strategy) {
        this.setState({
            strategy: strategy,
            layersLoaded: false
        }, () => {
            this.loadData();
            this.loadClusters();
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
                                <Layers layers={this.state.layers} strategy={this.state.strategy} onStrategyChanged={this.handleStrategyChanged}/> :
                                <Loading/>
                        }/>

                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
