import React from "react";

import "./Clusters.css"

class Clusters extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            clusters: props.clusters
        }

    }

    render() {
        return (
            <div>
                {this.state.clusters.map(cluster =>
                    <div className={"cluster"}>
                        [{cluster.map(item =>
                            <span>{item},&emsp;</span>
                        )}]
                    </div>
                )}
            </div>
        )
    }
}

export default Clusters;