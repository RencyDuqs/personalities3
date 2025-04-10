import { useEffect, useState } from 'react';
import './App.css';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function Gallery() {
  const [players, setPlayers] = useState([]);
  const [index, setIndex] = useState(0);
  const [showMore, setShowMore] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/duque/personalities') // Changed URL to your /duque endpoint
      .then(res => res.json())
      .then(data => setPlayers(data))
      .catch(err => console.error('Failed to fetch:', err));
  }, []);

  const hasNext = index < players.length - 1;

  function handleNextClick() {
    setIndex((prevIndex) => (hasNext ? prevIndex + 1 : 0));
  }

  function handleBackClick() {
    setIndex((prevIndex) => (prevIndex > 0 ? prevIndex - 1 : players.length - 1));
  }

  function handleMoreClick() {
    setShowMore(!showMore);
  }

  if (players.length === 0) return <div>No Data</div>;

  let player = players[index];

  return (
    <div className="container">
      <Card className="card">
        <CardContent>
          <Typography variant="h4">BADMINTON PLAYERS</Typography> {/* Changed title */}
          <Typography variant="h5">Rency Dayne M. Duque - C-PEITEL3</Typography> {/* Kept your name */}
        </CardContent>

        <CardContent className="buttons">
          <Button variant="contained" onClick={handleBackClick}>BACK</Button>
          <Button variant="contained" onClick={handleNextClick}>NEXT</Button>
        </CardContent>

        <CardMedia component="img" alt={player.name} height="250" image={player.image} /> {/* Changed alt and image source */}

        <CardContent className="details-section">
          <Typography variant="h4">{player.name}</Typography> {/* Changed to player.name */}
          <Typography variant="h6">{index + 1} of {players.length}</Typography>
        </CardContent>

        <CardActions className="details-toggle">
          <Button size="small" onClick={handleMoreClick}>
            {showMore ? '▲' : '▼'}
          </Button>
        </CardActions>

        {showMore && (
          <CardContent className="description-section">
            <Typography variant="body1">{player.description}</Typography>
          </CardContent>
        )}
      </Card>
    </div>
  );
}