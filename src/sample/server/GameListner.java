package sample.server;

public interface GameListner {
    public void onMoved(String player, int pos);
    public void onPlayerWon(int p);

    public void setChar(String type);

    public void onReset();
}
