package me.rootdeibis.commonlib.factory.gui.listener;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;
import me.rootdeibis.commonlib.factory.gui.context.GUIClickContext;
import me.rootdeibis.commonlib.factory.gui.holders.GuiContainer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiControllerListener implements Listener {

    @EventHandler
    public void onGuiClickEvent(InventoryClickEvent event) {

        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() != null && event.getClickedInventory().getHolder() instanceof GuiContainer) {

            GuiContainer container = (GuiContainer) event.getClickedInventory().getHolder();
            event.setCancelled(true);

            GuiButton guiButton = container.getButton(event.getSlot());

            if (guiButton != null) {
                GUIClickContext context = new GUIClickContext(container, guiButton, event);

                guiButton.onClick(context);

                container.updateButton(event.getSlot());
            }

        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof GuiContainer) {
            GuiContainer container = (GuiContainer) e.getInventory().getHolder();


            GuiContainer.removeContainer(container);


        }
    }
}
