import { Component } from 'react';

import './styles.css';


import { loadPosts } from '../../utils/load-posts'
import { Posts } from '../../components/Posts';
import { Button } from '../../components/Button';
import { TextInput } from '../../components/TextInput';

class Home extends Component {
  state = {
    counter: 0, 
    posts: [],
    allPosts: [],
    page: 0,
    postsPerPage: 2,
    searchValue: '',
  };

  componentDidMount(){
    this.loadPosts()
  }

  loadPosts = async () => {
    const{page, postsPerPage} = this.state;

    const postsAndPhotos = await loadPosts();
    this.setState( { 
      posts: postsAndPhotos.slice(page,postsPerPage),
      allPosts: postsAndPhotos,
    } )
  }

  loadMorePosts = () => {
    const {
      page,
      postsPerPage,
      allPosts,
      posts
    } = this.state
    const nextPage = page + postsPerPage;
    const nextPost = allPosts.slice(nextPage, nextPage + postsPerPage);
    posts.push(...nextPost);
    this.setState({ posts, page: nextPage});
  }

  handleChange = (e) =>{
    const {value} = e.target
    this.setState({searchValue: value})
  }

  //tudo que é criado deve ser recuperado do estado exemplo 1º linha após o render, e tambem deve ser atualizado caso os valores mudem exemplo handleChange sengunda linha
  render(){
    const { posts, page, postsPerPage, allPosts, searchValue } = this.state;
    const noMorePosts = page + postsPerPage >= allPosts.length;

    const filteredPosts = !!searchValue ? 
      allPosts.filter(post => {
        return post.title.toLowerCase().includes(searchValue.toLowerCase());
      }) : 
      posts;
    
    return (
      <section className="container">
        <div className="search-container">
          {!!searchValue && (
            <h1>Search value: {searchValue} </h1>
          )}

          <TextInput 
            placeholder="Type your search"
            type="search"
            searchValue={searchValue}
            handleChange={this.handleChange} />
        </div>

        {filteredPosts.length > 0 && (
          <Posts posts={filteredPosts} />
        )}

        {filteredPosts.length === 0 && (
          <p>Não existe posts para a consulta =(</p>
        )}
       <div className="button-container">
          {!searchValue && (
            <Button 
              text="Load more posts" 
              onClick={this.loadMorePosts} 
              disabled={noMorePosts}/>
          )}
        </div>
      </section>
    );
  }
}
export default Home;
