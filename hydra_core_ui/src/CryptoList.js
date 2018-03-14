import React from 'react';

import axios from 'axios';

export default class CryptoList extends React.Component {
  state = {
    cryptosList: []
  }

//   componentDidMount() {
//     axios.get(`https://jsonplaceholder.typicode.com/users`)
//       .then(res => {
//         const persons = res.data;
//         console.log(persons)
//         this.setState(persons);
//     })
//   }

 hello = () => {
    axios.get(`https://jsonplaceholder.typicode.com/users`)
      .then(res => {
        const cryptosList = res.data;
        this.setState({cryptosList});
    })
  }

  render() {
    return (
    <div>
        <button onClick={this.hello}>click me!</button>
        <ul>
            { this.state.cryptosList.map(crypto => <li>{crypto.name}</li>)}
        </ul>

      </div>
    )
  }
}