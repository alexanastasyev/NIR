import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Clusters from "../clusters/Clusters";
import 'bootstrap/dist/css/bootstrap.min.css';

class Layers extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            layers: this.props.layers,
            active: 0,
            showSmall: true
        }

        this.handleLayerChange = this.handleLayerChange.bind(this);
        this.changeSmallVisibility = this.changeSmallVisibility.bind(this);
    }

    handleLayerChange(event) {
        this.setState({
            active: event.target.value
        });
    }

    changeSmallVisibility() {
        this.setState({
            showSmall: !this.state.showSmall
        })
    }

    render() {
        return <div>
            Current layer:&emsp;<select value={this.state.active} onChange={this.handleLayerChange}>
            {this.state.layers.map((layer, index) =>
                <option key={index} value={index}>{index}</option>
            )}
            </select>
            &emsp;
            <button className="btn btn-sm btn-secondary" onClick={this.changeSmallVisibility}>
                {this.state.showSmall ? "Hide small" : "Show small"}
            </button>

            <br/>
            
            <Clusters
                key={this.state.active + this.state.showSmall}
                clusters={this.state.layers[this.state.active]}
                showSmall={this.state.showSmall}/>
        </div>
    }

}

export default Layers;