
public class TennisGame1 implements TennisGame {
    private static final int DEUCE_THRESHOLD = 3;
    private static final int OVERTIME_THRESHOLD = 4;

    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)){
            m_score1 += 1;
        } else if(playerName.equals(player2Name)) {
            m_score2 += 1;
        }
    }

    public String getScore() {
        if (isTied())
        {
            return getScoreInTie();
        }
        else if (isOvertime())
        {
            return getScoreInOvertime();
        }
        else
        {
            return getScoreInRegulation();
        }
    }

    private boolean isTied() {
        return m_score1==m_score2;
    }

    private boolean isOvertime() {
        return m_score1>=OVERTIME_THRESHOLD || m_score2>=OVERTIME_THRESHOLD;
    }

    private String getScoreInTie() {
        if(m_score1 >= DEUCE_THRESHOLD) {
            return "Deuce";
        } else {
            return String.format("%s-All", getNameFromPoints(m_score1));
        }
    }

    private String getScoreInOvertime() {
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) return "Advantage player1";
        else if (minusResult == -1) return "Advantage player2";
        else if (minusResult >= 2) return "Win for player1";
        else return "Win for player2";
    }

    private String getScoreInRegulation() {
        return String.format("%s-%s", getNameFromPoints(m_score1), getNameFromPoints(m_score2));
    }

    private String getNameFromPoints(int point) {
        return switch (point) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> "";
        };
    }
}
