package com.dotahelper.json;

public class Hero {

    private static Hero hero;

    private boolean alive;
    private boolean stunned;
    private boolean hexed;
    private boolean arcaneBoots;

    public String arcaneBootsSlot;

    private int mana;
    private int maxMana;
    private int health;
    private int maxHealth;

    private Hero() {};

    public static synchronized Hero getInstance() {
        if (hero == null)
            hero = new Hero();

        return hero;
    }

    public boolean isAlive() {
        return alive;
    }

    void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isStunned() {
        return stunned;
    }

    void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean isHexed() {
        return hexed;
    }

    void setHexed(boolean hexed) {
        this.hexed = hexed;
    }

    public int getMana() {
        return mana;
    }

    void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean hasManaBoots() {
        return arcaneBoots;
    }

    public void setArcaneBoots(boolean arcaneBoots) {
        this.arcaneBoots = arcaneBoots;
    }

    public String getArcaneBootsSlot() {
        return arcaneBootsSlot;
    }

    public void setArcaneBootsSlot(String arcaneBootsSlot) {
        this.arcaneBootsSlot = arcaneBootsSlot;
    }
}
