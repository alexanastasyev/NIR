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

                <table>
                    <tbody>
                        {this.state.clusters.map((cluster, index) =>
                            <tr>
                                <td className={"cluster-table-item"}>{index}</td>
                                <td className={"cluster-table-item"}>
                                    {cluster.map(item =>
                                        <span>{item},&emsp;</span>
                                    )}
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Clusters;