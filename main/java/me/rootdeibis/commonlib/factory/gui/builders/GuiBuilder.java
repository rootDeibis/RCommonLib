package me.rootdeibis.commonlib.factory.gui.builders;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;

import java.util.HashMap;

public class GuiBuilder {

    private String title = "InventoryGui Title";

    private int rows = 2;

    private final HashMap<Integer, GuiButton> buttons = new HashMap<>();

    public GuiBuilder() {
    }


    public GuiBuilder setTitle(String title) {
        this.title = title;

        return this;
    }

    public GuiBuilder setRows(int rows) {
        this.rows = rows;

        return this;
    }

    public GuiBuilder addButton(int slot, GuiButton btn) {

        buttons.put(slot, btn);

        return this;
    }

    public String getTitle() {
        return title;
    }

    public int getRows() {
        return rows;
    }


    public HashMap<Integer, GuiButton> getButtons() {
        return buttons;
    }

    public GUIBuildContainer build() {
        return new GUIBuildContainer(this);
    }


}
