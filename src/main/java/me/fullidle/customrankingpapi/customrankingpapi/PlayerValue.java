package me.fullidle.customrankingpapi.customrankingpapi;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlayerValue implements Comparable<PlayerValue>{
    private final OfflinePlayer player;
    private final Integer value;

    public PlayerValue(OfflinePlayer player,Integer value){
        this.player = player;
        this.value = value;
    }

    @Override
    public int compareTo(PlayerValue o) {
        return Integer.compare(this.value,o.value);
    }
}
