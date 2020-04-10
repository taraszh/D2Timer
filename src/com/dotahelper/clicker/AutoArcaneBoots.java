package com.dotahelper.clicker;

import com.dotahelper.json.Hero;
import com.dotahelper.json.Map;
import com.dotahelper.settings.General;
import com.dotahelper.utils.Utils;

import java.awt.*;

/**
 * works bad, so disabled
 * todo make it in separated thread
 */
public class AutoArcaneBoots {

    private Robot robot;

    public AutoArcaneBoots() throws AWTException {
        robot = new Robot();
    }

    public void click() {
        int arcaneBootsKey = General.getAutoArcaneBootsKey();

        if (General.getAutoManaSettings() && arcaneBootsKey != 0 && !Map.getInstance().isGamePaused()) {
            Hero hero = Hero.getInstance();
            boolean restoreMana = hero.getMaxMana() - hero.getMana() > 100;

            if (Utils.getForegroundWindowName().equals("dota 2")
                    && hero.isAlive()
                    && !hero.isHexed()
                    && !hero.isStunned()
                    && hero.hasManaBoots()
                    && restoreMana
            ) {
                robot.keyPress(arcaneBootsKey);
                robot.keyRelease(arcaneBootsKey);

                hero.setArcaneBoots(false);
            }
        }
    }

}

