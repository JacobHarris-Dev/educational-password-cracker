import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect, useRef } from 'react';

export default function App() {
  const [status, setStatus] = useState('Loading...');

  useEffect(() => {
  fetch('http://localhost:8080/api/status')
    .then(res => res.text())
    .then(data => setStatus(data))
    .catch(err => {
      console.error(err);
      setStatus('Error fetching status');
    });
}, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>There are a variety of different methods to crack a password, experiment
          with different attack types and hash combinations to learn more about each cracking
          technique!
        </h1>
        <h3>
          Backend status: 
        </h3>
         <p>{status}</p>
      </header>

      <div className="button-grid">
        <BruteForceButton />
        <DictionaryButton />
        <BfDictionaryButton />
        <RainbowTableButton />
        <PhishingButton />
      </div>


    </div>
  );
}

function BruteForceButton() {
   const [logs, setLogs] = useState([]);
  const [isRunning, setIsRunning] = useState(false);
  const [hash, setHash] = useState('');
  const eventSourceRef = useRef(null); //  keeps EventSource persistent

  const startBruteForce = () => {
    if (!hash) {
      alert("Please enter a hash to crack.");
      return;
    }

    if (eventSourceRef.current) {
      console.warn("Brute force already running.");
      return; // don't start again if already running
    }

    setLogs([]);
    setIsRunning(true);

    const length = 8;
    const hashType = 1;

    const source = new EventSource(`http://localhost:8080/api/brute-force?hash=${hash}&length=${length}&hashType=${hashType}`);
    eventSourceRef.current = source;

    source.onopen = () => {
      console.log("✅ SSE connection opened.");
    };

    source.onmessage = (event) => {
      setLogs(prev => [...prev, event.data]);
    };

    source.onerror = (err) => {
      console.error("SSE error:", err);
      stopAttack(); // stop if error
    };
  };

  const stopAttack = () => {
    if (eventSourceRef.current) {
      eventSourceRef.current.close();
      eventSourceRef.current = null;
    }
    setIsRunning(false);
    setLogs([]);
    setHash('');
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Enter hash here"
        value={hash}
        onChange={(e) => setHash(e.target.value)}
        disabled={isRunning}
        style={{ marginBottom: "1em", padding: "0.5em", width: "100%" }}
      />

      <div style={{ display: "flex", gap: "1em", marginBottom: "1em" }}>
        <button onClick={startBruteForce} disabled={isRunning}>
          {isRunning ? "Running..." : "Start Brute Force"}
        </button>

        <button onClick={stopAttack} disabled={!isRunning}>
          Stop
        </button>
      </div>

      <div
        className="log-box"
        style={{
          textAlign: "left",
          marginTop: "1em",
          maxHeight: "200px",
          overflowY: "auto",
          border: "1px solid gray",
          padding: "1em",
          backgroundColor: "#f9f9f9"
        }}
      >
        {logs.map((log, i) => (
          <div key={i}>{log}</div>
        ))}
      </div>
    </div>
  );
}


function DictionaryButton() {
  return (
    <p>
      <button>Dictionary</button>
    </p>
  );
}

function BfDictionaryButton() {
  return (
    <p>
      <button>Brute Force + Dictionary</button>
    </p>
  );
}

function RainbowTableButton() {
  return (
    <p>
      <button>Rainbow Table</button>
    </p>
  );
}

function PhishingButton() {
  return (
    <p>
      <button>Phishing Simulation</button>
    </p>
  );
}


