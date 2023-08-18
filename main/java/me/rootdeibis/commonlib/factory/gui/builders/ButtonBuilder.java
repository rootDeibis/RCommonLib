package me.rootdeibis.commonlib.factory.gui.builders;

import me.rootdeibis.commonlib.factory.gui.button.GuiButton;
import me.rootdeibis.commonlib.factory.gui.button.GuiButtonData;
import me.rootdeibis.commonlib.factory.gui.context.GUIClickContext;
import org.bukkit.Material;

import java.util.List;
import java.util.function.Consumer;

public class ButtonBuilder extends GuiButton {



    private final GuiButtonData<Material> data = new GuiButtonData<>();
    private Consumer<GUIClickContext> onClick = (e) -> {};

    public ButtonBuilder() {}

    @Override
    public GuiButtonData<Material> getData() {
        return null;
    }


    public ButtonBuilder setMaterial(Material material) {
        this.data.setMaterial(material);
        return this;
    }

    public ButtonBuilder setDisplayName(String displayName) {
        this.data.setDisplayName(displayName);

        return this;
    }

    public ButtonBuilder setLore(List<String> lore) {
        this.data.setLore(lore);

        return this;
    }

    public ButtonBuilder setAmount(int amount) {
        this.data.setAmount(amount);

        return this;
    }

    public ButtonBuilder onClick(Consumer<GUIClickContext> onClick) {
        this.onClick = onClick;

        return this;
    }


    @Override
    public void onClick(GUIClickContext e) {
        onClick.accept(e);
    }


}
