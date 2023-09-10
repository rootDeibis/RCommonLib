package me.rootdeibis.commonlib.factory.gui.builders;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;
import me.rootdeibis.commonlib.factory.gui.holders.GuiContainer;

import java.util.HashMap;

public class GUIBuildContainer extends GuiContainer {

    private final GuiBuilder builder;

    public GUIBuildContainer(GuiBuilder builder) {
        this.builder = builder;
    }


    @Override
    public HashMap<Integer, GuiButton> getButtons() {
        return builder.getButtons();
    }

    @Override
    public String getTitle() {
        return builder.getTitle();
    }

    @Override
    public int getSize() {
        return builder.getRows();
    }
}
