import logo from './logo.svg';
import './Home.css';
import { Component } from 'react';

class Home_Example extends Component {
  state = {
    name: 'Evandro Ribeiro',
    counter: 0
  };

  handlePClick = () => {
    this.setState({ name: 'Junior'})
  }

  handleAClick = (event) => {
    event.preventDefault();
    const { counter } = this.state;
    this.setState({ counter: counter + 1})
  }

  render(){
    const { name, counter } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p onClick={this.handlePClick}>
            {name} {counter}
          </p>
          <a
          onClick={this.handleAClick}
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}
export default Home_Example;
