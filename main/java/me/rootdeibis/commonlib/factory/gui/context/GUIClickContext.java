package me.rootdeibis.commonlib.factory.gui.context;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;
import me.rootdeibis.commonlib.factory.gui.holders.GuiContainer;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIClickContext {

    private final GuiContainer container;
    private final GuiButton clickedButton;
    private final InventoryClickEvent event;

    public GUIClickContext(GuiContainer container, GuiButton clickedButton, InventoryClickEvent event) {
        this.container = container;
        this.clickedButton = clickedButton;
        this.event = event;
    }

    public GuiContainer getContainer() {
        return container;
    }

    public GuiButton getClickedButton() {
        return clickedButton;
    }

    public InventoryClickEvent getEvent() {
        return event;
    }
}
