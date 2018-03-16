import React from 'react';
import axios from 'axios';
import {GridList, GridTile} from 'material-ui/GridList';
import IconButton from 'material-ui/IconButton';
import Subheader from 'material-ui/Subheader';
import StarBorder from 'material-ui/svg-icons/toggle/star-border';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

const styles = {
  root: {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
  },
  gridList: {
    display: 'flex',
    flexWrap: 'nowrap',
    overflowX: 'auto',
  },
  titleStyle: {
    color: 'rgb(0, 188, 212)',
  },
};


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
      <div className="AllCryptos">
        <MuiThemeProvider className="Root">
            <GridList className="CryptoList" cols={2.2}>
              <Subheader>Choose your crypto currency to initiate arbitrage</Subheader>
              {this.state.cryptosList.map(crypto => (
                <GridTile className="Crypto" title={crypto.symbol1} subtitle={<span><b>{crypto.symbol2}</b></span>}/>
              ))}
            </GridList>
        </MuiThemeProvider>
      </div>
    )
  }
}