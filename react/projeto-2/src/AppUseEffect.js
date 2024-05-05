import { useEffect, useState } from 'react';
import './App.css';

const eventFn = () => {};

//usando hooks mais propriamente dito o hook useEffect
function AppUseEffect() {
  const [counter, setCounter] = useState(0);

  //componentDidUpdate - executa toda vez qeu o component atualizar
  useEffect(() => {
    console.log('componentDidUpdate');
  });

  //componentDidMount sem dependencia - executa 1 vezes quando a pagina é montada
  useEffect(() => {
    document.querySelector('h1')?.addEventListener('click', eventFn);

    //componentWillUmount return abaixo serve para desmontar o evento setado acima no h1
    //este deve ser usado sempre pra limpar possiveis lixos que ficam nas variaveis
    return () => {
      document.querySelector('h1')?.removeEventListener('click', eventFn);
    };
  }, []); //Array refere-se as dependencias em branco passará uma unica vez na montagem da pagina

  //componentDidMount com dependencia - executa 1 vezes quando as dependencias são montadas ou alteradas
  useEffect(() => {
    console.log('componentDidMount com dependencia');
  }, [counter]); //Array refere-se as dependencias
  //obs.: para useEffect com dependencia tomar cuidado, não incluir exemplo setCounter(counter + 1)
  //dentro deste mesmo metodo, pois neste caso possivelmente ocorrerá um loop infinito ou recursão,
  //até pode mas deve ser feito com cuidado, de qualquer maneira pode ter um efeito estranho

  return (
    <div className="App">
      <h1>Contador: {counter}</h1>
      <button onClick={() => setCounter(counter + 1)}>+</button>
    </div>
  );
}

export default AppUseEffect;
