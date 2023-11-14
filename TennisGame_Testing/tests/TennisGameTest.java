import static org.junit.Assert.*;
import org.junit.Test;

public class TennisGameTest {

	// Here is the format of the scores: "player1Score - player2Score"
	// "love - love"
	// "15 - 15"
	// "30 - 30"
	// "deuce"
	// "15 - love", "love - 15"
	// "30 - love", "love - 30"
	// "40 - love", "love - 40"
	// "30 - 15", "15 - 30"
	// "40 - 15", "15 - 40"
	// "player1 has advantage"
	// "player2 has advantage"
	// "player1 wins"
	// "player2 wins"

	@Test
	public void testTennisGame_Start() {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);
	}

	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);
	}

	@Test(expected = TennisGameException.class)
	public void testTennisGame_getScore_ResultsExpection() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Assert
		assertEquals("love - love", game.getScore());

		game.player1Scored();
		assertEquals("love - 15", game.getScore());

		game.player2Scored();
		assertEquals("15 - 15", game.getScore());

		game.player1Scored();
		game.player1Scored();
		assertEquals("15 - 40", game.getScore());

		game.player2Scored();
		assertEquals("30 - 40", game.getScore());
	}
	///////////////////////////////////////////
	// PLAYER 1
	///////////////////////////////////////////

	// WON
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 4; i++) {
			game.player1Scored();
		}
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("player1 wins", score);
	}

	// Advantage
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player1HasAdvantage_ResultsExpection() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 3; i++) {
			game.player1Scored();
			game.player2Scored();
		}
		// Act & Assert
		game.player1Scored();
		String score = game.getScore();
		assertEquals("player1 has advantage", score);
	}

	// GAME ENDED
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player1ScoredAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 4; i++) {
			game.player1Scored();
		}
		// Act & Assert
<<<<<<< Updated upstream
		assertThrows(TennisGameException.class,() -> game.player1Scored());
=======
		try {
			game.player1Scored();
			fail("TennisGameException was not throws Exception");
		} catch (TennisGameException e) {
			throw new TennisGameException();
		}
>>>>>>> Stashed changes
	}
	///////////////////////////////////////////
	// PLAYER 2
	///////////////////////////////////////////

	// WON
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsExpection() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 4; i++) {
			game.player2Scored();
		}
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("player2 wins", score);
	}

	// Advantage
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player2HasAdvantage_ResultsExpection() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 3; i++) {
			game.player2Scored();
			game.player1Scored();
		}
		// Act & Assert
		game.player2Scored();
		String score = game.getScore();
		assertEquals("player2 has advantage", score);
	}

	// GAME ENDED
	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player2ScoredAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		for (int i = 0; i < 4; i++) {
			game.player2Scored();
		}
		// Act & Assert
<<<<<<< Updated upstream
		assertThrows(TennisGameException.class,() -> game.player2Scored());
=======
		try {
			game.player2Scored();
			fail("TennisGameException was not throws Exception");
		} catch (TennisGameException e) {
			throw new TennisGameException();
		}
>>>>>>> Stashed changes
	}

}
