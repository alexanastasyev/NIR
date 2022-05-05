import React from "react";
import axios from "axios";

import "./App.css";

import Table from "../table/Table";
import Clusters from "../clusters/Clusters";

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
        axios.get("http://localhost:8080/api/customers/clusters")
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
                {
                    this.state.data ?
                        <div>
                            <Table data={this.state.data}/>
                        </div>
                        :
                        <div>Loading data...</div>
                }

                {
                    this.state.clusters ?
                        <div>
                            <Clusters clusters={this.state.clusters}/>
                        </div>
                        :
                        <div>Loading clusters...</div>
                }
            </div>

        );
    }

}

export default App;
