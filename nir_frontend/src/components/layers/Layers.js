import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Clusters from "../clusters/Clusters";

class Layers extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            layers: this.props.layers,
            active: 0
        }

        this.handleLayerChange = this.handleLayerChange.bind(this);
    }

    handleLayerChange(event) {
        this.setState({
            active: event.target.value
        });
    }

    render() {
        return <div>
            Current layer:&emsp;<select value={this.state.active} onChange={this.handleLayerChange}>
                {this.state.layers.map((layer, index) =>
                    <option key={index} value={index}>{index}</option>
                )}
            </select>
            
            <Clusters
                key={this.state.active}
                clusters={this.state.layers[this.state.active]}/>
        </div>
    }

}

export default Layers;