package com.dotahelper.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Mapper {

    public static boolean isMatchStarted = false;

    public Mapper(String json) {
        try {
            isMatchStarted = false;
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("hero"))
                setHeroState(jsonObject.getJSONObject("hero"));
            if (jsonObject.has("map"))
               setMapState(jsonObject.getJSONObject("map"));
//            if (jsonObject.has("items"))
//                setItemsState(jsonObject.getJSONObject("items"));

        } catch (JSONException e) {
            isMatchStarted = false;
            System.out.println(e.getMessage());
        }
    }

    private void setHeroState(JSONObject heroInfo) {
        isMatchStarted = true;
        Hero hero = Hero.getInstance();

        hero.setAlive(heroInfo.getBoolean("alive"));
        hero.setHexed(heroInfo.getBoolean("hexed"));
        hero.setStunned(heroInfo.getBoolean("stunned"));

        hero.setHealth(heroInfo.getInt("health"));
        hero.setMaxHealth(heroInfo.getInt("max_health"));
        hero.setMana(heroInfo.getInt("mana"));
        hero.setMaxMana(heroInfo.getInt("max_mana"));
    }

    private void setMapState(JSONObject mapInfo) {
        isMatchStarted = true;
        Map map = Map.getInstance();

        map.setClockTime(mapInfo.getInt("clock_time"));
        map.setWardPurchaseCooldown(mapInfo.getInt("ward_purchase_cooldown"));

        map.setDaytime(mapInfo.getBoolean("daytime"));
        map.setNightStalkerNight(mapInfo.getBoolean("nightstalker_night"));
        map.setGamePaused(mapInfo.getBoolean("paused"));
    }

    private void setItemsState(JSONObject itemsInfo) {
        isMatchStarted = true;
        Hero hero = Hero.getInstance();

        if (hero.getArcaneBootsSlot() == null) {
            Iterator<String> keys = itemsInfo.keys();
            while(keys.hasNext()) {
                String key = keys.next();
                JSONObject item = itemsInfo.getJSONObject(key);

                if (item.get("name").equals("item_arcane_boots") && item.get("can_cast").equals(true)) {
                    hero.setArcaneBoots(true);
                    hero.setArcaneBootsSlot(key);
                    break;
                }
            }
        } else if (itemsInfo.getJSONObject(hero.getArcaneBootsSlot()).get("name").equals("item_arcane_boots")) {
            hero.setArcaneBoots(itemsInfo.getJSONObject(hero.getArcaneBootsSlot()).get("can_cast").equals(true));
        }
    }

}
