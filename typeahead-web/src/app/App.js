import React, { Component } from "react";
import { Route, withRouter, Switch } from "react-router-dom";
import Home from "../pages/home/Home";

class App extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/" render={props => <Home {...props} />} />
      </Switch>
    );
  }
}

export default withRouter(App);
