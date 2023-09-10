package me.rootdeibis.commonlib.factory.npc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.cryptomorin.xseries.XEntity;
import me.rootdeibis.commonlib.test;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;


public class NPCEntity {


    public static void test(Player player,Location location) {

        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.NAMED_ENTITY_SPAWN);

        packetContainer.getUUIDs().write(0, UUID.randomUUID());
        packetContainer.getDoubles().write(0, location.getX());
        packetContainer.getDoubles().write(1, location.getY());
        packetContainer.getDoubles().write(2, location.getZ());
        packetContainer.getIntegers().write(0, 1);

        test.protocolManager.sendServerPacket(player, packetContainer);


    }





}
