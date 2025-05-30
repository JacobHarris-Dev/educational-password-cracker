import logo from './logo.svg';
import './App.css';

export default function App() {
  const [status, setStatus] = useState('Loading...');

  useEffect(() => {
    fetch('http://localhost:8080/status')
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
  return (
    <p>
      <button>Brute Force</button>
    </p>
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


