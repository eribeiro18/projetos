import './styles.css'

import { PostCard } from "../PostCard"

export const Posts = (props) =>{
    const posts = props.posts;
    return (
        <div className="posts">
            {posts.map(post => (
            <PostCard key={post.id} post={post}/>
            ))}
        </div>
  )
}