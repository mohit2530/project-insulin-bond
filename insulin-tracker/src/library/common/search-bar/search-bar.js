import React, {useState} from "react";
import './search-bar.scss'

const SearchBar = () => {
    const [search, setSearch] = useState({});
    const handleChange = (event) => {
      setSearch({[event.target.name]: event.target.value});
    };
    const handleSubmit = (event) => {
      console.log(event.target.value);
    };
  return (
    <div className="search-bar">
        <form className="search-bar__container" onSubmit={handleSubmit}>
            <input
                className="search-bar__field"
                type="text"
                name="search"
                placeholder="Search"/>
            <button className="search-bar__button" type="submit"><span className="search-bar__submit"></span></button>
        </form>
    </div>
  );
};

export default SearchBar;
