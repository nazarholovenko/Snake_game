public class Apple {
    public int positionY;
    public int positionX;
    public Apple(int x, int y) {
        positionX = x;
        positionY = y;
    }
    public void setRandomPosition() {
        positionX = Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1));
        positionY = Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1));
    }
}
