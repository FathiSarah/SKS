package entities;   

public class NPCs extends Entity {

    protected enum NPC_state {
        IDLE,
        PATROL,
        CHASE,
    }

    protected enum NPC_type {
        COMBATIVE,
        FEARFUL
    }

    protected enum Direction {
        RIGHT,
        LEFT
    }

    protected Player player;

    protected NPC_state currentState;
    protected NPC_type nPC_type;
    protected Direction currentDirection;

    protected String name;
    protected int nextAction = 5000;

    protected long startTime = System.nanoTime();
    protected long elapsedTime = 0;

    public NPCs(int x, int y, String name) {
        super(x, y, 50, 100);
        this.speed = 2;
        this.name = name;
        this.currentState = NPC_state.IDLE;
        this.currentDirection = Direction.RIGHT;
        this.nPC_type = NPC_type.FEARFUL;
    }


    public void update() {
        
    }

    public void detectPlayer() {
        if (Math.abs(this.x - player.getX()) < 100) {
            currentState = NPC_state.CHASE;
        }
    }

    public boolean seesPlayer() {
        if (player.getX() > x && player.getX() < x + width) {

            return true;
        }
        return false;
    }

    public void getNPC_type() {
        this.nPC_type = nPC_type;
    }
}