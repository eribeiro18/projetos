import './Home.css';
import { Component } from 'react';

class Home_Examples2 extends Component {
  state = {
    counter: 0, 
    posts: [
      {
        id: 1,
        title: 'O Titulo 1',
        body: 'O Corpo 1'
      },
      {
        id: 2,
        title: 'O Titulo 2',
        body: 'O Corpo 2'
      },
      {
        id: 3,
        title: 'O Titulo 3',
        body: 'O Corpo 3'
      }
    ]
  };
  timeoutUpdate = null;

  //É executado toda vez depois que os objetos são montados
  componentDidMount(){
    this.handleTimeout();
  }

  //É executado toda vez que os objetos são atualizados
  //no caso abaixo gerou um loop infinito e o counter continua sendo atualizado 
  componentDidUpdate(){
    clearTimeout(this.timeoutUpdate);
    this.handleTimeout();
  }

  //É executado tada vez que os objetos forem desmontados
  componentWillUnmount(){
    clearTimeout(this.timeoutUpdate);
  }

  handleTimeout = () => {
    const { posts, counter } = this.state;
    posts[0].title = 'O titulo mudou'
    this.timeoutUpdate = setTimeout(() => {
      this.setState({ posts, counter: counter+1 })
    }, 1000);
  }

  render(){
    const { posts, counter } = this.state;
    return (
      <div className="App">
        <p>{counter}</p>
        {posts.map(post => (
          <div key={post.id}>
            <h1>{post.title}</h1>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}
export default Home_Examples2;
