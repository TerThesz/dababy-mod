package terthesz.dababy.mod.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class CustomMusicDisc extends MusicDiscItem {
  public CustomMusicDisc(int comparatorOutput, SoundEvent sound, Settings settings) {
      super(comparatorOutput, sound, settings);
  }
}