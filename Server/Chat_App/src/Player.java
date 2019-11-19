public class Player {

    private String id;

    public Player(String id) {
        if (id.equals("X") || id.equals("O")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getXO() {
        return id;
    }

    public boolean equals(Player player) {
        if (this.id.equals(player.getXO())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return id;
    }
}
