import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

//usando hooks mais propriamente dito o hook usestate
function App() {
  const [reverse, setReverse] = useState(false);
  const [counter, setCounter] = useState(0);
  const reverseClass = reverse ? 'reverse' : '';

  const handleClick = () => {
    setReverse(!reverse);
    setCounter(counter + 1);
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className={`App-logo ${reverseClass}`} alt="logo" />
        <h1>Contador: {counter}</h1>
        <button onClick={handleClick} type="button">
          Reverse {reverseClass}
        </button>
      </header>
    </div>
  );
}

//sem usar hooks
// class App extends Component {
//   constructor(props) {
//     super(props);

//     this.handleClick = this.handleClick.bind(this);

//     this.state = {
//       reverse: true,
//     };
//   }

//   handleClick() {
//     const { reverse } = this.state;
//     this.setState({ reverse: !reverse });
//   }

//   render() {
//     const { reverse } = this.state;
//     const reverseClass = reverse ? 'reverse' : '';
//     return (
//       <div className="App">
//         <header className="App-header">
//           <img src={logo} className={`App-logo ${reverseClass}`} alt="logo" />
//           <button onClick={this.handleClick} type="button">
//             Reverse {reverseClass}
//           </button>
//         </header>
//       </div>
//     );
//   }
// }

export default App;
