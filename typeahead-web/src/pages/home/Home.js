import React, { Component } from "react";
import news_logo from "../../logo.png";
import { Input, AutoComplete, Icon, notification } from "antd";
import { autocomplete, addPhrase } from "../../util/ApiUtil";
import "./Home.css";

const { Search } = Input;
const { Option } = AutoComplete;

function renderOption(item) {
  return (
    <Option key={item} text={item}>
      <Icon type="search" className="autocomplete-icon" />
      {item}
    </Option>
  );
}

class Home extends Component {
  state = {
    value: "",
    autoComplete: []
  };

  handleSearch = value => {
    if (value !== "") {
      this.add(value);
    }
  };

  handleInputChange = value => {
    this.getAutocomplete(value);
  };

  getAutocomplete = prefix => {
    if(prefix == '') {
      this.setState({ value: prefix, autoComplete: [] });
      return;
    }

    autocomplete(prefix)
      .then(res => {
        this.setState({ value: prefix, autoComplete: res });
      })
      .catch(error => {
        console.log("error: " + error);
      });
  };

  add = phrase => {
    addPhrase(phrase)
    .then(res => {
      notification.success({
        message: "Type Ahead",
        description:
          "Phrase (" + phrase + ") added"
      });
    })
    .catch(error => {
      console.log("error: " + error);
    });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Icon type="FileSearchOutlined"/>
          
          <AutoComplete
            value={this.state.value}
            dataSource={this.state.autoComplete.map(renderOption)}
            onChange={this.handleInputChange}
            onSelect={value => this.handleSearch(value)}
            size="large"
            optionLabelProp="text"
            defaultActiveFirstOption={false}
          >
            <Search
              className="search-input"
              placeholder="input search text"
              onSearch={value => this.handleSearch(value)}
              // size="large"
            />
          </AutoComplete>
        </header>
      </div>
    );
  }
}

export default Home;
