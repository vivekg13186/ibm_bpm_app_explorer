package com.bpm;

import com.bpm.ui.ExplorerUI;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

public class Main {

    public static void main(String args[]){
        LafManager.install(new DarculaTheme());
        ExplorerUI explorerUI = new ExplorerUI();
    }
}
