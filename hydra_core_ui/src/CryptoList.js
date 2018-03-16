import React from 'react';

import axios from 'axios';

export default class CryptoList extends React.Component {
  state = {
    cryptosList: []
  }

  componentDidMount() {
    axios.get(`https://cex.io/api/currency_limits`)
      .then(res => {
        console.log(res.data.data.pairs);
        const cryptosList = res.data.data.pairs;
        this.setState({cryptosList});
    })
  }

  render() {
    return (
    <div>
        <div className="CryptoListComponent">
            {this.state.cryptosList.map(crypto => <div className="crypto" >{crypto.symbol1} Currency: {crypto.symbol2}</div>)}
        </div>
    </div>
    )
  }
}