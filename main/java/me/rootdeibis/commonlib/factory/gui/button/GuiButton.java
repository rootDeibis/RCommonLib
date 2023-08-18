package me.rootdeibis.commonlib.factory.gui.button;

import me.rootdeibis.commonlib.factory.gui.context.GUIClickContext;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public abstract class GuiButton {

    public GuiButton() {}


    public abstract GuiButtonData<?> getData();



    public ItemStack getItem() {

        GuiButtonData<?> data = new GuiButtonData<>();

        ItemStack itemStack = new ItemStack(Material.BEDROCK);

       if (data.getMaterial() != null) {
           if (data.getMaterial() instanceof Material) {
               itemStack = new ItemStack((Material) getData().getMaterial());
           } else if (data.getMaterial() instanceof ItemStack){
               itemStack = (ItemStack) data.getMaterial();
           } else {
               throw new RuntimeException("Invalid getItem Material Type");
           }

       }

        ItemMeta meta = itemStack.getItemMeta();


        if (meta instanceof SkullMeta) {

        }

        int amount = data.getAmount();

        if(itemStack.getAmount() != amount)
            itemStack.setAmount(amount);

        String displayName = data.getDisplayName();


        if (displayName != null)
            meta.setDisplayName(displayName);


        List<String> lore = data.getLore();

        if (lore != null)
            meta.setLore(lore);


        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public abstract void onClick(GUIClickContext e);

}
