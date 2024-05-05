import P from 'prop-types';
import React, { useCallback, useState } from 'react';
import './App.css';

//abaixo é um component que deve ser usado no retUrn ao final deste arquivo
//React.memo deve ser usado para impedir que o component seja recriado caso
//o mesmo não tenha sofrido alteração
// eslint-disable-next-line react/prop-types
const Button = React.memo(function Button({ incrementButton }) {
  console.log('Filho renderizou');
  return <button onClick={() => incrementButton(10)}>+</button>;
});

Button.prototype = {
  incrementButton: P.func,
};

//usando hooks mais propriamente dito o hook useCallback
function App() {
  const [counter, setCounter] = useState(0);

  //useCallback se assemelha com o useEffect em questão das dependencias, conforme pode ser visto abaixo no caso o array vazio []
  //para que a junção do React.memo(do Button acima) funcione e o filho não seja renderizado a cada alteração que ocorre no counter
  //ao envés de implementar "counter + num" no useCallback deve ser implementado "(c) => c + num" conforme pode ser visto abaixo
  const incrementButton = useCallback((num) => {
    setCounter((c) => c + num);
  }, []);

  console.log('Pai renderizou');

  return (
    <div className="App">
      <h1>Contador: {counter}</h1>
      <Button incrementButton={incrementButton} />
    </div>
  );
}

export default App;
