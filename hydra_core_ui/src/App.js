import React, { Component } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import account from './account.svg'
import './App.css';
import CryptoList from './CryptoList';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <img src={account} className="Account-logo" alt="account-logo" />
          <h1 className="App-title">Hydra</h1>
        </header>

        <CryptoList/>
      </div>
    );
  }
}

export default App;
