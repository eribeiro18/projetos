import './styles.css'

export const TextInput = (props) => {
    const searchValue = props.searchValue;
    const handleChange = props.handleChange;
    const placeholder = props.placeholder;
    const type = props.type

    return (
        <input 
            className="text-input"
            type={type}
            onChange={handleChange} 
            value={searchValue} 
            placeholder={placeholder}/>
    )
}