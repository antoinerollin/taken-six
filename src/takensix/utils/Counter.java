package takensix.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import takensix.player.Player;

public abstract class Counter {

	public final static String GOD_LABEL = "THE GOD";
	public final static String SCORER_LABEL = "THE SCORER";
	public final static String IMMORTAL_LABEL = "THE IMMORTAL";
	public final static String PUNCHER_LABEL = "THE PUNCHER";
	public final static String FLASH_LABEL = "THE FLASH";

	public static Map<String, List<Player>> getScoreRewards(List<Player> players) {
		Map<String, List<Player>> statistics = new TreeMap<>();

		List<Player> bestVictoryPlayers = null, bestBestScorePlayers = null, bestSurvivePlayers = null,
				bestFatalityPlayers = null, bestPlayTimePlayers = null;

		int bestVictoryNumber = -1, bestBestScoreNumber = -1, bestSurviveNumber = -1, bestFatalityNumber = -1;
		long bestPlayTimeNumber = Long.MAX_VALUE;

		for (Player p : players) {
			final int numberOfVictory = p.getNumberOfVictory();
			final int numberOfBestScore = p.getNumberOfBestScore();
			final int numberOfSurvive = p.getNumberOfSurvive();
			final int numberOfFatality = p.getNumberOfFatality();
			final long averagePlayTime = p.getAveragePlayTime();

			if (numberOfVictory > bestVictoryNumber) {
				bestVictoryNumber = numberOfVictory;
				bestVictoryPlayers = new ArrayList<>();
			}

			if (numberOfBestScore > bestBestScoreNumber) {
				bestBestScoreNumber = numberOfBestScore;
				bestBestScorePlayers = new ArrayList<>();
			}

			if (numberOfSurvive > bestSurviveNumber) {
				bestSurviveNumber = numberOfSurvive;
				bestSurvivePlayers = new ArrayList<>();
			}

			if (numberOfFatality > bestFatalityNumber) {
				bestFatalityNumber = numberOfFatality;
				bestFatalityPlayers = new ArrayList<>();
			}

			if (averagePlayTime < bestPlayTimeNumber) {
				bestPlayTimeNumber = averagePlayTime;
				bestPlayTimePlayers = new ArrayList<>();
			}

			if (numberOfVictory == bestVictoryNumber)
				bestVictoryPlayers.add(p);
			if (numberOfBestScore == bestBestScoreNumber)
				bestBestScorePlayers.add(p);
			if (numberOfSurvive == bestSurviveNumber)
				bestSurvivePlayers.add(p);
			if (numberOfFatality == bestFatalityNumber)
				bestFatalityPlayers.add(p);
			if (averagePlayTime == bestPlayTimeNumber)
				bestPlayTimePlayers.add(p);
		}

		buildStatistics(statistics, bestVictoryPlayers, bestBestScorePlayers, bestSurvivePlayers, bestFatalityPlayers,
				bestPlayTimePlayers);

		return statistics;
	}

	private static void buildStatistics(Map<String, List<Player>> statistics, List<Player> bestVictoryPlayers,
			List<Player> bestBestScorePlayers, List<Player> bestSurvivePlayers, List<Player> bestFatalityPlayers,
			List<Player> bestPlayTimePlayers) {
		statistics.put(GOD_LABEL, bestVictoryPlayers);
		statistics.put(SCORER_LABEL, bestBestScorePlayers);
		statistics.put(IMMORTAL_LABEL, bestSurvivePlayers);
		statistics.put(PUNCHER_LABEL, bestFatalityPlayers);
		statistics.put(FLASH_LABEL, bestPlayTimePlayers);
	}
}
