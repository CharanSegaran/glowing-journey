
import { useState } from 'react'
import { CHARACTERS as characters } from './constants/characters';
import './App.css'

function App() {

  const [plainText, setPlainText] = useState("");
  const [offsetCharacter, setOffsetCharacter] = useState("A");
  const [encodedText, setEncodedText] = useState("");
  const [encodedMessage, setEncodedMessage] = useState("");
  const [decodedMessage, setDecodedMessage] = useState("");

  const encodeText = async () => {
    const encode = await fetch('http://localhost:8080/encode', 
      {
        method:"POST",
        headers: {
          "Content-Type":"application/json"
        },
        body: JSON.stringify({offsetCharacter, plainText})
      }
    )
    // console.log(offsetCharacter, plainText);
    const response = await encode.json();
    // console.log(response.result);
    setEncodedMessage(response.result);
  }
  
  const decodeText = async () => {
    const decode = await fetch('http://localhost:8080/decode',
      {
        method: 'POST',
        headers: {
          "Content-Type":"application/json"
        },
        body: JSON.stringify({encodedText})
      }
    )
    const response = await decode.json();
    setDecodedMessage(response.result)
  }


  return (
    <>
     <h1>Encoder-Decoder App</h1>
     <div className='encoder'>
      <h2>Encoder</h2>
      <textarea 
        name="plainText" 
        id="1" 
        placeholder='input your plain text here'
        value={plainText}
        onChange={(e) => {setPlainText(e.target.value)}}
      >
      </textarea>
      <p>Choose your offset:</p>
      <select 
        value={offsetCharacter}
        onChange={(e) => {setOffsetCharacter(e.target.value)}}
      >
        {
          characters.map((char,index) => (
            <option value={char} key={index}>
              {char}
            </option>
          ))
        }
      </select>
      <button onClick={encodeText}>ENCODE</button>
      Encoded message: {encodedMessage}
     </div>

     <div className='decoder'>
      <h2>Decoder</h2>
      <textarea 
        name="encodedText" 
        id="2" 
        placeholder='input your encoded text here'
        value={encodedText}
        onChange={(e) => {setEncodedText(e.target.value)}}
      >
      </textarea>
      
      <button onClick={decodeText}>DECODE</button>
      Decoded message: {decodedMessage}
     </div>
    </>
  )
}

export default App
