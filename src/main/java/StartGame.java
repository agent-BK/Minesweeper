import game.InitGame;

public class StartGame {

    private static final int TIME = 5000;

    public static void main(String[] args) {
        new SplashScreen(TIME).showSplashAndExit();
        new InitGame();
    }
}
