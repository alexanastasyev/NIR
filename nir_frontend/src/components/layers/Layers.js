import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Clusters from "../clusters/Clusters";
import 'bootstrap/dist/css/bootstrap.min.css';

class Layers extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            layers: this.props.layers,
            strategy: this.props.strategy,
            active: 0,
            showSmall: true
        }

        this.handleLayerChange = this.handleLayerChange.bind(this);
        this.changeSmallVisibility = this.changeSmallVisibility.bind(this);
        this.handleStrategyChanged = this.handleStrategyChanged.bind(this);
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

    handleStrategyChanged(event) {
        this.setState({
            strategy: event.target.value
        });
        this.props.onStrategyChanged(event.target.value)
    }

    render() {
        return <div className="container">
            <div className="row">
                <div className="col-sm">
                    Current layer:&emsp;<select className='form-select' value={this.state.active} onChange={this.handleLayerChange}>
                        {this.state.layers.map((layer, index) =>
                            <option key={index} value={index}>{index}</option>
                        )}
                    </select>
                </div>
                <div className="col-sm">
                    Strategy:&emsp;<select className='form-select' value={this.state.strategy} onChange={this.handleStrategyChanged}>
                        <option value="average">Average</option>
                        <option value="skip">Skip</option>
                    </select>
                </div>
                <div className="col-sm">
                    Extra:<br/>
                    <button className="btn btn-secondary" onClick={this.changeSmallVisibility}>
                        {this.state.showSmall ? "Hide small" : "Show small"}
                    </button>
                </div>

                <br/><br/><br/><br/><br/>

                <div>
                    <Clusters
                        key={this.state.active + this.state.showSmall}
                        clusters={this.state.layers[this.state.active]}
                        showSmall={this.state.showSmall}/>
                </div>

            </div>
        </div>
    }

}

export default Layers;