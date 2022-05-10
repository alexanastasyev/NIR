import React from "react";
import Highcharts from 'highcharts'
import HighchartsReact from 'highcharts-react-official'

import "./Clusters.css"

class Clusters extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            clusters: props.clusters
        }

    }

    render() {
        const options = {
            chart: {
              type: 'column'
            },
            title: {
                text: 'Clusters size'
            },
            series: [{
                name: 'Cluster size',
                data: this.state.clusters.map(cluster => cluster.length),
                dataLabels: {
                    enabled: true
                },
                showInLegend: false
            }]
        }

        return (
            <div>

                <HighchartsReact
                    highcharts={Highcharts}
                    options={options}
                />
                &emsp;
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