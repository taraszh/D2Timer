package com.ui;

import com.dotahelper.settings.General;
import com.sun.deploy.util.WinRegistry;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CreateGSI {

    private String exePathFromDotaRoot = "\\game\\bin\\win64\\dota2.exe";
    private String exePathFromSteamRoot = "\\steamapps\\common\\dota 2 beta\\game\\bin\\win64\\dota2.exe";
    private String gsiPathFromDotaRoot = "\\game\\dota\\cfg\\gamestate_integration";
    private String gsiPathFromSteamRoot = "\\steamapps\\common\\dota 2 beta\\game\\dota\\cfg\\gamestate_integration";

    /**
     * Add game state integration file to dota config
     */
    public CreateGSI() {
        if (!create()) {
            JFrame frame = new JFrame("dota2 helper");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(false);

            String path = JOptionPane.showInputDialog(frame, "Cant find game folder. Enter path to the game root folder \n" + "(Example: 'S:\\games\\SteamLibrary\\steamapps\\common\\dota 2 beta')", null);

            if (path == null) System.exit(0);

            General.setDotaRootDir(path);

            new CreateGSI();
        }
    }

    private boolean create() {
        try {
            String userConfigDotaRoot = General.getDotaRootDir();
            String gsiDirPath;

            if (userConfigDotaRoot.equals("")) {
                String steamPath = WinRegistry.getString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Valve\\Steam", "SteamPath");
                if (!new File(steamPath + exePathFromSteamRoot).exists()) return false;

                gsiDirPath = steamPath + gsiPathFromSteamRoot;
            } else {
                if (!new File(userConfigDotaRoot + exePathFromDotaRoot).exists()) return false;

                gsiDirPath = userConfigDotaRoot + gsiPathFromDotaRoot;
            }

            WriteToFile(gsiDirPath);
        } catch (IOException e) {
            System.out.println("Integration file cannot be created, fix it by your self");
        } catch (Exception e) {
            System.out.println("you broke everything");
        }

        return true;
    }

    private void WriteToFile(String gsiDirPath) throws IOException {
        if (new File(gsiDirPath + "\\gamestate_integration_testGSI.cfg").exists()) return;

        File dir = new File(gsiDirPath);
        dir.mkdir();

        File file = new File(gsiDirPath + "\\gamestate_integration_testGSI.cfg");
        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        String contentOfGSIFile = "\"Dota 2 Integration Configuration\"\n" + "{\n" + "    \"uri\"           \"http://localhost:4000\"\n" + "    \"timeout\"       \"0.5\"\n" + "    \"buffer\"        \"0.1\"\n" + "    \"throttle\"      \"0.1\"\n" + "    \"heartbeat\"     \"30.0\"\n" + "    \"data\"\n" + "    {\n" + "        \"map\"           \"1\"\n" + "        \"hero\"          \"1\"\n" + "        \"items\"         \"1\"\n" + "    }\n" + "}\n";

        writer.write(contentOfGSIFile);
        writer.close();
    }

}
