import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";

import "./Nav.css";

class Nav extends React.Component {

    render() {
        return(
            <div className={"navigation"}>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="nav-link" to="/data">Data</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/clusters">Clusters</Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        );
    }

}

export default Nav;