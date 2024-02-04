package me.fullidle.customrankingpapi.customrankingpapi;

import lombok.Getter;
import org.bukkit.OfflinePlayer;

@Getter
public class PlayerValue implements Comparable<PlayerValue>{
    private final OfflinePlayer player;
    private final Double value;

    public PlayerValue(OfflinePlayer player,Double value){
        this.player = player;
        this.value = value;
    }

    @Override
    public int compareTo(PlayerValue o) {
        return Double.compare(this.value,o.value);
    }
}
