import './styles.css'

//quando passar props diferente da foma abaixo deve criar uma variavel exemplo props.post e aplicar o return exemplo no arquivo Posts/index.jsx
//quando fizer igual abaixo a propriedade já estará disponivel e não precisa do return 
export const PostCard = ({post}) =>(
        <div className='post'>
        <img src={post.cover} alt={post.title}/>
        <div className='post-content'>
          <h1>{post.title}</h1>
          <p>{post.body}</p>
        </div>
      </div>
)