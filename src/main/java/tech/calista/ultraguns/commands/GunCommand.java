package tech.calista.ultraguns.commands;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import tech.calista.ultraguns.UltraGuns;
import tech.calista.ultraguns.weapons.Gun;

import java.util.List;

@RequiredArgsConstructor
public class GunCommand implements TabExecutor {
    private final UltraGuns ultraGuns;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Validate.isTrue(sender instanceof Player, "§cYou must be a player to use this command!");

        Player player = (Player) sender;
        for (Gun gun : ultraGuns.getGunRepository().getAll()) {
            player.getInventory().addItem(gun.getGunItem());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
